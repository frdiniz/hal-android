package io.github.frdiniz.hal.viewController;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import com.mega4tech.linkpreview.GetLinkPreviewListener;
import com.mega4tech.linkpreview.LinkPreview;
import com.mega4tech.linkpreview.LinkUtil;

import io.github.frdiniz.hal.R;
import io.github.frdiniz.hal.model.Article;
import io.github.frdiniz.hal.model.ArticleDAO;
import io.github.frdiniz.hal.model.DataBitmapUtil;


public class ArticleNew extends AppCompatActivity {

    private LinearLayout activityMain;
    private EditText linkEt;
    private Button linkFetchBtn;
    private RelativeLayout previewGroup;
    private ImageView imgPreviewIv;
    private TextView titleTv;
    private TextView descTv;
    private TextView siteTv;
    private ProgressBar progress;
    private Article article = new Article();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_new);
        initView();

        ActionBar ab = getActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setHomeButtonEnabled(true);
        }

        linkFetchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress.setVisibility(View.VISIBLE);
                previewGroup.setVisibility(View.GONE);
                LinkUtil.getInstance().getLinkPreview(ArticleNew.this, linkEt.getText().toString(), new GetLinkPreviewListener() {
                    @Override
                    public void onSuccess(final LinkPreview link) {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progress.setVisibility(View.GONE);
                                previewGroup.setVisibility(View.VISIBLE);
                                titleTv.setText(link.getTitle() != null ? link.getTitle() : "" );

                                descTv.setText(link.getDescription() != null ? link.getDescription() : "" );
                                siteTv.setText(link.getSiteName() != null ? link.getSiteName() : "" );
                                previewGroup.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link.getLink()));
                                        startActivity(browserIntent);
                                    }
                                });
                                if(link.getImageFile() != null) {
                                    Glide.with(ArticleNew.this).load(link.getImageFile()).asBitmap().into(imgPreviewIv);
                                }
                                else
                                    Glide.with(ArticleNew.this).load(R.mipmap.ic_launcher).into(imgPreviewIv);
                            }
                        });

                    }

                    @Override
                    public void onFailed(final Exception e) {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progress.setVisibility(View.GONE);
                                previewGroup.setVisibility(View.VISIBLE);
                                Toast.makeText(ArticleNew.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });
            }
        });
    }

    private void initView() {
        activityMain = (LinearLayout) findViewById(R.id.activity_add);
        linkEt = (EditText) findViewById(R.id.link_et);
        linkFetchBtn = (Button) findViewById(R.id.link_fetch_btn);
        previewGroup = (RelativeLayout) findViewById(R.id.preview_group);

        imgPreviewIv = (ImageView) findViewById(R.id.img_preview_iv);
        titleTv = (TextView) findViewById(R.id.title_tv);
        descTv = (TextView) findViewById(R.id.desc_tv);
        siteTv = (TextView) findViewById(R.id.site_tv);
        progress = (ProgressBar) findViewById(R.id.progress);
    }

    public void createArticle() {

        String title = titleTv.getText().toString();
        String desc = descTv.getText().toString();
        String site = siteTv.getText().toString();
        String url = linkEt.getText().toString();
        Drawable img = imgPreviewIv.getDrawable();

        if(title.equals("") || site.equals("") || url.equals("")) {
            Toast.makeText(this, "Artigo inválido", Toast.LENGTH_LONG).show();
        } else {

            article.setTitle(title);
            article.setDescription(desc);
            article.setSite(site);
            article.setUrl(url);
            if (img != null ) {
                // convert to persist
                byte[] temp = DataBitmapUtil.toBytes(DataBitmapUtil.getBitmap(img));
                article.setImage(temp);
            }

            ArticleDAO dao = new ArticleDAO(getBaseContext());
            dao.create(article);

            Toast.makeText(this, "Artigo salvo", Toast.LENGTH_LONG).show();

            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int btn = item.getItemId();

        if (btn == android.R.id.home) {
            finish();
        }
        else if (btn == R.id.save) {
            createArticle();
        }
        return true;
    }

}
