package com.tc.labt.sgabs.cokpit.commons;

public class ErrorOrResult<T> {

    public final T result;
    public final Exception exception;
    public final boolean success;

    public ErrorOrResult(T result){
    	this.success = true;
        this.result = result;
        this.exception = null;
    }

    public ErrorOrResult(Exception e){
    	this.success = false;
        this.result = null;
        this.exception = e;
    }

    public ErrorOrResult(Exception e, T t){
    	this.success = false;
        this.result = t;
        this.exception = e;
    }

    public static <T> ErrorOrResult<T> ok(T result) { return new ErrorOrResult<>(result);}
    public static <T> ErrorOrResult<T> error(Exception e) { return new ErrorOrResult<>(e);}
    public static <T> ErrorOrResult<T> error(Exception e, T t) { return new ErrorOrResult<>(e, t);}

}
