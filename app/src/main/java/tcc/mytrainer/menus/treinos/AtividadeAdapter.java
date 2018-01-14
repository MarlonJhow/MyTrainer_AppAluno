package tcc.mytrainer.menus.treinos;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import tcc.mytrainer.R;
import tcc.mytrainer.dto.AtividadeDTO;
import tcc.mytrainer.model.Atividade;

/**
 * Created by Marlon on 27/08/2017.
 */

public class AtividadeAdapter extends RecyclerView.Adapter {

    List<AtividadeDTO> atividades;
    private Context context;
    private AtividadeAdapter.OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public AtividadeAdapter(HashMap<String, Atividade> atividades, Context context, OnItemClickListener mOnItemClickListener) {
        this.atividades = AtividadeDTO.toList(new ArrayList<Atividade>(atividades.values()));
        this.context = context;
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.treino_atividade_item_card, parent, false);
        AtividadeHolder holder = new AtividadeHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        AtividadeHolder atividadeHolder = (AtividadeHolder) holder;
        AtividadeDTO atividade = atividades.get(position);

        atividadeHolder.getCheckBox().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                atividadeHolder.getCheckBox().setChecked(isChecked);
                atividade.setCheck(isChecked);

                Integer countCheck = 0;
                for (AtividadeDTO dto : atividades) {
                    if (dto.getCheck()) {
                        countCheck++;
                    }
                }

                if (countCheck == atividades.size()) {
                    mOnItemClickListener.onItemClick(buttonView, position);
                }
            }
        });

        atividadeHolder.update(atividade);
    }

    @Override
    public int getItemCount() {
        return atividades.size();
    }

    public void updateAtividadaes(HashMap<String, Atividade> atividades) {
        this.atividades = AtividadeDTO.toList(new ArrayList<Atividade>(atividades.values()));
    }
}
