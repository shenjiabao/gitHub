package com.jk.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Book {
    private BigDecimal bookid;

    private String bookname;

    private Short bookprice;

    private Date bookdate;

    private String bookimg;





    public BigDecimal getBookid() {
        return bookid;
    }

    public void setBookid(BigDecimal bookid) {
        this.bookid = bookid;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname == null ? null : bookname.trim();
    }

    public Short getBookprice() {
        return bookprice;
    }

    public void setBookprice(Short bookprice) {
        this.bookprice = bookprice;
    }

    public Date getBookdate() {
        return bookdate;
    }

    public void setBookdate(Date bookdate) {
        this.bookdate = bookdate;
    }

    public String getBookimg() {
        return bookimg;
    }

    public void setBookimg(String bookimg) {
        this.bookimg = bookimg == null ? null : bookimg.trim();
    }
}