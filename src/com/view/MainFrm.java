/*
 * Created by JFormDesigner on Tue Sep 17 23:57:26 CST 2024
 */

package com.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author dh
 */
public class MainFrm extends JFrame {
    public MainFrm() {
        initComponents();
    }


    //  退出键设置
    private void menuItem6(ActionEvent e) {
        exitManagerSystem(e);
    }
    private void exitManagerSystem(ActionEvent e) {
        int result=JOptionPane.showConfirmDialog(null,"是否退出系统");
        if(result==JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }
    //技术栈选项设置
    private void menuItemAbout(ActionEvent e) {
        AboutMe.setVisible(true);
    }

    // 添加图书类选项
    private void menuItem2(ActionEvent e) {
        addBookClassView(e);
    }
    private void addBookClassView(ActionEvent e) {
        new BookTypeAddFrame().setVisible(true);
    }
    //图书类别管理
    private void menuItem3(ActionEvent e) {
       new BookTypeManageFrm().setVisible(true);
    }

    private void menuItem4(ActionEvent e) {
        new BookAddFrm().setVisible(true);
    }

    private void menuItem5(ActionEvent e) {
        new BookManagerFrm().setVisible(true);
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        menu2 = new JMenu();
        menuItem2 = new JMenuItem();
        menuItem3 = new JMenuItem();
        menu4 = new JMenu();
        menuItem4 = new JMenuItem();
        menuItem5 = new JMenuItem();
        menuItem6 = new JMenuItem();
        menu3 = new JMenu();
        menuItem1 = new JMenuItem();
        table = new JDesktopPane();
        AboutMe = new JInternalFrame();
        label1 = new JLabel();
        label2 = new JLabel();

        //======== this ========
        setTitle("\u56fe\u4e66\u7ba1\u7406\u7cfb\u7edf\u4e3b\u754c\u9762");
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== menuBar1 ========
        {

            //======== menu1 ========
            {
                menu1.setText("\u57fa\u672c\u6570\u636e\u7ef4\u62a4");

                //======== menu2 ========
                {
                    menu2.setText("\u56fe\u4e66\u7c7b\u522b\u7ba1\u7406");

                    //---- menuItem2 ----
                    menuItem2.setText("\u56fe\u4e66\u7c7b\u522b\u6dfb\u52a0");
                    menuItem2.addActionListener(e -> menuItem2(e));
                    menu2.add(menuItem2);

                    //---- menuItem3 ----
                    menuItem3.setText("\u56fe\u4e66\u7c7b\u522b\u7ef4\u62a4");
                    menuItem3.addActionListener(e -> menuItem3(e));
                    menu2.add(menuItem3);
                }
                menu1.add(menu2);

                //======== menu4 ========
                {
                    menu4.setText("\u56fe\u4e66\u7ba1\u7406");

                    //---- menuItem4 ----
                    menuItem4.setText("\u56fe\u4e66\u6dfb\u52a0");
                    menuItem4.addActionListener(e -> menuItem4(e));
                    menu4.add(menuItem4);

                    //---- menuItem5 ----
                    menuItem5.setText("\u56fe\u4e66\u7ef4\u62a4");
                    menuItem5.addActionListener(e -> menuItem5(e));
                    menu4.add(menuItem5);
                }
                menu1.add(menu4);

                //---- menuItem6 ----
                menuItem6.setText("\u9000\u51fa\u7cfb\u7edf");
                menuItem6.addActionListener(e -> menuItem6(e));
                menu1.add(menuItem6);
            }
            menuBar1.add(menu1);

            //======== menu3 ========
            {
                menu3.setText("\u5173\u4e8e\u9879\u76ee");

                //---- menuItem1 ----
                menuItem1.setText("\u6280\u672f\u6808");
                menuItem1.addActionListener(e -> menuItemAbout(e));
                menu3.add(menuItem1);
            }
            menuBar1.add(menu3);
        }
        setJMenuBar(menuBar1);
        contentPane.add(table, BorderLayout.NORTH);

        //======== AboutMe ========
        {
            AboutMe.setVisible(true);
            AboutMe.setIconifiable(true);
            AboutMe.setClosable(true);
            AboutMe.setBackground(Color.lightGray);
            AboutMe.setTitle("\u6280\u672f\u6808");
            Container AboutMeContentPane = AboutMe.getContentPane();

            //---- label1 ----
            label1.setBackground(new Color(0x6666ff));
            label1.setIcon(new ImageIcon(getClass().getResource("/images/mysql.png")));

            //---- label2 ----
            label2.setIcon(new ImageIcon(getClass().getResource("/images/java.png")));
            label2.setText("java");
            label2.setBackground(new Color(0x33ff00));

            GroupLayout AboutMeContentPaneLayout = new GroupLayout(AboutMeContentPane);
            AboutMeContentPane.setLayout(AboutMeContentPaneLayout);
            AboutMeContentPaneLayout.setHorizontalGroup(
                AboutMeContentPaneLayout.createParallelGroup()
                    .addGroup(AboutMeContentPaneLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                        .addComponent(label2, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
            );
            AboutMeContentPaneLayout.setVerticalGroup(
                AboutMeContentPaneLayout.createParallelGroup()
                    .addGroup(AboutMeContentPaneLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(AboutMeContentPaneLayout.createParallelGroup()
                            .addComponent(label2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(AboutMeContentPaneLayout.createSequentialGroup()
                                .addComponent(label1, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap(208, Short.MAX_VALUE))
            );
        }
        contentPane.add(AboutMe, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JMenuBar menuBar1;
    private JMenu menu1;
    private JMenu menu2;
    private JMenuItem menuItem2;
    private JMenuItem menuItem3;
    private JMenu menu4;
    private JMenuItem menuItem4;
    private JMenuItem menuItem5;
    private JMenuItem menuItem6;
    private JMenu menu3;
    private JMenuItem menuItem1;
    private JDesktopPane table;
    private JInternalFrame AboutMe;
    private JLabel label1;
    private JLabel label2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on




}
