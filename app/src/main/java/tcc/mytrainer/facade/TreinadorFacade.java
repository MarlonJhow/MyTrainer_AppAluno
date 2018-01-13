package tcc.mytrainer.facade;

import tcc.mytrainer.database.Session;
import tcc.mytrainer.model.Treinador;

/**
 * Created by Marlon on 12/01/2018.
 */

public class TreinadorFacade {
    public static void saveOrUpdate(Treinador treinador) {
        Session.mDatabase.child("Treinador").child(treinador.getId()).setValue(treinador);
    }
}
