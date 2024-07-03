package my.exam.catalog.apiserver.libapiserver.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import my.exam.catalog.apiserver.libapiserver.dto.AuthorDTO;
import my.exam.catalog.apiserver.libapiserver.dto.BookDTO;
import my.exam.catalog.apiserver.libapiserver.entity.AuthorEntity;
import my.exam.catalog.apiserver.libapiserver.entity.BookEntity;
import my.exam.catalog.apiserver.libapiserver.mapper.AuthorMapper;
import my.exam.catalog.apiserver.libapiserver.mapper.BookMapper;
import my.exam.catalog.apiserver.libapiserver.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CatalogService {

    @Autowired
    private BookRepository bookRepo;
    @Autowired
    private AuthorMapper authorMapper;
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private AuthorService authorService;

    public CatalogService(BookRepository bookRepo, BookMapper bookMapper) {
        this.bookRepo = bookRepo;
        this.bookMapper = bookMapper;
    }

    @Transactional
    public BookDTO create(BookDTO dto) {
        if (dto == null) {
            return null;
        }
        BookEntity entity = bookMapper.toEntity(dto);
        Long existId = isTitleExists(dto);
        List<AuthorEntity> authorEntities = checkOrCreateAuthors(dto.getAuthors());
        if (existId == null) {
            entity.setAuthors(authorEntities);
            return bookMapper.toDTO(bookRepo.save(entity));
        } else {
            return update(dto, existId);
        }
    }

    @Transactional
    public BookDTO update(BookDTO dto, Long id) {
        Optional<BookEntity> optionalEntity = bookRepo.findById(id);
        if (optionalEntity.isPresent()) {
            BookEntity entity = optionalEntity.get();
            entity.setAuthors(dto.getAuthors());
            entity.setIsbn(dto.getIsbn());
            entity.setYear(dto.getYear());
            entity.setTitle(dto.getTitle());
//            bookRepo.save(entity);
            return bookMapper.toDTO(entity);
        } else {
            return null;
        }
    }

    public void delete(Long id) {
        bookRepo.deleteById(id);
    }

    public Optional<List<BookDTO>> read(List<Long> idList) {
        List<BookEntity> books = bookRepo.findAllById(idList);
        return ( books == null || books.isEmpty() ) ? Optional.empty()
                : Optional.of(bookMapper.toListDTO(books));
    }

    public Optional<BookDTO> read(Long id) {
        Optional<BookEntity> optionalEntity = bookRepo.findById(id);
        return optionalEntity.map(bookEntity -> bookMapper.toDTO(bookEntity));
    }

    public Optional<List<BookDTO>> getAll() {
        return Optional.ofNullable(bookMapper.toListDTO(bookRepo.findAll()));
    }

    public Optional<List<BookDTO>> findByTitleContaining(String title) {
        if(title.isEmpty()){
            return Optional.empty();
        }
        List<BookEntity> entities = bookRepo.findByTitleContaining(title);
        return entities.isEmpty() ? Optional.empty()
                : Optional.ofNullable(bookMapper.toListDTO(entities));
    }

    public Optional<List<BookDTO>> findByYear(Integer year) {
        List<BookEntity> entities = bookRepo.findByYearEquals(year);
        return entities.isEmpty() ? Optional.empty()
                : Optional.ofNullable(bookMapper.toListDTO(entities));
    }

    public Long isTitleExists(BookDTO dto) {
        List<BookEntity> entities = bookRepo.findByTitleContaining(dto.getTitle());
        return entities.isEmpty() ? null : entities.get(0).getId();
    }

    private List<AuthorEntity> checkOrCreateAuthors(List<AuthorEntity> authors) {
        List<AuthorEntity> checkedAuthors = new ArrayList<>();
        if (!(authors == null || authors.isEmpty())) {
            for (AuthorEntity author : authors) {
                AuthorEntity checkedAuthor = authorService.getCheckingAllNames(author);
                if (!(checkedAuthor == null)) {
                    checkedAuthors.add(checkedAuthor);
                } else {
                    Optional<AuthorDTO> optionalAuthor = authorService.create(authorMapper.toDTO(author));
                    optionalAuthor.ifPresent(dto -> checkedAuthors.add(authorMapper.toEntity(dto)));
                }
            }
        }
        return checkedAuthors;
    }

    public Optional<List<BookDTO>> tuncDubles( Optional<List<BookDTO>> booksOne, Optional<List<BookDTO>> booksTwo){
        List<BookDTO> result = new ArrayList<>();
        if(booksOne.isPresent()){
            result =  booksOne.get();
            if(booksTwo.isPresent()){
                result.retainAll(booksTwo.get());
                return Optional.of(result);
            }else{
                return booksOne;
            }
        }else{
            return booksTwo;
        }
    }
}
