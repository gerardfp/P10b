package com.company.p9.view;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.company.p9.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DatabaseFragment extends Fragment {


    public DatabaseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_database, container, false);
    }

    static class DatabaseSectionsPagerAdapter extends FragmentPagerAdapter {

        @StringRes
        private static final int[] TAB_TITLES = new int[]{R.string.tab_text_3, R.string.tab_text_4};
        private final Context mContext;

        public DatabaseSectionsPagerAdapter(Context context, FragmentManager fm) {
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
