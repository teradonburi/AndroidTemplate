package com.example.daiki.androidtemplate.inject.request;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by daiki on 2017/05/05.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface Request {
}
