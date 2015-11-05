package com.ccb.qd.webcrawler;

import java.lang.annotation.*;

/**
 * Created by han on 2015/7/10.
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
public @interface WebStep {
        String value();
}
