package tcc.mytrainer.menus.alunos;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import tcc.mytrainer.R;
import tcc.mytrainer.database.Session;
import tcc.mytrainer.dto.AlunoDTO;
import tcc.mytrainer.menus.cobranca.AlunoAdapter;
import tcc.mytrainer.menus.cobranca.CadastroCobrancaActivity;
import tcc.mytrainer.model.Aluno;

/**
 * Created by Marlon on 28/07/2017.
 */

public class AlunosFragment extends Fragment implements AlunoAdapter.OnItemClickListener {

    View view;
    Context context;

    private RecyclerView recyclerView;
    private AlunoAdapter alunoAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.alunos_fragment, container, false);
        context = view.getContext();

        //BUTTON ADD COBRANCA
        FloatingActionButton buttonAddCobranca = (FloatingActionButton) view.findViewById(R.id.buttonAddAluno);
        buttonAddCobranca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, AlunosBuscaActivity.class));
            }
        });

        //REYCLER VIEW
        recyclerView = (RecyclerView) view.findViewById(R.id.cobranca_recyclerview);

        alunoAdapter = new AlunoAdapter(AlunoDTO.toList(new ArrayList<Aluno>(Session.alunos.values())), getActivity(), this);
        recyclerView.setAdapter(alunoAdapter);
        RecyclerView.LayoutManager layout = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layout);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        alunoAdapter.update();
        alunoAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(String idAluno) {
        Intent intent = new Intent(context, InfoAlunoActivity.class);
        intent.putExtra("ID_ALUNO", idAluno);
        startActivity(intent);
    }
}
