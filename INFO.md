# PROJECT INFORMATION

## 模块：mindmap-framework

该项目的框架，提供通用操作。工厂类，异常类，工具类

### 工厂类 factory

#### EntityFactory

实体工厂，负责创建实体对象，管理实体对象及其生命周期，对应的实体类必须提供空参构造器

属性：

```java
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
```

外部方法：

```java
/**
     * 根据字节码对象，实例类型，参数列表生成对应的实体对象
     *
     * @param clazz  需要创建实例对应类的字节码对象
     * @param type   创建实例的类型
     * @param params 需要注入的属性列表
     * @return
     */
    public static Object createEntity(@NonNull Class<?> clazz, @NonNull InstanceType type, Map<String, Object> params)
        
    /**
     * 根据字节码对象，实例类型，生成对应的实体对象
     * 不带参数列表
     *
     * @param clazz 需要创建实例对应类的字节码对象
     * @param type  创建实例的类型
     * @return 实体对象
     */
    public static Object createEntity(@NonNull Class<?> clazz, @NonNull InstanceType type)
    
    /**
     * 根据字节码对象，实例类型，参数列表生成自定义id的实体对象
     *
     * @param clazz  需要创建实例对应类的字节码对象
     * @param type   创建实例的类型
     * @param id     自定义的id
     * @param params 需要注入的属性列表
     * @return
     */
    public static Object createEntity(@NonNull Class<?> clazz, @NonNull InstanceType type, @NonNull String id, Map<String, Object> params)
    
    /**
     * 根据字节码对象，实例类型生成自定义id的实体对象
     * 不带参数列表
     *
     * @param clazz 需要创建实例对应类的字节码对象
     * @param type  创建实例的类型
     * @param id    自定义的id
     * @return 实体对象
     */
    public static Object createEntity(@NonNull Class<?> clazz, @NonNull InstanceType type, @NonNull String id)
        
    /**
     * 通过字节码对象来获取单例实体
     *
     * @param clazz 字节码对象
     * @return 单例实体对象
     */
    public static Object getEntity(@NonNull Class<?> clazz)
    
    /**
     * 通过自定义id来获取单例或多例实体
     *
     * @param id 自定义的id
     * @return 实体对象
     */
    public static Object getEntity(@NonNull String id)
    
    /**
     * 删除单例实体
     *
     * @param clazz 对应单例实体的字节码对象
     * @return 操作结果
     * 如果为true，说明该字节码对象的单例实体存在，操作成功
     * 如果为false，说明该字节码对象的单例实体未创建，操作失败
     */
    public static Boolean deleteEntity(Class<?> clazz)
        
    /**
     * 删除多例实体
     *
     * @param clazz 对应多例实体的字节码对象
     * @param id    该多例实体的id
     * @return 操作结果
     * 如果为true，说明该字节码对象的对应id的多例实体存在，操作成功
     * 如果为false，说明该字节码对象的多例实体不存在或对应的id的多例实体不存在，操作失败
     */
    public static Boolean deleteEntity(Class<?> clazz, String id)
```



内部方法：

```java
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
    protected Object createObject(Class<?> clazz, InstanceType type, Map<String, Object> params, String id)
    
    /**
     * 根据字节码对象，实例类型，参数列表生成对应的实体对象
     *
     * @param clazz  需要创建实例对应类的字节码对象
     * @param type   创建实例的类型
     * @param params 需要注入的属性列表
     * @return 对象
     */
    private Object doCreateObject(Class<?> clazz, InstanceType type, Map<String, Object> params, String id)
        
    /**
     * 根据参数列表创建对象
     *
     * @param clazz  需要创建实例对应类的字节码对象
     * @param params 需要注入的属性列表
     * @return 对象
     */
    private Object instantialize(Class<?> clazz, Map<String, Object> params)
        
    /**
     * 通过字节码对象来获取单例实体
     *
     * @param clazz 字节码对象
     * @return 单例实体对象
     */
    protected Object getObject(Class<?> clazz)
    
    /**
     * 通过对象id来获取实体
     *
     * @param id 对象存储id
     * @return 实体对象
     */
    protected Object getObject(String id)
        
    /**
     * 删除单例实体
     *
     * @param clazz 对应单例实体的字节码对象
     * @return 操作结果
     * 如果为true，说明该字节码对象的单例实体存在，操作成功
     * 如果为false，说明该字节码对象的单例实体未创建，操作失败
     */
    protected Boolean deleteObject(Class<?> clazz)
        
    /**
     * 删除多例实体
     *
     * @param clazz 对应多例实体的字节码对象
     * @param id    该多例实体的id
     * @return 操作结果
     * 如果为true，说明该字节码对象的对应id的多例实体存在，操作成功
     * 如果为false，说明该字节码对象的多例实体不存在或对应的id的多例实体不存在，操作失败
     */
    protected Boolean deleteObject(Class<?> clazz, String id)
```



