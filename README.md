## Spring Boot Demo Project



#### Using docker wrk to make performance testing:

```bash
# 以64个线程执行480次或持续120秒访问本机（docker主机）服务
docker run --rm --add-host=host.docker.internal:host-gateway williamyeh/wrk -t64 -c480 -d120s http://host.docker.internal:8180/employeesAsync/2
```



#### application.yml

```yaml
server:
  shutdown: graceful
  port: 8180
  undertow:
    threads:
      io: 4          # 限定4个IO线程，默认为应用识别到的CPU核心数（包括超线程） 
      worker: 8      # 限定8个Worker线程，默认为IO线程乘以16
  
spring:
  datasource:
    url: jdbc:h2:mem:demodb
    username: sa
    password: 
    hikari:
      maximum-pool-size: 20
      minimum-idle: 5
      idle-timeout: 600000
      connection-timeout: 30000
      max-lifetime: 1800000
      auto-commit: true
      pool-name: master      
  lifecycle:
    timeout-per-shutdown-phase: 30s
    
management:
  server:
    port: 8192    # 修改默认管理端口，跟应用端口区别开 
        
  endpoints:  
    web:
      exposure:
        include: 
        - 'health'
        - 'info'
        - 'prometheus'
        - 'metrics'
  endpoint:
    health:
      show-details: always
      probes:
        enabled: true     
```

