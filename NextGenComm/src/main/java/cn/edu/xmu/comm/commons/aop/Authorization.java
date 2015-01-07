package cn.edu.xmu.comm.commons.aop;

import cn.edu.xmu.comm.commons.annotation.Required;
import cn.edu.xmu.comm.commons.exception.NoPermissionException;
import cn.edu.xmu.comm.commons.exception.NoUserInSessionException;
import cn.edu.xmu.comm.commons.utils.Constants;
import cn.edu.xmu.comm.commons.utils.SessionUtils;
import cn.edu.xmu.comm.commons.utils.StringUtils;
import cn.edu.xmu.comm.entity.User;
import com.opensymphony.xwork2.ActionContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
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

    @Pointcut("execution(* cn.edu.xmu.comm.service.impl.*.*(..)) " +
            "&& !execution(* cn.edu.xmu.comm.service.impl.SystemServiceImpl.*(..))")
    private void anyServiceExceptLogin() {
    }

    @Around("anyServiceExceptLogin()")
    public Object processService(ProceedingJoinPoint jp) throws java.lang.Throwable {
        User user = SessionUtils.getUser();
        if (user == null) {
            logger.info(jp.getSignature().getDeclaringType().getSimpleName() +
                    ":" + jp.getSignature().getName() + ":用户没有登录");
            throw new NoUserInSessionException();
        }
        Required annotation = ((MethodSignature) jp.getSignature())
                .getMethod().getAnnotation(Required.class);
        String permission = annotation == null ? "" : annotation.name();
        if (permission == null || !StringUtils.isBlank(permission) && !permission.contains(user.getType())) {
            logger.info(jp.getSignature().getDeclaringType().getSimpleName() +
                    ":" + jp.getSignature().getName() + ":用户没有权限");
            throw new NoPermissionException();
        }
        return jp.proceed();
    }

}
