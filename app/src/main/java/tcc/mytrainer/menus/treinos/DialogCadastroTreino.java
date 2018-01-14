package tcc.mytrainer.menus.treinos;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import tcc.mytrainer.R;

/**
 * Created by Marlon on 27/08/2017.
 */

public class DialogCadastroTreino extends DialogFragment {

    CadastroTreinoDialogListener mListener;

    public interface CadastroTreinoDialogListener {
        public void onDialogPositiveClick(DialogCadastroTreino dialog);
    }

    public EditText atividadeNome;
    public EditText atividadeDescricao;
    public EditText atividadeRepeticoes;
    public EditText atividadeSeries;

    public String idAtividade;

    @Override
    public Dialog onCreateDialog(Bundle bundle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogAtividade = inflater.inflate(R.layout.treino_dialog_cadastro, null);

        //FIELDS DIALOG
        atividadeNome = (EditText) dialogAtividade.findViewById(R.id.atividadeNome);
        atividadeDescricao = (EditText) dialogAtividade.findViewById(R.id.atividadeDescricao);
        atividadeRepeticoes = (EditText) dialogAtividade.findViewById(R.id.atividadeRepeticoes);
        atividadeSeries = (EditText) dialogAtividade.findViewById(R.id.atividadeSeries);

        //NEW OR EDIT
        if(getArguments() != null){
            idAtividade = getArguments().getString("atividadeId");

            atividadeNome.setText(getArguments().getString("atividadeNome"));
            atividadeDescricao.setText(getArguments().getString("atividadeDescricao"));
            atividadeRepeticoes.setText(getArguments().getString("atividadeRepeticoes"));
            atividadeSeries.setText(getArguments().getString("atividadeSeries"));
        }

        //LISTENERS BUTTON
        builder.setView(dialogAtividade)
                // Add action buttons
                .setPositiveButton("Cadastrar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.onDialogPositiveClick(DialogCadastroTreino.this);
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        DialogCadastroTreino.this.getDialog().cancel();
                    }
                });

        return builder.create();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callbackRecyclerViewButton interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (CadastroTreinoDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }

}
