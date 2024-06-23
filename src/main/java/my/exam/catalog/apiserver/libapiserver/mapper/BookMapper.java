package my.exam.catalog.apiserver.libapiserver.mapper;


import java.util.List;
import java.util.Optional;
import my.exam.catalog.apiserver.libapiserver.dto.BookDTO;
import my.exam.catalog.apiserver.libapiserver.entity.BookEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookMapper {

    BookDTO toDTO(BookEntity entity);

    BookEntity toEntity(BookDTO dto);

    List<BookDTO> toListDTO(List<BookEntity> entities);

    List<BookEntity> toListEntity(List<BookDTO> dtos);

}
