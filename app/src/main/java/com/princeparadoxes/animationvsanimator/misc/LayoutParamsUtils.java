package com.princeparadoxes.animationvsanimator.misc;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Danil on 02.03.2016.
 */
public class LayoutParamsUtils {

    public static void setHeight(View view, Object height) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (int) height;
        view.setLayoutParams(layoutParams);
    }
}
