/*
 * Created by JFormDesigner on Fri Sep 20 03:35:03 CST 2024
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
import java.util.Vector;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.border.*;
import javax.swing.table.*;

/**
 * @author dh
 */
public class BookManagerFrm extends JFrame {

    DbUtil dbUtil = new DbUtil();
    BookTypeDAO bookTypeDAO = new BookTypeDAO();
    BookDAO bookDAO = new BookDAO();


//初始化下拉框
    private void fillBookType(String type){
        Connection con = null;
        BookType bookType = null;
        try{
            con = dbUtil.getConnection();
            ResultSet rs= bookTypeDAO.list(con,new BookType());

            if("search".equals(type)){
                bookType = new BookType();
                bookType.setBookTypeName("请选择");
                bookType.setId(-1);
                this.s_bookTypeJcb.addItem(bookType);
            }
            while(rs.next()){
                bookType = new BookType();
                bookType.setBookTypeName(rs.getString("bookTypeName"));
                bookType.setId(rs.getInt("id"));
                if("search".equals(type)){
                    this.s_bookTypeJcb.addItem(bookType);
                }else if("modify".equals(type)){
                    this.bookTypeJcb.addItem(bookType);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            try {
                dbUtil.closeConnection(con);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
//初始化表格
    private void fillTable(Book book){
        DefaultTableModel dtm=(DefaultTableModel) bookTable.getModel();
        dtm.setRowCount(0);
        Connection con=null;
        try{
            con=dbUtil.getConnection();
            ResultSet rs= bookDAO.getBook(con,book);
            while(rs.next()){
                Vector v=new Vector();
                v.add(rs.getString("id"));
                v.add(rs.getString("bookName"));
                v.add(rs.getString("author"));
                v.add(rs.getString("gender"));
                v.add(rs.getFloat("price"));
                v.add(rs.getString("bookDesc"));
                v.add(rs.getString("bookTypeName"));
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





    public BookManagerFrm() {
        initComponents();
        this.fillBookType("search");
        this.fillBookType("modify");
        this.fillTable(new Book());
    }
    //查询
    private void button1(ActionEvent e) {
        bookSearch(e);
    }
    private void bookSearch(ActionEvent e) {
        String bookName=this.s_bookNameText.getText();
        String author=this.s_bookAuthorText.getText();
        BookType bookType=(BookType)this.s_bookTypeJcb.getSelectedItem();
        int bookTypeId=bookType.getId();
        Book book=new Book(bookName,author,bookTypeId);
        this.fillTable(book);

    }
//表格点击事件处理
    private void bookTableMousePressed(MouseEvent e) {
        int row=this.bookTable.getSelectedRow();
        this.idText.setText((String) bookTable.getValueAt(row,0));
        this.bookNameText.setText((String) bookTable.getValueAt(row,1));
        this.authorText.setText((String) bookTable.getValueAt(row,2));
        String gender=(String) bookTable.getValueAt(row,3);
        if("男".equals(gender)){
            this.manJrb.setSelected(true);
        }else if("女".equals(gender)){
            this.womanJrb.setSelected(true);
        }
        this.priceText.setText((Float) bookTable.getValueAt(row,4)+"");
        this.bookDescText.setText((String) bookTable.getValueAt(row,5));
        String bookTypeName=(String) this.bookTable.getValueAt(row,6);
        int cnt=this.bookTypeJcb.getItemCount();
        if(cnt==0){
            return;
        }
        for(int i=0;i<cnt;i++){
            BookType item=(BookType) this.bookTypeJcb.getItemAt(i);
            if(item!=null&&item.getBookTypeName().equals(bookTypeName)){
                this.bookTypeJcb.setSelectedIndex(i);
                break;
            }
        }

    }
    //重置
    private void reSetValue(){
        this.idText.setText("");
        this.bookNameText.setText("");
        this.authorText.setText("");
       if(this.bookTypeJcb.getSelectedItem()!=null){
           this.bookTypeJcb.setSelectedIndex(0);
       }
        this.manJrb.setSelected(false);
        this.womanJrb.setSelected(false);
        this.priceText.setText("");
        this.bookDescText.setText("");
        this.bookTypeJcb.setSelectedIndex(0);
    }
//修改按钮
    private void button2(ActionEvent e) {
        updateBookActive(e);
    }
    private void updateBookActive(ActionEvent e) {
        String id=(String) this.idText.getText();
        if(StringUtil.isEmpty(id)){
            JOptionPane.showMessageDialog(null,"请选择要修改的记录");
            return;
        }
        String bookName=(String) this.bookNameText.getText();
        String author=(String) this.authorText.getText();
        String price=(String) this.priceText.getText();
        String bookDesc=(String) this.bookDescText.getText();
        String gender="";
        if(manJrb.isSelected()){
            gender="男";
        }else if(womanJrb.isSelected()){
            gender="女";
        }
        BookType bookType=(BookType) bookTypeJcb.getSelectedItem();
        int bookTypeId= 0;
        if (bookType != null) {
            bookTypeId = bookType.getId();
        }
        Book book=new Book(Integer.parseInt(id),bookName,author,gender,Float.parseFloat(price),bookTypeId,bookDesc);

        Connection con=null;
        try {
            con=dbUtil.getConnection();
            int modifyNum=bookDAO.updateBook(con,book);
            if(modifyNum==1){
                JOptionPane.showMessageDialog(null,"图书修改成功");
                this.reSetValue();
                this.fillTable(new Book());
            }else{
                JOptionPane.showMessageDialog(null,"图书修改失败");
                return;
            }
        }catch(Exception e2){
            e2.printStackTrace();
            JOptionPane.showMessageDialog(null,"图书修改失败");
        }finally{
            try {
                dbUtil.closeConnection(con);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }
//删除按键
    private void button3(ActionEvent e) {
        deleteBookActionPerformed(e);
    }
    private void deleteBookActionPerformed(ActionEvent e) {
        String id = this.idText.getText();
        if(StringUtil.isEmpty(id)){
            JOptionPane.showMessageDialog(null,"请选择要删除的数据");
            return;
        }
        int result=JOptionPane.showConfirmDialog(null,"确定要删除这条记录吗");
        if(result==JOptionPane.OK_OPTION){
            Connection con=null;
            try {
                con= dbUtil.getConnection();
                int deleteNum= bookDAO.deleteBook(con,id);
                if(deleteNum==1){
                    JOptionPane.showMessageDialog(null,"删除成功");
                    this.reSetValue();
                    this.fillTable(new Book());
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
        bookTable = new JTable();
        panel1 = new JPanel();
        label1 = new JLabel();
        s_bookNameText = new JTextField();
        label2 = new JLabel();
        s_bookAuthorText = new JTextField();
        label3 = new JLabel();
        s_bookTypeJcb = new JComboBox();
        button1 = new JButton();
        panel2 = new JPanel();
        label4 = new JLabel();
        idText = new JTextField();
        label5 = new JLabel();
        bookNameText = new JTextField();
        label6 = new JLabel();
        manJrb = new JRadioButton();
        womanJrb = new JRadioButton();
        label7 = new JLabel();
        priceText = new JTextField();
        label8 = new JLabel();
        authorText = new JTextField();
        label9 = new JLabel();
        bookTypeJcb = new JComboBox();
        label10 = new JLabel();
        scrollPane2 = new JScrollPane();
        bookDescText = new JTextArea();
        button2 = new JButton();
        button3 = new JButton();

        //======== this ========
        setTitle("\u56fe\u4e66\u7ba1\u7406");
        Container contentPane = getContentPane();

        //======== scrollPane1 ========
        {

            //---- bookTable ----
            bookTable.setModel(new DefaultTableModel(
                new Object[][] {
                    {null, null, null, null, null, null, null},
                },
                new String[] {
                    "\u7f16\u53f7", "\u56fe\u4e66\u540d\u79f0", "\u56fe\u4e66\u4f5c\u8005", "\u6027\u522b", "\u56fe\u4e66\u4ef7\u683c", "\u56fe\u4e66\u7c7b\u522b", "\u56fe\u4e66\u63cf\u8ff0"
                }
            ) {
                boolean[] columnEditable = new boolean[] {
                    true, true, true, true, true, false, true
                };
                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return columnEditable[columnIndex];
                }
            });
            bookTable.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    bookTableMousePressed(e);
                }
            });
            scrollPane1.setViewportView(bookTable);
        }

        //======== panel1 ========
        {
            panel1.setBorder(new TitledBorder("\u641c\u7d22\u6761\u4ef6"));

            //---- label1 ----
            label1.setText("\u56fe\u4e66\u540d\u79f0\uff1a");

            //---- label2 ----
            label2.setText("\u56fe\u4e66\u4f5c\u8005\uff1a");

            //---- label3 ----
            label3.setText("\u56fe\u4e66\u7c7b\u522b\uff1a");

            //---- button1 ----
            button1.setText("\u67e5\u8be2");
            button1.addActionListener(e -> button1(e));

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(label1)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(s_bookNameText, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(label2)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(s_bookAuthorText, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(label3)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(s_bookTypeJcb, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(button1)
                        .addContainerGap(146, Short.MAX_VALUE))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label1, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                            .addComponent(s_bookNameText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label2)
                            .addComponent(s_bookAuthorText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label3)
                            .addComponent(s_bookTypeJcb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(button1))
                        .addContainerGap(38, Short.MAX_VALUE))
            );
        }

        //======== panel2 ========
        {
            panel2.setBorder(new TitledBorder("\u8868\u5355\u64cd\u4f5c"));

            //---- label4 ----
            label4.setText("\u7f16\u53f7\uff1a");

            //---- idText ----
            idText.setEditable(false);

            //---- label5 ----
            label5.setText("\u56fe\u4e66\u540d\u79f0\uff1a");

            //---- label6 ----
            label6.setText("\u4f5c\u8005\u6027\u522b\uff1a");

            //---- manJrb ----
            manJrb.setText("\u7537");
            manJrb.setSelected(true);

            //---- womanJrb ----
            womanJrb.setText("\u5973");

            //---- label7 ----
            label7.setText("\u4ef7\u683c\uff1a");

            //---- label8 ----
            label8.setText("\u56fe\u4e66\u4f5c\u8005\uff1a");

            //---- label9 ----
            label9.setText("\u56fe\u4e66\u7c7b\u522b\uff1a");

            //---- label10 ----
            label10.setText("\u56fe\u4e66\u63cf\u8ff0\uff1a");

            //======== scrollPane2 ========
            {
                scrollPane2.setViewportView(bookDescText);
            }

            GroupLayout panel2Layout = new GroupLayout(panel2);
            panel2.setLayout(panel2Layout);
            panel2Layout.setHorizontalGroup(
                panel2Layout.createParallelGroup()
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel2Layout.createParallelGroup()
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addGroup(panel2Layout.createSequentialGroup()
                                        .addComponent(label4, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(idText))
                                    .addGroup(panel2Layout.createSequentialGroup()
                                        .addComponent(label7)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(priceText, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)))
                                .addGap(31, 31, 31)
                                .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addComponent(label8)
                                    .addComponent(label5))
                                .addGap(18, 18, 18)
                                .addGroup(panel2Layout.createParallelGroup()
                                    .addComponent(bookNameText, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(authorText))
                                .addGap(33, 33, 33)
                                .addGroup(panel2Layout.createParallelGroup()
                                    .addGroup(panel2Layout.createSequentialGroup()
                                        .addComponent(label6)
                                        .addGap(33, 33, 33)
                                        .addComponent(manJrb)
                                        .addGap(44, 44, 44)
                                        .addComponent(womanJrb))
                                    .addGroup(panel2Layout.createSequentialGroup()
                                        .addComponent(label9)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(bookTypeJcb, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(116, Short.MAX_VALUE))
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addComponent(label10)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 536, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(115, Short.MAX_VALUE))))
            );
            panel2Layout.setVerticalGroup(
                panel2Layout.createParallelGroup()
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label4)
                            .addComponent(womanJrb)
                            .addComponent(manJrb)
                            .addComponent(label6)
                            .addComponent(bookNameText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label5)
                            .addComponent(idText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label7, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label9)
                            .addComponent(bookTypeJcb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(priceText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label8)
                            .addComponent(authorText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel2Layout.createParallelGroup()
                            .addComponent(label10)
                            .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(43, Short.MAX_VALUE))
            );
        }

        //---- button2 ----
        button2.setText("\u4fee\u6539");
        button2.addActionListener(e -> button2(e));

        //---- button3 ----
        button3.setText("\u5220\u9664");
        button3.addActionListener(e -> button3(e));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(44, 44, 44)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 739, Short.MAX_VALUE)
                        .addComponent(panel2, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(0, 0, Short.MAX_VALUE))
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(111, 111, 111)
                    .addComponent(button2)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 434, Short.MAX_VALUE)
                    .addComponent(button3)
                    .addGap(112, 112, 112))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(15, 15, 15)
                    .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(27, 27, 27)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(panel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(button2)
                        .addComponent(button3))
                    .addContainerGap(27, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JScrollPane scrollPane1;
    private JTable bookTable;
    private JPanel panel1;
    private JLabel label1;
    private JTextField s_bookNameText;
    private JLabel label2;
    private JTextField s_bookAuthorText;
    private JLabel label3;
    private JComboBox s_bookTypeJcb;
    private JButton button1;
    private JPanel panel2;
    private JLabel label4;
    private JTextField idText;
    private JLabel label5;
    private JTextField bookNameText;
    private JLabel label6;
    private JRadioButton manJrb;
    private JRadioButton womanJrb;
    private JLabel label7;
    private JTextField priceText;
    private JLabel label8;
    private JTextField authorText;
    private JLabel label9;
    private JComboBox bookTypeJcb;
    private JLabel label10;
    private JScrollPane scrollPane2;
    private JTextArea bookDescText;
    private JButton button2;
    private JButton button3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
