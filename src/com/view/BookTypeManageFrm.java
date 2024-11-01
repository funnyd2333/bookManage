/*
 * Created by JFormDesigner on Thu Sep 19 21:53:10 CST 2024
 */

package com.view;

import java.awt.event.*;
import javax.swing.border.*;

import com.dao.BookDAO;
import com.dao.BookTypeDAO;
import com.model.BookType;
import com.utils.DbUtil;
import com.utils.StringUtil;

import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.table.*;

/**
 * @author dh
 */
public class BookTypeManageFrm extends JFrame {
    public BookTypeManageFrm() {
        initComponents();
        this.fillTable(new BookType());
    }

    //初始化表格
    private void fillTable(BookType bookType) {
        DbUtil dbUtil=new DbUtil();
        BookTypeDAO bookTypeDAO=new BookTypeDAO();
        DefaultTableModel dtm=(DefaultTableModel) bookTypeTable.getModel();
        dtm.setRowCount(0);
        Connection con=null;
        try{
            con=dbUtil.getConnection();
            ResultSet rs= bookTypeDAO.list(con,bookType);
            while(rs.next()){
                Vector v=new Vector();
                v.add(rs.getString("id"));
                v.add(rs.getString("bookTypeName"));
                v.add(rs.getString("bookTypeDesc"));
                dtm.addRow(v);
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

//查询按钮事件
    private void button1(ActionEvent e) {
        bookTypeSearchActionPerformed(e);
    }
    private void bookTypeSearchActionPerformed(ActionEvent e) {
        String s_BookTypeName = this.searchBookClassText.getText();
        BookType bookType = new BookType();
        bookType.setBookTypeName(s_BookTypeName);
        this.fillTable(bookType);
    }
//表格行点击事件处理
    private void bookTypeTableMousePressed(MouseEvent e) {
        int row = bookTypeTable.getSelectedRow();
        bookTypeIdText.setText((String)bookTypeTable.getValueAt(row, 0));
        bookTypeNameText.setText((String)bookTypeTable.getValueAt(row, 1));
        bookTypeDescText.setText((String)bookTypeTable.getValueAt(row, 2));
    }
//修改按键
    private void button2(ActionEvent e) {
    bookTypeUpdate(e);
    }
    private void bookTypeUpdate(ActionEvent e) {

        DbUtil dbUtil=new DbUtil();
        BookTypeDAO bookTypeDAO=new BookTypeDAO();
        String id = bookTypeIdText.getText();
        String bookTypeName = bookTypeNameText.getText();
        String bookTypeDesc = bookTypeDescText.getText();

        if(StringUtil.isEmpty(id)){
            JOptionPane.showMessageDialog(null,"请选择要修改的记录");
            return;
        }
        BookType bookType = new BookType(Integer.parseInt(id),bookTypeName,bookTypeDesc);
        Connection con=null;
        try {
                con=dbUtil.getConnection();
                int result= JOptionPane.showConfirmDialog(null,"请确定是否修改");
                if(result==JOptionPane.OK_OPTION){
                    int modifyNum = bookTypeDAO.updateBookType(con,bookType);
                    if(modifyNum==1){
                        JOptionPane.showMessageDialog(null,"修改成功");
                        this.reSetValue();
                        this.fillTable(new BookType());
                    }else{
                        JOptionPane.showMessageDialog(null,"修改失败了..");
                    }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally{
            try {
                dbUtil.closeConnection(con);
            } catch (Exception evt) {
                throw new RuntimeException(evt);
            }
        }

    }

    //重置表单操作界面数据
    private void reSetValue(){
        this.bookTypeIdText.setText("");
        this.bookTypeNameText.setText("");
        this.bookTypeDescText.setText("");
    }

    // 删除按键
    private void button3(ActionEvent e) {
        bookTypeDelete(e);
    }
    private void  bookTypeDelete(ActionEvent e) {
        DbUtil dbUtil=new DbUtil();
        BookTypeDAO bookTypeDAO=new BookTypeDAO();
        BookDAO bookDAO=new BookDAO();
        String id = bookTypeIdText.getText();
        if(StringUtil.isEmpty(id)){
            JOptionPane.showMessageDialog(null,"请选择要删除的数据");
            return;
        }
        int result=JOptionPane.showConfirmDialog(null,"确定要删除这条记录吗");
        if(result==JOptionPane.OK_OPTION){
            Connection con=null;
            try {
                con= dbUtil.getConnection();
                boolean flag= bookDAO.exitsBookByBookTypeId(con,id);
                if(flag){
                    JOptionPane.showMessageDialog(null,"当前图书类下有书籍，不能删除此类别");
                    return;
                }
               int deleteNum= bookTypeDAO.deleteBookType(con,id);
                if(deleteNum==1){
                    JOptionPane.showMessageDialog(null,"删除成功");
                    this.reSetValue();
                    this.fillTable(new BookType());
                }else{
                    JOptionPane.showMessageDialog(null,"哎呀，删除出了点问题");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }finally{
                try {
                    dbUtil.closeConnection(con);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        scrollPane1 = new JScrollPane();
        bookTypeTable = new JTable();
        label1 = new JLabel();
        searchBookClassText = new JTextField();
        button1 = new JButton();
        panel1 = new JPanel();
        label2 = new JLabel();
        bookTypeIdText = new JTextField();
        label3 = new JLabel();
        label4 = new JLabel();
        bookTypeNameText = new JTextField();
        textArea1 = new JTextArea();
        scrollPane2 = new JScrollPane();
        bookTypeDescText = new JTextArea();
        button2 = new JButton();
        button3 = new JButton();

        //======== this ========
        setVisible(true);
        setTitle("\u56fe\u4e66\u7c7b\u522b\u7ba1\u7406");
        setForeground(SystemColor.text);
        Container contentPane = getContentPane();

        //======== scrollPane1 ========
        {

            //---- bookTypeTable ----
            bookTypeTable.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                    "\u7f16\u53f7", "\u56fe\u4e66\u7c7b\u522b\u540d\u79f0", " \u56fe\u4e66\u7c7b\u522b\u63cf\u8ff0"
                }
            ) {
                boolean[] columnEditable = new boolean[] {
                    false, true, true
                };
                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return columnEditable[columnIndex];
                }
            });
            bookTypeTable.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    bookTypeTableMousePressed(e);
                }
            });
            scrollPane1.setViewportView(bookTypeTable);
        }

        //---- label1 ----
        label1.setText("\u56fe\u4e66\u7c7b\u522b\u540d\u79f0\uff1a");

        //---- button1 ----
        button1.setText("\u67e5\u8be2");
        button1.addActionListener(e -> button1(e));

        //======== panel1 ========
        {
            panel1.setBorder(new TitledBorder("\u8868\u5355\u64cd\u4f5c"));

            //---- label2 ----
            label2.setText("\u7f16\u53f7\uff1a");

            //---- bookTypeIdText ----
            bookTypeIdText.setEditable(false);

            //---- label3 ----
            label3.setText("\u56fe\u4e66\u7c7b\u522b\u540d\u79f0\uff1a");

            //---- label4 ----
            label4.setText("\u63cf\u8ff0\uff1a");

            //======== scrollPane2 ========
            {
                scrollPane2.setViewportView(bookTypeDescText);
            }

            //---- button2 ----
            button2.setText("\u4fee\u6539");
            button2.addActionListener(e -> button2(e));

            //---- button3 ----
            button3.setText("\u5220\u9664");
            button3.addActionListener(e -> button3(e));

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addComponent(button2)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 141, Short.MAX_VALUE)
                        .addComponent(button3)
                        .addGap(132, 132, 132))
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(label2)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(bookTypeIdText, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46)
                                .addComponent(label3)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bookTypeNameText, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addGap(41, 41, 41)
                                        .addComponent(textArea1, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE))
                                    .addComponent(label4))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 413, GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(49, Short.MAX_VALUE))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label3)
                            .addComponent(label2)
                            .addComponent(bookTypeNameText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(bookTypeIdText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(label4)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textArea1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel1Layout.createParallelGroup()
                            .addComponent(button2)
                            .addComponent(button3)))
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addContainerGap(46, Short.MAX_VALUE)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGap(29, 29, 29))
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addComponent(label1)
                            .addGap(18, 18, 18)
                            .addComponent(searchBookClassText, GroupLayout.PREFERRED_SIZE, 234, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(button1)
                            .addGap(122, 122, 122))
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 389, GroupLayout.PREFERRED_SIZE)
                            .addGap(128, 128, 128))))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label1)
                        .addComponent(searchBookClassText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(button1))
                    .addGap(18, 18, 18)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
                    .addGap(27, 27, 27)
                    .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(25, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JScrollPane scrollPane1;
    private JTable bookTypeTable;
    private JLabel label1;
    private JTextField searchBookClassText;
    private JButton button1;
    private JPanel panel1;
    private JLabel label2;
    private JTextField bookTypeIdText;
    private JLabel label3;
    private JLabel label4;
    private JTextField bookTypeNameText;
    private JTextArea textArea1;
    private JScrollPane scrollPane2;
    private JTextArea bookTypeDescText;
    private JButton button2;
    private JButton button3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on


}
