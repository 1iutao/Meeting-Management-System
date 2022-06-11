# Meeting-Management-System based on SSM


**技术栈**	
  
·JDK17  

·开发工具：IDEA    

·前端：HTML、CSS     

·后端技术：spring5.3.2、mybatis3.5.6、springmvc5.3.2、freemarker2.3.3       

·数据库：Mysql 8.0  



    
     *这个简单项目的一些问题和解释*        
     
     
*一、登录验证*

未设置密码验证，仅设置了用户存在与否、用户状态校验。

缺点：不符合企业级Java开发，数据不安全。

解决方法：

1.Token验证

请求通过在请求头中携带token发送登录请求后，通过在后端查询数据库验证合法性（用户表和token关联，并设置过期时间）。

2.Cookie-Session认证

web场景下让前端用local stroage存储cookie，APP中使用客户端数据库存储，这样就实现了跨域，并避免了CSRF。

1）用户输入id、password登录；

2）服务端经过验证，将认证信息构造好的数据结构存储到Redis中，将key返回到客户端；

3）客户端拿到key存储到local stroage或本地数据库；

4）下次客户端再请求的时候把key附加到header或者请求体中；

5）服务端根据获取的key到Redis获取查询相关value得到认证信息。



*二、注册：*

在注册时获取数据库用户信息，通过if语句判断数据库中用户状态，如果是否为1若成功则成功返回登录页面；若失败则再次返回注册页面。

在两次填写密码时在前端使用了check方法检验两次输入是否一致，若不一致则提示error。



*三、Ajax*

工作原理：客户端发送请求，请求交给Ajax，Ajax把请求交给服务，服务器进行service处理，服务器相应数据交给Ajax对象，Ajax对象接收数据，由js把数据写到页面。

![img](https://pics3.baidu.com/feed/7a899e510fb30f245b56517d11d0bc4aac4b034a.jpeg?token=284651c2553b940510a6b34e1a4107b1)



*四、Mybatis*

持久层框架，免除jdbc代码及设置参数和获取结果集和获取结果集的工作。

通过XML配置或者注解来配置和映射原始类型、接口和Java对象为数据库中的记录。本项目配置文件在map层。

只用简单配置一下数据源和mybatis，它就能帮我们完成数据库的很多工作。

<!--    配置数据源-->
    <bean class="com.alibaba.druid.pool.DruidDataSource" id="dataSource">
        <property name="username" value="${db.username}"/>
        <property name="password" value="${db.password}"/>
        <property name="url" value="${db.url}"/>
    </bean>

<!--    配置mybatis,有两个bean-->
    <bean class="org.mybatis.spring.SqlSessionFactoryBean" id="sqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource"/>
        <property name="typeAliasesPackage" value="org.mm.meetingmanage.model"/>    <!--放实体类的地方-->
        <property name="mapperLocations">
            <value>
                classpath*:org/mm/meetingmanage/mapper/*.xml
            </value>
        </property>
    </bean>
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer" id="mapperScannerConfigurer">
        <property name="sqlSessionFactoryBeanName" value="sqlSessionFactoryBean"/>
       <property name="basePackage" value="org.mm.meetingmanage.mapper"/>     <!-- 接口文件路径-->
    </bean>


*五、AOP*

本项目引入了AspectJ依赖，主要使用了事务的功能，降低了耦合性。

先配置事务，主要是四个方法：简单的CRUD。

再配置AOP：通过AOP将CRUD定义为一个切面，整个service层的所有类都可以灵活切入，大大提升了开发效率。

<!--    事务配置-->
    <bean class="org.springframework.jdbc.datasource.DataSourceTransactionManager" id="dataSourceTransactionManager">
        <property name="dataSource" ref="dataSource"/>
    </bean>

    <tx:advice id="txAdvice" transaction-manager="dataSourceTransactionManager" >
        <tx:attributes>
            <tx:method name="add*"/>
            <tx:method name="insert*"/>
            <tx:method name="update*"/>
            <tx:method name="delete*"/>
        </tx:attributes>
    </tx:advice>

<!--    配置aop 事务-->
    <aop:config>
<!--        第一个*代表所有的返回值类型；第二个*代表所有的类*第三个*代表所有的方法；..代表所有的参数  -->
        <aop:pointcut id="pc1" expression="execution(* org.mm.meetingmanage.service.*.*(..))"/>
        <aop:advisor advice-ref="txAdvice" pointcut-ref="pc1"/>
    </aop:config>

</beans>
