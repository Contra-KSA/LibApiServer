package my.exam.catalog.apiserver.libapiserver.service;

import java.util.ArrayList;
import java.util.List;
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
        if(entity == null){
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

    public Optional<List<AuthorDTO>> findAll(){
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
}
