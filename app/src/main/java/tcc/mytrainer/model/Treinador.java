package tcc.mytrainer.model;

import java.util.HashMap;

/**
 * Created by Marlon on 26/08/2017.
 */

public class Treinador {

    private String id;
    private String nome;
    private String email;
    private String fotoUrl;
    private ContaPagSeguro contaPagSeguro;
    private HashMap<String, String> idTreinos = new HashMap<String, String>();
    private HashMap<String, String> idCobrancas = new HashMap<String, String>();
    private HashMap<String, String> idAlunos = new HashMap<String, String>();

    public Treinador() {
    }

    public Treinador(String email) {
        this.email = email;
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

    public ContaPagSeguro getContaPagSeguro() {
        return contaPagSeguro;
    }

    public void setContaPagSeguro(ContaPagSeguro contaPagSeguro) {
        this.contaPagSeguro = contaPagSeguro;
    }

    public HashMap<String, String> getIdAlunos() {
        return idAlunos;
    }

    public void setIdAlunos(HashMap<String, String> idAlunos) {
        this.idAlunos = idAlunos;
    }

    public HashMap<String, String> getIdCobrancas() {
        return idCobrancas;
    }

    public void setIdCobrancas(HashMap<String, String> idCobrancas) {
        this.idCobrancas = idCobrancas;
    }

    public HashMap<String, String> getIdTreinos() {
        return idTreinos;
    }

    public void setIdTreinos(HashMap<String, String> idTreinos) {
        this.idTreinos = idTreinos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }
}
