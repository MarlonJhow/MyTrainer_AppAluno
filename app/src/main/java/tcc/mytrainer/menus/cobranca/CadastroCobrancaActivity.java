package tcc.mytrainer.menus.cobranca;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Calendar;

import br.com.uol.pslibs.checkout_in_app.PSCheckout;
import br.com.uol.pslibs.checkout_in_app.transparent.listener.PSCheckoutListener;
import br.com.uol.pslibs.checkout_in_app.transparent.vo.InstallmentVO;
import br.com.uol.pslibs.checkout_in_app.transparent.vo.PSCheckoutResponse;
import br.com.uol.pslibs.checkout_in_app.transparent.vo.PSTransparentDefaultRequest;
import br.com.uol.pslibs.checkout_in_app.wallet.util.PSCheckoutConfig;
import tcc.mytrainer.R;
import tcc.mytrainer.database.Session;
import tcc.mytrainer.enums.Status;
import tcc.mytrainer.facade.CobrancaFacade;
import tcc.mytrainer.model.Cobranca;
import tcc.mytrainer.model.Treinador;
import tcc.mytrainer.util.CheckoutPagseguro;

/**
 * Created by Marlon on 15/09/2017.
 */

public class CadastroCobrancaActivity extends AppCompatActivity implements PSCheckoutListener {

    private Context context;

    //COMPONENTES
    private ImageView fotoTreinador;
    private TextView txtNomeTreinador;
    private TextView txtValor;
    private EditText nCartao;
    private EditText vencimentoCartao;
    private Button btCancelar;
    private Button btSalvar;

    //DTO
    private Treinador treinador;
    private Cobranca cobranca;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cobranca_cadastro_activity);
        context = this;

        //CARREGA ENTIDADES
        cobranca = Session.cobrancas.get(getIntent().getStringExtra("ID_COBRANCA"));
        treinador = Session.treinadores.get(cobranca.getIdTreinador());

        //INJETA COMPONENTES
        fotoTreinador = (ImageView) findViewById(R.id.FotoTreinador);
        txtNomeTreinador = (TextView) findViewById(R.id.textNomeTreinador);
        txtValor = (TextView) findViewById(R.id.textValorPagar);
        nCartao = (EditText) findViewById(R.id.textNCartao);
        vencimentoCartao = (EditText) findViewById(R.id.textVencimentoCartao);

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
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Código de Segurança");

                final EditText input = new EditText(context);
                input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                builder.setView(input);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String ccv = input.getText().toString();
                        pagar(ccv);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        //PRIMEIRO CARD
        Picasso.with(context).load(treinador.getFotoUrl()).into(fotoTreinador);
        txtNomeTreinador.setText(treinador.getNome());
        txtValor.setText("R$ "+String.format("%.2f", cobranca.getValor()));
    }

    public void pagar(String ccv) {

        String anoValidade = vencimentoCartao.getText().toString().substring(3,5);
        String mesValidade = vencimentoCartao.getText().toString().substring(0,2);

        //INIT CHECKOUT
        PSCheckoutConfig psCheckoutConfig = new PSCheckoutConfig();
        psCheckoutConfig.setSellerEmail(CheckoutPagseguro.SELLER_EMAIL);
        psCheckoutConfig.setSellerToken(CheckoutPagseguro.SELLER_TOKEN);
        //Informe o fragment container
        psCheckoutConfig.setContainer(R.id.pagamento_fragment);

        //Inicializa apenas os recursos de pagamento transparente
        PSCheckout.initTransparent(this, psCheckoutConfig);
        PSTransparentDefaultRequest psTransparentDefaultRequest = CheckoutPagseguro.build(String.valueOf(cobranca.getValor()), ccv, nCartao.getText().toString(), anoValidade, mesValidade);
        PSCheckout.payTransparentDefault(psTransparentDefaultRequest, this, this);
        View view  = findViewById(R.id.layoutPagamento);
        Snackbar bar = Snackbar.make(view, "Processando pagameto", Snackbar.LENGTH_LONG);
        Snackbar.SnackbarLayout snack_view = (Snackbar.SnackbarLayout) bar.getView();
        bar.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PSCheckout.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @Override
    public void onSuccess(PSCheckoutResponse psCheckoutResponse) {
        cobranca.setStatus(Status.PAGO);
        CobrancaFacade.saveOrUpdate(cobranca);

        Intent resultIntent = new Intent();
        resultIntent.putExtra("SUCESSO_TRANSACAO", "PAGO");
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }

    @Override
    public void onFailure(PSCheckoutResponse psCheckoutResponse) {
        View view = findViewById(R.id.layoutPagamento);
        Snackbar bar = Snackbar.make(view, "Erro ao realizar pagamento", Snackbar.LENGTH_LONG);
        Snackbar.SnackbarLayout snack_view = (Snackbar.SnackbarLayout) bar.getView();
        bar.show();

    }

    @Override
    public void onProcessing() {

    }

}
