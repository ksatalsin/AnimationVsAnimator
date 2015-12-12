package com.princeparadoxes.animationvsanimator;

import android.support.annotation.DrawableRes;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.princeparadoxes.animationvsanimator.misc.OnEndAnimationListener;

/**
 * Created by Danil on 17.11.2015.
 */
public class LikeAnimationV2 {
    public static final long SHOW_DURATION = 200;
    public static final long TO_NORMAL_DURATION = 100;
    public static final long HIDE_DURATION = 200;
    public static final long HIDE_DELAY = 400;

    public static void likeAnimation(@DrawableRes int icon,
                                     final ImageView imageView) {
        imageView.setImageResource(icon);
        imageView.setVisibility(View.VISIBLE);
        AnimationSet animationSet = new AnimationSet(false);
        animationSet.addAnimation(showAnimationSet());
        animationSet.addAnimation(toNormalAnimationSet());
        animationSet.addAnimation(hideAnimationSet());
        animationSet.setAnimationListener(new OnEndAnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                imageView.setVisibility(View.GONE);
            }
        });
        imageView.startAnimation(animationSet);
    }

    private static AnimationSet showAnimationSet() {
        AlphaAnimation showAlphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        ScaleAnimation showScaleAnimation = new ScaleAnimation(
                0.2f, 1.4f, 0.2f, 1.4f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        AnimationSet set = new AnimationSet(false);
        set.addAnimation(showAlphaAnimation);
        set.addAnimation(showScaleAnimation);
        set.setDuration(SHOW_DURATION);
        return set;
    }

    private static AnimationSet hideAnimationSet() {
        AlphaAnimation hideAlphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        ScaleAnimation hideScaleAnimation = new ScaleAnimation(
                1.0f, 0.2f, 1.0f, 0.2f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        AnimationSet set = new AnimationSet(false);
        set.setDuration(HIDE_DURATION);
        set.addAnimation(hideAlphaAnimation);
        set.addAnimation(hideScaleAnimation);
        set.setStartOffset(SHOW_DURATION + TO_NORMAL_DURATION + HIDE_DELAY);
        return set;
    }


    private static AnimationSet toNormalAnimationSet() {
        ScaleAnimation toNormalScaleAnimation = new ScaleAnimation(
                1.4f, 1.0f, 1.4f, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        AnimationSet set = new AnimationSet(false);
        set.addAnimation(toNormalScaleAnimation);
        set.setDuration(TO_NORMAL_DURATION);
        set.setStartOffset(SHOW_DURATION);
        return set;
    }
}
