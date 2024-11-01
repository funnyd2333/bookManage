package com.model;

public class BookType {
    private int id;
    private String bookTypeName;
    private String bookTypeDesc;

    public BookType(int id, String bookTypeName, String bookTypeDesc) {
        this.id = id;
        this.bookTypeName = bookTypeName;
        this.bookTypeDesc = bookTypeDesc;
    }

    public BookType() {
        super();
    }

    public BookType(String bookTypeName, String bookTypeDesc) {
        super();
        this.bookTypeName = bookTypeName;
        this.bookTypeDesc = bookTypeDesc;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
       this.id = id;
    }
    public String getBookTypeName() {
        return bookTypeName;
    }
    public void setBookTypeName(String bookTypeName) {
        this.bookTypeName = bookTypeName;
    }
    public String getBookTypeDesc() {
        return bookTypeDesc;
    }
    public void setBookTypeDesc(String bookTypeDesc) {
           this.bookTypeDesc = bookTypeDesc;
    }

    public String toString() {
        return bookTypeName;
    }

}
