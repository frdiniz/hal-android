package io.github.frdiniz.hal.viewController;

import android.content.Intent;
import android.os.Bundle;
import android.app.ActionBar;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.List;

import io.github.frdiniz.hal.R;
import io.github.frdiniz.hal.model.Article;
import io.github.frdiniz.hal.model.ArticleDAO;

public class ArticleList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_list);

        final ArticleDAO dao = new ArticleDAO(getBaseContext());
        final ListView listView = (ListView) findViewById(R.id.listView);

        // activate the back of the bar action
        ActionBar ab = getActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setHomeButtonEnabled(true);
        }

        final SwipeRefreshLayout swipe = (SwipeRefreshLayout)findViewById(R.id.swiperefresh);
        swipe.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        // read database and update list
                        List<Article> list = dao.read();
                        listView.setAdapter(new ArticleItemAdapter(getBaseContext(), list));
                        // stop spinner
                        swipe.setRefreshing(false);
                    }
                }
        );

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.addArticle);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), ArticleNew.class);
                startActivity(intent);
            }
        });

        // read database and update list
        List<Article> list = dao.read();
        listView.setAdapter(new ArticleItemAdapter(this, list));

    }

    @Override
    protected void onResume() {
        final ArticleDAO dao = new ArticleDAO(getBaseContext());
        final ListView listView = (ListView) findViewById(R.id.listView);

        // read database and update list
        List<Article> list = dao.read();
        listView.setAdapter(new ArticleItemAdapter(getBaseContext(), list));

        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }
}
