package cn.edu.xmu.comm.commons.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Yiu-Wah WONG on 2014/12/18.
 */
@Aspect
@Component
public class Logging {

    @Around("execution(* cn.edu.xmu.comm.service.*.*(..))")
    public Object processTx(ProceedingJoinPoint jp) throws java.lang.Throwable {
        Logger logger = LoggerFactory.getLogger(jp.getSignature().getDeclaringType());
        String methodName = jp.getSignature().getName();
        Transactional annotion = jp.getTarget().getClass().getAnnotation(Transactional.class);
        System.out.print("Transactional:readOnly" + annotion.readOnly());
        Object[] args = jp.getArgs();
        Object rvt = jp.proceed(args);
        StringBuilder sb = new StringBuilder(methodName).append("(");
        for (Object arg : args) {
            sb.append(arg.getClass().getName()).append(",");
        }
        if (rvt == null) {
            logger.info(sb.append(")").toString());
            return null;
        }
        logger.info(sb.append("):").append(rvt.getClass().getName()).toString());
        return rvt;
    }

}
