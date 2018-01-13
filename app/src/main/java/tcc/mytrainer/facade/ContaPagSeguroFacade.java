package tcc.mytrainer.facade;

import tcc.mytrainer.database.Session;
import tcc.mytrainer.model.ContaPagSeguro;

/**
 * Created by Marlon on 07/09/2017.
 */

public class ContaPagSeguroFacade {
    public static void salvarConta(ContaPagSeguro contaPagSeguro) {
        if (contaPagSeguro.getId() == null) {
            contaPagSeguro.setId(Session.getId());
        }

        Session.treinador.setContaPagSeguro(contaPagSeguro);
        Session.mDatabase.child("Treinador").child(Session.treinador.getId()).child("contaPagSeguro").setValue(contaPagSeguro);
    }
}
