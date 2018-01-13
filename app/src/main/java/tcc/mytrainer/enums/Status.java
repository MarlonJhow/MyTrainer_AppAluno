package tcc.mytrainer.enums;

/**
 * Created by Marlon on 14/10/2017.
 */

public enum Status {

    PENDENTE("Pendente"), PAGO("Pago");

    String label;

    Status(String label){
        this.label = label;
    }

    @Override
    public String toString() {
        return this.label;
    }

}
