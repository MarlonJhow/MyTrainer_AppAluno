package tcc.mytrainer.menus.treinos;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;

import tcc.mytrainer.R;
import tcc.mytrainer.database.Session;
import tcc.mytrainer.model.Treino;

public class CadastroTreinoActivity extends AppCompatActivity implements AtividadeAdapter.OnItemClickListener {

    private RecyclerView rvAtividades;
    private Treino treino;
    private Context context;
    private AtividadeAdapter atividadeAdapter;

    //DECLARA COMPOENENTES
    TextView nomeTreino;
    TextView descricaoTreino;
    ImageView imageView;

    Date tempoDecorrido;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.treino_cadastro_activity);
        context = this;

        //INIT TREINO
        treino = Session.treinos.get(getIntent().getStringExtra("ID_TREINO"));
        tempoDecorrido = new Date();

        //INJETA COMPONENTES
        nomeTreino = (TextView) findViewById(R.id.textNomeTreino);
        descricaoTreino = (TextView) findViewById(R.id.textDescricaoTreino);
        imageView = (ImageView) findViewById(R.id.imageTreinoCadastro);
        //CARREGA INFORMAÇÕES
        nomeTreino.setText(treino.getNome());
        descricaoTreino.setText(treino.getDescricao());
        imageView.setImageResource(treino.getImageTreino().getDrawable());

        //CREATE RecyclerVIew
        rvAtividades = (RecyclerView) findViewById(R.id.rvAtividades);
        atividadeAdapter = new AtividadeAdapter(treino.getAtividades(), this, this);
        rvAtividades.setAdapter(atividadeAdapter);
        RecyclerView.LayoutManager layout = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        rvAtividades.setLayoutManager(layout);
    }

    //CALLBACK CADASTRO DE ATIVIDADE
    @Override
    public void onItemClick(View view, final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        Long diferenca = tempoDecorrido.getTime() - new Date().getTime();
        int qunatidade = treino.getAtividades().size();
        long diffSeconds = (diferenca / 1000 % 60) * -1;
        long diffMinutes = (diferenca / (60 * 1000) % 60) * -1;
        long diffHours = (diferenca / (60 * 60 * 1000) % 24) * -1;



        builder.setMessage("Você acabou de concluir o treino '"+ treino.getNome()
                +"'\n Tempo médio: H "+diffHours/qunatidade+" Min "+diffMinutes/qunatidade+" Sec "+diffSeconds/qunatidade
                +"\n Tempo total: H "+diffHours+" Min "+diffMinutes+" Sec "+diffSeconds )
                .setTitle("TREINO CONCLUÍDO");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
