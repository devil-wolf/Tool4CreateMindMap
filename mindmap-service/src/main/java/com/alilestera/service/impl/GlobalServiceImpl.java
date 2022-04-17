package com.alilestera.service.impl;

import com.alilestera.controller.BackgroundController;
import com.alilestera.controller.NodeController;
import com.alilestera.entity.Background;
import com.alilestera.entity.Node;
import com.alilestera.factory.EntityFactory;
import com.alilestera.service.GlobalService;
import com.alilestera.util.FileManageUtils;

/**
 * @author Alilestera
 * @date 4/11/2022
 */
public class GlobalServiceImpl implements GlobalService {
    @Override
    public Boolean init() {
        //创建新的节点，并设置为新的中心节点
        Node centralNode = NodeController.createNode(null);
        centralNode.setCentralNode(true);
        //创建背景画布，并将上面的中心节点注入
        Background background = BackgroundController.createBackground(centralNode);
        Background.setBackground(background);
        return Background.getBackground() != null;
    }

    @Override
    public Boolean load(String fileFullName) {
        //加载实体工厂
        EntityFactory entityFactory = (EntityFactory) FileManageUtils.load(fileFullName);
        EntityFactory.setEntityFactory(entityFactory);
        //从实体工厂中得到背景画布的实例
        Background background = (Background) EntityFactory.getEntity(Background.class);
        Background.setBackground(background);
        return Background.getBackground() != null;
    }

    @Override
    public Boolean save(String fileFullName) {
        Boolean operateResult = FileManageUtils.save(EntityFactory.getEntityFactory(), fileFullName);
        if (!operateResult) {
            return false;
        }
        Background background = Background.getBackground();
        background.setStorageLocation(fileFullName);
        return true;
    }
}
