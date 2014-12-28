package cn.edu.xmu.comm.commons.annotation;

import java.lang.annotation.*;

/**
 * 将Field标注为从上一个Action中获得的参数
 *
 * @author Mengmeng Yang
 * @version 12/27/2014 0027
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ChainParameter {
    String fieldName() default "";
}
