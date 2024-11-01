package com.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import static java.lang.Class.forName;

//数据库工具
public class DbUtil {
    private String db_url="jdbc:mysql://localhost:3306/db_book";
    private String db_username="root";
    private String db_password="123456";
    private String jdbcName="com.mysql.jdbc.Driver";
    //连接数据库
    public Connection getConnection()throws Exception{
        Class.forName(jdbcName);
        return DriverManager.getConnection(db_url,db_username,db_password);
    }
    //断开连接
    public void closeConnection(Connection con)throws Exception{
        if (con != null){
            con.close();
        }
    }


}

