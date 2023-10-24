package com.wacmob.foodhub.dagger;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by KP on 1/9/2019.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}