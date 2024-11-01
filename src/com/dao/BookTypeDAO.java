package com.dao;

import com.model.BookType;
import com.utils.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//图书类别DAO类
public class BookTypeDAO {
    public static int AddBookType(Connection con, BookType bt)throws Exception {
        String sql="insert into booktype values(null,?,?)";
        PreparedStatement pstmt= con.prepareStatement(sql);
        pstmt.setString(1, bt.getBookTypeName());
        pstmt.setString(2, bt.getBookTypeDesc());

        return pstmt.executeUpdate();
    }

    //查询图书类别
    public ResultSet list(Connection con,BookType bookType)throws Exception {
        StringBuffer sql = new StringBuffer("select * from booktype");
        if (StringUtil.isNotEmpty(bookType.getBookTypeName())) {
            sql.append(" and bookTypeName like '%"+bookType.getBookTypeName()+"%'");
        }

        PreparedStatement pstmt= con.prepareStatement(sql.toString().replaceFirst("and","where"));
        return pstmt.executeQuery();
    }

    //删除图书类别

    public int deleteBookType(Connection con,String id)throws Exception {
        String sql="delete from booktype where id=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, id);
        return pstmt.executeUpdate();
    }


    //更新图书类别
    public int updateBookType(Connection con,BookType bt)throws Exception {
        String sql="update booktype set bookTypename=?,bookTypeDesc=? where id=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, bt.getBookTypeName());
        pstmt.setString(2, bt.getBookTypeDesc());
        pstmt.setInt(3,bt.getId());
        return pstmt.executeUpdate();
    }


}
