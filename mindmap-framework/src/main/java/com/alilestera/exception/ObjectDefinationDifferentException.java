package com.alilestera.exception;

/**
 * 对象定义有歧义的异常
 *
 * @author Alilestera
 * @date 3/25/2022
 */
public class ObjectDefinationDifferentException extends RuntimeException {

    public ObjectDefinationDifferentException(Class<?> clazz) {
        super("不允许同时存在实体对应类 { " + clazz.getName() + " } 的单例和多例实体对象");
    }
}
