package dmk.poc.anotherworld.controller;

import dmk.poc.anotherworld.dto.BookDto;
import dmk.poc.anotherworld.service.BookService;
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
