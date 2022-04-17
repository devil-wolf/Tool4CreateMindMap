package com.alilestera.exception;

/**
 * 对象未定义异常
 *
 * @author Alilestera
 * @date 3/25/2022
 */
public class ObjectUndefinationException extends RuntimeException {
    public ObjectUndefinationException(Class<?> clazz) {
        super("{ " + clazz.getCanonicalName() + " } 对象未定义");
    }

    public ObjectUndefinationException(String id) {
        super("{ " + id + " } 对象未定义");
    }
}
