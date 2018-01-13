package tcc.mytrainer.menus.cobranca;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;

import tcc.mytrainer.R;
import tcc.mytrainer.database.Session;
import tcc.mytrainer.dto.AlunoDTO;
import tcc.mytrainer.model.Aluno;

/**
 * Created by Marlon on 07/09/2017.
 */

public class ListAlunosDialog extends DialogFragment implements AlunoAdapter.OnItemClickListener{

    View view;
    AlunoAdapter alunoAdapter;
    ListAlunosDialog.ListAlunosDialogListener mListener;

    public interface ListAlunosDialogListener {
        public void getAlunoId(String idAluno);
    }

    @Override
    public Dialog onCreateDialog(Bundle bundle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.cobranca_cadastro_alunos_recyclerview, null);

        //INIT RECYCLERVIEW
        RecyclerView rvView = (RecyclerView) view.findViewById(R.id.alunosRecyclerView);

        alunoAdapter = new AlunoAdapter(AlunoDTO.toList(new ArrayList<Aluno>(Session.alunos.values())), getActivity(), this);
        rvView.setAdapter(alunoAdapter);
        RecyclerView.LayoutManager layout = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        rvView.setLayoutManager(layout);

        builder.setView(view);
        return builder.create();
    }

    @Override
    public void onItemClick(String idAluno) {
        mListener.getAlunoId(idAluno);
        dismiss();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callbackRecyclerViewButton interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (ListAlunosDialog.ListAlunosDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }
}
