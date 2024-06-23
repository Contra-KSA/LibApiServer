package my.exam.catalog.apiserver.libapiserver.repository;

import my.exam.catalog.apiserver.libapiserver.entity.BookEntity;
import org.mapstruct.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {

}
