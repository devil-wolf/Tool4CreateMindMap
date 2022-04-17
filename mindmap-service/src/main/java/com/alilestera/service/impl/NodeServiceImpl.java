package com.alilestera.service.impl;

import com.alilestera.entity.Background;
import com.alilestera.entity.Node;
import com.alilestera.enums.InstanceType;
import com.alilestera.factory.EntityFactory;
import com.alilestera.service.NodeService;
import com.alilestera.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @author Alilestera
 * @date 4/11/2022
 */
public class NodeServiceImpl implements NodeService {
    @Override
    public Node newInstance(Node parentNode) {
        Integer uid = null;
        String id = null;
        if (Background.getBackground() == null) {
            //背景画布不存在，即创建中心节点
            uid = 0;
            id = "centralNode";
        } else {
            //背景画布存在，即创建非中心节点
            uid = Background.getIdAndIncrement();
            id = StringUtils.concatAll("Node@", uid);
        }
        Map<String, Object> params = new HashMap<>();
        params.put("parentNode", parentNode);
        params.put("uid", uid);
        //父节点在子节点列表中添加子节点的操作
        Node newNode = (Node) EntityFactory.createEntity(Node.class, InstanceType.PROTOTYPE, params, id);
        if (newNode == null) {
            return null;
        }
        if (parentNode != null) {
            parentNode.addChild(newNode);
        }
        return newNode;
    }

    @Override
    public Boolean updateContext(Node node, String context) {
        if (!StringUtils.hasText(context)) {
            return false;
        }
        node.setContext(context);
        return true;
    }

    @Override
    public Boolean updateParent(Node childNode, Node parentNode) {
        if (childNode == null || parentNode == null) {
            return false;
        }
        //先从该子节点当前的父节点的子节点列表中删除该子节点
        Node parent = childNode.getParentNode();
        parent.removeChild(childNode);
        //然后设置该子节点的父节点为新的父节点
        childNode.setParentNode(parentNode);
        //在新的父节点的子节点列表中增加该子节点
        parentNode.addChild(childNode);
        return true;
    }

    @Override
    public Boolean deleteInstance(Node node) {
        if (node == null || node.getCentralNode()) {
            //如果节点为null，或者该节点为中心节点，则返回false
            return false;
        }
        //先然后从父节点中删除该子节点
        Node parentNode = node.getParentNode();
        parentNode.removeChild(node);
        //再进行递归删除节点树
        deleteInstanceTree(node);
        return true;
    }

    /**
     * 递归删除节点树，
     * 实现删除该节点及其所有子节点
     *
     * @param node 需要删除的节点
     */
    private void deleteInstanceTree(Node node) {
        List<Node> childrenNodes = node.getChildrenNodes();
        childrenNodes.forEach(this::deleteInstanceTree);
        childrenNodes.clear();
        EntityFactory.deleteEntity(Node.class, StringUtils.concatAll("Node@", node.getUid()));
    }
}
