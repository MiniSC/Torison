# Torison
毕业设计项目

##1.18
框架搭建
<li>com.torison.dao will put sqls to connect with mysql</li>
<li>com.torison.service will deal with business</li>
<li>com.torison.web will be uesd for webs</li>
<br>
com.torison.Server will be put on dubbo and provide server for send email
</br>

##1.19
###上午
<li>dao层和数据库的链接和逆向工程生成mapper</li>
<li>SpringbootTest进行单元测试</li>

###下午
<li>com.torison.Server中邮件发送接口</li>
<li>使用zookeeper作为注册中心,通过dubbo暴露emailsent接口</li>
接口暴露为了给其他的同学的毕设使用


