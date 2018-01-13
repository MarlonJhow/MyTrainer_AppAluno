package tcc.mytrainer.facade;

import tcc.mytrainer.database.Session;
import tcc.mytrainer.model.Cobranca;
import tcc.mytrainer.model.Treinador;

/**
 * Created by marlonjhow on 04/10/17.
 */

public class CobrancaFacade {


    public static void saveOrUpdate(Cobranca cobranca) {
        Treinador treinador = Session.treinador;
        cobranca.setIdTreinador(treinador.getId());

        if(cobranca.getId() == null){
            cobranca.setId(Session.getId());
        }

        //UPDATE TREINADOR FK COBRANCA
        Session.treinador.getIdCobrancas().put(cobranca.getId() , cobranca.getId());
        Session.mDatabase.child("Treinador").child(Session.treinador.getId()).child("idCobrancas").child(cobranca.getId()).setValue(cobranca.getId());

        //UPDATE ALUNO FK COBRANCA
        Session.alunos.get(cobranca.getIdAluno()).getIdCobrancas().put(cobranca.getId(), cobranca.getId());
        Session.mDatabase.child("Aluno").child(cobranca.getIdAluno()).child("idCobrancas").child(cobranca.getId()).setValue(cobranca.getId());

        //UPDATE COBRANCA
        Session.cobrancas.put(cobranca.getId(), cobranca);
        Session.mDatabase.child("Cobranca").child(cobranca.getId()).setValue(cobranca);
    }
}
