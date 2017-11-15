package io.github.frdiniz.hal.viewController;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import io.github.frdiniz.hal.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goStashList(View view) {
        Intent intent = new Intent(this, ArticleList.class);
        startActivity(intent);
    }
}
