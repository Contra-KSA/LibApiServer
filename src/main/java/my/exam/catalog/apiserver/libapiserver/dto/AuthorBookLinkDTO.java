package my.exam.catalog.apiserver.libapiserver.dto;

import lombok.Data;

@Data
public class AuthorBookLinkDTO {
    private Long authorId;
    private Long bookId;
}
