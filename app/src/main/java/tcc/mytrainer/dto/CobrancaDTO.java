package tcc.mytrainer.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import tcc.mytrainer.database.Session;
import tcc.mytrainer.enums.Periodo;
import tcc.mytrainer.enums.Status;
import tcc.mytrainer.model.Cobranca;

/**
 * Created by Marlon on 14/10/2017.
 */

public class CobrancaDTO {
    private String id;
    private String fotoAlunoUrl;
    private String nomeAluno;
    private Double valor;
    private Status status;
    private Date vencimento;
    private Periodo periodo;

    public CobrancaDTO() {
    }

    public CobrancaDTO(Cobranca cobranca) {
        this.id = cobranca.getId();
        this.fotoAlunoUrl = Session.treinadores.get(cobranca.getIdTreinador()).getFotoUrl();
        this.nomeAluno = Session.treinadores.get(cobranca.getIdTreinador()).getNome();
        this.valor = cobranca.getValor();
        this.status = cobranca.getStatus();
        this.vencimento = cobranca.getVencimento();
        this.periodo = cobranca.getPeriodo();
    }

    public static List<CobrancaDTO> toList(HashMap<String, Cobranca> cobrancasMap) {
        ArrayList<CobrancaDTO> cobrancasList = new ArrayList<>();

        for (Cobranca cobranca : cobrancasMap.values()) {
            cobrancasList.add(new CobrancaDTO(cobranca));
        }

        return cobrancasList;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getVencimento() {
        return vencimento;
    }

    public void setVencimento(Date vencimento) {
        this.vencimento = vencimento;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    public String getFotoAlunoUrl() {
        return fotoAlunoUrl;
    }

    public void setFotoAlunoUrl(String fotoAlunoUrl) {
        this.fotoAlunoUrl = fotoAlunoUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
