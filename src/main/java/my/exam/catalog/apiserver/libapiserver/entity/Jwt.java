package my.exam.catalog.apiserver.libapiserver.entity;

import java.io.Serializable;

public class Jwt implements Serializable {

    private String token;

    public Jwt(String token) {
        this.token = token;
    }
    public Jwt() {
    }

    public String getToken() {
        return token;
    }
}
