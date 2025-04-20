package dmk.poc.allyoucanread.controller;

import dmk.poc.allyoucanread.dto.BookDto;
import dmk.poc.allyoucanread.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @ModelAttribute("books")
    public List<BookDto> books() {
        return bookService.findAll();
    }

    @RequestMapping({"/books"})
    public String showBooks() {
        return "books";
    }
}
