package tcc.mytrainer.menus.inicio;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tcc.mytrainer.R;
import tcc.mytrainer.database.Session;
import tcc.mytrainer.enums.Status;
import tcc.mytrainer.model.Cobranca;

/**
 * Created by Marlon on 28/07/2017.
 */

public class InicioFragment extends Fragment {

    Fragment context;
    View view;

    //COMPONENTES
    TextView nTreinos;
    TextView nAlunos;
    TextView nPagamentosPendentes;
    TextView nValorReceber;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.inicio_fragment, container, false);
        context = this;

        //CARREGANDO COMPONENTES
        nTreinos = (TextView) view.findViewById(R.id.textNTreinos);
        nAlunos = (TextView) view.findViewById(R.id.textNAlunos);
        nPagamentosPendentes = (TextView) view.findViewById(R.id.textNPagamentosPendentes);
        nValorReceber = (TextView) view.findViewById(R.id.textNValorReceber);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        //PROCESSA DADOS
        Integer pagamentosPendentes = 0;
        Double valorReceber = 0.0;
        for (Cobranca cobranca : Session.cobrancas.values()) {
            if (cobranca.getStatus().equals(Status.PENDENTE)) {
                valorReceber += cobranca.getValor();
                pagamentosPendentes++;
            }
        }

        //ATUALIZA COMPONENETES
        nTreinos.setText(String.valueOf(Session.aluno.getIdTreinos().size()));
        nAlunos.setText(String.valueOf(Session.aluno.getIdTreinadores().size()));
        nPagamentosPendentes.setText(String.valueOf(pagamentosPendentes));
        nValorReceber.setText(String.format("%.2f", valorReceber));
    }
}
