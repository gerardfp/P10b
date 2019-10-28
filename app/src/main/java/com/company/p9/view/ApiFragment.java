package com.company.p9.view;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.company.p9.R;
import com.google.android.material.tabs.TabLayout;


public class ApiFragment extends Fragment {

    public ApiFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView =  inflater.inflate(R.layout.fragment_api, container, false);

        ApiSectionsPagerAdapter apiSectionsPagerAdapter = new ApiSectionsPagerAdapter(getActivity(), getChildFragmentManager());
        ViewPager viewPager = fragmentView.findViewById(R.id.api_view_pager);
        viewPager.setAdapter(apiSectionsPagerAdapter);
        TabLayout tabs = fragmentView.findViewById(R.id.api_tabs);
        tabs.setupWithViewPager(viewPager);

        return fragmentView;
    }

    static class ApiSectionsPagerAdapter extends FragmentPagerAdapter {

        @StringRes
        private static final int[] TAB_TITLES = new int[]{R.string.tab_text_3, R.string.tab_text_4};
        private final Context mContext;

        public ApiSectionsPagerAdapter(Context context, FragmentManager fm) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
            mContext = context;
        }

        @Override
        public Fragment getItem(int position) {
            if(position == 0){
                return new ApiAbcFragment();
            }
            return new ApiDateFragment();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mContext.getResources().getString(TAB_TITLES[position]);
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
