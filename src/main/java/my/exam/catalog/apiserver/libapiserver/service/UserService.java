package my.exam.catalog.apiserver.libapiserver.service;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import my.exam.catalog.apiserver.libapiserver.dto.UserDTO;
import my.exam.catalog.apiserver.libapiserver.entity.UserCustom;
import my.exam.catalog.apiserver.libapiserver.entity.UserEntity;
import my.exam.catalog.apiserver.libapiserver.mapper.UserMapper;
import my.exam.catalog.apiserver.libapiserver.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private UserMapper mapper;
    private UserRepository repo;

    public Optional<UserDTO> create(UserDTO dto) {
//        if (dto == null) {
//            return Optional.empty();
//        }
//        dto.createPasswordHash();
//        return Optional.ofNullable(mapper.toDTO(repo.save(mapper.toEntity(dto))));
        return Optional.empty();
    }

    public Optional<UserDTO> findById(Long id) {
//        Optional<UserEntity> optionalUser = repo.findById(id);
//        return optionalUser.map(userEntity -> mapper.toDTO(userEntity));
        return Optional.empty();
    }

    public Optional<UserDTO> deleteById(Long id) {
//        Optional<UserEntity> optionalUser = repo.findById(id);
//        if (optionalUser.isPresent()) {
//            UserEntity entity = optionalUser.get();
//            repo.delete(entity);
//            return Optional.of(mapper.toDTO(entity));
//        } else {
//            return Optional.empty();
//        }
        return Optional.empty();
    }

    public Optional<List<UserDTO>> findAll() {
//        List<UserEntity> users = repo.findAll();
//        return users.isEmpty() ? Optional.empty() : Optional.of(mapper.toListDTO(users));
        return Optional.empty();
    }

    public Optional<UserDTO> update(UserDTO dto, Long id) {
//        Optional<UserEntity> optionalUser = repo.findById(id);
//        if (optionalUser.isPresent()) {
//            UserEntity entity = optionalUser.get();
//            entity.setName(dto.getName());
//            entity.setLogin(dto.getLogin());
//            entity.setPasswordHash(dto.getPasswordHash());
//            return Optional.of(mapper.toDTO(entity));
//        } else {
//            return Optional.empty();
//        }
        return Optional.empty();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new UserCustom(repo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("USER NOT FOUND!")));    }
}
