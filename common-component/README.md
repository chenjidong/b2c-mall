# 公共组件 （非必须）

### 目录说明
* component 第三方缓存封装 如redis
* config 数据库配置
* domain 数据库实体基类
* dto 数据传输类 与 domain 实体类对等
* enums 所有数据实体枚举
* exception 自定义包裹异常
* feign 所有服务外部接口声明和实现
* mapper 封装 mybatis mapper 基类
* model mybatis 分页封装
* service 服务基类

### 引用方式
* 1.直接引用 导入module 即可
* 2.本地编译后引用 1.进入项目 执行 mvn install 
* 3.jar 包引用 mvn install:install-file -Dfile=xxx.jar -DgroupId=com.ppepper -DartifactId=common -Dversion=0.0.1-SNAPSHOT -Dpackaging=jar
```xml
 <dependency>
    <groupId>com.ppepper</groupId>
    <artifactId>common</artifactId>
    <version>0.0.1-SNAPSHOT</version>
 </dependency>
```

