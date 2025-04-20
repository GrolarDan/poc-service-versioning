package dmk.poc.allyoucanread.service;

import dmk.poc.allyoucanread.dto.BookDto;

import java.util.List;

public interface BookService {
    void save(BookDto book);
    List<BookDto> findAll();
}
