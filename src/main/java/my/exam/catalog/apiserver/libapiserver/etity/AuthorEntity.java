package my.exam.catalog.apiserver.libapiserver.etity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class AuthorEntity {

    @Id
    private Long id;

    private String firstName;
    private String middleName;
    private String lastName;

    public AuthorEntity() {
    }
}