package my.exam.catalog.apiserver.libapiserver.dto;

import lombok.Data;

@Data
public class UserDTO {

    private Long Id;
    private String login;
    private String name;
    private String passwordHash;
    private String password;

    public void createPasswordHash() {
        passwordHash = Integer.valueOf(password.hashCode()).toString();
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
