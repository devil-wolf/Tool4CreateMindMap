package com.alilestera.service;

import com.alilestera.entity.Background;
import com.alilestera.entity.Node;

/**
 * @author Alilestera
 * @date 4/11/2022
 */
public interface BackgroundService {

    /**
     * 新建背景画布
     * @param centralNode 中心节点
     * @return 背景画布实例
     */
    Background newInstance(Node centralNode);
}
