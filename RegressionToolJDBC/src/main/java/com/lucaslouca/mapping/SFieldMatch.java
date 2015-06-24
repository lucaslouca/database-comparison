package com.lucaslouca.mapping;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Put this annotation on above of a CTrade field and set the name property to
 * point to the corresponding STrade field name. Those two fields will then be
 * checked for equality.
 * 
 * @author lucas
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface SFieldMatch {
	String name();
}
