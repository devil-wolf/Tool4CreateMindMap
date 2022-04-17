package com.alilestera.entity;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 画布中的节点
 *
 * @author Alilestera
 * @date 3/22/2022
 */
@Data
@NoArgsConstructor
public class Node implements Serializable {

    @Serial
    private static final long serialVersionUID = -2702183415858647164L;

    /**
     * 节点的唯一标识Id。
     */
    private Integer uid;

    /**
     * 该节点是否为中心节点，
     * 如果是则为true，
     * 否则为false。
     */
    private Boolean centralNode;

    /**
     * 记录该节点的父节点，
     * 只有该节点不为中心节点时有值，
     * 如果该节点为中心节点，则为null。
     */
    private Node parentNode;

    /**
     * 记录该节点的所有子节点。
     */
    @ToString.Exclude
    private List<Node> childrenNodes;

    /**
     * 储存节点展示的内容。
     * 例如：
     * context = "topic 1";
     */
    private String context;

    //初始化
    {
        // 新建的节点不会是中心节点
        centralNode = false;
        // 初始化维护子节点的列表
        childrenNodes = new ArrayList<>();
        //默认内容
        context = "Topic";
    }

    public Node(Integer uid, Node parentNode) {
        this.uid = uid;
        this.parentNode = parentNode;
    }

    public Boolean addChild(Node childNode) {
        if (childNode == null) {
            return false;
        }
        childrenNodes.add(childNode);
        return true;
    }

    public Boolean removeChild(Node childNode) {
        if (childNode == null) {
            return false;
        }
        return childrenNodes.remove(childNode);
    }
}
