package tcc.mytrainer.facade;

import tcc.mytrainer.database.Session;
import tcc.mytrainer.model.Treino;

/**
 * Created by Marlon on 27/08/2017.
 */

public class TreinoFacade {

    public static void salvarTreino(Treino treino) {
        if (treino.getId() == null) {
            treino.setId(Session.getId());
        }

        //UPDATE FK Treinador
        Session.treinador.getIdTreinos().put(treino.getId(),treino.getId());
        Session.mDatabase
                .child("Treino")
                .child(treino.getId())
                .setValue(treino);

        //UPDATE Treino;
        Session.treinos.put(treino.getId(), treino);
        Session.mDatabase
                .child("Treinador")
                .child(Session.treinador.getId())
                .child("idTreinos")
                .child(treino.getId())
                .setValue(treino.getId());
    }

    public static void excluirTreino(String idTreino){

        Session.treinos.remove(idTreino);
        Session.mDatabase.child("Treino").child(idTreino).removeValue();

        Session.treinador.getIdTreinos().remove(idTreino);
        Session.mDatabase
                .child("Treino")
                .child(Session.treinador.getId())
                .child("treinos")
                .child(idTreino)
                .removeValue();
    }


}
