package com.alilestera.controller;

import com.alilestera.service.GlobalService;
import com.alilestera.service.impl.GlobalServiceImpl;

/**
 * @author Alilestera
 * @date 4/11/2022
 */
public class GlobalController {

    /**
     * 注入全局服务实现类
     */
    private static final GlobalService GLOBAL_SERVICE;

    static {
        GLOBAL_SERVICE = new GlobalServiceImpl();
    }

    /**
     * 初始化思维导图
     *
     * @return
     */
    public static Boolean init() {
        return GLOBAL_SERVICE.init();
    }

    /**
     * 根据路径加载思维导图
     *
     * @param fileFullName 思维导图文件的文件全名（路径+文件名）
     * @return 操作结果
     * 如果为true，则加载成功，实体工厂和背景画布实例导入完毕
     * 如果为false，则加载失败
     */
    public static Boolean load(String fileFullName) {
        return GLOBAL_SERVICE.load(fileFullName);
    }

    /**
     * 根据路径保存思维导图
     *
     * @param fileFullName 文件全名（路径+文件名）
     * @return 操作结果
     * 如果为true，则保存成功
     * 如果为false，则保存失败，可能路径有问题
     */
    public static Boolean save(String fileFullName) {
        return GLOBAL_SERVICE.save(fileFullName);
    }
}
