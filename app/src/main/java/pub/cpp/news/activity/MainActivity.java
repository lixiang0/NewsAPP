package pub.cpp.news.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import pub.cpp.news.pojo.Category;
import pub.cpp.news.pojo.NewsItem;
import pub.cpp.news.utils.JsonUtil;
import pub.cpp.news.utils.LogUtils;
import pub.cpp.slamwiki.R;
import pub.cpp.news.adapter.MyAdapter;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{
    RelativeLayout layout_main;
    private RecyclerView mRecyclerView;
    DrawerLayout drawer;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    SwipeRefreshLayout swipeView;
    Handler mHandler;
    int newsItem=0;
    Toolbar toolbar;
    private boolean flagLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("焦点");
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "建议", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(0);
        flagLogin=false;
        if(flagLogin){

        }else{
            LogUtils.show(this.getLocalClassName()+"___user not login");
//            View viewPhoto=getLayoutInflater().inflate(R.layout.activity_main,null).findViewById(R.id.nav_view);
//            viewPhoto.findViewById(R.id.account_name).setVisibility(View.INVISIBLE);
//            viewPhoto.findViewById(R.id.account_email).setVisibility(View.INVISIBLE);
            ( (TextView) ((NavigationView) findViewById(R.id.nav_view)).getHeaderView(0).findViewById(R.id.account_name)).setText("点击头像登录");
            ( (TextView) ((NavigationView) findViewById(R.id.nav_view)).getHeaderView(0).findViewById(R.id.account_email)).setText("");
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // improve performance if you know that changes in content
        // do not change the size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);


        mHandler=new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                if(message.what==1){

                    mRecyclerView.setAdapter(null);
                    mRecyclerView.setAdapter(new MyAdapter(( List<NewsItem>)message.obj,MainActivity.this.getApplicationContext()));
                    swipeView.setRefreshing(false);
                }
                return false;
            }
        });
         swipeView = (SwipeRefreshLayout) findViewById(R.id.sl_items);
       swipeView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeView.setRefreshing(true);
                (new AsyncLoader()).execute(new Integer[]{newsItem});
            }
        });
        // specify an adapter (see also next example
        swipeView.setRefreshing(true);
        (new AsyncLoader()).execute(new Integer[]{newsItem});

    }

    public void onClickLogin(View view) {
        if(flagLogin){
            LogUtils.show(this.getLocalClassName()+"___user is login");
        }else{
            LogUtils.show(this.getLocalClassName()+"___user is not login");
            Intent intent=new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
        }
    }

    class AsyncLoader extends AsyncTask<Integer, Integer, Void> {

        @Override
        protected Void doInBackground(Integer... integers) {
            List<NewsItem> result = null;
            result = JsonUtil.getNewsList(integers[0]);
            for (NewsItem n : result) {
                System.out.println(n);
                Log.d("123",n.toString());
            }
            Message msg=new Message();
            msg.what=1;
            msg.obj=result;
            mHandler.sendMessage(msg);
            return null;
        }

        @Override
        protected void onPostExecute(Void v) {

        }

        @Override
        protected void onProgressUpdate(Integer... values) {

        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
      int i=0;
        switch (item.getItemId()) {

            case R.id.menu_entertainment:new AsyncLoader().execute(new Integer[]{Category.Entainment});toolbar.setTitle("娱乐");i=Category.Entainment;break;
            case R.id.menu_finance:new AsyncLoader().execute(new Integer[]{Category.Finance});i=Category.Finance;toolbar.setTitle("财经");break;
            case R.id.menu_focus:new AsyncLoader().execute(new Integer[]{Category.Focus});i=Category.Focus;toolbar.setTitle("焦点");break;
            case R.id.menu_mainland:new AsyncLoader().execute(new Integer[]{Category.Mainland});i=Category.Mainland;toolbar.setTitle("国内");break;
            case R.id.menu_sport:new AsyncLoader().execute(new Integer[]{Category.Sport});i=Category.Sport;toolbar.setTitle("体育");break;
            case R.id.menu_teconology:new AsyncLoader().execute(new Integer[]{Category.Technology});i=Category.Technology;toolbar.setTitle("科技");break;
            case R.id.menu_world:new AsyncLoader().execute(new Integer[]{Category.World});toolbar.setTitle("国际");i=Category.World;break;
            default:new AsyncLoader().execute(new Integer[]{Category.Focus});toolbar.setTitle("焦点");i=Category.Focus;break;
        }
        newsItem=i;
        drawer.closeDrawers();
        return true;
    }


    }


