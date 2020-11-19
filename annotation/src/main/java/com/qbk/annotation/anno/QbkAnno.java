package com.qbk.annotation.anno;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface QbkAnno {
    String value() default "";
}
