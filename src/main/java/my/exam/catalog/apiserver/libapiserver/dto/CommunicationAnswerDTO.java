package my.exam.catalog.apiserver.libapiserver.dto;

import java.util.List;
import org.springframework.http.HttpStatus;


public class CommunicationAnswerDTO {
    private HttpStatus status;
    private String description;
    private List<BookDTO> books;
    private AuthorDTO author;

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<BookDTO> getBooks() {
        return books;
    }

    public void setBooks(List<BookDTO> books) {
        this.books = books;
    }

    public AuthorDTO getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDTO author) {
        this.author = author;
    }
}
