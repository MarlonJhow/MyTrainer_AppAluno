package tcc.mytrainer.menus.conta;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import tcc.mytrainer.R;

/**
 * Created by Marlon on 07/09/2017.
 */

public class EditContaDialog extends DialogFragment {

    //STRINGS
    public String email;
    public String token;

    //FIELDS
    EditText textEmail;
    EditText textToken;

    View view;

    @Override
    public Dialog onCreateDialog(Bundle bundle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.pagseguro_edit_dialog, null);

        //INIT FIELDS
        textEmail = (EditText) view.findViewById(R.id.pagseguroEmail);
        textToken = (EditText) view.findViewById(R.id.pagseguroToken);

        if (getArguments() != null) {
            textEmail.setText(getArguments().getString("email"));
            textToken.setText(getArguments().getString("token"));
        }


        builder.setView(view)
                // Add action buttons
                .setPositiveButton("Cadastrar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {


                    }

                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        EditContaDialog.this.getDialog().cancel();
                    }
                });

        return builder.create();
    }

    @Override
    public void onStart() {
        super.onStart();
        AlertDialog d = (AlertDialog) getDialog();
        if (d != null) {
            Button positiveButton = (Button) d.getButton(Dialog.BUTTON_POSITIVE);
            positiveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    email = textEmail.getText().toString();
                    token = textToken.getText().toString();

                    if (validarCampos(email, token)) {
                        getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, getActivity().getIntent().putExtra("email",email).putExtra("token", token));
//                        mListener.onDialogPositiveClick(EditContaDialog.this);
                        dismiss();
                    }

                }
            });
        }
    }

    private boolean validarCampos(String email, String token) {
        boolean validate = true;

        if (email.isEmpty() || token.isEmpty()) {
            toastError("TODOS OS CAMPOS DEVEM SER PREENCHIDOS");
            validate = false;
        }

        return validate;
    }

    private void toastError(String textError) {
        Toast.makeText(getActivity(), textError, Toast.LENGTH_LONG).show();
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getDialog().getCurrentFocus().getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
    }


}
