package tcc.mytrainer.menus.treinos;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import tcc.mytrainer.R;
import tcc.mytrainer.enums.ImageTreino;

/**
 * Created by Marlon on 04/09/2017.
 */

public class DialogSelectImage extends DialogFragment {


    DialogSelectImageListener mListener;

    public interface DialogSelectImageListener {
        public void onDialogPositiveClickSelectImage(DialogSelectImage dialog);
    }

    //ATRIBUTOS GRID
    public ImageTreino imageTreino;

    @Override
    public Dialog onCreateDialog(Bundle bundle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View grid = inflater.inflate(R.layout.treino_dialog_imagem, null);
        GridView gridview = (GridView) grid.findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(getActivity()));
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                imageTreino = (ImageTreino) adapterView.getAdapter().getItem(i);
                mListener.onDialogPositiveClickSelectImage(DialogSelectImage.this);
            }
        });

        //LISTENERS BUTTON
        builder.setView(grid);
                // Add action buttons
//                .setPositiveButton("Cadastrar", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int id) {
//                        mListener.onDialogPositiveClickSelectImage(DialogSelectImage.this);
//                    }
//                })
//                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        DialogSelectImage.this.getDialog().cancel();
//                    }
//                });

        return builder.create();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callbackRecyclerViewButton interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (DialogSelectImageListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }

}
