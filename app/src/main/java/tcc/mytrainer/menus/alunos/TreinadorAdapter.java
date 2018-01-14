package tcc.mytrainer.menus.alunos;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import tcc.mytrainer.R;
import tcc.mytrainer.database.Session;
import tcc.mytrainer.dto.TreinadorDTO;

/**
 * Created by Marlon on 15/09/2017.
 */

public class TreinadorAdapter extends RecyclerView.Adapter {

    private List<TreinadorDTO> treinadoresDtos;
    private Context context;
    private TreinadorAdapter.OnItemClickListener mOnItemClickListener;

    public void update() {
        treinadoresDtos = TreinadorDTO.toList(Session.treinadores.values());
    }

    public interface OnItemClickListener {
        void onItemClick(String idAluno);
    }

    public TreinadorAdapter(List<TreinadorDTO> treinadores, Context context, OnItemClickListener onItemClickListener) {
        this.treinadoresDtos = treinadores;
        this.context = context;
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.cobranca_aluno_item_card, parent, false);
        TreinadorHolder holder = new TreinadorHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        TreinadorHolder treinadorHolder = (TreinadorHolder) holder;
        TreinadorDTO treinadorDTO = treinadoresDtos.get(position);

        treinadorHolder.getNome().setText(treinadorDTO.getNome());
        treinadorHolder.getEmail().setText(treinadorDTO.getEmail());
        Picasso.with(context).load(treinadoresDtos.get(position).getFoto()).into(treinadorHolder.getFoto());

        treinadorHolder.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(treinadoresDtos.get(position).getId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return treinadoresDtos.size();
    }
}
