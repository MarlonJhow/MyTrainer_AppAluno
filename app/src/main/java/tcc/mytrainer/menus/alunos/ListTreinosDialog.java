package tcc.mytrainer.menus.alunos;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import tcc.mytrainer.R;
import tcc.mytrainer.database.Session;

/**
 * Created by Marlon on 12/01/2018.
 */

public class ListTreinosDialog extends DialogFragment implements AlunoTreinoAdapterAdd.OnItemClickListener{

    View view;
    AlunoTreinoAdapterAdd treinoAdapter;
    Listener mListener;

    public interface Listener {
        public void callbackModal(String idAluno);
    }

    @Override
    public Dialog onCreateDialog(Bundle bundle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.cobranca_cadastro_alunos_recyclerview, null);

        //INIT RECYCLERVIEW
        RecyclerView rvView = (RecyclerView) view.findViewById(R.id.alunosRecyclerView);

        treinoAdapter = new AlunoTreinoAdapterAdd(Session.treinos, getActivity(), this);
        rvView.setAdapter(treinoAdapter);
        RecyclerView.LayoutManager layout = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        rvView.setLayoutManager(layout);

        builder.setView(view);
        return builder.create();
    }

    @Override
    public void onItemClick(String id) {
        mListener.callbackModal(id);
        dismiss();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callbackRecyclerViewButton interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (Listener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }

}
