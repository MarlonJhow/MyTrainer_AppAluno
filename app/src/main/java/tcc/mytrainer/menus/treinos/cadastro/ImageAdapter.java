package tcc.mytrainer.menus.treinos.cadastro;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import tcc.mytrainer.R;
import tcc.mytrainer.enums.ImageTreino;

/**
 * Created by Marlon on 04/09/2017.
 */

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    // TODO FIX EXERCISE 12 BIKE
    private ImageTreino[] mThumbIds = {
            ImageTreino.ic_exercise,
            ImageTreino.ic_exercise1,
            ImageTreino.ic_exercise2,
            ImageTreino.ic_exercise3,
            ImageTreino.ic_exercise4,
            ImageTreino.ic_exercise5,
            ImageTreino.ic_exercise6,
            ImageTreino.ic_exercise7,
            ImageTreino.ic_exercise8,
            ImageTreino.ic_exercise9,
            ImageTreino.ic_exercise10,
            ImageTreino.ic_exercise11,
            ImageTreino.ic_exercise12,
            ImageTreino.ic_exercise13,
            ImageTreino.ic_exercise14
    };

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public ImageTreino getItem(int position) {
        return mThumbIds[position];
    }

    public long getItemId(int position) {
        return mThumbIds[position].getDrawable();
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position].getDrawable());

        return imageView;
    }

}
