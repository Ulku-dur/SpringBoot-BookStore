package com.bookstore.bookstore.controller;

import com.bookstore.bookstore.entity.Book;
import com.bookstore.bookstore.entity.MyBookList;
import com.bookstore.bookstore.service.BookService;
import com.bookstore.bookstore.service.MyBookListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@Controller
public class BookController {
    @Autowired
    private BookService service;
    @Autowired
    private MyBookListService myBookListService;
    @GetMapping("/")
    public String home() {
        return "home";

    }
    @GetMapping("/book_register")
    public String bookRegister() {
        return "bookRegister";
    }
    @GetMapping("/available_books")
    public ModelAndView getAllBook() {
        List<Book> list = service.getAllBook();
//        ModelAndView m = new ModelAndView();
//        m.setViewName("bookList");
//        m.addObject("book", list);
        return new ModelAndView("bookList", "book", list);
    }
    @PostMapping("/save")
    public String addBook(@ModelAttribute Book book) {
        service.save(book);
        return "redirect:/available_books";
    }
    @GetMapping("/my_books")
    public String getMyBooks(Model model) {
        List<MyBookList> list = myBookListService.getAllMyBooks();
        model.addAttribute("book", list);
        return "myBooks";
    }
    @RequestMapping("/mylist/{id}")
    public String getMyList(@PathVariable("id") int id) {
        Book book = service.getBookById(id);
        MyBookList mb = new MyBookList(book.getId(), book.getName(), book.getAuthor(), book.getPrice());
        myBookListService.saveMyBooks(mb);

        return "redirect:/my_books";
    }
    @RequestMapping("/editBook/{id}")
    public String editBook(@PathVariable("id") int id, Model model) {
            Book b = service.getBookById(id);
            model.addAttribute("book",b);
        return "bookEdit";
    }
    @RequestMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        service.deleteById(id);
        return "redirect:/available_books";
    }
}
