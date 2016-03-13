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
 * Created by Danil on 18.11.2015.
 */
public class AnimationExample {
    public static final long SHOW_DURATION = 200;
    public static final long TO_NORMAL_DURATION = 100;
    public static final long HIDE_DURATION = 200;
    public static final long HIDE_DELAY = 400;

    public static void likeAnimation(@DrawableRes int icon,
                                     final ImageView imageView) {
        imageView.setImageResource(icon);
        imageView.setVisibility(View.VISIBLE);
        AlphaAnimation showAlphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        showAlphaAnimation.setDuration(SHOW_DURATION);
        ScaleAnimation showScaleAnimation = new ScaleAnimation(0.2f, 1.4f, 0.2f, 1.4f,
                android.view.animation.Animation.RELATIVE_TO_SELF, 0.5f,
                android.view.animation.Animation.RELATIVE_TO_SELF, 0.5f);
        showScaleAnimation.setDuration(SHOW_DURATION);
        AnimationSet showAnimationSet = new AnimationSet(false);
        showAnimationSet.addAnimation(showAlphaAnimation);
        showAnimationSet.addAnimation(showScaleAnimation);
        showAnimationSet.setAnimationListener(new OnEndAnimationListener() {
            @Override
            public void onAnimationEnd(android.view.animation.Animation animation) {
                ScaleAnimation toNormalScaleAnimation = new ScaleAnimation(1.4f, 1.0f, 1.4f, 1.0f,
                        android.view.animation.Animation.RELATIVE_TO_SELF, 0.5f,
                        android.view.animation.Animation.RELATIVE_TO_SELF, 0.5f);
                toNormalScaleAnimation.setDuration(TO_NORMAL_DURATION);
                toNormalScaleAnimation.setAnimationListener(new OnEndAnimationListener() {
                    @Override
                    public void onAnimationEnd(android.view.animation.Animation animation) {
                        AlphaAnimation hideAlphaAnimation = new AlphaAnimation(1.0f, 0.0f);
                        hideAlphaAnimation.setDuration(HIDE_DURATION);
                        ScaleAnimation hideScaleAnimation = new ScaleAnimation(1.0f, 0.2f, 1.0f, 0.2f,
                                android.view.animation.Animation.RELATIVE_TO_SELF, 0.5f,
                                android.view.animation.Animation.RELATIVE_TO_SELF, 0.5f);
                        hideScaleAnimation.setDuration(HIDE_DURATION);
                        AnimationSet hideAnimationSet = new AnimationSet(false);
                        hideAnimationSet.setStartOffset(HIDE_DELAY);
                        hideAnimationSet.addAnimation(hideAlphaAnimation);
                        hideAnimationSet.addAnimation(hideScaleAnimation);
                        hideAnimationSet.setAnimationListener(new OnEndAnimationListener() {
                            @Override
                            public void onAnimationEnd(android.view.animation.Animation animation) {
                                imageView.setVisibility(View.GONE);
                            }
                        });
                        imageView.startAnimation(hideAnimationSet);
                    }
                });
                imageView.startAnimation(toNormalScaleAnimation);
            }
        });
        imageView.startAnimation(showAnimationSet);
    }
}
