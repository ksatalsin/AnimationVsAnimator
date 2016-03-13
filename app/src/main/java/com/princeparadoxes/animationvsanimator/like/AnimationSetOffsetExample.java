package com.princeparadoxes.animationvsanimator.like;

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
public class AnimationSetOffsetExample {
    public static final long SHOW_DURATION = 200;
    public static final long TO_NORMAL_DURATION = 100;
    public static final long HIDE_DURATION = 200;
    public static final long HIDE_DELAY = 400;

    public static void likeAnimation(@DrawableRes int icon,
                                     final ImageView imageView) {
        imageView.setImageResource(icon);
        imageView.setVisibility(View.VISIBLE);
        AnimationSet animationSet = new AnimationSet(false);
        animationSet.addAnimation(showAlphaAnimation());
        animationSet.addAnimation(showScaleAnimation());
        animationSet.addAnimation(toNormalScaleAnimation());
        animationSet.addAnimation(hideAlphaAnimation());
        animationSet.addAnimation(hideScaleAnimation());
        animationSet.setAnimationListener(new OnEndAnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                imageView.setVisibility(View.GONE);
            }
        });
        imageView.startAnimation(animationSet);
    }

    private static Animation showAlphaAnimation() {
        AlphaAnimation showAlphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        showAlphaAnimation.setDuration(SHOW_DURATION);
        return showAlphaAnimation;
    }

    private static Animation showScaleAnimation() {
        ScaleAnimation showScaleAnimation = new ScaleAnimation(
                0.2f, 1.4f, 0.2f, 1.4f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        showScaleAnimation.setDuration(SHOW_DURATION);
        return showScaleAnimation;
    }

    private static Animation toNormalScaleAnimation() {
        ScaleAnimation toNormalScaleAnimation = new ScaleAnimation(
                1.4f, 1.0f, 1.4f, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        toNormalScaleAnimation.setDuration(TO_NORMAL_DURATION);
        toNormalScaleAnimation.setStartOffset(SHOW_DURATION);
        return toNormalScaleAnimation;
    }

    private static Animation hideAlphaAnimation() {
        AlphaAnimation hideAlphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        hideAlphaAnimation.setDuration(HIDE_DURATION);
        hideAlphaAnimation.setStartOffset(SHOW_DURATION + TO_NORMAL_DURATION + HIDE_DELAY);
        return hideAlphaAnimation;
    }

    private static Animation hideScaleAnimation() {
        ScaleAnimation hideScaleAnimation = new ScaleAnimation(
                1.0f, 0.2f, 1.0f, 0.2f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        hideScaleAnimation.setDuration(HIDE_DURATION);
        hideScaleAnimation.setStartOffset(SHOW_DURATION + TO_NORMAL_DURATION + HIDE_DELAY);
        return hideScaleAnimation;
    }

}
