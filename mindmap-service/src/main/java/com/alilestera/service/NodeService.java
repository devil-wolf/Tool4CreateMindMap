package com.alilestera.service;

import com.alilestera.entity.Node;

/**
 * @author Alilestera
 * @date 4/11/2022
 */
public interface NodeService {
    /**
     * 新建父节点为传入节点的新节点
     *
     * @param parentNode 新建节点的父节点
     * @return 新建的节点
     */
    Node newInstance(Node parentNode);

    /**
     * 更新传入节点的内容
     *
     * @param node    传入节点
     * @param context 新的内容
     * @return 操作结果
     * 如果为true，内容更新成功
     * 如果为false，内容为空，无法更新
     */
    Boolean updateContext(Node node, String context);

    /**
     * 更改节点的父节点
     *
     * @param childNode  需要更改父节点的子节点
     * @param parentNode 父节点
     * @return 操作结果
     */
    Boolean updateParent(Node childNode, Node parentNode);

    /**
     * 循环删除节点及其子节点
     *
     * @param node 目标删除节点
     * @return 操作结果
     * 如果为true，则删除成功
     * 如果为false，操作失败，节点不存在或该节点为中心节点
     */
    Boolean deleteInstance(Node node);
}
