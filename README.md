# bookManage
java,大二下实训作业

## 项目简介：
一个简单的java GUI项目，实现了图书管理员的登录，和图书类别和图书的增删改查。


## 技术栈：
java，swing（Jframedesigner）,JDBC,mysql，


## 项目的亮点（个人感觉比较好的点）
1.在图书类别管理和图书管理界面，表单查询后的记录可以直接点击，并且点击的数据会自动填充到下方表单操作的界面，方便信息的维护（MousePressed）；
2.数据库的模糊查询，（之前只是学习了数据库，没有试着在开发语言里使用，第一次写CRUD，个人感觉完成度还不错）
3.项目分包，代码封装，比较好加功能，扩展性还不错（虽然是个单体小项目，但是想以后加功能会方便一些）


## 写代码困扰我的点
1.调试复杂的逻辑错误：这个项目基本都是边学边写的，JDBC、swing, 有些函数用的不熟练，闹了点笑话，调试的时候发现一直没有出现我期待的值，又感觉没有什么逻辑问题，重新看了库函数的源码，才发现出了一个用错函数的低级错误。
2.模糊查询实现：使用StringBuffer来构建基础的SQL查询语句，用字符拼接的方式来完善sql语句，PreparedStatement来执行构建好的SQL语句。




## 个人收获
1.代码的调试逐渐熟练
2.代码规范、分包，让代码修改维护变得简单
3.熟悉数据库的操作


