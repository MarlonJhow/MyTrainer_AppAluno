package tcc.mytrainer.database;

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
    public static Aluno aluno;
    public static HashMap<String, Treinador> treinadores = new HashMap<String, Treinador>();
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
        mDatabase.child("Aluno").child(keyTreinador).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                aluno = dataSnapshot.getValue(Aluno.class);
                //CARREGA ENTIDADES RELACIONADAS A TREINADOR
                bindTreinadores();
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
        if (aluno != null) {
            for (String idTreino : aluno.getIdTreinos().values()) {
                mDatabase.child("Treino").child(idTreino).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Treino treino = dataSnapshot.getValue(Treino.class);
                        if (aluno.getIdTreinos().containsKey(treino.getId())) {
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

    private static void bindTreinadores() {
        if (aluno != null) {
            for (String idTreinador : aluno.getIdTreinadores().values()) {
                mDatabase.child("Treinador").child(idTreinador).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Treinador treinador = dataSnapshot.getValue(Treinador.class);
                        if (aluno.getIdTreinadores().containsKey(treinador.getId())) {
                            treinadores.put(treinador.getId(), treinador);
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
        if (aluno != null) {
            for (String idCobranca : aluno.getIdCobrancas().values()) {
                mDatabase.child("Cobranca").child(idCobranca).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Cobranca cobranca = dataSnapshot.getValue(Cobranca.class);
                        if (aluno.getIdCobrancas().containsKey(cobranca.getId())) {
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
