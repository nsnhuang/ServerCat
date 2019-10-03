package servermc.annotation;

import server.entiry.http.enums.RequestMethod;

import java.lang.annotation.*;

/**
 * 描述:
 *
 * @author huang
 * @create 2019-10-03 3:11 PM
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {

    /**
     * 请求路径
     */
    String value() default "";

    /**
     * 请求方式
     */
    RequestMethod method() default RequestMethod.GET;
}