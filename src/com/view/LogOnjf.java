/*
 * Created by JFormDesigner on Tue Sep 17 16:09:27 CST 2024
 */

package com.view;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import javax.swing.*;

import com.dao.UserDAO;
import com.formdev.flatlaf.extras.*;
import com.model.User;
import com.utils.DbUtil;
import com.utils.StringUtil;

/**
 * @author Lenovo
 */
public class LogOnjf extends JFrame {
    //实例化对象
    private DbUtil dbUtil = new DbUtil();
    private UserDAO userDao = new UserDAO();

    public LogOnjf() {
        initComponents();
    }
    //重置按钮
    private void button2(ActionEvent e) {
         resetValueAction(e);
    }
    //重置
    private void resetValueAction(ActionEvent e) {
            this.usernameTxt.setText("");
            this.passwordTxt.setText("");
    }
    //登录按键
    private void button1(ActionEvent e) {
        loginAction(e);
    }
    //登录事件处理
    private void loginAction(ActionEvent e) {
        String username = this.usernameTxt.getText();
        String password = new String(this.passwordTxt.getPassword());
        if(StringUtil.isEmpty(username) || StringUtil.isEmpty(password)) {
            JOptionPane.showMessageDialog(null,"用户名或者密码不能为空");
            return;
        }
        User user = new User(username, password);
        Connection con=null;
        try{
            con=dbUtil.getConnection();
            User curruntUser =userDao.login(con,user);
            if(curruntUser != null) {
                dispose();
                new MainFrm().setVisible(true);
            }else{
                JOptionPane.showMessageDialog(null,"账号或密码错误");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            try {
                dbUtil.closeConnection(con);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        label2 = new JLabel();
        usernameTxt = new JTextField();
        label3 = new JLabel();
        button1 = new JButton();
        button2 = new JButton();
        passwordTxt = new JPasswordField();

        //======== this ========
        setTitle("\u7ba1\u7406\u5458\u767b\u5f55");
        Container contentPane = getContentPane();

        //---- label1 ----
        label1.setText("\u56fe\u4e66\u7ba1\u7406\u7cfb\u7edf");
        label1.setIcon(new ImageIcon(getClass().getResource("/images/book.png")));
        label1.setFont(new Font("\u5b8b\u4f53", Font.BOLD, 20));

        //---- label2 ----
        label2.setText("\u7528\u6237");
        label2.setIcon(new ImageIcon(getClass().getResource("/images/user.png")));

        //---- label3 ----
        label3.setText("\u5bc6\u7801");
        label3.setIcon(new ImageIcon(getClass().getResource("/images/password.png")));

        //---- button1 ----
        button1.setText("\u767b\u5f55");
        button1.addActionListener(e -> button1(e));

        //---- button2 ----
        button2.setText("\u91cd\u7f6e");
        button2.addActionListener(e -> button2(e));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(45, 45, 45)
                    .addComponent(label1)
                    .addGap(0, 0, Short.MAX_VALUE))
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addContainerGap(83, Short.MAX_VALUE)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(label3)
                                .addComponent(label2))
                            .addGap(38, 38, 38)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(usernameTxt, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                                .addComponent(passwordTxt, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
                            .addGap(121, 121, 121))
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addComponent(button1)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(button2)
                            .addGap(74, 74, 74))))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(44, 44, 44)
                    .addComponent(label1)
                    .addGap(26, 26, 26)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label2)
                        .addComponent(usernameTxt, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
                    .addGap(31, 31, 31)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label3)
                        .addComponent(passwordTxt, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(button2)
                        .addComponent(button1))
                    .addGap(19, 19, 19))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JLabel label2;
    private JTextField usernameTxt;
    private JLabel label3;
    private JButton button1;
    private JButton button2;
    private JPasswordField passwordTxt;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
