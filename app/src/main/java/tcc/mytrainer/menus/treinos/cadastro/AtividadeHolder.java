package tcc.mytrainer.menus.treinos.cadastro;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import tcc.mytrainer.R;
import tcc.mytrainer.model.Atividade;

/**
 * Created by Marlon on 27/08/2017.
 */

class AtividadeHolder extends RecyclerView.ViewHolder {

    private TextView nome;
    private TextView nRepeticoes;
    private TextView nAtividades;
    private FloatingActionButton buttonEdit;

    public AtividadeHolder(View itemView) {
        super(itemView);
        nome = (TextView) itemView.findViewById(R.id.item_atividade_nome_rv);
        nRepeticoes = (TextView) itemView.findViewById(R.id.nRepeticoes_rv);
        nAtividades = (TextView) itemView.findViewById(R.id.nSeries_rv);
        buttonEdit = (FloatingActionButton) itemView.findViewById(R.id.buttonAtividadeEdit);
    }

    public void update(Atividade atividade) {
        this.nome.setText(atividade.getNome());
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

    public FloatingActionButton getButtonEdit() {
        return buttonEdit;
    }

    public void setButtonEdit(FloatingActionButton buttonEdit) {
        this.buttonEdit = buttonEdit;
    }
}
