package cn.edu.xmu.comm.commons.aop;

import cn.edu.xmu.comm.commons.exception.NoPermissionException;
import cn.edu.xmu.comm.commons.exception.NoUserInSessionException;
import cn.edu.xmu.comm.entity.User;
import com.opensymphony.xwork2.ActionContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 验证是否登陆
 *
 * @author Mengmeng Yang
 * @version 12/28/2014 0028
 */
@Aspect
@Component
public class Authorization {

    private static Logger logger = LoggerFactory.getLogger(Logging.class);

    @Pointcut("execution(* cn.edu.xmu.comm.action.*.*(..)) " +
            "&& !execution(* cn.edu.xmu.comm.action.*.get*(..)) " +
            "&& !execution(* cn.edu.xmu.comm.action.*.set*(..)) " +
            "&& !execution(* cn.edu.xmu.comm.action.*LoginAction.*(..)) ")
    private void anyActionExceptLogin() {
    }

    @Pointcut("execution(* cn.edu.xmu.comm.service.*.*(..)) " +
            "&& !execution(* cn.edu.xmu.comm.service.impl.SystemServiceImpl.*login*(..))" +
            "&& !execution(* cn.edu.xmu.comm.service.impl.SystemServiceImpl.*Login(..))")
    private void anyServiceExceptLogin() {
    }

    @Around("anyActionExceptLogin()")
    public Object processAction(ProceedingJoinPoint jp) throws java.lang.Throwable {
        User user = (User) ActionContext.getContext().getSession().get("USER");
        if (user == null) {
            logger.info(jp.getSignature().getName() + ":用户没有登录");
            return "login";
        }
        if (1 == 2) {
            logger.info(jp.getSignature().getName() + ":用户没有权限");
            return "unauthorized";
        }
        try {
            Object ret = jp.proceed();
            return ret;
        } catch (NoUserInSessionException ex) {
            return "login";
        } catch (NoPermissionException ex) {
            return "unauthorized";
        }
    }

    @Around("anyServiceExceptLogin()")
    public Object processService(ProceedingJoinPoint jp) throws java.lang.Throwable {
        User user = (User) ActionContext.getContext().getSession().get("USER");
        if (user == null) {
            logger.info(jp.getSignature().getName() + ":用户没有登录");
            throw new NoUserInSessionException();
        }
        if (1 == 2) {
            logger.info(jp.getSignature().getName() + ":用户没有权限");
            throw new NoPermissionException();
        }
        return jp.proceed();
    }

}
