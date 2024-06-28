package my.exam.catalog.apiserver.libapiserver.mapper;

import java.util.List;
import my.exam.catalog.apiserver.libapiserver.dto.UserDTO;
import my.exam.catalog.apiserver.libapiserver.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    UserDTO toDTO(UserEntity entity);

    UserEntity toEntity(UserDTO dto);

    List<UserDTO> toListDTO(List<UserEntity> entities);

    List<UserEntity> toListEntity(List<UserDTO> dtos);
}