### 异常类 exception

特定场景下抛出的异常分类

#### About EntityObject

```java
/**
 * 对象定义有歧义的异常
 * 不允许同时存在同一实体类的单例和多例实体对象
 * @author Alilestera
 * @date 3/25/2022
 */
public class ObjectDefinationDifferentException extends RuntimeException
```

```java
/**
 * 对象重定义异常
 * 不允许定义多个同一实体类的单例对象
 * @author Alilestera
 * @date 3/25/2022
 */
public class ObjectRedefinationException extends RuntimeException
```

```java
/**
 * 对象未定义异常
 * 
 * @author Alilestera
 * @date 3/25/2022
 */
public class ObjectUndefinationException extends RuntimeException
```



### 枚举类 enums

#### InstanceType

实例类型

1. 单例 SINGLETON
2. 多例 （原型） PROTOTYPE



### 工具类 util

#### StringUtils

字符串工具

- 字符串拼接

  ```java
  public static String concatAll(@NonNull Object... objs)
  ```

- 字符串是否有内容

  ```java
  public static Boolean hasText(String string)
  ```


#### FileManageUtils

文件管理工具

- 保存对象

```java
public static Boolean save(Object obj, String fileFullName)
```

- 加载对象

```java
public static Object load(String fileFullName)
```



## 模块：mindmap-entity

该项目的实体，负责定义各种实体类

### 背景画布 Background

```java
/**
 * 一个思维导图中唯一的画布，
 * 负责管理思维导图整体的信息。
 *
 * @author Alilestera
 * @date 3/22/2022
 */
@Data
public class Background implements Serializable
```

属性：

```java
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
```



### 节点 Node

```java
/**
 * 画布中的节点
 *
 * @author Alilestera
 * @date 3/22/2022
 */
@Data
@NoArgsConstructor
public class Node implements Serializable
```

属性：

```java
/**
     * 节点的唯一标识Id。
     */
    private Integer uid;

    /**
     * 该节点是否为中心节点，
     * 如果是则为true，
     * 否则为false。
     */
    private Boolean centralNode;

    /**
     * 记录该节点的父节点，
     * 只有该节点不为中心节点时有值，
     * 如果该节点为中心节点，则为null。
     */
    private Node parentNode;

    /**
     * 记录该节点的所有子节点。
     */
    @ToString.Exclude
    private List<Node> childrenNodes;

    /**
     * 储存节点展示的内容。
     * 例如：
     * context = "topic 1";
     */
    private String context;
```



## 模块：mindmap-service

### BackgroundController

背景画布处理器

负责画背景布的功能

```java
/**
     * 创建背景画布
     * @param centralNode 中心节点
     * @return 背景画布的实例
     */
    public static Background createBackground(Node centralNode)
```

### NodeController

节点处理器

负责节点的功能，例如创建、删除、更新等

```java
/**
     * 新建父节点为传入节点的子节点
     *
     * @param parentNode 父节点
     * @return 新建的节点
     */
    public static Node createNode(Node parentNode)
        
	/**
     * 更新传入节点的内容
     *
     * @param node    需要更新的节点
     * @param context 新的内容
     * @return 操作结果
     */
    public static Boolean updateContext(Node node, String context)
        
    /**
     * 更改节点的父类
     *
     * @param childNode  需要更改父节点的子节点
     * @param parentNode 父节点
     * @return 操作结果
     */
    public static Boolean updateParent(Node childNode, Node parentNode)
     
    /**
     * 删除该节点及其子节点
     *
     * @param node 需要删除的节点
     * @return 操作结果
     * 如果为true，则代表删除成功
     * 如果为false，说明该节点为null，或者该节点为中心节点，不可删除
     */
    public static Boolean deleteNode(Node node)
```

### GlobalController

全局处理器

负责全局需要的功能，初始化，加载文件，保存文件

```java
/**
     * 初始化思维导图
     *
     * @return
     */
    public static Boolean init()
        
    /**
     * 根据路径加载思维导图
     *
     * @param fileFullName 思维导图文件的文件全名（路径+文件名）
     * @return 操作结果
     * 如果为true，则加载成功，实体工厂和背景画布实例导入完毕
     * 如果为false，则加载失败
     */
    public static Boolean load(String fileFullName)
     
    /**
     * 根据路径保存思维导图
     *
     * @param fileFullName 文件全名（路径+文件名）
     * @return 操作结果
     * 如果为true，则保存成功
     * 如果为false，则保存失败，可能路径有问题
     */
    public static Boolean save(String fileFullName)
```

