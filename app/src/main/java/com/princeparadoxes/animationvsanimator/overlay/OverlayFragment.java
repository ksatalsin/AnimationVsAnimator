package com.princeparadoxes.animationvsanimator.overlay;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;
import android.widget.ImageView;

import com.princeparadoxes.animationvsanimator.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OverlayFragment extends Fragment {

    @Bind(R.id.overlay_image)
    ImageView imageView;

    public OverlayFragment() {
    }

    public static OverlayFragment newInstance() {
        return new OverlayFragment();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    @OnClick(R.id.overlay_button)
    protected void onClick() {
        final ViewGroupOverlay overlay = ((ViewGroup) getActivity()
                .findViewById(android.R.id.content)).getOverlay();
        OverlayExample.likeAnimation(overlay, imageView);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.overlay_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
