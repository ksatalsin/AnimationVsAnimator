package com.princeparadoxes.animationvsanimator.scroll;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;

import com.princeparadoxes.animationvsanimator.misc.LayoutParamsUtils;

/**
 * Created by Danil on 02.03.2016.
 */
public class AnimatorUtils {
    private static final int DEFAULT_DURATION = 300;

    public static Animator heightAnimator(int start, int end, View view) {
        return heightAnimator(start, end, view, DEFAULT_DURATION);
    }

    public static Animator heightAnimator(int start, int end, final View view, int duration) {
        ValueAnimator heightAnimator = ValueAnimator.ofInt(start, end);
        heightAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                LayoutParamsUtils.setHeight(view, valueAnimator.getAnimatedValue());

            }
        });
        heightAnimator.setDuration(duration);
        heightAnimator.addListener(getLayerTypeListener(view));
        return heightAnimator;
    }

    public static Animator translationYAnimator(int end, final View view) {
        return translationYAnimator(view.getTranslationY(), end, view, DEFAULT_DURATION);
    }

    public static Animator translationYAnimator(final float start, int end, final View view, int duration) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationY", end);
        animator.setDuration(duration);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                view.setTranslationY(start);
            }
        });
        return animator;
    }

    public static Animator scrollAnimator(int start, int end, View view) {
        return scrollAnimator(start, end, view, DEFAULT_DURATION);
    }

    public static Animator scrollAnimator(int start, int end, final View view, int duration) {
        ValueAnimator scrollAnimator = ValueAnimator.ofInt(start, end);
        scrollAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                view.scrollTo(0, (int) valueAnimator.getAnimatedValue());
            }
        });
        scrollAnimator.setDuration(duration);
        scrollAnimator.addListener(getLayerTypeListener(view));
        return scrollAnimator;
    }

    public static Animator alphaAnimator(int start, int end, View view) {
        return alphaAnimator(start, end, view, DEFAULT_DURATION);
    }

    public static Animator alphaAnimator(int start, int end, View view, int duration) {
        ValueAnimator alphaAnimator = ObjectAnimator.ofFloat(view, "alpha", start, end);
        alphaAnimator.setDuration(duration);
        alphaAnimator.addListener(getLayerTypeListener(view));
        return alphaAnimator;
    }

    private static AnimatorListenerAdapter getLayerTypeListener(final View view) {
        return new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                view.setLayerType(View.LAYER_TYPE_HARDWARE, null);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                view.setLayerType(View.LAYER_TYPE_NONE, null);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                view.setLayerType(View.LAYER_TYPE_NONE, null);
            }
        };
    }
}
