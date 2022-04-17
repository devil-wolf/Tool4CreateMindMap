package com.alilestera.service.impl;

import com.alilestera.entity.Background;
import com.alilestera.entity.Node;
import com.alilestera.enums.InstanceType;
import com.alilestera.factory.EntityFactory;
import com.alilestera.service.BackgroundService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Alilestera
 * @date 4/11/2022
 */
public class BackgroundServiceImpl implements BackgroundService {

    @Override
    public Background newInstance(Node centralNode) {
        Map<String, Object> params = new HashMap<>();
        params.put("centralNode", centralNode);
        return (Background) EntityFactory.createEntity(Background.class, InstanceType.SINGLETON, params);
    }
}
