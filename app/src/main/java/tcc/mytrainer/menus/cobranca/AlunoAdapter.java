package tcc.mytrainer.menus.cobranca;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import tcc.mytrainer.R;
import tcc.mytrainer.database.Session;
import tcc.mytrainer.dto.AlunoDTO;
import tcc.mytrainer.dto.CobrancaDTO;
import tcc.mytrainer.model.Aluno;

/**
 * Created by Marlon on 15/09/2017.
 */

public class AlunoAdapter extends RecyclerView.Adapter {

    private List<AlunoDTO> alunosDtos;
    private Context context;
    private AlunoAdapter.OnItemClickListener mOnItemClickListener;

    public void update() {
        alunosDtos = AlunoDTO.toList(new ArrayList<Aluno>(Session.alunos.values()));
    }

    public interface OnItemClickListener {
        void onItemClick(String idAluno);
    }

    public AlunoAdapter(List<AlunoDTO> alunos, Context context, OnItemClickListener onItemClickListener) {
        this.alunosDtos = alunos;
        this.context = context;
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.cobranca_aluno_item_card, parent, false);
        AlunoHolder holder = new AlunoHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        AlunoHolder alunoHolder = (AlunoHolder) holder;
        AlunoDTO alunoDTO = alunosDtos.get(position);

        alunoHolder.getNome().setText(alunoDTO.getNome());
        alunoHolder.getEmail().setText(alunoDTO.getEmail());
        Picasso.with(context).load(alunosDtos.get(position).getFoto()).into(alunoHolder.getFoto());

        alunoHolder.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(alunosDtos.get(position).getId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return alunosDtos.size();
    }
}
