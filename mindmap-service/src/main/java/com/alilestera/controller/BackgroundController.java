package com.alilestera.controller;

import com.alilestera.entity.Background;
import com.alilestera.entity.Node;
import com.alilestera.service.BackgroundService;
import com.alilestera.service.impl.BackgroundServiceImpl;

/**
 * @author Alilestera
 * @date 4/11/2022
 */
public class BackgroundController {

    /**
     * 注入背景画布的服务实现类
     */
    private static final BackgroundService BACKGROUND_SERVICE;

    static {
        BACKGROUND_SERVICE = new BackgroundServiceImpl();
    }

    /**
     * 创建背景画布
     * @param centralNode 中心节点
     * @return 背景画布的实例
     */
    public static Background createBackground(Node centralNode) {
        return BACKGROUND_SERVICE.newInstance(centralNode);
    }
}
