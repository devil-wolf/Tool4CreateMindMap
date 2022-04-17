package com.alilestera.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 一个思维导图中唯一的画布，
 * 负责管理思维导图整体的信息。
 *
 * @author Alilestera
 * @date 3/22/2022
 */
@Data
public class Background implements Serializable {

    @Serial
    private static final long serialVersionUID = -3432661696723609360L;

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
    private Integer autoIncrementId;

    /**
     * 记录中心节点
     */
    private Node centralNode;

    /**
     * 背景画布类的静态实例对象
     */
    private static Background background;

    //初始化画布
    public Background() {
        autoIncrementId = 1;
        //默认没有储存
        storageLocation = null;
    }

    public static Background getBackground() {
        return background;
    }

    public static void setBackground(Background background) {
        Background.background = background;
    }

    public static Integer getIdAndIncrement() {
        return background.doGetIdAndIncrement();
    }

    protected Integer doGetIdAndIncrement() {
        return autoIncrementId++;
    }
}
