package tcc.mytrainer.menus.cobranca;

import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import tcc.mytrainer.R;
import tcc.mytrainer.dto.CobrancaDTO;
import tcc.mytrainer.enums.Periodo;
import tcc.mytrainer.enums.Status;

/**
 * Created by Marlon on 14/10/2017.
 */

class CobrancaHolder extends RecyclerView.ViewHolder {

    private ImageView fotoAluno;
    private TextView nomeAluno;
    private TextView valor;
    private TextView status;
    private TextView vencimento;
    private ImageView recorrente;
    private ImageView iconPagamento;
    private FloatingActionButton buttonEdit;

    public CobrancaHolder(View itemView) {
        super(itemView);

        fotoAluno = (ImageView) itemView.findViewById(R.id.cobrancaItemCardFotoAluno);
        nomeAluno = (TextView) itemView.findViewById(R.id.cobrancaItemCardNomeAluno);
        valor = (TextView) itemView.findViewById(R.id.cobrancaItemCardValor);
        status = (TextView) itemView.findViewById(R.id.cobrancaItemCardStatus);
        vencimento = (TextView) itemView.findViewById(R.id.cobrancaItemCardVencimento);
        recorrente = (ImageView) itemView.findViewById(R.id.cobrancaItemCardRecorrente);
        buttonEdit = (FloatingActionButton) itemView.findViewById(R.id.cobrancaItemCardButtonEdit);
        iconPagamento = (ImageView) itemView.findViewById(R.id.iconPagamento);
    }

    public void update(CobrancaDTO cobranca, Context context) {

        Picasso.with(context).load(cobranca.getFotoAlunoUrl()).into(fotoAluno);
        nomeAluno.setText(cobranca.getNomeAluno());
        valor.setText("R$ "+String.format("%.2f", cobranca.getValor()));
        status.setText(cobranca.getStatus().toString());
        vencimento.setText(new SimpleDateFormat("dd/MM/yyyy").format(cobranca.getVencimento()));
        if (cobranca.getPeriodo().equals(Periodo.MENSAL)) {
            recorrente.setImageResource(R.drawable.ic_repeat_black_24dp);
        } else {
            recorrente.setImageResource(0);
        }

        if(cobranca.getStatus().equals(Status.PAGO)){
            buttonEdit.hide();
            buttonEdit.setEnabled(false);
            iconPagamento.setImageResource(R.drawable.ic_beenhere_black_24dp);
        }

        //TODO Implementar botão

    }

    public ImageView getFotoAluno() {
        return fotoAluno;
    }

    public void setFotoAluno(ImageView fotoAluno) {
        this.fotoAluno = fotoAluno;
    }

    public TextView getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(TextView nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public TextView getValor() {
        return valor;
    }

    public void setValor(TextView valor) {
        this.valor = valor;
    }

    public TextView getStatus() {
        return status;
    }

    public void setStatus(TextView status) {
        this.status = status;
    }

    public TextView getVencimento() {
        return vencimento;
    }

    public void setVencimento(TextView vencimento) {
        this.vencimento = vencimento;
    }

    public ImageView getRecorrente() {
        return recorrente;
    }

    public void setRecorrente(ImageView recorrente) {
        this.recorrente = recorrente;
    }

    public FloatingActionButton getButtonEdit() {
        return buttonEdit;
    }

    public void setButtonEdit(FloatingActionButton buttonEdit) {
        this.buttonEdit = buttonEdit;
    }

}
