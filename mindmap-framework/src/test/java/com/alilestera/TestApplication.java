package com.alilestera;

import com.alilestera.entity.Background;
import com.alilestera.entity.Node;
import com.alilestera.enums.InstanceType;
import com.alilestera.factory.EntityFactory;
import com.alilestera.util.FileManageUtils;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

/**
 * @author Alilestera
 * @date 3/24/2022
 */
public class TestApplication {

    @Test
    public void test() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("uid", 1);
        Node node1 = (Node) EntityFactory.createEntity(Node.class, InstanceType.PROTOTYPE, map);
        map.put("uid", 2);
        Node node2 = (Node) EntityFactory.createEntity(Node.class, InstanceType.PROTOTYPE, map);
        map.put("uid", 3);
        Node node3 = (Node) EntityFactory.createEntity(Node.class, InstanceType.PROTOTYPE, map);
        System.out.println(node1);
    }

    public String fileFullName = "D:\\TEST\\map.dt";

    @Test
    public void testSaveFile() {
        Node node = new Node();
        FileManageUtils.save(node, fileFullName);
        System.out.println(1);
    }

    @Test
    public void testSaveFile2() {
        HashMap<String, Object> map = new HashMap<>();
        Node node1 = new Node();
        Node node2 = new Node();
        Background background1 = new Background();
        Background background2 = new Background();
        map.put("node1", node1);
        map.put("node2", node2);
        map.put("background1", background1);
        map.put("background2", background2);

        EntityFactory factory = new EntityFactory();
        map.put("factory", factory);

        FileManageUtils.save(map, fileFullName);
        System.out.println(1);
    }

    @Test
    public void testSaveFile3() {
        HashMap<String, Object> map = new HashMap<>();
        EntityFactory.createEntity(Node.class, InstanceType.PROTOTYPE, null, String.valueOf(1));
        EntityFactory.createEntity(Node.class, InstanceType.PROTOTYPE, null, String.valueOf(2));
        EntityFactory.createEntity(Node.class, InstanceType.PROTOTYPE, null, String.valueOf(3));
        map.put("factory", EntityFactory.getEntityFactory());

        FileManageUtils.save(map, fileFullName);
        System.out.println(1);
    }

    @Test
    public void testLoadFile() {
        HashMap<String, Object> map = (HashMap<String, Object>) FileManageUtils.load(fileFullName);
        System.out.println(1);
    }
}
