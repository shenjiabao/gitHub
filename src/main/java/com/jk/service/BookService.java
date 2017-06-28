package com.jk.service;

import com.jk.pojo.Book;
import com.jk.util.PageUtil;

/**
 * Created by lenovo on 2017/6/27.
 */
public interface BookService {
    PageUtil<Book> selectbookList(PageUtil<Book> bookPage);
}
