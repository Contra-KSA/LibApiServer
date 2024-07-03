package my.exam.catalog.apiserver.libapiserver.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import my.exam.catalog.apiserver.libapiserver.entity.RoleEntity;

@ToString
public class UserDTO {

    private String username;
    private String password;
    private String status = "ACTIVE";
    private List<RoleEntity> roles = new ArrayList<>();

    public List<RoleEntity> getRoles() {
        return roles;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
    }
}
