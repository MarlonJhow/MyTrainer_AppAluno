package tcc.mytrainer.menus.treinos;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import tcc.mytrainer.R;


/**
 * Created by Marlon on 04/08/2017.
 */

public class TreinoHolder extends RecyclerView.ViewHolder {

    private TextView nome;
    private TextView nAtividades;
    private ImageView imageTreino;
    private FloatingActionButton buttonEdit;

    public TreinoHolder(View itemView) {
        super(itemView);
        nome = (TextView) itemView.findViewById(R.id.item_atividade_nome);
        nAtividades = (TextView) itemView.findViewById(R.id.nAtividades);
        buttonEdit = (FloatingActionButton) itemView.findViewById(R.id.floatingActionButton);
        imageTreino = (ImageView) itemView.findViewById(R.id.treinoImage);
    }

    public TextView getNome() {
        return nome;
    }

    public void setNome(TextView nome) {
        this.nome = nome;
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

    public ImageView getImageTreino() {
        return imageTreino;
    }

    public void setImageTreino(ImageView imageTreino) {
        this.imageTreino = imageTreino;
    }
}
