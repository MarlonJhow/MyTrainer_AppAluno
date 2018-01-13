package tcc.mytrainer.model;

import java.util.HashMap;

import tcc.mytrainer.enums.ImageTreino;

/**
 * Created by Marlon on 03/08/2017.
 */

public class Treino {
    private String id;
    private String nome;
    private String descricao;
    private ImageTreino imageTreino;
    private HashMap<String, Atividade> atividades = new HashMap<>();

    public Treino() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public HashMap<String, Atividade> getAtividades() {
        return atividades;
    }

    public void setAtividades(HashMap<String, Atividade> atividades) {
        this.atividades = atividades;

    }

    public ImageTreino getImageTreino() {
        return imageTreino;
    }

    public void setImageTreino(ImageTreino imageTreino) {
        this.imageTreino = imageTreino;
    }

}
