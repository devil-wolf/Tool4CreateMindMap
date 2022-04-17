package com.alilestera.util;

import java.io.*;

/**
 * 文件管理器，
 * 负责文件读取，保存等操作
 *
 * @author Alilestera
 * @date 4/4/2022
 */
public class FileManageUtils {

    /**
     * 创建静态文件管理器对象
     */
    private static final FileManageUtils FILE_MANAGER;

    static {
        FILE_MANAGER = new FileManageUtils();
    }

    /**
     * 储存文件
     *
     * @param obj 储存的对象
     * @param fileFullName 文件全名，即路径名+文件名
     * @return 操作结果
     * 如果为true则代表文件储存成功
     * 如果为false则代表文件储存失败，出现异常
     */
    public static Boolean save(Object obj, String fileFullName) {
        return FILE_MANAGER.saveObjectAsFile(obj, fileFullName);
    }

    /**
     * 加载文件
     *
     * @param fileFullName 文件全名，即路径名+文件名
     * @return 从文件中读取的对象
     * 如果返回的对象不为null，说明读取成功
     * 如果返回的对象为null，说明文件读取失败，出现异常
     */
    public static Object load(String fileFullName) {
        return FILE_MANAGER.loadFileAsObject(fileFullName);
    }

    /**
     * 将对象储存为文件
     *
     * @param obj 储存的对象
     * @param fileFullName 文件全名，即路径名+文件名
     * @return 操作结果
     */
    protected Boolean saveObjectAsFile(Object obj, String fileFullName) {
        ObjectOutputStream objectOutputStream = null;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileFullName);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(obj);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return objectOutputStream != null;
    }

    /**
     * 将文件读取为对象
     *
     * @param fileFullName 文件全名，即路径名+文件名
     * @return 读取得到的对象
     */
    protected Object loadFileAsObject(String fileFullName) {
        ObjectInputStream objectInputStream = null;
        Object object = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(fileFullName);
            objectInputStream = new ObjectInputStream(fileInputStream);
            object = objectInputStream.readObject();
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return object;
    }
}
