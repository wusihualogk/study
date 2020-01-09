package org.saber.study.thread.t13;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * description:
 *
 * @author: saber
 * @date: 2020/1/7 15:37
 **/

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ActiveMethod {

}
