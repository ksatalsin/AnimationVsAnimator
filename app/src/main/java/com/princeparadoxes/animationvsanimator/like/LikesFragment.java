package com.princeparadoxes.animationvsanimator.like;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.princeparadoxes.animationvsanimator.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LikesFragment extends Fragment {

    public LikesFragment() {
    }

    @Bind(R.id.animation_example)
    ImageView animationExample;

    @Bind(R.id.animation_set_offset_example)
    ImageView animationSetOffsetExample;

    @Bind(R.id.animation_set_in_set_example)
    ImageView animationSetInSetExample;

    @Bind(R.id.animator_example)
    ImageView thirdExample;

    @OnClick(R.id.animation_example_container)
    void onAnimationContainerClick() {
        AnimationExample.likeAnimation(R.drawable.ic_like, animationExample);
    }

    @OnClick(R.id.animation_set_offset_example_container)
    void onAnimationSetOffsetContainerClick() {
        AnimationSetOffsetExample.likeAnimation(R.drawable.ic_like, animationSetOffsetExample);
    }

    @OnClick(R.id.animation_set_in_set_example_container)
    void onAnimationSetInSetContainerClick() {
        AnimationSetInSetExample.likeAnimation(R.drawable.ic_like, animationSetInSetExample);
    }

    @OnClick(R.id.animator_example_container)
    void onThirdContainerClick() {
        AnimatorExample.likeAnimation(R.drawable.ic_like, thirdExample);
    }

    public static LikesFragment newInstance() {
        LikesFragment fragment = new LikesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.likes_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
