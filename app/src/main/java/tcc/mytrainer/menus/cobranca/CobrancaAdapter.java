package tcc.mytrainer.menus.cobranca;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.List;

import tcc.mytrainer.R;
import tcc.mytrainer.database.Session;
import tcc.mytrainer.dto.CobrancaDTO;
import tcc.mytrainer.model.Cobranca;

/**
 * Created by Marlon on 14/10/2017.
 */

class CobrancaAdapter extends RecyclerView.Adapter{

    private List<CobrancaDTO> cobrancas;
    private Context context;

    public CobrancaAdapter(HashMap<String, Cobranca> cobrancas, Context context) {
        this.cobrancas = CobrancaDTO.toList(cobrancas);
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(context)
                .inflate(R.layout.cobranca_item_card, parent, false);
        CobrancaHolder holder = new CobrancaHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CobrancaHolder cobrancaHolder = (CobrancaHolder) holder;
        CobrancaDTO cobranca = cobrancas.get(position);

        cobrancaHolder.update(cobranca, context);
    }

    @Override
    public int getItemCount() {
        return cobrancas.size();
    }

    public void update() {
        cobrancas = CobrancaDTO.toList(Session.cobrancas);
    }
}
