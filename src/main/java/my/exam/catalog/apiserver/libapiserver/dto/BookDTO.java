package my.exam.catalog.apiserver.libapiserver.dto;

import java.util.List;
import java.util.Objects;
import lombok.Data;
import my.exam.catalog.apiserver.libapiserver.entity.AuthorEntity;

@Data
public class BookDTO {
    private Long id;
    private String title;
    private int year;
    private String isbn;
    private List<AuthorEntity> authors;

    public BookDTO(){}

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setAuthors(List<AuthorEntity> authors) {
        this.authors = authors;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public String getIsbn() {
        return isbn;
    }

    public List<AuthorEntity> getAuthors() {
        return authors;
    }

    public List<AuthorEntity> addAuthor(AuthorEntity author){
        authors.add(author);
        return authors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BookDTO bookDTO = (BookDTO) o;
        return year == bookDTO.year && Objects.equals(title, bookDTO.title) && Objects.equals(isbn,
                bookDTO.isbn) && Objects.equals(authors, bookDTO.authors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, year, isbn, authors);
    }
}
