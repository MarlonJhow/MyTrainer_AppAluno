package tcc.mytrainer.menus.alunos;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import tcc.mytrainer.R;

/**
 * Created by Marlon on 16/09/2017.
 */

public class TreinadorHolder extends RecyclerView.ViewHolder {

    private TextView nome;
    private TextView email;
    private ImageView foto;
    private View view;


    public TreinadorHolder(View itemView) {
        super(itemView);
        nome = (TextView) itemView.findViewById(R.id.alunoCardNomeText);
        email = (TextView) itemView.findViewById(R.id.alunoCardEmailText);
        foto = (ImageView) itemView.findViewById(R.id.alunoCardImageView);
        view = itemView;
    }

    public TextView getNome() {
        return nome;
    }

    public void setNome(TextView nome) {
        this.nome = nome;
    }

    public TextView getEmail() {
        return email;
    }

    public void setEmail(TextView email) {
        this.email = email;
    }

    public ImageView getFoto() {
        return foto;
    }

    public void setFoto(ImageView foto) {
        this.foto = foto;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }
}
