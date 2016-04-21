package com.princeparadoxes.animationvsanimator.scroll;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.danil.recyclerbindableadapter.library.SimpleBindableAdapter;
import com.danil.recyclerbindableadapter.library.view.BindableViewHolder;
import com.princeparadoxes.animationvsanimator.R;
import com.princeparadoxes.animationvsanimator.misc.LayoutParamsUtils;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.BindDimen;
import butterknife.ButterKnife;

public class ScrollFragment extends Fragment implements BindableViewHolder.ActionListener, ScrollButtons.ScrollButtonsListener {

    public static final int SPAN_COUNT = 4;
    private static final int COUNT_ELEMENTS = 32;
    private final LinearLayoutManager linearLayoutManager;
    private final GridLayoutManager gridLayoutManager;
    @Bind(R.id.scroll_view)
    NestedScrollView scrollView;
    @Bind(R.id.scroll_header)
    LinearLayout header;
    @Bind(R.id.scroll_header_articul)
    TextView articul;
    @Bind(R.id.scroll_recycler)
    RecyclerView recyclerView;
    @Bind(R.id.scroll_footer_buttons)
    ScrollButtons footerButtons;
    @Bind(R.id.scroll_footer_text)
    TextView footerText;
    @BindDimen(R.dimen.scroll_recycler_show_height)
    int showRecyclerHeight;
    @BindDimen(R.dimen.scroll_recycler_hide_height)
    int hideRecyclerHeight;
    @BindDimen(R.dimen.scroll_buttons_height)
    int buttonsHeight;
    private SimpleBindableAdapter<Integer> adapter;

    public ScrollFragment() {
        linearLayoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false);
        gridLayoutManager = new GridLayoutManager(getContext(), SPAN_COUNT,
                LinearLayoutManager.VERTICAL, false);
        adapter = new SimpleBindableAdapter<>(R.layout.scroll_item, ScrollItemViewHolder.class);
        adapter.setActionListener(this);
    }

    public static ScrollFragment newInstance() {
        return new ScrollFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.scroll_fragment, container, false);
        ButterKnife.bind(this, view);
        showItems();
        return view;
    }

    public void showItems() {
        recyclerView.setAdapter(adapter);
        if (recyclerView.getLayoutManager() == null) {
            recyclerView.setLayoutManager(linearLayoutManager);
        }
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        ArrayList<Integer> list = new ArrayList<>(COUNT_ELEMENTS);
        for (int i = 0; i < COUNT_ELEMENTS; i++) {
            list.add(i);
        }
        adapter.clear();
        adapter.addAll(list);
        footerButtons.setScrollButtonsListener(this);
    }


    @Override
    public void OnItemClickListener(int position, Object Item) {
        articul.setText(String.format(getContext().getString(R.string.scroll_fragment_position),
                String.valueOf(position)));
    }

    @Override
    public void onFirstButtonClick() {

    }

    @Override
    public void onSecondButtonClick() {
        final boolean isGrid = recyclerView.getLayoutManager() instanceof GridLayoutManager;

        //translateY animator data
        int translationY = isGrid ? hideRecyclerHeight - showRecyclerHeight
                : showRecyclerHeight - hideRecyclerHeight;

        //scroll animator data
        int startScroll = scrollView.computeVerticalScrollOffset();
        int contentHeight = header.getHeight() + (isGrid ? hideRecyclerHeight : showRecyclerHeight);
        int scrollViewHeight = (scrollView.getHeight() - buttonsHeight);
        int endScroll = contentHeight - scrollViewHeight;
        
        AnimatorSet showHideSet = new AnimatorSet();
        showHideSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                recyclerView.setLayoutManager(isGrid ? linearLayoutManager : gridLayoutManager);
                ScrollAnimatorUtils.alphaAnimator(0, 1, recyclerView).start();
                LayoutParamsUtils.setHeight(recyclerView, isGrid ? hideRecyclerHeight : showRecyclerHeight);
                if (!isGrid)
                    footerText.setVisibility(View.GONE);
            }
        });
        if (isGrid)
            footerText.setVisibility(View.VISIBLE);

        showHideSet.playTogether(
                ScrollAnimatorUtils.translationYAnimator(translationY, footerButtons),
                ScrollAnimatorUtils.translationYAnimator(translationY, footerText),
                ScrollAnimatorUtils.scrollAnimator(startScroll, endScroll, scrollView),
                ScrollAnimatorUtils.alphaAnimator(1, 0, recyclerView)
        );
        showHideSet.start();
    }

    @Override
    public void onThirdButtonClick() {

    }
}
