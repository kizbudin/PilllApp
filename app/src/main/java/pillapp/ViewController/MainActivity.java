package pillapp.ViewController;

import pillapp.ViewController.adapter.TabsAdapter;
import teamqitalach.pillapp.R;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

//Disini Adalah Source code untuk mengatur tampilan utama pada aplikasi

public class MainActivity extends ActionBarActivity implements android.support.v7.app.ActionBar.TabListener{
    private ViewPager tabsviewPager;
    private TabsAdapter mTabsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabsviewPager = (ViewPager) findViewById(R.id.tabspager);

        mTabsAdapter = new TabsAdapter(getSupportFragmentManager());

        tabsviewPager.setAdapter(mTabsAdapter);

        getSupportActionBar().setTitle("Pills Reminder");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date tomorrow = calendar.getTime();

        String todayString = new SimpleDateFormat("EEE, MMM d").format(today);
        String tomorrowString = new SimpleDateFormat("EEE, MMM d").format(tomorrow);

        Tab historytab = getSupportActionBar().newTab().setTabListener(this);
        Tab todaytab = getSupportActionBar().newTab().setTabListener(this);
        Tab tomorrowtab = getSupportActionBar().newTab().setTabListener(this);

        TextView tt1 = new TextView(this);
        tt1.setText(Html.fromHtml("<b>HISTORY</b>"));
        tt1.setTextColor(Color.WHITE);
        tt1.setGravity(Gravity.CENTER);
        tt1.setHeight(200);
        historytab.setCustomView(tt1);

        TextView tt2 = new TextView(this);
        tt2.setText(Html.fromHtml("<b>HARI INI</b><br><small>" + todayString + "</small>"));
        tt2.setTextColor(Color.WHITE);
        tt2.setGravity(Gravity.CENTER);
        tt2.setHeight(200);
        todaytab.setCustomView(tt2);

        TextView tt3 = new TextView(this);
        tt3.setText(Html.fromHtml("<b>BESOK</b><br><small>" + tomorrowString + "</small>"));
        tt3.setTextColor(Color.WHITE);
        tt3.setGravity(Gravity.CENTER);
        tt3.setHeight(200);
        tomorrowtab.setCustomView(tt3);

        getSupportActionBar().addTab(historytab);
        getSupportActionBar().addTab(todaytab);
        getSupportActionBar().addTab(tomorrowtab);

        getSupportActionBar().setSelectedNavigationItem(1);


        tabsviewPager.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                getSupportActionBar().setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }


    @Override
        public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
        public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            sendSetting();
            return true;
        }

        if (id == R.id.action_add) {
            sendAdd();
            return true;
        }

        if (id == R.id.action_edit) {
            sendEdit();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
    }

    @Override
    public void onTabSelected(Tab selectedtab, FragmentTransaction arg1) {
        tabsviewPager.setCurrentItem(selectedtab.getPosition());
    }

    @Override
    public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {

    }

    public void sendAdd() {
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);
        finish();
    }

    public void sendSetting() {
        Intent intent = new Intent(this, ScheduleActivity.class);
        startActivity(intent);
        finish();
    }

    public void sendEdit() {
        Intent intent = new Intent(this, PillBoxActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        return;
    }
}