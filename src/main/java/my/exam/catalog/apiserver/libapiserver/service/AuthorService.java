package my.exam.catalog.apiserver.libapiserver.service;

import jakarta.persistence.Tuple;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import lombok.AllArgsConstructor;
import my.exam.catalog.apiserver.libapiserver.dto.AuthorDTO;
import my.exam.catalog.apiserver.libapiserver.dto.BookDTO;
import my.exam.catalog.apiserver.libapiserver.dto.CommunicationAnswerDTO;
import my.exam.catalog.apiserver.libapiserver.entity.AuthorEntity;
import my.exam.catalog.apiserver.libapiserver.mapper.AuthorMapper;
import my.exam.catalog.apiserver.libapiserver.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private AuthorMapper authorMapper;
    @Autowired
    private CatalogService catalogService;
    @Autowired
    private Environment environment;


    /**
     * @param dto
     * @return
     */
    @Transactional
    public Optional<AuthorDTO> create(AuthorDTO dto) {
        return Optional.ofNullable(authorMapper.toDTO(authorRepository.save(authorMapper.toEntity(dto))));
    }

    /**
     * @param id
     * @return
     */
    public Optional<AuthorDTO> read(Long id) {
        Optional<AuthorEntity> optionalEntities = authorRepository.findById(id);
        return optionalEntities.map(authorEntity -> authorMapper.toDTO(authorEntity));
    }

    public AuthorEntity getCheckingAllNames(AuthorEntity entity) {
        if (entity == null) {
            return null;
        }
        String lastName = entity.getLastName();
        List<AuthorEntity> authors = authorRepository.findByLastNameContaining(lastName);
        if (!(authors == null || authors.isEmpty())) {
            for (AuthorEntity author : authors) {
                if (author.equals(entity)) {
                    return author;
                }
            }
        }
        return null;
    }

    public Optional<List<AuthorDTO>> findAll() {
        List<AuthorEntity> authors = authorRepository.findAll();
        return Optional.ofNullable(authorMapper.toListDTO(authors));
    }

    public List<AuthorEntity> checkOrCreateAuthors(List<AuthorEntity> authors) {
        List<AuthorEntity> checkedAuthors = new ArrayList<>();
        for (AuthorEntity author : authors) {
            checkedAuthors.add(getCheckingAllNames(author));
        }
        return checkedAuthors;
    }

    public CommunicationAnswerDTO delete(Long id) {
        CommunicationAnswerDTO answer = new CommunicationAnswerDTO();
        Optional<AuthorDTO> optionalAuthor = read(id);
        if (optionalAuthor.isEmpty()) {
            answer.setStatus(HttpStatus.BAD_REQUEST);
            answer.setDescription(environment.getProperty("texts.message.no-such-author"));
        }else{
            AuthorDTO authorDTO = optionalAuthor.get();
            answer.setAuthor(authorDTO);
            Optional<List<BookDTO>> optionalLinkedBooks = getBooksByAuthor(id);
            if (optionalLinkedBooks.isPresent()){
                answer.setBooks(optionalLinkedBooks.get());
                answer.setStatus(HttpStatus.CONFLICT);
                answer.setDescription(environment.getProperty("texts.message.author-has-linked-books"));
            }else{
                authorRepository.delete(authorMapper.toEntity(authorDTO));
                answer.setStatus(HttpStatus.OK);
                answer.setDescription(environment.getProperty("texts.message.success-deletetion"));
            }
        }
        return answer;
    }

    private Optional<List<BookDTO>> getBooksByAuthor(Long id){
        List<BookDTO> linkedBooks = new ArrayList<>();
        List<Tuple> tuples = authorRepository.findBooksIdForAuthor(id);
        if(tuples.isEmpty()){
            return Optional.empty();
        }else{
            Function<Object, Long> toLong = (obj) -> obj == null ? null : Long.valueOf(obj.toString());
            Long book_id;
            for (Tuple tuple : tuples) {
                book_id = toLong.apply(tuple.get("book_id"));
                Optional<BookDTO> book = catalogService.read(book_id);
                book.ifPresent(linkedBooks::add);
            }
            return Optional.of(linkedBooks);
        }
    }

    public Optional<List<BookDTO>> findBooksIdForAuthorByHisName(String firstName, String lastName){
        List<AuthorEntity> byFirstName = authorRepository.findByFirstNameContaining(firstName);
        List<AuthorEntity> byLastName = authorRepository.findByLastNameContaining(lastName);
        Optional<List<AuthorEntity>> optionalAuthors =
                Utils.getIntersectionAuthors(Optional.ofNullable(byFirstName),
                Optional.ofNullable(byLastName));
        if(optionalAuthors.isEmpty()){
            return Optional.empty();
        }else{
            List<AuthorEntity> authors = optionalAuthors.get();
            Optional<List<BookDTO>> books = Optional.of(new ArrayList<>());
            for(AuthorEntity author : authors){
                Optional<List<BookDTO>> optionalBookDTO = getBooksByAuthor(author.getId());
                if(optionalBookDTO.isPresent()){
                    books = Utils.getIntersectionBooks(books, optionalBookDTO);
                }
            }
            return books;
        }
    }
}
