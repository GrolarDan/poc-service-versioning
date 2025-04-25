package dmk.poc.anotherworld.service;


import dmk.poc.anotherworld.dto.BookDto;

import java.util.List;

public interface BookService {
    BookDto save(BookDto book);
    List<BookDto> findAll();
}
