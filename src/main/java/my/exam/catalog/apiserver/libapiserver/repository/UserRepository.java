package my.exam.catalog.apiserver.libapiserver.repository;

import java.util.Optional;
import my.exam.catalog.apiserver.libapiserver.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

}
