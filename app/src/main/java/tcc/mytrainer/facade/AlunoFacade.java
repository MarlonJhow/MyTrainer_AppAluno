package tcc.mytrainer.facade;

import tcc.mytrainer.database.Session;
import tcc.mytrainer.model.Aluno;
import tcc.mytrainer.model.Cobranca;
import tcc.mytrainer.model.Treinador;

/**
 * Created by Marlon on 12/01/2018.
 */

public class AlunoFacade {

    public static void vincularAluno(Aluno aluno) {
        Treinador treinador = Session.treinador;

        //UPDATE TREINADOR FK COBRANCA
        Session.mDatabase.child("Treinador").child(treinador.getId()).child("idAlunos").child(aluno.getId()).setValue(aluno.getId());
        Session.mDatabase.child("Aluno").child(aluno.getId()).child("idTreinadores").child(treinador.getId()).setValue(treinador.getId());
    }

    public static void saveOrUpdate(Aluno aluno){
        Session.mDatabase.child("Aluno").child(aluno.getId()).setValue(aluno);
    }

}
