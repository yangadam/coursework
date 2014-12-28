package cn.edu.xmu.comm.commons.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * AOP权限拦截
 *
 * @author Mengmeng Yang
 * @version 12/18/2014 0027
 */
@Aspect
@Component
public class Logging {

    private static Logger logger = LoggerFactory.getLogger(Logging.class);

    @Around("execution(* cn.edu.xmu.comm.service.*.*(..))")
    public Object processTx(ProceedingJoinPoint jp) throws java.lang.Throwable {
        String methodName = jp.getSignature().getName();
        Object[] args = jp.getArgs();
        Object rvt = jp.proceed(args);
        StringBuilder sb = new StringBuilder(methodName).append("(");
        for (Object arg : args) {
            sb.append(arg.getClass().getSimpleName()).append(",");
        }
        if (rvt == null) {
            logger.info(sb.append(")").toString());
            return null;
        }
        logger.info(sb.append("):").append(rvt.getClass().getSimpleName()).toString());
        return rvt;
    }

}
