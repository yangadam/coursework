package cn.edu.xmu.comm.commons.annotation;

import java.lang.annotation.*;

/**
 * 权限过滤注解
 *
 * @author Mengmeng Yang
 * @version 12/22/2014 0022
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Required {

    String name() default "admin";

}
