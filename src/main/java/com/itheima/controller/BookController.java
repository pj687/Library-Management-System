package com.itheima.controller;
import com.github.pagehelper.PageHelper;
import com.itheima.domain.Book;
import com.itheima.domain.Record;
import com.itheima.domain.User;
import com.itheima.pojo.BookDto;
import com.itheima.pojo.ResultData;
import com.itheima.pojo.SearchDto;
import com.itheima.service.BookService;
import com.itheima.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequestMapping("/book")
@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private RecordService recordService;

    //查找新书
    @RequestMapping("/selectNewbooks")
//    public ModelAndView select(){
//        ModelAndView modelAndView=new ModelAndView();
//        modelAndView.setViewName("book_new");
//        modelAndView
//    }
    public String selectNewbooks(Model model){
        Book book=new Book();
        List<Book> bookList=bookService.findNewBook(book);
        model.addAttribute("rows",bookList);
        return "books_new";
    }


    //图书借阅
    @ResponseBody
    @RequestMapping("/borrowBook")
    public ResultData borrowBook(Integer id, @Validated String returnTime, HttpServletRequest request){
        try {
            ResultData<Integer> resultData = new ResultData<Integer>();
//          resultData.setData(updated);
            resultData.setSuccess(true);
            resultData.setMessage("借阅成功！！！");

            Date todayDate = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date returnDate = simpleDateFormat.parse(returnTime);
            if (returnDate.before(todayDate)){
                resultData.setSuccess(false);
                resultData.setMessage("归还日期不能早于现在");
                return resultData;
            }
            Book book = new Book();
            book.setBook_id(id);
            List<Book> books = bookService.find(book);
            Book bookinfo = books.get(0);
            bookinfo.setBook_status("1");
            bookinfo.setBook_returntime(returnTime);
            User userSesson = (User) request.getSession().getAttribute("USER_SESSION");
            bookinfo.setBook_borrower(userSesson.getName());

            String today = simpleDateFormat.format(todayDate);
            bookinfo.setBook_borrowtime(today);

//        图书更新的方法
            Integer updated = bookService.updateBook(bookinfo);

            return resultData;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    //查找图书
    @RequestMapping("/findById")
    @ResponseBody
    public ResultData findById(Integer id) {
        Book book = new Book();
        book.setBook_id(id);
        List<Book> books = bookService.find(book);

        ResultData<Book> resultData = new ResultData<Book>();
        resultData.setData(books.get(0));
        return resultData;
    }

    //分页查询符合条件未下载的图书
    @RequestMapping("/search")
    public String search(Model model, SearchDto searchDto) {
        PageHelper.startPage(1, 5);//limit 1, 5
        Book book = new Book();
        book.setBook_name(searchDto.getName());
        book.setBook_author(searchDto.getAuthor());
        book.setBook_press(searchDto.getPress());
        model.addAttribute("");

        List<Book> books = new ArrayList<>();

        if(!StringUtils.isEmpty(searchDto.getName())  || !StringUtils.isEmpty(searchDto.getAuthor()) ||  !StringUtils.isEmpty(searchDto.getPress()) ){
            books = bookService.find(book);
        }

        model.addAttribute("search", searchDto);
        model.addAttribute("total", books.size());
        model.addAttribute("rows", books);
        model.addAttribute("pageNum", books);
        model.addAttribute("gourl", books);
        return "books";
    }

    //新增图书
    @RequestMapping({"/addBook"})
    @ResponseBody
    public ResultData addBook(BookDto bookDto) {
        Book book = new Book();
        book.setBook_id(bookDto.getId());
        book.setBook_name(bookDto.getName());
        book.setBook_isbn(bookDto.getIsbn());
        book.setBook_author(bookDto.getAuthor());
        book.setBook_pagination(bookDto.getPagination());
        book.setBook_price(bookDto.getPrice());
        book.setBook_status(bookDto.getStatus());
        Integer res = this.bookService.addBook(book);
        ResultData<Book> resultData = new ResultData();
        if (res > 0) {
            resultData.setSuccess(true);
            resultData.setMessage("添加成功！");
        } else {
            resultData.setSuccess(false);
        }

        return resultData;
    }

    //编辑图书信息
    @RequestMapping({"/editBook"})
    @ResponseBody
    public ResultData editBook(BookDto bookDto) {
        Book book = new Book();
        book.setBook_id(bookDto.getId());
        book.setBook_name(bookDto.getName());
        book.setBook_isbn(bookDto.getIsbn());
        book.setBook_author(bookDto.getAuthor());
        book.setBook_pagination(bookDto.getPagination());
        book.setBook_price(bookDto.getPrice());
        book.setBook_status(bookDto.getStatus());
        Integer res = this.bookService.updateBook(book);
        ResultData<Book> resultData = new ResultData();
        if (res > 0) {
            resultData.setSuccess(true);
            resultData.setMessage("编辑成功！");
        } else {
            resultData.setSuccess(false);
        }
        return resultData;
    }

    //当前借阅
    //分页查询当前被借阅且未归还的图书信息
    @RequestMapping({"/searchBorrowed"})
    public String searchBorrowed(Model model) {
        Book book = new Book();
        model.addAttribute("");
        new ArrayList();
        List<Book> books = this.bookService.find(book);
        SearchDto searchDto = new SearchDto();
        model.addAttribute("search", searchDto);
        model.addAttribute("total", books.size());
        model.addAttribute("rows", books);
        model.addAttribute("pageNum", books);
        model.addAttribute("gourl", books);
        return "book_borrowed";
    }

    //归还图书功能
    @RequestMapping({"/returnBook"})
    @ResponseBody
    public ResultData<Book> returnBook(Model model, Integer id) {
        Book book = new Book();
        book.setBook_status("2");
        book.setBook_id(id);
        this.bookService.updateBook(book);
        List<Book> books = this.bookService.find(book);
        Book bookInfo = (Book)books.get(0);
        Date todayDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Record record=new Record();
        record.setRecord_bookisbn(bookInfo.getBook_isbn());
        record.setRecord_bookname(bookInfo.getBook_name());
        record.setRecord_borrower(bookInfo.getBook_borrower());
        record.setRecord_borrowtime(bookInfo.getBook_borrowtime());
        record.setRemandtime(simpleDateFormat.format(todayDate));
        Integer res =recordService.addRecord(record);
        ResultData<Book> resultData = new ResultData();
        if (res > 0) {
            resultData.setSuccess(true);
            resultData.setMessage("归还成功！");
        } else {
            resultData.setSuccess(false);
        }

        return resultData;
    }

    //确认归还功能
    @RequestMapping("/returnConfirm")
    @ResponseBody
    public ResultData<Book> returnConfirm(Model model, Integer id) {

        Book book = new Book();
        book.setBook_status("0");
        book.setBook_id(id);
        book.setBook_borrower("");
        book.setBook_returntime("");
        book.setBook_borrowtime("");
        Integer res = bookService.updateBook(book);


        ResultData<Book> resultData = new ResultData<Book>();
        if (res > 0){
            resultData.setSuccess(true);
            resultData.setMessage("归还成功！");
        }else{
            resultData.setSuccess(false);
        }
        return resultData;
    }

    //借阅记录页面
    //
}
