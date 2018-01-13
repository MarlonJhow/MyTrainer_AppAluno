package tcc.mytrainer.menus.alunos;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import tcc.mytrainer.R;
import tcc.mytrainer.database.Session;
import tcc.mytrainer.facade.AlunoFacade;
import tcc.mytrainer.facade.TreinadorFacade;
import tcc.mytrainer.model.Aluno;
import tcc.mytrainer.model.Treino;

/**
 * Created by Marlon on 12/01/2018.
 */

public class InfoAlunoActivity extends AppCompatActivity implements AlunoTreinoAdapter.OnItemClickListener, ListTreinosDialog.Listener {

    private Context context;

    private Aluno aluno;
    private HashMap<String, Treino> treinos = new HashMap<>();

    //COMPONENTES
    ImageView alunoFoto;
    TextView alunoNome;
    TextView alunoEmail;
    FloatingActionButton buttonDeletar;
    FloatingActionButton buttonAddTreino;
    Button buttonCancelar;
    Button buttonSalvar;
    RecyclerView rvView;
    AlunoTreinoAdapter treinoAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_aluno_activity);
        context = this;

        //CARREGAR COMPONENTES
        alunoFoto = (ImageView) findViewById(R.id.alunoFoto);
        alunoNome = (TextView) findViewById(R.id.alunoCardNomeText);
        alunoEmail = (TextView) findViewById(R.id.alunoCardEmailText);
        buttonDeletar = (FloatingActionButton) findViewById(R.id.buttonDeletarAluno);
        buttonAddTreino = (FloatingActionButton) findViewById(R.id.buttonAddTreino);
        buttonCancelar = (Button) findViewById(R.id.buttonVoltar);
        buttonSalvar = (Button) findViewById(R.id.buttonSalvar);

        //CARREGA PERFIL DO ALUNO
        String idAluno = getIntent().getStringExtra("ID_ALUNO");
        aluno = Session.alunos.get(idAluno);
        Picasso.with(context).load(aluno.getFotoUrl()).into(alunoFoto);
        alunoNome.setText(aluno.getNome());
        alunoEmail.setText(aluno.getEmail());

        //INIT Recycler View
        rvView = (RecyclerView) findViewById(R.id.rv_treinos);
        treinoAdapter = new AlunoTreinoAdapter(treinos, this, this);
        rvView.setAdapter(treinoAdapter);
        RecyclerView.LayoutManager layout = new LinearLayoutManager(context,
                LinearLayoutManager.VERTICAL, false);
        rvView.setLayoutManager(layout);

        //CARREGAR TREINOS
        for (String idTreino : aluno.getIdTreinos().values()) {
            Task task = Session.getTreino(idTreino);
            task.addOnCompleteListener(new OnCompleteListener<Treino>() {
                @Override
                public void onComplete(@NonNull Task<Treino> task) {
                    addTreinoAndInitRecyclerView(task.getResult());
                }
            });
        }

        //ADICIONAR TREINO
        buttonAddTreino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogAddAtividade = new ListTreinosDialog();
                dialogAddAtividade.show(getSupportFragmentManager(), "searchAluno");
            }
        });

        //BOTÃO CANCELAR
        buttonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //BOTÃO SALVAR
        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, String> ids = new HashMap<String, String>();
                for (Treino treino : treinos.values()) {
                    ids.put(treino.getId(), treino.getId());
                }

                aluno.setIdTreinos(ids);
                AlunoFacade.saveOrUpdate(aluno);
                finish();
            }
        });

        //BOTÃO DELETAR
        buttonDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //DESFAZ VINCULO
                Session.treinador.getIdAlunos().remove(aluno.getId());
                TreinadorFacade.saveOrUpdate(Session.treinador);

                aluno.getIdTreinadores().remove(Session.treinador.getId());
                AlunoFacade.saveOrUpdate(aluno);
                Session.alunos.remove(aluno.getId());
                finish();
            }
        });

    }

    private void addTreinoAndInitRecyclerView(Treino treino) {
        if (Session.treinos.containsKey(treino.getId())) {
            treinos.put(treino.getId(), treino);
            if (treinos.size() == aluno.getIdTreinos().size()) {
                //ATUALIZA RECYCLERVIEW
                treinoAdapter.update(new ArrayList<Treino>(treinos.values()));
                treinoAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void callbackRecyclerViewButton(String idTreino) {
        treinos.remove(idTreino);

        //ATUALIZA RECYCLERVIEW
        treinoAdapter.update(new ArrayList<Treino>(treinos.values()));
        treinoAdapter.notifyDataSetChanged();
    }

    @Override
    public void callbackModal(String idTreino) {
        //PEGA O TREINO E SALVA
        Treino treino = Session.treinos.get(idTreino);
        treinos.put(treino.getId(), treino);

        //ATUALIZA RECYCLERVIEW
        treinoAdapter.update(new ArrayList<Treino>(treinos.values()));
        treinoAdapter.notifyDataSetChanged();
    }


}
