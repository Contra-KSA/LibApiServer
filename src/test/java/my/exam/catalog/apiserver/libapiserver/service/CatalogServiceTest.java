package my.exam.catalog.apiserver.libapiserver.service;

import static org.junit.jupiter.api.Assertions.*;

import jakarta.inject.Inject;
import java.sql.SQLException;
import my.exam.catalog.apiserver.libapiserver.dto.BookDTO;
import my.exam.catalog.apiserver.libapiserver.entity.AuthorEntity;
import my.exam.catalog.apiserver.libapiserver.entity.BookEntity;
import my.exam.catalog.apiserver.libapiserver.mapper.BookMapper;
import my.exam.catalog.apiserver.libapiserver.repository.AuthorRepository;
import my.exam.catalog.apiserver.libapiserver.repository.BookRepository;
import my.exam.catalog.apiserver.libapiserver.service.configuration.SpringDataConfiguration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Commit;
import org.h2.tools.Server;

@DataJpaTest
//@Import(SpringDataConfiguration.class)
@Commit
class CatalogServiceTest {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    private BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    @BeforeAll
    public static void initTest() throws SQLException {
        Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082")
                .start();
    }

    @BeforeEach
    public void booksShouldBeAdded() {
//        AuthorEntity author1 = new AuthorEntity();
//        author1.setFirstName("FirstName1");
//        author1.setLastName("LastName1");
//        AuthorEntity author2 = new AuthorEntity();
//        author2.setFirstName("FirstName2");
//        author2.setLastName("LastName2");
//
//        AuthorEntity entity1 = authorRepository.save(author1);
//        AuthorEntity entity2 = authorRepository.save(author2);
//
//        BookDTO book1 = new BookDTO();
//        book1.setTitle("The Last of the Mohicans");
//        book1.setYear(2000);
//        BookEntity bookEntity1 = bookRepository.save(INSTANCE.toEntity(book1));
//        bookEntity1.addAuthor(author1);
//        bookRepository.save(bookEntity1);
//
//        BookDTO book2 = new BookDTO();
//        book2.setTitle("The Last of the Mohicans");
//        book2.setYear(2002);
//        bookRepository.save(INSTANCE.toEntity(book2));
//        book2.addAuthor(author1);
//        book2.addAuthor(author2);
    }

//    @Test
//    public void doIt() {
//    }
}