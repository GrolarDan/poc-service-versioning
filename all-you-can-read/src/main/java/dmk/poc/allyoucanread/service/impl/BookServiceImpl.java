package dmk.poc.allyoucanread.service.impl;

import dmk.poc.allyoucanread.dto.BookDto;
import dmk.poc.allyoucanread.repository.BookRepository;
import dmk.poc.allyoucanread.service.BookMapper;
import dmk.poc.allyoucanread.service.BookService;
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
    public void save(BookDto book) {
        bookRepository.save(bookMapper.mapToModel(book));
    }

    @Override
    public List<BookDto> findAll() {
        return bookRepository.findAll().stream().map(bookMapper::mapToDto).collect(Collectors.toList());
    }
}
