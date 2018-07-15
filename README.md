# seclogin
spring security login register demo

使用spring boot+spring security的登录注册功能。
参考 [Anoyi](https://www.jianshu.com/u/7b7ec6f2db21)、汪云飞《Spring Boot实战》、
[使用 JPA 及 UserDetailsService](https://waylau.gitbooks.io/spring-security-tutorial/docs/jpa-userdetailsservice.html)

## Usage:
使用前请创建数据库，并设置用户名密码
```sql
CREATE DATABASE `seclogin` CHARACTER SET utf8 COLLATE utf8_general_ci;
GRANT ALL ON `vlearncom`.* TO `cj`@`%` IDENTIFIED BY '123';
FLUSH PRIVILEGES;
```

创建数据库后启动程序一次，然后杀掉，执行以下命令：
```sql
insert into sys_role(id,authority) values(1,'ROLE_ADMIN');
insert into sys_role(id,authority) values(2,'ROLE_USER');
```

`ROLE_ADMIN` 和 `ROLE_USER` 若要修改，则需同时修改 WebSecurityConfig.java 的 `hasRole("USER")` 句及 SysUserService.java 的 `save` 函数