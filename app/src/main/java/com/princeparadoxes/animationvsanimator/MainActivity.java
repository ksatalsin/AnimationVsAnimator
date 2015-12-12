package com.princeparadoxes.animationvsanimator;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.first_example)
    ImageView firstExample;

    @Bind(R.id.second_example)
    ImageView secondExample;

    @Bind(R.id.third_example)
    ImageView thirdExample;

    @OnClick(R.id.first_example_container)
    void onFirstContainerClick() {
        LikeAnimationV1.likeAnimation(R.drawable.ic_like, firstExample);
    }

    @OnClick(R.id.second_example_container)
    void onSecondContainerClick() {
        LikeAnimationV2.likeAnimation(R.drawable.ic_like, secondExample);

    }

    @OnClick(R.id.third_example_container)
    void onThirdContainerClick() {
        LikeAnimationV3.likeAnimation(R.drawable.ic_like, thirdExample);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
}
