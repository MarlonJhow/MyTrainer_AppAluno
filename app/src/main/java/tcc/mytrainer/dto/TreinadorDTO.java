package tcc.mytrainer.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import tcc.mytrainer.model.Aluno;
import tcc.mytrainer.model.Treinador;

/**
 * Created by Marlon on 15/09/2017.
 */

public class TreinadorDTO {
    private String id;
    private String nome;
    private String email;
    private String foto;


    public TreinadorDTO() {
    }

    public TreinadorDTO(Aluno aluno){
        this.id = aluno.getId();
        this.nome = aluno.getNome();
        this.email = aluno.getEmail();
        this.foto = aluno.getFotoUrl();
    }

    public TreinadorDTO(String nome, String email, String foto) {
        this.nome = nome;
        this.email = email;
        this.foto = foto;
    }

    public TreinadorDTO(Treinador aluno) {
        this.id = aluno.getId();
        this.nome = aluno.getNome();
        this.email = aluno.getEmail();
        this.foto = aluno.getFotoUrl();
    }

    public static List<TreinadorDTO> toList(List<Aluno> alunos){
        List<TreinadorDTO> treinadorDTOList =  new ArrayList<>();

        for(Aluno aluno : alunos){
            treinadorDTOList.add(new TreinadorDTO(aluno));
        }

        return treinadorDTOList;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static List<TreinadorDTO> toList(Collection<Treinador> values) {
        ArrayList<TreinadorDTO> dtos = new ArrayList<>();
        for(Treinador t : values){
            dtos.add(new TreinadorDTO(t));
        }
        return dtos;
    }
}
