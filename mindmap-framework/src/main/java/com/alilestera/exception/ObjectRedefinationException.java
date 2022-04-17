package com.alilestera.exception;

/**
 * 对象重定义异常
 *
 * @author Alilestera
 * @date 3/25/2022
 */
public class ObjectRedefinationException extends RuntimeException {
    public ObjectRedefinationException(Class<?> clazz) {
        super("{ " + clazz.getName() + " } 实体对象重定义");
    }
}
