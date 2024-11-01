package com.model;

public class Book {
    private int id;
    private String bookName;
    private String author;
    private String gender;
    private float price;
    private Integer bookTypeId;
    private String bookTypeName;
    private String bookDesc;
    public Book() {
        super();
    }
    public Book(int id, String bookName, String author, String gender, float price, Integer bookTypeId, String bookTypeName) {
        super();
        this.id = id;
        this.bookName = bookName;
        this.author = author;
        this.gender = gender;
        this.price = price;
        this.bookTypeId = bookTypeId;
        this.bookTypeName = bookTypeName;
        this.bookDesc = bookName;
    }
    public Book(String bookName, String author, String gender, float price, Integer bookTypeId, String bookDesc) {
        super();
        this.bookName = bookName;
        this.author = author;
        this.gender = gender;
        this.price = price;
        this.bookTypeId = bookTypeId;
        this.bookDesc = bookDesc;
    }

    public Book(String bookName, String author, int bookTypeId) {
        super();
        this.bookName = bookName;
        this.author = author;
        this.bookTypeId = bookTypeId;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getBookName() {
        return bookName;
    }
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
    public String getAuthor() {
        return author;
    }
      public void setAuthor(String author) {
        this.author = author;
      }
      public String getGender() {
        return gender;
      }
      public void setGender(String gender) {
        this.gender = gender;
      }
      public float getPrice() {
        return price;
      }
      public void setPrice(float price) {
        this.price = price;
      }
      public Integer getBookTypeId() {
        return bookTypeId;
      }
      public void setBookTypeId(Integer bookTypeId) {
        this.bookTypeId = bookTypeId;
      }

      public String getBookTypeName() {
        return bookTypeName;
      }
      public void setBookTypeName(String bookTypeName) {
        this.bookTypeName = bookTypeName;
      }
      public String getBookDesc() {
        return bookDesc;
      }
      public void setBookDesc(String bookDesc) {
        this.bookDesc = bookDesc;
      }


      @Override
      public String toString(){
           return  bookName;
      }

}
