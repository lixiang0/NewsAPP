package com.mylibs.view.recycleview;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.mylibs.R;

import java.util.ArrayList;
import java.util.List;

public class RecycleListActivity extends Activity {

    //不关心item是否显示正确的位置     LayoutMnager
//    不关心item如何分割               ItemDecoration
//    不关注item增加和删除动画效果      ItemAnimator
//    仅仅关注如何回收和复用View

    //RecyclerView  实现：listView（横向和竖向） GridView（横向和竖向）  瀑布流， 定制item增加和删除动画
    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private RecycleListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_activity);

        initDatas();
        initViews();
        mAdapter = new RecycleListAdapter(this,mDatas);
        mRecyclerView.setAdapter(mAdapter);

        //设置RecyclerView的布局管理
        LinearLayoutManager linearLaoutMnager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(linearLaoutMnager);

        //设置RecyclerView的Item之间分割线
        RecyclerView.ItemDecoration itemDecor = new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDraw(c, parent, state);
            }

            @Override
            public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDrawOver(c, parent, state);
            }

            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
            }
        };
        mRecyclerView.addItemDecoration( itemDecor);//设置分割线
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());//设置动画

        mAdapter.setOnItemClickListener(new RecycleListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(RecycleListActivity.this,position+" click", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(RecycleListActivity.this,position+" longclick", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initDatas() {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i <= 'Z'; i++) {
            mDatas.add(""+(char)i);
        }
    }

    private void initViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.recycle_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.actiong_add) {
            mAdapter.addData(1);
        } else if (id == R.id.actiong_delete) {
            mAdapter.deleteData(1);
        } else if (id == R.id.action_gridview) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        } else if (id == R.id.actiong_ListView) {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        } else if (id == R.id.action_hor_gridview) {
            mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.HORIZONTAL));
        } else if (id == R.id.action_staggered) {
//            Intent intent = new Intent(this, StaggeredActivity.class);
//            startActivity(intent);
        } else {
        }
        return super.onOptionsItemSelected(item);
    }
}
