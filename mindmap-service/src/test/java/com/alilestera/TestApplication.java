package com.alilestera;

import com.alilestera.controller.GlobalController;
import com.alilestera.factory.EntityFactory;
import com.alilestera.util.FileManageUtils;
import org.junit.jupiter.api.Test;

/**
 * @author Alilestera
 * @date 4/11/2022
 */
public class TestApplication {

    @Test
    public void test() {
        Boolean init = GlobalController.init();
        System.out.println(init);
    }


    @Test
    public void test2() {
        String fileFullName = "D:\\TEST\\dt.map";
        Boolean init = GlobalController.init();
        FileManageUtils.save(EntityFactory.getEntityFactory(), fileFullName);
        Boolean load = GlobalController.load(fileFullName);
        System.out.println(load);
    }

    @Test
    public void test3() {
        String fileFullName = "D:\\TEST\\dt.map";
        Boolean init = GlobalController.init();
        Boolean save = GlobalController.save(fileFullName);
        Boolean load = GlobalController.load(fileFullName);
        System.out.println(load);
    }
}
