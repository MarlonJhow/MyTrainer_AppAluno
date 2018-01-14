package tcc.mytrainer.util;

import br.com.uol.pslibs.checkout_in_app.transparent.vo.PSTransparentDefaultRequest;

/**
 * Created by Marlon on 14/01/2018.
 */

public class CheckoutPagseguro{

    public static String SELLER_TOKEN = "8A019ACD197F417C8D7B3778DD0B37E1";
    public static String SELLER_EMAIL = "jhow.mjy@gmail.com";

    public static PSTransparentDefaultRequest build(String valor, String ccv, String nCartao, String ano, String mes){
        PSTransparentDefaultRequest psTransparentDefaultRequest = new PSTransparentDefaultRequest();
        psTransparentDefaultRequest
                //(String) - Numero do documento
                .setDocumentNumber("08512122986")
                //(String) - Nome do comprador
                .setName("Marlon Jhow Yeung")
                //(String) - Email do comprador
                .setEmail("marlon.jhow@live.com")
                //(String) - Codigo de area do telefone do comprador
                .setAreaCode("41")
                //(String) - Numero de telefone do comprador
                .setPhoneNumber("999544514")
                //(String) - Rua do comprador
                .setStreet("Roque Lazarotto")
                //(String) - Complemento do endereço do comprador
                .setAddressComplement("apto")
                //(String) - Numero da casa do comprador
                .setAddressNumber("376")
                //(String) - Bairro do comprador
                .setDistrict("Boa Vista")
                //(String) - Cidade do comprador
                .setCity("Curitiba")
                //(String) - Estado do comprador
                .setState("PR")
                //(String) - País do comprador
                .setCountry("Brazil")
                //(String) - CEP do comprador
                .setPostalCode("82560420")
                //(String) - Minimo R$ 1.00 - Valor total da transação
                .setTotalValue(valor)
                //(String) - Valor unitário do produto
                .setAmount("1.00")
                //(String) - Maximo 100 caracteres - Descrição do pagamento
                .setDescriptionPayment("Treino")
                //(int) - Quantidade de produtos escolhidos
                .setQuantity(1)
                //(String) - Numero do cartão de credito
                .setCreditCard(nCartao)
                //(String) - Codigo de segurança do cartão de credito
                .setCvv(ccv)
                //(String) - Mes de expiração do cartão de credito
                .setExpMonth(mes)
                //(String) - Ano de expiração do cartão de credito
                .setExpYear(ano)
                //(String) - formato: dd/MM/yyyy - Data de nascimento do comprador
                .setBirthDate("11/11/1991");
        //(Installments) - Objeto que é retornado no serviço de parcelamento que corresponde a parcela escolhida
        //.setInstallments(installmentVO);
        return psTransparentDefaultRequest;
    }

}
