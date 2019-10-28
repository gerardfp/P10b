package com.company.p9.view;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.company.p9.R;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;


public class DatabaseFragment extends Fragment {


    public DatabaseFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragment =  inflater.inflate(R.layout.fragment_database, container, false);

        DatabaseSectionsPagerAdapter databaseSectionsPagerAdapter = new DatabaseSectionsPagerAdapter(getActivity(), getChildFragmentManager());
        ViewPager viewPager = fragment.findViewById(R.id.db_view_pager);
        viewPager.setAdapter(databaseSectionsPagerAdapter);
        TabLayout tabs = fragment.findViewById(R.id.db_tabs);
        tabs.setupWithViewPager(viewPager);

        return fragment;
    }

    static class DatabaseSectionsPagerAdapter extends FragmentPagerAdapter {

        @StringRes
        private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2};
        private final Context mContext;

        public DatabaseSectionsPagerAdapter(Context context, FragmentManager fm) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
            mContext = context;
        }

        @Override
        public Fragment getItem(int position) {
            if(position == 0){
                return new DatabaseAbcFragment();
            }
            return new DatabaseDateFragment();
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
