package com.company.p9.view;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.company.p9.R;
import com.company.p9.viewmodel.MainViewModel;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;


public class DatabaseFragment extends Fragment {


    private MainViewModel mainViewModel;

    public DatabaseFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_database, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mainViewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel.class);

        mainViewModel.setSort(MainViewModel.Sort.ABC);

        ViewPager viewPager = view.findViewById(R.id.db_view_pager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == 0){ mainViewModel.setSort(MainViewModel.Sort.ABC); }
                else { mainViewModel.setSort(MainViewModel.Sort.DATE); }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        TabLayout tabs = view.findViewById(R.id.db_tabs);
        tabs.setupWithViewPager(viewPager);

        DatabaseSectionsPagerAdapter databaseSectionsPagerAdapter = new DatabaseSectionsPagerAdapter(getActivity(), getChildFragmentManager());
        viewPager.setAdapter(databaseSectionsPagerAdapter);
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
