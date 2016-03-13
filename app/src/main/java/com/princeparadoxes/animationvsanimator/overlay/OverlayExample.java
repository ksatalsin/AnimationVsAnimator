package com.princeparadoxes.animationvsanimator.overlay;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.view.ViewGroupOverlay;
import android.widget.ImageView;

/**
 * Created by Danil on 17.11.2015.
 */
@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
public class OverlayExample {
    public static final long SHOW_DURATION = 400;
    public static final long TO_NORMAL_DURATION = 200;
    public static final long HIDE_DURATION = 400;
    public static final long HIDE_DELAY = 400;

    private static boolean isAnimate = false;

    public static void likeAnimation(ViewGroupOverlay overlay, final ImageView view) {
        if (view != null && !isAnimate) {
            AnimatorSet set = new AnimatorSet();
            set.playSequentially(
                    showAnimatorSet(view),
                    toNormalAnimatorSet(view),
                    hideAnimatorSet(view));
            set.addListener(getOverlayEndListener(overlay, view));
            set.start();
        }
    }

    private static AnimatorListenerAdapter getOverlayEndListener(
            final ViewGroupOverlay overlay, final ImageView view) {
        return new AnimatorListenerAdapter() {
            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
                isAnimate = false;
                overlay.remove(view);
                view.setLayerType(View.LAYER_TYPE_NONE, null);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                isAnimate = true;
                view.setLayerType(View.LAYER_TYPE_HARDWARE, null);
                overlay.add(view);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                isAnimate = false;
                overlay.remove(view);
                view.setLayerType(View.LAYER_TYPE_NONE, null);
            }
        };
    }

    private static AnimatorSet showAnimatorSet(View view) {
        AnimatorSet set = new AnimatorSet();
        ValueAnimator showAlpha = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
        ValueAnimator showScaleX = ObjectAnimator.ofFloat(view, "scaleX", 0.2f, 5f);
        ValueAnimator showScaleY = ObjectAnimator.ofFloat(view, "scaleY", 0.2f, 5f);
        set.play(showAlpha).with(showScaleX).with(showScaleY);
        set.setDuration(SHOW_DURATION);
        return set;
    }

    private static AnimatorSet toNormalAnimatorSet(View view) {
        AnimatorSet set = new AnimatorSet();
        ValueAnimator toNormalScaleX = ObjectAnimator.ofFloat(view, "scaleX", 5f, 1f);
        ValueAnimator toNormalScaleY = ObjectAnimator.ofFloat(view, "scaleY", 5f, 1f);
        set.play(toNormalScaleX).with(toNormalScaleY);
        set.setDuration(TO_NORMAL_DURATION);
        return set;
    }

    private static AnimatorSet hideAnimatorSet(View view) {
        AnimatorSet set = new AnimatorSet();
        ValueAnimator hideAlpha = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f);
        ValueAnimator hideScaleX = ObjectAnimator.ofFloat(view, "scaleX", 1f, 0.2f);
        ValueAnimator hideScaleY = ObjectAnimator.ofFloat(view, "scaleY", 1f, 0.2f);
        set.play(hideAlpha).with(hideScaleX).with(hideScaleY);
        set.setStartDelay(HIDE_DELAY);
        set.setDuration(HIDE_DURATION);
        return set;
    }
}
