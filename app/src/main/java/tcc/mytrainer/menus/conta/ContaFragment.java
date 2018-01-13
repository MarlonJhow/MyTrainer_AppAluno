package tcc.mytrainer.menus.conta;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import tcc.mytrainer.R;
import tcc.mytrainer.database.Session;
import tcc.mytrainer.facade.ContaPagSeguroFacade;
import tcc.mytrainer.model.ContaPagSeguro;

/**
 * Created by Marlon on 28/07/2017.
 */

public class ContaFragment extends Fragment  {

    private View view;
    private FragmentActivity myContext;
    private ContaPagSeguro contaPagSeguro;
    private Fragment self;


    //FIELDS
    TextView pagSeguroEmail;
    TextView pagSeguroToken;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = inflater.inflate(R.layout.pagseguro_activity, container, false);
        self = this;

        //INIT FIELDS
        pagSeguroEmail = (TextView) view.findViewById(R.id.contaEmail);
        pagSeguroToken = (TextView) view.findViewById(R.id.contaToken);

        //INIT CONTA BANCARIA
        if (Session.treinador != null && Session.treinador.getContaPagSeguro() != null) {
            contaPagSeguro = Session.treinador.getContaPagSeguro();
            atualizarCampos(contaPagSeguro.getEmail(), contaPagSeguro.getToken());
        } else {
            contaPagSeguro = new ContaPagSeguro();
        }

        //BUTTON EDITAR
        FloatingActionButton buttonEdit = (FloatingActionButton) view.findViewById(R.id.buttonEditConta);
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                if (contaPagSeguro != null) {
                    bundle.putString("email", contaPagSeguro.getEmail());
                    bundle.putString("token", contaPagSeguro.getToken());
                }

                DialogFragment dialogAddAtividade = new EditContaDialog();
                dialogAddAtividade.setArguments(bundle);
                dialogAddAtividade.setTargetFragment(self, 0);
                dialogAddAtividade.show(myContext.getSupportFragmentManager(), "editContaDialog");
            }
        });

        return view;
    }

    private void atualizarCampos(String email, String token) {
        pagSeguroEmail.setText(email);
        pagSeguroToken.setText(getEstrelinhas(token));
    }

    private String getEstrelinhas(String token) {
        if (token != null) {
            int count = 0;
            String estrelinhas = "";
            while (count < token.length()) {
                estrelinhas += "*";
                count++;
            }
            return estrelinhas;
        } else {
            return null;
        }


    }

    @Override
    public void onAttach(Activity activity) {
        myContext = (FragmentActivity) activity;
        super.onAttach(activity);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        String email = data.getStringExtra("email");
        String token = data.getStringExtra("token");

        contaPagSeguro.setEmail(email);
        contaPagSeguro.setToken(token);

        atualizarCampos(email, token);
        ContaPagSeguroFacade.salvarConta(contaPagSeguro);

        Snackbar.make(view, "CONTA SALVA COM SUCESSO!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

    }
}

