package my.exam.catalog.apiserver.libapiserver.mapper;

import java.util.List;
import my.exam.catalog.apiserver.libapiserver.dto.AuthorDTO;
import my.exam.catalog.apiserver.libapiserver.entity.AuthorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AuthorMapper {
    AuthorDTO toDTO(AuthorEntity entity);

    AuthorEntity toEntity(AuthorDTO dto);

    List<AuthorDTO> toListDTO(List<AuthorEntity> entities);

    List<AuthorEntity> toListEntity(List<AuthorDTO> dtos);

}
