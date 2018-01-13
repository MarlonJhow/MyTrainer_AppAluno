package tcc.mytrainer.enums;

/**
 * Created by Marlon on 15/10/2017.
 */
import tcc.mytrainer.R;

public enum ImageTreino {
    ic_exercise(R.drawable.ic_exercise),
    ic_exercise1(R.drawable.ic_exercise1),
    ic_exercise2(R.drawable.ic_exercise2),
    ic_exercise3(R.drawable.ic_exercise3),
    ic_exercise4(R.drawable.ic_exercise4),
    ic_exercise5(R.drawable.ic_exercise5),
    ic_exercise6(R.drawable.ic_exercise6),
    ic_exercise7(R.drawable.ic_exercise7),
    ic_exercise8(R.drawable.ic_exercise8),
    ic_exercise9(R.drawable.ic_exercise9),
    ic_exercise10(R.drawable.ic_exercise10),
    ic_exercise11(R.drawable.ic_exercise11),
    ic_exercise12(R.drawable.ic_exercise12),
    ic_exercise13(R.drawable.ic_exercise13),
    ic_exercise14(R.drawable.ic_exercise14)
    ;

    private int Drawable;

    ImageTreino(int ic_exercise) {
        Drawable = ic_exercise;

    }

    public int getDrawable(){
        return Drawable;
    }

}
