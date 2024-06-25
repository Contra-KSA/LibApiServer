package my.exam.catalog.apiserver.libapiserver.service;

import java.util.Optional;
import lombok.AllArgsConstructor;
import my.exam.catalog.apiserver.libapiserver.dto.AuthorDTO;
import my.exam.catalog.apiserver.libapiserver.entity.AuthorEntity;
import my.exam.catalog.apiserver.libapiserver.mapper.AuthorMapper;
import my.exam.catalog.apiserver.libapiserver.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private AuthorMapper authorMapper;

    /**
     * @param dto
     * @return
     */
    @Transactional
    public Optional<AuthorDTO> create(AuthorDTO dto){
        return Optional.ofNullable(authorMapper.toDTO(authorRepository.save(authorMapper.toEntity(dto))));
    }

    /**
     * @param id
     * @return
     */
    public Optional<AuthorDTO> read(Long id){
        Optional<AuthorEntity> optionalEntities = authorRepository.findById(id);
        return optionalEntities.map(authorEntity -> authorMapper.toDTO(authorEntity));
    }
}
