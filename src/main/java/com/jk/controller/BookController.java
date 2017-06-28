package com.jk.controller;

import com.jk.pojo.Book;
import com.jk.service.BookService;
import com.jk.util.DataGridJson;
import com.jk.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lenovo on 2017/6/27.
 */
@Controller
@RequestMapping("book")
public class BookController {
    @Autowired
    private BookService bookService;

    //分页+条件查询
    @RequestMapping(value = "selectbookList", method = RequestMethod.POST)
    @ResponseBody
    public DataGridJson selectbookList(int page, int rows, PageUtil<Book> bookPage) {

        bookPage.setCpage(page);
        bookPage.setPageSize(rows);

        bookPage = bookService.selectbookList(bookPage);

        DataGridJson dg = new DataGridJson();

        dg.setTotal(bookPage.getTotalCount());

        dg.setRows(bookPage.getList());

        return dg;
    }
    /**
     * tiaozhaun
     * @return
     */
    @RequestMapping(value = "tiao",method = RequestMethod.GET)
    public  String tiao(){
        return "book/bookList";
    }


}
