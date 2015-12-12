package com.princeparadoxes.animationvsanimator;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.widget.ImageView;

import com.princeparadoxes.animationvsanimator.misc.OnEndAnimatorListener;

/**
 * Created by Danil on 17.11.2015.
 */
public class LikeAnimationV3 {
    public static final long SHOW_DURATION = 200;
    public static final long TO_NORMAL_DURATION = 100;
    public static final long HIDE_DURATION = 200;
    public static final long HIDE_DELAY = 400;

    public static void likeAnimation(@DrawableRes int icon,
                                     final ImageView view) {
        if (view != null) {
            beforeLikeAnimation(view, icon);
            AnimatorSet set = new AnimatorSet();
            set.playSequentially(
                    showAnimatorSet(view),
                    toNormalAnimatorSet(view),
                    hideAnimatorSet(view));
            set.addListener(getLikeEndListener(view));
            set.start();
        }
    }

    private static void beforeLikeAnimation(ImageView view, int icon) {
        view.setVisibility(View.VISIBLE);
        view.setImageResource(icon);
        view.setLayerType(View.LAYER_TYPE_HARDWARE, null);
    }

    private static OnEndAnimatorListener getLikeEndListener(final ImageView view) {
        return new OnEndAnimatorListener() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.GONE);
                view.setImageDrawable(null);
                view.setLayerType(View.LAYER_TYPE_NONE, null);
            }
        };
    }

    private static AnimatorSet showAnimatorSet(View view) {
        AnimatorSet set = new AnimatorSet();
        ValueAnimator showAlpha = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
        ValueAnimator showScaleX = ObjectAnimator.ofFloat(view, "scaleX", 0.2f, 1.4f);
        ValueAnimator showScaleY = ObjectAnimator.ofFloat(view, "scaleY", 0.2f, 1.4f);
        set.play(showAlpha).with(showScaleX).with(showScaleY);
        set.setDuration(SHOW_DURATION);
        return set;
    }

    private static AnimatorSet toNormalAnimatorSet(View view) {
        AnimatorSet set = new AnimatorSet();
        ValueAnimator toNormalScaleX = ObjectAnimator.ofFloat(view, "scaleX", 1.4f, 1f);
        ValueAnimator toNormalScaleY = ObjectAnimator.ofFloat(view, "scaleY", 1.4f, 1f);
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
