package tcc.mytrainer.enums;

import tcc.mytrainer.model.Cobranca;

/**
 * Created by Marlon on 14/10/2017.
 */

public enum Periodo {
    UNICO("Unico"), MENSAL("Mensal");

    String label;

    Periodo(String label){
        this.label = label;
    }

    @Override
    public String toString() {
        return this.label;
    }

    public static Periodo get(String entrada) {
        Periodo periodo;

        periodo = entrada.equals(UNICO) ? UNICO : MENSAL;

        return periodo;
    }
}
