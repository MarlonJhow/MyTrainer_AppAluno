package tcc.mytrainer.dto;

import java.util.ArrayList;
import java.util.List;

import tcc.mytrainer.model.Atividade;

/**
 * Created by Marlon on 13/01/2018.
 */

public class AtividadeDTO {

    private String id;
    private String nome;
    private String descricao;
    private Integer repeticoes;
    private Integer series;
    private Boolean check = false;

    public AtividadeDTO() {
    }

    public AtividadeDTO(Atividade atividade) {
        this.id = atividade.getId();
        this.nome = atividade.getNome();
        this.descricao = atividade.getDescricao();
        this.repeticoes = atividade.getRepeticoes();
        this.series = atividade.getSeries();
    }

    public static List<AtividadeDTO> toList(List<Atividade> atividades){
        List<AtividadeDTO> dtos = new ArrayList<>();
        for(Atividade atividade : atividades){
            dtos.add(new AtividadeDTO(atividade));
        }
        return dtos;
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

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }
}
