package com.alilestera.factory;

import com.alilestera.enums.InstanceType;
import com.alilestera.exception.ObjectDefinationDifferentException;
import com.alilestera.exception.ObjectRedefinationException;
import com.alilestera.exception.ObjectUndefinationException;
import com.alilestera.util.StringUtils;
import lombok.NonNull;

import java.io.Serial;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 实体工厂，负责创建实体对象，管理实体对象及其生命周期
 * 对应的实体类必须提供空参构造器
 *
 * @author Alilestera
 * @date 3/24/2022
 */
public class EntityFactory implements Serializable {

    @Serial
    private static final long serialVersionUID = -4635851114353654571L;

    /**
     * 管理单例对象的map集合
     * key值是对象的名称
     */
    private final Map<String, Object> singletonObjects;

    /**
     * 储存单例对象map集合的key值，
     * 通过字节码对象来获取对应的key值
     */
    private final Map<Class<?>, String> singletonObjectNames;

    /**
     * 管理多例对象的map集合
     * key值是对象的名称
     */
    private final Map<String, Object> prototypeObjects;

    /**
     * 储存多例对象的字节码对象的数量
     */
    private final Map<Class<?>, Integer> prototypeObjectClasses;

    /**
     * 创建静态实体工厂对象
     */
    private static EntityFactory entityFactory;

    static {
        //实例化实体工厂
        entityFactory = new EntityFactory();
    }

    public EntityFactory() {
        //初始化实体工厂
        singletonObjects = new HashMap<>();
        singletonObjectNames = new HashMap<>();
        prototypeObjects = new HashMap<>();
        prototypeObjectClasses = new HashMap<>();
    }

    /**
     * 覆盖旧的实体工厂实例，
     * 更新为新的实体工厂实例
     *
     * @param entityFactory 新的实体工厂实例
     */
    public static void setEntityFactory(EntityFactory entityFactory) {
        EntityFactory.entityFactory = entityFactory;
    }

    /**
     * 获取实体工厂
     *
     * @return 目前使用的实体工厂实例
     */
    public static EntityFactory getEntityFactory() {
        return entityFactory;
    }

    /**
     * 根据字节码对象，实例类型，参数列表生成对应的实体对象
     *
     * @param clazz  需要创建实例对应类的字节码对象
     * @param type   创建实例的类型
     * @param params 需要注入的属性列表
     * @return 实体对象
     */
    public static Object createEntity(@NonNull Class<?> clazz, @NonNull InstanceType type, Map<String, Object> params) {
        return entityFactory.createObject(clazz, type, params, null);
    }

    /**
     * 根据字节码对象，实例类型，生成对应的实体对象
     * 不带参数列表
     *
     * @param clazz 需要创建实例对应类的字节码对象
     * @param type  创建实例的类型
     * @return 实体对象
     */
    public static Object createEntity(@NonNull Class<?> clazz, @NonNull InstanceType type) {
        return entityFactory.createObject(clazz, type, null, null);
    }

    /**
     * 根据字节码对象，实例类型，参数列表生成自定义id的实体对象
     *
     * @param clazz  需要创建实例对应类的字节码对象
     * @param type   创建实例的类型
     * @param id     自定义的id
     * @param params 需要注入的属性列表
     * @return 实体对象
     */
    public static Object createEntity(@NonNull Class<?> clazz, @NonNull InstanceType type, Map<String, Object> params, String id) {
        return entityFactory.createObject(clazz, type, params, id);
    }

    /**
     * 根据字节码对象，实例类型生成自定义id的实体对象
     * 不带参数列表
     *
     * @param clazz 需要创建实例对应类的字节码对象
     * @param type  创建实例的类型
     * @param id    自定义的id
     * @return 实体对象
     */
    public static Object createEntity(@NonNull Class<?> clazz, @NonNull InstanceType type, String id) {
        return entityFactory.createObject(clazz, type, null, id);
    }

    /**
     * 根据字节码对象，实例类型，参数列表生成对应的实体对象
     * 检查操作是否可行
     * 可行则调用doCreateEntity方法
     *
     * @param clazz  需要创建实例对应类的字节码对象
     * @param type   创建实例的类型
     * @param params 需要注入的属性列表
     * @return 对象
     */
    protected Object createObject(Class<?> clazz, InstanceType type, Map<String, Object> params, String id) {
        if (type == InstanceType.SINGLETON) {
            //先检查该对象是否已经被创建
            if (singletonObjectNames.containsKey(clazz)) {
                throw new ObjectRedefinationException(clazz);
            }
            //如果已经是多例对象，但仍创建单例对象
            //则抛出对象定义有歧义的异常
            if (prototypeObjectClasses.containsKey(clazz)) {
                throw new ObjectDefinationDifferentException(clazz);
            }
        } else if (type == InstanceType.PROTOTYPE) {
            //检查多例对象里是否已经存在该id的对象
            if (StringUtils.hasText(id) && prototypeObjects.containsKey(id)) {
                throw new ObjectRedefinationException(clazz);
            }
            //如果已经是单例对象，但仍创建多例对象
            //则抛出对象定义有歧义的异常
            if (singletonObjectNames.containsKey(clazz)) {
                throw new ObjectDefinationDifferentException(clazz);
            }
        }
        return doCreateObject(clazz, type, params, id);
    }

