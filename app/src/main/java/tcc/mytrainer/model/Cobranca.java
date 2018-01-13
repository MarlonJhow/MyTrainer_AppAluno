package tcc.mytrainer.model;

import java.util.Date;

import tcc.mytrainer.enums.Periodo;
import tcc.mytrainer.enums.Status;

/**
 * Created by marlonjhow on 04/10/17.
 */

public class Cobranca {

    public String getIdTreinador() {
        return idTreinador;
    }

    public void setIdTreinador(String idTreinador) {
        this.idTreinador = idTreinador;
    }


    private String id;
    private String idAluno;
    private String idTreinador;
    private Periodo periodo;
    private Status status = Status.PENDENTE;
    private Date vencimento;
    private Double valor;

    public Cobranca() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(String idAluno) {
        this.idAluno = idAluno;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    public Date getVencimento() {
        return vencimento;
    }

    public void setVencimento(Date vencimento) {
        this.vencimento = vencimento;
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
}
