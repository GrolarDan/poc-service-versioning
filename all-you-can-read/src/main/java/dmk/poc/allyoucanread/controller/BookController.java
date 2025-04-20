package dmk.poc.allyoucanread.controller;

import dmk.poc.allyoucanread.dto.BookDto;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final Faker faker;

     @ModelAttribute("books")
     public List<BookDto> books() {
         List<BookDto> books = new ArrayList<>();
         for (int i = 0; i < 10; i++) {
             var book = faker.book();
             books.add(new BookDto(faker.code().isbn13(), book.title(), book.author(), book.publisher(), book.genre(), faker.timeAndDate().past().toString()));
         }
         return books;
     }

    @RequestMapping({"/books"})
    public String showBooks() {
        return "books";
    }
}
