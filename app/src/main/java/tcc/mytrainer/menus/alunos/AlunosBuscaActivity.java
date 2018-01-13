package tcc.mytrainer.menus.alunos;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;

import tcc.mytrainer.R;
import tcc.mytrainer.database.Session;
import tcc.mytrainer.facade.AlunoFacade;
import tcc.mytrainer.model.Aluno;
import tcc.mytrainer.util.StringUtil;

/**
 * Created by Marlon on 11/01/2018.
 */

public class AlunosBuscaActivity extends AppCompatActivity {

    Context context;

    //COMPONENTES
    private ImageView fotoAluno;
    private TextView nomeAluno;
    private TextView emailAluno;

    private Aluno aluno;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alunos_busca_activity);
        context = this;

        //DADOS DO CARD
        fotoAluno = (ImageView) findViewById(R.id.alunoCardImageView);
        nomeAluno = (TextView) findViewById(R.id.alunoCardNomeText);
        emailAluno = (TextView) findViewById(R.id.alunoCardEmailText);

        //FOTO ALUNO
        ImageView searchImage = (ImageView) findViewById(R.id.CobrancaCadastroImgSearch);
        searchImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                procurarAluno();
            }
        });

        //BOTÃO ADICIONAR
        Button adicionar = (Button) findViewById(R.id.adicionarAluno);
        adicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarAluno();
            }
        });

        //BOTÃO CANCELAR
        Button cancelar = (Button) findViewById(R.id.buttonCancelar);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void salvarAluno() {
        if (aluno == null) {
            Toast.makeText(context, "Nenhum aluno selecionado", Toast.LENGTH_LONG).show();
        } else {
            Session.alunos.put(aluno.getId(), aluno);
            AlunoFacade.vincularAluno(aluno);
            finish();
        }
    }

    private void procurarAluno() {
        EditText campoBusca = (EditText) findViewById(R.id.textBuscaAluno);
        String email = campoBusca.getText().toString();
        email = StringUtil.formatEmailToId(email);
        Task task = Session.getAluno(email);
        task.addOnCompleteListener(new OnCompleteListener<Aluno>() {
            @Override
            public void onComplete(@NonNull Task task) {
                aluno = (Aluno) task.getResult();
                if (aluno != null) {
                    Picasso.with(context).load(aluno.getFotoUrl()).into(fotoAluno);
                    nomeAluno.setText(aluno.getNome());
                    emailAluno.setText(aluno.getEmail());
                } else {
                    Toast.makeText(context, "Nenhum aluno encontrado com esse e-mail", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
