package dmk.poc.anotherworld.service.impl;

import dmk.poc.anotherworld.dto.BookDto;
import dmk.poc.anotherworld.repository.BookRepository;
import dmk.poc.anotherworld.service.BookMapper;
import dmk.poc.anotherworld.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BookDto save(BookDto book) {
        var savedBook = bookRepository.save(bookMapper.mapToModel(book));
        return bookMapper.mapToDto(savedBook);
    }

    @Override
    public List<BookDto> findAll() {
        return bookRepository.findAll().stream().map(bookMapper::mapToDto).collect(Collectors.toList());
    }
}
