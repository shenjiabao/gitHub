package com.jk.service;

import com.jk.dao.BookMapper;
import com.jk.pojo.Book;
import com.jk.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lenovo on 2017/6/27.
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;
  //分页查询
    @Override
    public PageUtil<Book> selectbookList(PageUtil<Book> bookPage) {

        //查询list
        List<Book> bookList = bookMapper.selectBookManyList(bookPage);

        //查询count
        int totalCount = bookMapper.selectBookManyListCount(bookPage);

        bookPage.setList(bookList);

        bookPage.setTotalCount(totalCount);

        return bookPage;
    }
}
