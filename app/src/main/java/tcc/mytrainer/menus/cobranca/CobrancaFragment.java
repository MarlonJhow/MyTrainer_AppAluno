package tcc.mytrainer.menus.cobranca;

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

import tcc.mytrainer.R;
import tcc.mytrainer.database.Session;

/**
 * Created by Marlon on 15/09/2017.
 */

public class CobrancaFragment extends Fragment implements CobrancaAdapter.Listener {

    private View view;
    private Context context;

    private RecyclerView recyclerView;
    private CobrancaAdapter cobrancaAdapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //LOAD CONTEXT
        super.onCreate(savedInstanceState);
        view = inflater.inflate(R.layout.cobranca_fragment, container, false);
        context = view.getContext();

        //BUTTON ADD COBRANCA
        FloatingActionButton buttonAddCobranca = (FloatingActionButton) view.findViewById(R.id.buttonAddCobranca);
        buttonAddCobranca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, CadastroCobrancaActivity.class));
            }
        });

        //REYCLER VIEW
        recyclerView = (RecyclerView) view.findViewById(R.id.cobranca_recyclerview);
        cobrancaAdapter = new CobrancaAdapter(Session.cobrancas, getActivity(), this);
        recyclerView.setAdapter(cobrancaAdapter);
        RecyclerView.LayoutManager layout = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layout);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        cobrancaAdapter.update();
        cobrancaAdapter.notifyDataSetChanged();
    }

    @Override
    public void callback(String idCobranca) {

    }
}
