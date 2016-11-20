package com.example.facetoface;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import com.example.facetoface.data.Data;
import com.example.facetoface.fragments.FirstFragment;
import com.example.facetoface.fragments.SecondFragment;
import com.example.facetoface.data.DataDBHandler;

public class AnalyticActivity extends AppCompatActivity {

    private int[] tabIcons = {
            R.drawable.graph_pic,
            R.drawable.statistics
    };
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private Statistics stats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analytic);

        DataDBHandler dbHandler = new DataDBHandler(this, null, null, 1);
        stats = new Statistics(dbHandler.getAll());
        stats.setCurrent(dbHandler.getLatestEntry());

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        setupTabIcons();
    }

    public Statistics getStats() {
        return stats;
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        FirstFragment f = new FirstFragment();
        f.setStats(stats);
        adapter.addFragment(f, "Graphs");

        SecondFragment s = new SecondFragment();
        s.setStats(stats);
        adapter.addFragment(s, "Statistics");

        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
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
