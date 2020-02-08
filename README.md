# b2c 商城

### 基于springcloud 架构设计 
For further reference, please consider the following sections:

* **-server 结尾项目可直接运行
* **-component 结尾属于依赖组件

### 开发环境
* idea 2018+
* maven 3.5.0+
* java8+

### 端口返回设计
* 7000+  eureka 中心 config 配置中心
* 8000+ **业务服务
* 9000+ zuul 网关

### 框架
* springboot
* springcloud 组件（eureka,zuul,hystrix,openfeign,ribbon）

### 运行错误说明
* 1.common jar引入失败 详情看 common-component README.md 进行操作
* 2. redis connect failed  检查redis 是否安装或已启动、密码配置

### 打包测试
* mvn clean package/install
* linux  nohup java -jar **-server.jar (可选：--server.port=8080) & 
* window cmd shell ./**-server.jar

### 微服务
* eureka-server    服务控制中心
* goods-server     商品服务
* order-server     订单服务
* sso-server       登录服务
* zuul-server      网关服务
* common-component 公共组件（集成数据库配置，redis配置 mybatis 分页配置 数据库实体类配置）

### 环境配置
* 使用以下配置不能使用默认 idea maven 配置 需到setting 中更改默认maven 引用
* maven -> conf -> settings.xml 找到 <mirrors> 标签 加入 以下镜像
```xml

  <mirrors>
      <!-- mirror
       | Specifies a repository mirror site to use instead of a given repository. The repository that
       | this mirror serves has an ID that matches the mirrorOf element of this mirror. IDs are used
       | for inheritance and direct lookup purposes, and must be unique across the set of mirrors.
       |
      <mirror>
        <id>mirrorId</id>
        <mirrorOf>repositoryId</mirrorOf>
        <name>Human Readable Name for this Mirror.</name>
        <url>http://my.repository.com/repo/path</url>
      </mirror> -->
  
  	
  	 <mirror>
              <id>alimaven</id>
              <name>aliyun maven</name>
              <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
              <mirrorOf>central</mirrorOf>
          </mirror>
  
          <mirror>
              <id>uk</id>
              <mirrorOf>central</mirrorOf>
              <name>Human Readable Name for this Mirror.</name>
              <url>http://uk.maven.org/maven2/</url>
          </mirror>
  
          <mirror>
              <id>CN</id>
              <name>OSChina Central</name>
              <url>http://maven.oschina.net/content/groups/public/</url>
              <mirrorOf>central</mirrorOf>
          </mirror>
  
          <mirror>
              <id>nexus</id>
              <name>internal nexus repository</name>
              <!-- <url>http://192.168.1.100:8081/nexus/content/groups/public/</url>-->
              <url>http://repo.maven.apache.org/maven2</url>
              <mirrorOf>central</mirrorOf>
          </mirror>
    </mirrors>
```



