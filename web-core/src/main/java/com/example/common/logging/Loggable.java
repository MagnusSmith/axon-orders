package com.example.common.logging;

import java.lang.annotation.*;

/**
* Created with IntelliJ IDEA.
* User: magnus.smith
* Date: 03/06/13
* Time: 15:22
*
*/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface Loggable {
    //for slf4j
}
