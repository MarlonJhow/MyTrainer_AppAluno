package tcc.mytrainer.model;

/**
 * Created by Marlon on 15/09/2017.
 */


public class ContaPagSeguro {

    private String id;
    private String email;
    private String token;

    public ContaPagSeguro() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
