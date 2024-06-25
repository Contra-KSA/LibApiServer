package my.exam.catalog.apiserver.libapiserver.mapper;


import java.util.List;
import my.exam.catalog.apiserver.libapiserver.dto.BookDTO;
import my.exam.catalog.apiserver.libapiserver.entity.BookEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ValueMappings;
import org.mapstruct.ValueMapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookMapper {
    AuthorMapper INSTANCE = Mappers.getMapper( AuthorMapper.class );

//    @Mapping(target = "authors", expression = "java(INSTANCE.toListDTO(entity.getAuthors()))")
    BookDTO toDTO(BookEntity entity);

//    @Mapping(target = "authors", expression = "java(INSTANCE.toListEntity(dto.getAuthors()))")
    BookEntity toEntity(BookDTO dto);

    List<BookDTO> toListDTO(List<BookEntity> entities);

    List<BookEntity> toListEntity(List<BookDTO> dtos);

}
