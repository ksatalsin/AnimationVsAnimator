package com.princeparadoxes.animationvsanimator.scroll;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.princeparadoxes.animationvsanimator.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Danil on 01.03.2016.
 */
public class ScrollButtons extends LinearLayout {
    private ScrollButtonsListener scrollButtonsListener;

    public ScrollButtons(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setScrollButtonsListener(ScrollButtonsListener scrollButtonsListener) {
        this.scrollButtonsListener = scrollButtonsListener;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        setOrientation(VERTICAL);
        LayoutInflater.from(getContext()).inflate(R.layout.scroll_buttons, this, true);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.scroll_button_1)
    protected void onWishlistClick() {
        if (scrollButtonsListener != null) {
            scrollButtonsListener.onFirstButtonClick();
        }
    }

    @OnClick(R.id.scroll_button_2)
    protected void onShowHideClick() {
        if (scrollButtonsListener != null) {
            scrollButtonsListener.onSecondButtonClick();
        }
    }

    @OnClick(R.id.scroll_button_3)
    protected void onShareClick() {
        if (scrollButtonsListener != null) {
            scrollButtonsListener.onThirdButtonClick();
        }
    }

    interface ScrollButtonsListener {
        void onFirstButtonClick();

        void onSecondButtonClick();

        void onThirdButtonClick();
    }
}
