package com.example.remotecontrol;

import android.content.DialogInterface;
import android.nfc.Tag;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.Toast;

import com.example.remotecontrol.utils.HttpUtil;
import com.example.remotecontrol.utils.LogUtil;
import com.example.remotecontrol.utils.SPUtil;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TabLayout tbLayout;
    private ViewPager viewPager;
    private Toolbar toolbar;
    private FragmentPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        使用toolbar
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_backup);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: toolbar setNavigationOnClickListener");
            }
        });

        tbLayout = (TabLayout)findViewById(R.id.tabLayout);
        viewPager = (ViewPager)findViewById(R.id.viewpage);

        adapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);


        tbLayout.setupWithViewPager(viewPager);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.setting:
                //修改ip地址
                Log.d(TAG, "onOptionsItemSelected: setting");
                Toast.makeText(getApplicationContext(),"setting",Toast.LENGTH_SHORT).show();
                final TableLayout ipAddressForm = (TableLayout)getLayoutInflater().inflate(R.layout.ip_address,null);

                AlertDialog.Builder builder = new AlertDialog.Builder(this).setView(ipAddressForm);
                builder.setTitle("Please input ip address");
                builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText ipAddress = (EditText) ipAddressForm.findViewById(R.id.ipAddress);

                        //修改ip地址
//                        HttpUtil.ipAddress = ipAddress.getText().toString();

                        HttpUtil.url_pref = "http://"+ipAddress.getText().toString()+":8888/reg?";
//                        Request request = new Request.Builder().url(HttpUtil.url_pref).
//                        HttpUtil.client.newCall
                    }
                }).setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {

        AlertDialog dialog = new AlertDialog.Builder(this).setTitle("提醒")
                .setMessage("确定退出吗？")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(TAG, "onClick: 退出应用-----");
                        MainActivity.this.finish();
                    }
                }).show();

    }

    @Override
    protected void onDestroy() {
        LogUtil.d(TAG,"---MainActivity-----onDestroy---");
        super.onDestroy();


    }

    //页面适配器
  static  class SimpleFragmentPagerAdapter extends FragmentPagerAdapter{
        private List<CustumFragment> list;

        public SimpleFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
            list = new ArrayList<>();

//            list.add(new ShowPictureFragment("图像显示"));
            list.add(new CameraFragment("CCD参数"));
            list.add(new LctfFragment("LCTF参数"));
        }

        public void onDestroy(){
            for(CustumFragment  fragment : list){
                fragment.onDestroy();
            }
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return list.get(position).getTitle();
        }
    }

}
