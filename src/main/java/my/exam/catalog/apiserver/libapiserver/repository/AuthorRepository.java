package my.exam.catalog.apiserver.libapiserver.repository;

import java.util.List;
import my.exam.catalog.apiserver.libapiserver.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {
    List<AuthorEntity> findByLastNameContaining(String lastName);
    List<AuthorEntity> findByFirstNameContaining(String firstName);
}
