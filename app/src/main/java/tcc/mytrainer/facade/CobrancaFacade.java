package tcc.mytrainer.facade;

import tcc.mytrainer.database.Session;
import tcc.mytrainer.model.Cobranca;
import tcc.mytrainer.model.Treinador;

/**
 * Created by marlonjhow on 04/10/17.
 */

public class CobrancaFacade {


    public static void saveOrUpdate(Cobranca cobranca) {
        if(cobranca.getId() == null){
            cobranca.setId(Session.getId());
        }
        Session.cobrancas.put(cobranca.getId(), cobranca);
        Session.mDatabase.child("Cobranca").child(cobranca.getId()).setValue(cobranca);
    }
}
