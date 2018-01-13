package tcc.mytrainer.database;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import tcc.mytrainer.model.Aluno;
import tcc.mytrainer.model.Cobranca;
import tcc.mytrainer.model.Treinador;
import tcc.mytrainer.model.Treino;
import tcc.mytrainer.util.StringUtil;

/**
 * Created by Marlon on 02/09/2017.
 */

public class Session {

    public interface FinishLoad {
        void callback();
    }

    private static FinishLoad finishLoad;

    //INSTANCIAS DE AUTH E DATABASE
    public static FirebaseAuth mAuth = FirebaseAuth.getInstance();
    public static DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

    //INTETIDADES DO BANCO
    public static Treinador treinador;
    public static HashMap<String, Aluno> alunos = new HashMap<String, Aluno>();
    public static HashMap<String, Cobranca> cobrancas = new HashMap<String, Cobranca>();
    public static HashMap<String, Treino> treinos = new HashMap<String, Treino>();

    private static Boolean firstTime = true;

    public static void initEntitys(FinishLoad callback) {
        finishLoad = callback;
        bindTreinador();
    }


    private static void bindTreinador() {
        //OBTEM ID DO USUARIO DA SESSÃO
        String keyTreinador = StringUtil.formatEmailToId(mAuth.getCurrentUser().getEmail());
        //CRIA ID PARA O NOVO OBJETO TREINO
        mDatabase.child("Treinador").child(keyTreinador).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                treinador = dataSnapshot.getValue(Treinador.class);
                //CARREGA ENTIDADES RELACIONADAS A TREINADOR
                bindAlunos();
                bindCobrancas();
                bindTreinos();

                if (firstTime) {
                    firstTime = false;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                    }
                    finishLoad.callback();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private static void bindTreinos() {
        if (treinador != null) {
            for (String idTreino : treinador.getIdTreinos().values()) {
                mDatabase.child("Treino").child(idTreino).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Treino treino = dataSnapshot.getValue(Treino.class);
                        if (treinador.getIdTreinos().containsKey(treino.getId())) {
                            treinos.put(treino.getId(), treino);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        }
    }

    private static void bindAlunos() {
        if (treinador != null) {
            for (String idAluno : treinador.getIdAlunos().values()) {
                mDatabase.child("Aluno").child(idAluno).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Aluno aluno = dataSnapshot.getValue(Aluno.class);
                        if (treinador.getIdAlunos().containsKey(aluno.getId())) {
                            alunos.put(aluno.getId(), aluno);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        }
    }

    private static void bindCobrancas() {
        if (treinador != null) {
            for (String idCobranca : treinador.getIdCobrancas().values()) {
                mDatabase.child("Cobranca").child(idCobranca).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Cobranca cobranca = dataSnapshot.getValue(Cobranca.class);
                        if (treinador.getIdCobrancas().containsKey(cobranca.getId())) {
                            cobrancas.put(cobranca.getId(), cobranca);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        }
    }

    public static String getId() {
        return mDatabase.push().getKey();
    }

    public static Task getAluno(String email) {
        final TaskCompletionSource<Aluno> tcs = new TaskCompletionSource<>();
        mDatabase.child("Aluno").child(email).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Aluno aluno = dataSnapshot.getValue(Aluno.class);
                tcs.setResult(aluno);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return tcs.getTask();
    }

    public static Task getTreino(final String idTreino) {
        final TaskCompletionSource<Treino> tcs = new TaskCompletionSource<>();
        mDatabase.child("Treino").child(idTreino).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Treino treino = dataSnapshot.getValue(Treino.class);
                tcs.setResult(treino);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return tcs.getTask();
    }

}
