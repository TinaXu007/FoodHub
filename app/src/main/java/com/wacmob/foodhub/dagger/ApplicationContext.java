package com.wacmob.foodhub.dagger;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by KP on 1/9/2019.
 */

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ApplicationContext {
}
