package tcc.mytrainer.menus.treinos;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import tcc.mytrainer.R;
import tcc.mytrainer.dto.AtividadeDTO;
import tcc.mytrainer.model.Atividade;

/**
 * Created by Marlon on 27/08/2017.
 */

class AtividadeHolder extends RecyclerView.ViewHolder {

    private TextView nome;
    private TextView descricao;
    private TextView nRepeticoes;
    private TextView nAtividades;
    private CheckBox checkBox;

    public AtividadeHolder(View itemView) {
        super(itemView);
        nome = (TextView) itemView.findViewById(R.id.textNomeAtividade);
        descricao = (TextView) itemView.findViewById(R.id.textDescricaoAtividade);
        nRepeticoes = (TextView) itemView.findViewById(R.id.nRepeticoes_rv);
        nAtividades = (TextView) itemView.findViewById(R.id.nSeries_rv);
        checkBox = (CheckBox) itemView.findViewById(R.id.checkCompleteAtividade);
    }

    public void update(Atividade atividade) {
        this.nome.setText(atividade.getNome());
        this.descricao.setText(atividade.getDescricao());
        this.nRepeticoes.setText(atividade.getRepeticoes().toString());
        this.nAtividades.setText(atividade.getSeries().toString());
    }

    public void update(AtividadeDTO atividade) {
        this.nome.setText(atividade.getNome());
        this.descricao.setText(atividade.getDescricao());
        this.nRepeticoes.setText(atividade.getRepeticoes().toString());
        this.nAtividades.setText(atividade.getSeries().toString());
    }

    public TextView getNome() {
        return nome;
    }


    public void setNome(TextView nome) {
        this.nome = nome;
    }

    public TextView getnRepeticoes() {
        return nRepeticoes;
    }

    public void setnRepeticoes(TextView nRepeticoes) {
        this.nRepeticoes = nRepeticoes;
    }

    public TextView getnAtividades() {
        return nAtividades;
    }

    public void setnAtividades(TextView nAtividades) {
        this.nAtividades = nAtividades;
    }

    public TextView getDescricao() {
        return descricao;
    }

    public void setDescricao(TextView descricao) {
        this.descricao = descricao;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }
}
