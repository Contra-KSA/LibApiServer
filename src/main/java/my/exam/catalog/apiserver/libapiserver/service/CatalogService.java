package my.exam.catalog.apiserver.libapiserver.service;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import my.exam.catalog.apiserver.libapiserver.dto.BookDTO;
import my.exam.catalog.apiserver.libapiserver.entity.BookEntity;
import my.exam.catalog.apiserver.libapiserver.mapper.BookMapper;
import my.exam.catalog.apiserver.libapiserver.repository.AuthorRepository;
import my.exam.catalog.apiserver.libapiserver.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CatalogService {

    @Autowired
    private BookRepository bookRepo;
    @Autowired
    private AuthorRepository authorRepo;
    @Autowired
    private BookMapper bookMapper;

    public CatalogService(BookRepository bookRepo, AuthorRepository authorRepo, BookMapper bookMapper) {
        this.bookRepo = bookRepo;
        this.authorRepo = authorRepo;
        this.bookMapper = bookMapper;
    }

    @Transactional
    public BookDTO create(BookDTO dto){
        BookEntity entity = bookMapper.toEntity(dto);
        bookRepo.save(entity);
        return bookMapper.toDTO(entity);
    }

    public void update(BookDTO dto, Long id){
    }

    public void delete(Long id){
    }

    public List<BookDTO> read(List<Long> idList){
        return null;
    }

    public Optional<BookDTO> read(Long id){
        Optional<BookEntity> optionalEntity = bookRepo.findById(id);
        return optionalEntity.map(bookEntity -> bookMapper.toDTO(bookEntity));
    }

    public List<BookDTO> getAll() {
        return bookMapper.toListDTO(bookRepo.findAll());
    }
}
