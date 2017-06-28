package com.jk.dao;

import com.jk.pojo.Book;
import com.jk.util.PageUtil;

import java.math.BigDecimal;
import java.util.List;

public interface BookMapper {
    int deleteByPrimaryKey(BigDecimal bookid);

    int insert(Book record);

    int insertSelective(Book record);

    Book selectByPrimaryKey(BigDecimal bookid);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKey(Book record);
    //分页查询list
    List<Book> selectBookManyList(PageUtil<Book> bookPage);
   //查询总条数
    int selectBookManyListCount(PageUtil<Book> bookPage);
}