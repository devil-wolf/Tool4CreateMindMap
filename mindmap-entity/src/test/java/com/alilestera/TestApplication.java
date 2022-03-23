package com.alilestera;

import com.alilestera.entity.Background;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Alilestera
 * @date 3/23/2022
 */
public class TestApplication {

    Background background;

    @BeforeEach
    public void init() {
        background = new Background();
    }

    @Test
    public void test() {
        System.out.println(background);
    }
}
