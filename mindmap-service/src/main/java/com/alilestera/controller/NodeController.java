package com.alilestera.controller;

import com.alilestera.entity.Background;
import com.alilestera.entity.Node;
import com.alilestera.service.NodeService;
import com.alilestera.service.impl.NodeServiceImpl;
import com.alilestera.util.StringUtils;

/**
 * @author Alilestera
 * @date 4/11/2022
 */
public class NodeController {

    /**
     * 注入节点的服务实现类
     */
    private static final NodeService nodeService;

    static {
        nodeService = new NodeServiceImpl();
    }

    /**
     * 新建父节点为传入节点的子节点
     *
     * @param parentNode 父节点
     * @return 新建的节点
     */
    public static Node createNode(Node parentNode) {
        return nodeService.newInstance(parentNode);
    }

    /**
     * 更新传入节点的内容
     *
     * @param node    需要更新的节点
     * @param context 新的内容
     * @return 操作结果
     */
    public static Boolean updateContext(Node node, String context) {
        return nodeService.updateContext(node, context);
    }

    /**
     * 更改节点的父类
     *
     * @param childNode  需要更改父节点的子节点
     * @param parentNode 父节点
     * @return 操作结果
     */
    public static Boolean updateParent(Node childNode, Node parentNode) {
        return nodeService.updateParent(childNode, parentNode);
    }

    /**
     * 删除该节点及其子节点
     *
     * @param node 需要删除的节点
     * @return 操作结果
     * 如果为true，则代表删除成功
     * 如果为false，说明该节点为null，或者该节点为中心节点，不可删除
     */
    public static Boolean deleteNode(Node node) {
        return nodeService.deleteInstance(node);
    }
}