    /**
     * 根据字节码对象，实例类型，参数列表生成对应的实体对象
     *
     * @param clazz  需要创建实例对应类的字节码对象
     * @param type   创建实例的类型
     * @param params 需要注入的属性列表
     * @return 对象
     */
    private Object doCreateObject(Class<?> clazz, InstanceType type, Map<String, Object> params, String id) {
        Object obj = instantialize(clazz, params);
        if (type == InstanceType.SINGLETON) {
            //如果是创建单例实体
            //如果id为空，则将全类名赋值给id
            if (!StringUtils.hasText(id)) {
                id = obj.getClass().getCanonicalName();
            }
            singletonObjectNames.put(obj.getClass(), id);
            singletonObjects.put(obj.getClass().getCanonicalName(), obj);
        } else if (type == InstanceType.PROTOTYPE) {
            //如果是创建单例实体
            //如果id为空，则将拼接全类名和hashcode赋值给id
            if (!StringUtils.hasText(id)) {
                id = StringUtils.concatAll(obj);
            }
            //如果是创建单例实体
            prototypeObjectClasses.put(clazz, prototypeObjectClasses.getOrDefault(clazz, 0) + 1);
            prototypeObjects.put(id, obj);
        }
        return obj;
    }

    /**
     * 根据参数列表创建对象
     *
     * @param clazz  需要创建实例对应类的字节码对象
     * @param params 需要注入的属性列表
     * @return 对象
     */
    private Object instantialize(Class<?> clazz, Map<String, Object> params) {
        Object obj = null;
        try {
            //通过反射爆破将参数注入属性
            obj = clazz.getDeclaredConstructor().newInstance();
            if (params != null) {
                for (String key : params.keySet()) {
                    Field field = clazz.getDeclaredField(key);
                    field.setAccessible(true);
                    field.set(obj, params.get(key));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * 通过字节码对象来获取单例实体
     *
     * @param clazz 字节码对象
     * @return 单例实体对象
     */
    public static Object getEntity(@NonNull Class<?> clazz) {
        return entityFactory.getObject(clazz);
    }

    /**
     * 通过自定义id来获取单例或多例实体
     *
     * @param id 自定义的id
     * @return 实体对象
     */
    public static Object getEntity(@NonNull String id) {
        return entityFactory.getObject(id);
    }

    /**
     * 通过字节码对象来获取单例实体
     *
     * @param clazz 字节码对象
     * @return 单例实体对象
     */
    protected Object getObject(Class<?> clazz) {
        //检查是否为单例实体
        Object obj = null;
        if (singletonObjectNames.containsKey(clazz)) {
            //是单例实体
            obj = singletonObjects.get(singletonObjectNames.get(clazz));
        } else {
            //是多例实体
            throw new ObjectUndefinationException(clazz);
        }
        return obj;
    }

    /**
     * 通过对象id来获取实体
     *
     * @param id 对象存储id
     * @return 实体对象
     */
    protected Object getObject(String id) {
        //检查是否为单例实体
        Object obj = null;
        if (singletonObjects.containsKey(id)) {
            //是单例实体
            obj = singletonObjects.get(id);
        } else if (prototypeObjects.containsKey(id)) {
            //不是单例实体
            obj = prototypeObjects.get(id);
        } else {
            //不存在该对象
            throw new ObjectUndefinationException(id);
        }
        return obj;
    }

    /**
     * 删除单例实体
     *
     * @param clazz 对应单例实体的字节码对象
     * @return 操作结果
     * 如果为true，说明该字节码对象的单例实体存在，操作成功
     * 如果为false，说明该字节码对象的单例实体未创建，操作失败
     */
    public static Boolean deleteEntity(Class<?> clazz) {
        return entityFactory.deleteObject(clazz);
    }

    /**
     * 删除多例实体
     *
     * @param clazz 对应多例实体的字节码对象
     * @param id    该多例实体的id
     * @return 操作结果
     * 如果为true，说明该字节码对象的对应id的多例实体存在，操作成功
     * 如果为false，说明该字节码对象的多例实体不存在或对应的id的多例实体不存在，操作失败
     */
    public static Boolean deleteEntity(Class<?> clazz, String id) {
        return entityFactory.deleteObject(clazz, id);
    }

    /**
     * 删除单例实体
     *
     * @param clazz 对应单例实体的字节码对象
     * @return 操作结果
     * 如果为true，说明该字节码对象的单例实体存在，操作成功
     * 如果为false，说明该字节码对象的单例实体未创建，操作失败
     */
    protected Boolean deleteObject(Class<?> clazz) {
        String objName = null;
        if (singletonObjectNames.containsKey(clazz)) {
            //存在该单例对象，删除
            objName = singletonObjectNames.get(clazz);
            singletonObjects.remove(objName);
            singletonObjectNames.remove(clazz);
        }
        return StringUtils.hasText(objName);
    }

    /**
     * 删除多例实体
     *
     * @param clazz 对应多例实体的字节码对象
     * @param id    该多例实体的id
     * @return 操作结果
     * 如果为true，说明该字节码对象的对应id的多例实体存在，操作成功
     * 如果为false，说明该字节码对象的多例实体不存在或对应的id的多例实体不存在，操作失败
     */
    protected Boolean deleteObject(Class<?> clazz, String id) {
        if (prototypeObjectClasses.containsKey(clazz)) {
            //存在该字节码对象的多例实体
            if (prototypeObjects.containsKey(id)) {
                //存在该id的多例实体
                //移除该多例实体
                prototypeObjects.remove(id);
                //该字节码对象的实体数量减 1
                prototypeObjectClasses.put(clazz, prototypeObjectClasses.get(clazz) - 1);
                if (prototypeObjectClasses.get(clazz) == 0) {
                    //该字节码对象的实体数量已为 0
                    //删除该字节码对象的索引
                    prototypeObjectClasses.remove(clazz);
                }
            }
        } else {
            return false;
        }
        return true;
    }
}
