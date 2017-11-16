package io.github.frdiniz.hal.viewController;

import android.content.Intent;
import android.os.Bundle;
import android.app.ActionBar;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

        ActionBar ab = getActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setHomeButtonEnabled(true);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.addArticle);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), ArticleEdit.class);
                startActivity(intent);
            }
        });

        ArticleDAO dao = new ArticleDAO(this);
        List<Article> list = dao.read();

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new ArticleItemAdapter(this, list));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }
}
