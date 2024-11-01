/*
 * Created by JFormDesigner on Thu Sep 19 20:45:22 CST 2024
 */

package com.view;

import com.dao.BookTypeDAO;
import com.model.BookType;
import com.utils.DbUtil;
import com.utils.StringUtil;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author dh
 */


public class BookTypeAddFrame extends JFrame {


    public BookTypeAddFrame() {
        initComponents();
    }


    //图书类别添加
    private void button2(ActionEvent e) {
    addBookClass();
    }
    private void addBookClass()  {

        DbUtil dbUtil=new DbUtil();
        BookTypeDAO bookTypeDAO=new BookTypeDAO();

        String bookClass=this.bookTypeText.getText();
        String bookClassDesc=this.bookTypeDecsText.getText();
        if(StringUtil.isEmpty(bookClass)){
            JOptionPane.showMessageDialog(null,"图书类别不能为空");
            return;
        }
        BookType bookType=new BookType(bookClass,bookClassDesc);
        Connection con=null;
        try {
            con=dbUtil.getConnection();
           int n= BookTypeDAO.AddBookType(con,bookType);
           if(n==1){
               JOptionPane.showMessageDialog(null,"图书类别添加成功");
               reValue();
           }else{
               JOptionPane.showMessageDialog(null,"图书类别添加失败");
           }
        }catch (Exception evt) {
            evt.printStackTrace();
            JOptionPane.showMessageDialog(null,"图书类别添加失败");
        }finally {
            try {
                dbUtil.closeConnection(con);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }


    }


    private void button1(ActionEvent e) {
        reValue();
    }
    private void reValue() {
        this.bookTypeText.setText("");
        this.bookTypeDecsText.setText("");
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        label2 = new JLabel();
        bookTypeText = new JTextField();
        scrollPane1 = new JScrollPane();
        bookTypeDecsText = new JTextArea();
        button1 = new JButton();
        button2 = new JButton();

        //======== this ========
        setVisible(true);
        setTitle("\u6dfb\u52a0\u56fe\u4e66\u7c7b\u522b");
        Container contentPane = getContentPane();

        //---- label1 ----
        label1.setText("\u56fe\u4e66\u7c7b\u522b\u540d\u79f0\uff1a");

        //---- label2 ----
        label2.setText("\u56fe\u4e66\u7c7b\u522b\u63cf\u8ff0\uff1a");

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(bookTypeDecsText);
        }

        //---- button1 ----
        button1.setText("\u91cd\u7f6e");
        button1.addActionListener(e -> button1(e));

        //---- button2 ----
        button2.setText("\u63d0\u4ea4");
        button2.addActionListener(e -> button2(e));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(32, 32, 32)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(label1)
                        .addComponent(label2))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
                        .addComponent(bookTypeText))
                    .addContainerGap(96, Short.MAX_VALUE))
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(114, 114, 114)
                    .addComponent(button1)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 163, Short.MAX_VALUE)
                    .addComponent(button2)
                    .addGap(60, 60, 60))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(67, 67, 67)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label1)
                        .addComponent(bookTypeText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(38, 38, 38)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(label2)
                        .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 116, Short.MAX_VALUE)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(button2)
                        .addComponent(button1))
                    .addGap(20, 20, 20))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JLabel label2;
    private JTextField bookTypeText;
    private JScrollPane scrollPane1;
    private JTextArea bookTypeDecsText;
    private JButton button1;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
