package tcc.mytrainer.menus.cobranca;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import tcc.mytrainer.R;
import tcc.mytrainer.database.Session;
import tcc.mytrainer.enums.Periodo;
import tcc.mytrainer.facade.CobrancaFacade;
import tcc.mytrainer.model.Aluno;
import tcc.mytrainer.model.Cobranca;

/**
 * Created by Marlon on 15/09/2017.
 */

public class CadastroCobrancaActivity extends AppCompatActivity implements ListAlunosDialog.ListAlunosDialogListener, DatePickerDialog.OnDateSetListener {

    private Context context;

    //COMPONENTES
    private ImageView searchImage;
    private TextView txtNomeAluno;
    private TextView txtVencimento;
    private TextView txtValor;
    private Spinner spinnerPeriodo;
    private DatePickerDialog datePickerDialog;
    private Button btCancelar;
    private Button btSalvar;

    //DTO
    private Aluno aluno;
    private Date vencimento;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cobranca_cadastro_activity);
        context = this;

        //NOME ALUNO
        txtNomeAluno = (TextView) findViewById(R.id.CobrancaCadastroTxtNomeAluno);

        //FOTO ALUNO
        searchImage = (ImageView) findViewById(R.id.CobrancaCadastroImgSearch);
        searchImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogAddAtividade = new ListAlunosDialog();
                dialogAddAtividade.show(getSupportFragmentManager(), "searchAluno");
            }
        });

        //VENCIMENTO
        Calendar calendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(
                context, CadastroCobrancaActivity.this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        txtVencimento = (TextView) findViewById(R.id.CobrancaCadastroTxtVencimento);
        txtVencimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            datePickerDialog.setOnDateSetListener(CadastroCobrancaActivity.this);
        }

        //TEXT VALOR
        txtValor = (TextView) findViewById(R.id.CobrancaCadastroTxtValor);

        //BOTÃO CANCELAR
        btCancelar = (Button) findViewById(R.id.CobrancaCadastroBtCancelar);
        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //BOTÃO SALVAR
        btSalvar = (Button) findViewById(R.id.CobrancaCadastroBtSalvar);
        btSalvar.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (validar()) {
                    Double valor = Double.parseDouble(txtValor.getText().toString());
                    Cobranca cobranca = new Cobranca();
                    cobranca.setIdAluno(aluno.getId());
                    cobranca.setPeriodo(Periodo.UNICO);
                    cobranca.setVencimento(vencimento);
                    cobranca.setValor(valor);
                    CobrancaFacade.saveOrUpdate(cobranca);
                    finish();
                }

            }
        });

    }

    private boolean validar() {
        String messagem = null;

        if (aluno == null) {
            messagem = "ALUNO NÃO SELECIONADO";
        }

        if (vencimento == null) {
            messagem = "VENCIMENTO NÃO PREENCHIDO";
        }

        if (txtValor.getText().toString().isEmpty()) {
            messagem = "VALOR NÃO PREENCHIDO";
        }

        if (messagem != null) {
            Toast.makeText(context, messagem, Toast.LENGTH_LONG).show();
            return false;
        } else {
            return true;
        }

    }

    @Override
    public void getAlunoId(String idAluno) {
        //SET ALUNO
        aluno = Session.alunos.get(idAluno);

        //SET FOTO
        String urlFoto = aluno.getFotoUrl();
        Picasso.with(context).load(urlFoto).into(searchImage);

        //SET NOME
        txtNomeAluno.setText(aluno.getNome());
    }

    @Override
    public void onDateSet(DatePicker datePicker, int ano, int mes, int dia) {
        mes++;  //ESSE LAZARENTO COMEÇA EM 0

        txtVencimento.setText("" + dia + "/" + mes + "/" + ano);

        try {
            vencimento = new SimpleDateFormat("yyyyMMdd").parse("" + ano + mes + ano);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
