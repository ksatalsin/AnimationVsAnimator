package com.princeparadoxes.animationvsanimator.scroll;

import android.view.View;

import com.danil.recyclerbindableadapter.library.view.BindableViewHolder;

public class ScrollItemViewHolder extends BindableViewHolder<Integer> {

    public ScrollItemViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindView(int position, Integer item, ActionListener actionListener) {
        super.bindView(position, item, actionListener);
    }
}
