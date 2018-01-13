package tcc.mytrainer.model;

import android.text.Editable;

/**
 * Created by Marlon on 03/08/2017.
 */

public class Atividade {
    private String id;
    private String nome;
    private String descricao;
    private Integer repeticoes;
    private Integer series;

    public Atividade() {
    }

    public Atividade(String nome, String descricao, String repeticoes, String series) {
        this.nome = nome;
        this.descricao = descricao;
        this.repeticoes = Integer.parseInt(repeticoes);
        this.series = Integer.parseInt(series);
    }

    public Atividade(String nome, String descricao, int repeticoes, int series) {
        this.nome = nome;
        this.descricao = descricao;
        this.repeticoes = repeticoes;
        this.series = series;
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

    public Integer getRepeticoes() {
        return repeticoes;
    }

    public void setRepeticoes(Integer repeticoes) {
        this.repeticoes = repeticoes;
    }

    public Integer getSeries() {
        return series;
    }

    public void setSeries(Integer series) {
        this.series = series;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
