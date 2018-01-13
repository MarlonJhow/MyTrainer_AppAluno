package tcc.mytrainer.dto;

import android.graphics.Bitmap;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import tcc.mytrainer.model.Aluno;
import tcc.mytrainer.util.DownloadImageTask;

/**
 * Created by Marlon on 15/09/2017.
 */

public class AlunoDTO {
    private String id;
    private String nome;
    private String email;
    private String foto;


    public AlunoDTO() {
    }

    public AlunoDTO(Aluno aluno){
        this.id = aluno.getId();
        this.nome = aluno.getNome();
        this.email = aluno.getEmail();
        this.foto = aluno.getFotoUrl();
    }

    public AlunoDTO(String nome, String email, String foto) {
        this.nome = nome;
        this.email = email;
        this.foto = foto;
    }

    public static List<AlunoDTO> toList(List<Aluno> alunos){
        List<AlunoDTO> alunoDTOList =  new ArrayList<>();

        for(Aluno aluno : alunos){
            alunoDTOList.add(new AlunoDTO(aluno));
        }

        return alunoDTOList;
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
}
