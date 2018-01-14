package tcc.mytrainer.facade;

import tcc.mytrainer.database.Session;
import tcc.mytrainer.model.Aluno;
import tcc.mytrainer.model.Cobranca;
import tcc.mytrainer.model.Treinador;

/**
 * Created by Marlon on 12/01/2018.
 */

public class AlunoFacade {

    public static void saveOrUpdate(Aluno aluno){
        Session.mDatabase.child("Aluno").child(aluno.getId()).setValue(aluno);
    }

}
