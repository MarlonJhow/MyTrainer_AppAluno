package tcc.mytrainer.menus.treinos;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tcc.mytrainer.R;
import tcc.mytrainer.database.Session;

/**
 * Created by Marlon on 28/07/2017.
 */

public class TreinosFragment extends Fragment implements TreinoAdapter.OnItemClickListener {

    View view;
    Context context;
    RecyclerView rvView;
    TreinoAdapter treinoAdapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //CREATE VIEW
        view = inflater.inflate(R.layout.treino_fragment, container, false);
        context = view.getContext();

        //INIT Recycler View
        rvView = (RecyclerView) view.findViewById(R.id.rv_treinos);
        treinoAdapter = new TreinoAdapter(Session.treinos, getActivity(), this);
        rvView.setAdapter(treinoAdapter);
        RecyclerView.LayoutManager layout = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        rvView.setLayoutManager(layout);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        treinoAdapter.update();
        treinoAdapter.notifyDataSetChanged();
        if (treinoAdapter.getItemCount() != Session.treinos.size()) {
            Snackbar.make(view, "TREINO SALVO COM SUCESSO!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onItemClick(View view, final int position) {
        //PEGA O ID DO TREINO
        final String treinoId = treinoAdapter.treinos.get(position).getId();
        Intent intent = new Intent(context, CadastroTreinoActivity.class);
        intent.putExtra("ID_TREINO", treinoId);
        startActivity(intent);
    }

}
