package com.alilestera.util;

import lombok.NonNull;

import java.util.Arrays;

/**
 * String工具类
 *
 * @author Alilestera
 * @date 3/25/2022
 */
public class StringUtils {

    /**
     * 拼接实例所有对象
     * 例如：
     * com.alilestera.bean.cat@7232232
     *
     * @param objs 需要拼接的对象
     * @return 拼接后的字符串
     */
    public static String concatAll(@NonNull Object... objs) {
        return Arrays.stream(objs)
                .map(String::valueOf)
                .reduce(String::concat)
                .orElse("");
    }

    /**
     * 检测传入的String对象是否有内容
     *
     * @param string
     * @return
     */
    public static Boolean hasText(String string) {
        return string != null && !string.isEmpty();
    }
}
