package com.dao;

import com.model.Book;
import com.utils.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookDAO {
//添加图书
    public int addBook(Connection conn, Book book) throws SQLException {
        String sql = "insert into book values(null,?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,book.getBookName());
        ps.setString(2,book.getAuthor());
        ps.setString(3,book.getGender());
        ps.setFloat(4,book.getPrice());
        ps.setInt(5,book.getBookTypeId());
        ps.setString(6,book.getBookDesc());
        return ps.executeUpdate();
    }

//查询图书
    public ResultSet getBook(Connection con, Book book) throws Exception {
        StringBuffer sql = new StringBuffer("select * from book b,booktype bt where b.bookTypeId=bt.id ");
        if(StringUtil.isNotEmpty(book.getBookName())){
        sql.append(" and b.bookName like '%"+book.getBookName()+"%'");
        }
        if(StringUtil.isNotEmpty(book.getAuthor())){
            sql.append(" and b.author like '%"+book.getAuthor()+"%'");
        }
        if(book.getBookTypeId()!=null&&book.getBookTypeId()!=-1){
            sql.append(" and b.bookTypeId="+book.getBookTypeId());
        }
        PreparedStatement ps = con.prepareStatement(sql.toString());
        return ps.executeQuery();
    }
    //删除图书

    public int deleteBook(Connection con, String id) throws Exception {
        String sql = "delete from book where id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,id);
        return ps.executeUpdate();
    }

    //修改图书
    public int updateBook(Connection con, Book book) throws Exception {
        String sql="update book set bookName=?,author=?,gender=?,price=?,bookDesc=?,bookTypeId=? where id=? ";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,book.getBookName());
        ps.setString(2,book.getAuthor());
        ps.setString(3,book.getGender());
        ps.setFloat(4,book.getPrice());
        ps.setString(5,book.getBookDesc());
        ps.setInt(6,book.getBookTypeId());
        ps.setInt(7,book.getId());
        return ps.executeUpdate();
    }

    //指定图书下有没有图书
    public boolean exitsBookByBookTypeId(Connection con,String bookTypeId) throws Exception {
        String sql = "select * from book where bookTypeId=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,bookTypeId);
        ResultSet rs = ps.executeQuery();
        return rs.next();
    }


}
