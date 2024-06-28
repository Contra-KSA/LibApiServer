package my.exam.catalog.apiserver.libapiserver.repository;

import jakarta.persistence.Tuple;
import java.util.List;
import my.exam.catalog.apiserver.libapiserver.dto.AuthorBookLinkDTO;
import my.exam.catalog.apiserver.libapiserver.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {
    List<AuthorEntity> findByLastNameContaining(String lastName);
    List<AuthorEntity> findByFirstNameContaining(String firstName);

    @Query(value = "select * from book_author "
            + " where author_id = :id ",
            nativeQuery = true )
    public List<Tuple> findBooksIdForAuthor(Long id);

//    @Query(value = "select new AuthorBookLinkDTO()"
//            + "from ")
//    public List<AuthorBookLinkDTO> findDTOBooksIdForAuthor(Long id);
}
