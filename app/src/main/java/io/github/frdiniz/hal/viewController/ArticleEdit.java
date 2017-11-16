package io.github.frdiniz.hal.viewController;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import io.github.frdiniz.hal.R;
import io.github.frdiniz.hal.model.Article;
import io.github.frdiniz.hal.model.ArticleDAO;

public class ArticleEdit extends AppCompatActivity {
    private Article article = new Article();
    private EditText title;
    private EditText url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_edit);

        title = (EditText) findViewById(R.id.editTitle);
        url = (EditText) findViewById(R.id.editUrl);

        ActionBar ab = getActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setHomeButtonEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // create user
        String titleTxt = title.getText().toString();
        String urlTxt = url.getText().toString();

        if(titleTxt.equals("") || urlTxt.equals("")){
            Toast.makeText(this, "Por favor preencha todos os campos", Toast.LENGTH_LONG).show();
        } else {
            article.setTitle(titleTxt);
            article.setUrl(urlTxt);

            ArticleDAO dao = new ArticleDAO(getBaseContext());
            dao.create(article);

            finish();
        }
        return true;
    }

    public void createUser() {
        String titleTxt = title.getText().toString();
        String urlTxt = url.getText().toString();

        if(titleTxt.equals("") || urlTxt.equals("")){
            Toast.makeText(this, "Por favor preencha todos os campos", Toast.LENGTH_LONG).show();
        } else {
            article.setTitle(titleTxt);
            article.setUrl(urlTxt);

            ArticleDAO dao = new ArticleDAO(this);
            dao.create(article);
            finish();
        }
    }
}
