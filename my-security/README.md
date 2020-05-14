## my-security 快速开始
* 引入 my-security-starter 依赖，需要springboot基础环境支持，本项目基于springboot2.2
* 使用`@EnableMySecurityWeb`开启web端权限支持,服务调用不需要开启，必要的资源会自动装配
* 在yml文件中进行配置
```yml
my-security:
  # 白名单
  whitelist:
    - /login
    - /error
    - /sec/formLogin
    - /sec/jsonLogin
    - /sec/cookieLogin
  # 登录地址
  loginUrl: /login
  jwtProperties:
    # jwt sign算法
    algorithm: hs512
    # jwt secret
    secret: secretsecretsecretsecretsecretsecretsecretsecretsecret
    # jwt 有效时间
    expiration-in-second: 1209600
```
## 功能测试 代码在demo-security-web 目录下
### 同时启动demo-security-web,demo-security-service两个服务
* 同时支持Ajax、Cookie、Json 三种请求验证模式 我没有完全理解这个需求的意思，完成了登录支持form表单，body，cookie登录
```shell
    登录请求地址：localhost:8100/login 分别进行三种方式请求
```
* 需要通过注解实现权限判断，比如：@HasRole(“ADMIN”) 
```shell
    默认设置两个账号 username=user password=123，username=admin password=123，用于权限测试
    请求地址：localhost:8100/test 该方法上添加了@HasRole(“ADMIN”)
    在请求的header中需要添加用的token key：Authorization value：Bearer +token 注意Bearer后用一个空格
```
* 参考@RequestBody 的原理。完成登陆用户信息注入的功能
```shell
    请求地址：localhost:8100/test 该方法参数添加了@SecurityUser 控制台会打印出获取的用户信息
```
* 需要支持分布式环境
```shell
    请求地址：localhost:8100/test 该方法会调用demo-security-service 中的service方法（添加了如上注解）
    打印出获取的用户信息，暂时通过restTemplate调用，调用时需要在header中添加token 后续可用feign拦截器
```
