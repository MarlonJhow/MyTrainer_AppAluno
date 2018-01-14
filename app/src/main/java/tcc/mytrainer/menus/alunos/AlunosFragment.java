package tcc.mytrainer.menus.alunos;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tcc.mytrainer.R;
import tcc.mytrainer.database.Session;
import tcc.mytrainer.dto.TreinadorDTO;

/**
 * Created by Marlon on 28/07/2017.
 */

public class AlunosFragment extends Fragment implements TreinadorAdapter.OnItemClickListener {

    View view;
    Context context;

    private RecyclerView recyclerView;
    private TreinadorAdapter treinadorAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.treinadores_fragment, container, false);
        context = view.getContext();

        //REYCLER VIEW
        recyclerView = (RecyclerView) view.findViewById(R.id.cobranca_recyclerview);

        treinadorAdapter = new TreinadorAdapter(TreinadorDTO.toList(Session.treinadores.values()), getActivity(), this);
        recyclerView.setAdapter(treinadorAdapter);
        RecyclerView.LayoutManager layout = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layout);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        treinadorAdapter.update();
        treinadorAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(String idAluno) {

    }
}
