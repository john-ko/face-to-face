package com.example.facetoface;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import com.example.facetoface.R;
import com.example.facetoface.fragments.FirstFragment;
import com.example.facetoface.fragments.SecondFragment;
import com.example.facetoface.data.DataDBHandler;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;

public class AnalyticActivity extends AppCompatActivity {

    private int[] tabIcons = {
            R.drawable.graph_pic,
            R.drawable.statistics
    };
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analytic);

//        DataDBHandler dbHandler = new DataDBHandler(this, null, null, 1);
//
//        PieChart pieChart1 = (PieChart) findViewById(R.id.pieChart_1);
//        Utility.setPieChartData(pieChart1, dbHandler.getLatestEntry());
//
//        LineChart lineChart2 = (LineChart) findViewById(R.id.lineChart_3);
//        Utility.setLineChartData(lineChart2, Utility.formatDataList(dbHandler.getAll()));

//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        setupTabIcons();
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FirstFragment(), "Graphs");
        adapter.addFragment(new SecondFragment(), "Statistics");
        viewPager.setAdapter(adapter);
    }

//    static final int NUM_ITEMS = 2;
//    private final FragmentManager mFragmentManager;
//    private Fragment mFragmentAtPos0;
//
//    public MyAdapter(FragmentManager fm)
//    {
//        super(fm);
//        mFragmentManager = fm;
//    }
//
//    @Override
//    public Fragment getItem(int position)
//    {
//        if (position == 0)
//        {
//            if (mFragmentAtPos0 == null)
//            {
//                mFragmentAtPos0 = FirstPageFragment.newInstance(new FirstPageFragmentListener()
//                {
//                    public void onSwitchToNextFragment()
//                    {
//                        mFragmentManager.beginTransaction().remove(mFragmentAtPos0).commit();
//                        mFragmentAtPos0 = NextFragment.newInstance();
//                        notifyDataSetChanged();
//                    }
//                });
//            }
//            return mFragmentAtPos0;
//        }
//        else
//            return SecondPageFragment.newInstance();
//    }
//
//    @Override
//    public int getCount()
//    {
//        return NUM_ITEMS;
//    }
//
//    @Override
//    public int getItemPosition(Object object)
//    {
//        if (object instanceof FirstPageFragment && mFragmentAtPos0 instanceof NextFragment)
//            return POSITION_NONE;
//        return POSITION_UNCHANGED;
//    }
//}
//
//public interface FirstPageFragmentListener
//{
//    void onSwitchToNextFragment();
//}

    class ViewPagerAdapter extends FragmentPagerAdapter {
        static final int NUM_ITEMS = 2;
        private Fragment mFragmentAtPos0;

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            Log.d("the position", String.valueOf(position));
//            if (position == 0)
//            {
//                if (mFragmentAtPos0 == null)
//                {
//                    mFragmentAtPos0 = FirstPageFragment.newInstance(new FirstPageFragmentListener()
//                    {
//                        public void onSwitchToNextFragment()
//                        {
//                            mFragmentManager.beginTransaction().remove(mFragmentAtPos0).commit();
//                            mFragmentAtPos0 = NextFragment.newInstance();
//                            notifyDataSetChanged();
//                        }
//                    });
//                }
//                return mFragmentAtPos0;
//            }
//            else
//                return SecondPageFragment.newInstance();

            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
