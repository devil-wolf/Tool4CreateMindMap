package com.alilestera.service;

/**
 * @author Alilestera
 * @date 4/11/2022
 */
public interface GlobalService {

    /**
     * 初始化新的思维导图
     * @return 操作结果
     * 如果为true，则初始化成功
     * 如果为false，则初始化失败
     */
    Boolean init();

    /**
     * 加载磁盘中的思维导图
     * @param fileFullName 文件全名（路径+文件名）
     * @return 操作结果
     * 如果为true，则加载成功
     * 如果为false，则加载失败
     */
    Boolean load(String fileFullName);

    /**
     * 将思维导图保存在磁盘中
     *
     * @param fileFullName 文件全名（路径+文件名）
     * @return 操作结果
     * 如果为
     */
    Boolean save(String fileFullName);
}
