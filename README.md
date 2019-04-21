# spring-boot-cache
## 1.spring内置cache，simpleCache，启用spring的cache功能。
## 2.ehcache缓存配置完成，且测试通过，仍然中注解，除了引入了依赖库，做了xml配置，不需要其他配置。
## 3.guava缓存模块，整合并测试通过，不需要外部配置，但是需要一个配置类和spring-context-suport库，来实例化guavaCacheManager
## 4.caffien缓存他是guava-cache的java8升级版，代码式配置集成完成，但是部分参数未生效，（纯配置型未成功，原因不明）；