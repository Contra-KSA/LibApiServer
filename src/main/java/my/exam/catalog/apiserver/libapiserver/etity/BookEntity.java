package my.exam.catalog.apiserver.libapiserver.etity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class BookEntity {

    public BookEntity() {
    }

    @Id
    private Long id;

    private String title;

}
