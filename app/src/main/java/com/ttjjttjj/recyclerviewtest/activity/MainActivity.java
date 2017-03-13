package com.ttjjttjj.recyclerviewtest.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.ttjjttjj.recyclerviewtest.R;
import com.ttjjttjj.recyclerviewtest.adapter.FragmentAdapter;
import com.ttjjttjj.recyclerviewtest.fragment.FirstFragment;
import com.ttjjttjj.recyclerviewtest.fragment.FourFragment;
import com.ttjjttjj.recyclerviewtest.fragment.ThirdFragment;
import com.ttjjttjj.recyclerviewtest.fragment.WaterFallFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.tablayout)
    TabLayout mTabLayout;
    @Bind(R.id.viewpager)
    ViewPager mViewPager;

    private FragmentAdapter mFragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
    }

    private void initData(){
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        List<String> listStr = new ArrayList<String>();
        listStr.add("表格");
        listStr.add("瀑布流");
        listStr.add("优化");
        listStr.add("待更新");

        mTabLayout.addTab(mTabLayout.newTab().setTag(listStr.get(0)));
        mTabLayout.addTab(mTabLayout.newTab().setTag(listStr.get(1)));
        mTabLayout.addTab(mTabLayout.newTab().setTag(listStr.get(2)));
        mTabLayout.addTab(mTabLayout.newTab().setTag(listStr.get(3)));

        List<Fragment> fragments = new ArrayList<Fragment>();
        fragments.add(new FirstFragment());
        fragments.add(new WaterFallFragment());
        fragments.add(new ThirdFragment());
        fragments.add(new FourFragment());

        mFragmentAdapter = new FragmentAdapter(
                getSupportFragmentManager(), fragments,listStr);
        mViewPager.setAdapter(mFragmentAdapter);

        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(mFragmentAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
