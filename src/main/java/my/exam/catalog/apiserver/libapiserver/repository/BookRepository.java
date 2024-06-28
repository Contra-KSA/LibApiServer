package my.exam.catalog.apiserver.libapiserver.repository;

import java.util.List;
import my.exam.catalog.apiserver.libapiserver.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {

    List<BookEntity> findByTitleContaining(String title);
    List<BookEntity> findByYearEquals(Integer year);
//    List<BookEntity> findByYearContaining(Integer year);
}
