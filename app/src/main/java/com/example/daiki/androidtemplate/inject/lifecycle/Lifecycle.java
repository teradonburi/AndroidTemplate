package com.example.daiki.androidtemplate.inject.lifecycle;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by daiki on 2017/04/28.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface Lifecycle {
}