package com.alilestera.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 一个思维导图中唯一的画布，
 * 负责管理思维导图整体的信息。
 *
 * @author Alilestera
 * @date 3/22/2022
 */
@Data
public class Background {
    /**
     * 用于存储思维导图的储存位置，
     * 当思维导图是新建且未保存的状态时，该值为null，
     * 例如：
     * storageLocation = null;
     * 否则该值为思维导图的储存位置的绝对路径。
     * 例如：
     * storageLocation = "D:/folder/xxx";
     */
    private String storageLocation;

    /**
     * 全局标识自增id，从0开始
     * 用于赋值与新的节点的uid，赋值后自增。
     */
    public static Integer autoIncrementId;

    //TODO 经过保存的版本

    /**
     * 记录中心节点
     */
    private final Node CENTRAL_NODE;

    //初始化画布
    public Background() {
        //默认创建一个中心节点
        autoIncrementId = 0;
        CENTRAL_NODE = new Node(autoIncrementId, null);
        CENTRAL_NODE.setCentralNode(true);
        autoIncrementId++;
        //默认没有储存
        storageLocation = null;
    }
}
