package com.princeparadoxes.animationvsanimator;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.princeparadoxes.animationvsanimator.like.LikesFragment;
import com.princeparadoxes.animationvsanimator.overlay.OverlayFragment;
import com.princeparadoxes.animationvsanimator.scroll.ScrollFragment;

/**
 * Created by Danil on 11.03.2016.
 */
class SectionPagerAdapter extends FragmentPagerAdapter {

    public SectionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
            default:
                return LikesFragment.newInstance();
            case 1:
                return ScrollFragment.newInstance();
            case 2:
                return OverlayFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Like";
            case 1:
                return "Scroll";
            case 2:
                return "Overlay";
        }
        return null;
    }
}

