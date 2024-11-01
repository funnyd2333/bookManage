/*
 * Created by JFormDesigner on Fri Sep 20 01:54:54 CST 2024
 */

package com.view;

import java.awt.event.*;
import com.dao.BookDAO;
import com.dao.BookTypeDAO;
import com.model.Book;
import com.model.BookType;
import com.utils.DbUtil;
import com.utils.StringUtil;

import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author dh
 */
public class BookAddFrm extends JFrame {

    DbUtil dbUtil = new DbUtil();
    BookTypeDAO bookTypeDAO = new BookTypeDAO();
    BookDAO bookDAO = new BookDAO();
//定义初始化图书类别下拉框
    private void fillBookType(){
        Connection con=null;
        BookType bookType=new BookType();
        try{
            con=dbUtil.getConnection();
            ResultSet rs=bookTypeDAO.list(con,new BookType());
            while(rs.next()){
                bookType=new BookType();
                bookType.setId(rs.getInt("id"));
                bookType.setBookTypeName(rs.getString("bookTypeName"));
                this.bookTypeJcb.addItem(bookType);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                dbUtil.closeConnection(con);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }


    public BookAddFrm() {
        initComponents();
        this.fillBookType();
    }
//添加按钮
    private void button1(ActionEvent e) {
        bookAddPerformed(e);
    }
    private void bookAddPerformed(ActionEvent e) {
        String bookName= this.bookNameText.getText();
        String author = this.authorText.getText();
        String price = this.priceText.getText();
        String bookDesc = this.bookDescText.getText();
        if(StringUtil.isEmpty(bookName)){
            JOptionPane.showMessageDialog(null,"图书名称不能为空");
            return;
        }
        if(StringUtil.isEmpty(author)){
            JOptionPane.showMessageDialog(null,"作者名不能为空");
            return;
        }
        if(StringUtil.isEmpty(price)){
            JOptionPane.showMessageDialog(null,"图书价格不能为空");
            return;
        }
        String gender="";
        if(manJrb.isSelected()){
            gender="男";
        }else if(womenJrb.isSelected()){
            gender="女";
        }

        BookType bookType=(BookType)this.bookTypeJcb.getSelectedItem();
        int bookTypeId=bookType.getId();

        Book book=new Book(bookName,author,gender,Float.parseFloat(price),bookTypeId,bookDesc);
        Connection con=null;
        try{
            con=dbUtil.getConnection();
            int result= bookDAO.addBook(con,book);
            if(result==1){
                JOptionPane.showMessageDialog(null,"图书添加成功");
                reSerValue();
            }else{
                JOptionPane.showMessageDialog(null,"图书添加失败");
                return;
            }

        }catch (Exception ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null,"图书添加失败");
        }finally{
            try {
                dbUtil.closeConnection(con);
            } catch (Exception ex1) {
                ex1.printStackTrace();
            }
        }

    }

//重置表单
    private void reSerValue(){
        this.bookNameText.setText("");
        this.authorText.setText("");
        this.priceText.setText("");
        this.bookDescText.setText("");
        this.manJrb.setSelected(true);
        this.womenJrb.setSelected(false);
        if(this.bookTypeJcb.getItemCount()>0){
            this.bookTypeJcb.setSelectedIndex(0);
        }
    }

    private void button2(ActionEvent e) {
        this.reSerValue();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        bookNameText = new JTextField();
        label2 = new JLabel();
        authorText = new JTextField();
        label3 = new JLabel();
        manJrb = new JRadioButton();
        womenJrb = new JRadioButton();
        label4 = new JLabel();
        priceText = new JTextField();
        label5 = new JLabel();
        scrollPane1 = new JScrollPane();
        bookDescText = new JTextArea();
        label6 = new JLabel();
        bookTypeJcb = new JComboBox();
        button1 = new JButton();
        button2 = new JButton();

        //======== this ========
        setTitle("\u56fe\u4e66\u6dfb\u52a0");
        Container contentPane = getContentPane();

        //---- label1 ----
        label1.setText("\u56fe\u4e66\u540d\u79f0:");

        //---- label2 ----
        label2.setText("\u56fe\u4e66\u4f5c\u8005\uff1a");

        //---- label3 ----
        label3.setText("\u4f5c\u8005\u6027\u522b\uff1a");

        //---- manJrb ----
        manJrb.setText("\u7537");
        manJrb.setSelected(true);

        //---- womenJrb ----
        womenJrb.setText("\u5973");

        //---- label4 ----
        label4.setText("\u56fe\u4e66\u4ef7\u683c\uff1a");

        //---- label5 ----
        label5.setText("\u56fe\u4e66\u63cf\u8ff0\uff1a");

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(bookDescText);
        }

        //---- label6 ----
        label6.setText("\u56fe\u4e66\u7c7b\u522b\uff1a");

        //---- button1 ----
        button1.setText("\u6dfb\u52a0");
        button1.addActionListener(e -> button1(e));

        //---- button2 ----
        button2.setText("\u91cd\u7f6e");
        button2.addActionListener(e -> button2(e));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(56, 56, 56)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(button1)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(button2))
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                            .addComponent(label3)
                                            .addGap(18, 18, 18)
                                            .addComponent(manJrb)
                                            .addGap(18, 18, 18)
                                            .addComponent(womenJrb)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(label4))
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                            .addComponent(label1)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(bookNameText, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
                                            .addGap(74, 74, 74)
                                            .addComponent(label2)))
                                    .addGap(18, 18, 18)
                                    .addGroup(contentPaneLayout.createParallelGroup()
                                        .addComponent(authorText, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(priceText, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE))))
                            .addContainerGap(47, Short.MAX_VALUE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(label6, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(bookTypeJcb, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE))
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(label5)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 296, GroupLayout.PREFERRED_SIZE)))
                            .addGap(0, 85, Short.MAX_VALUE))))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(28, 28, 28)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(bookNameText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label1)
                        .addComponent(authorText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label2))
                    .addGap(30, 30, 30)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label3, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                        .addComponent(priceText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(manJrb)
                        .addComponent(womenJrb)
                        .addComponent(label4))
                    .addGap(34, 34, 34)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(label6, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(bookTypeJcb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(27, 27, 27)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(label5)
                        .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(button2)
                        .addComponent(button1))
                    .addGap(44, 44, 44))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JTextField bookNameText;
    private JLabel label2;
    private JTextField authorText;
    private JLabel label3;
    private JRadioButton manJrb;
    private JRadioButton womenJrb;
    private JLabel label4;
    private JTextField priceText;
    private JLabel label5;
    private JScrollPane scrollPane1;
    private JTextArea bookDescText;
    private JLabel label6;
    private JComboBox bookTypeJcb;
    private JButton button1;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
