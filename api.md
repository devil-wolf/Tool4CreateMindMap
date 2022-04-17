### BackgroundController

背景画布处理器

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



类信息可以查看INFO.md



**Background**和**Node**的属性可以通过getter和setter来获取和修改

处理器类不可实例化，请直接调用类方法

