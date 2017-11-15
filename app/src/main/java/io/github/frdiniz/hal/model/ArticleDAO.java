package io.github.frdiniz.hal.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ArticleDAO {
    // Table Name
    private static final String DB_TABLE = "articles";

    // columns Names
    private static final String KEY_ID = "_id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_URL = "url";
    private static final String KEY_DATE = "dateAdded";

    // init
    private SQLiteDatabase db;

    public ArticleDAO(Context context) {
        DataBaseCore auxDB = new DataBaseCore(context);
        db = auxDB.getWritableDatabase();
    }

    // CRUD methods
    public void create(Article article) {
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, article.getTitle());
        values.put(KEY_URL, article.getUrl());

        db.insert(DB_TABLE, null, values);
    }

    public List<Article> read() {
        List<Article> list = new ArrayList<Article>();
        String[] columns = new String[]{ KEY_ID, KEY_TITLE, KEY_URL, KEY_DATE };

        Cursor cursor = db.query(DB_TABLE, columns, null, null, null, null, KEY_DATE + "ASC");

        if(cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {
                Article art = new Article();
                art.setId(cursor.getLong(0));
                art.setTitle(cursor.getString(1));
                art.setUrl(cursor.getString(2));
                art.setDateAdded(cursor.getString(3));

            } while (cursor.moveToNext());
        }

        return(list);
    }

    public void update(Article article) {
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, article.getTitle());
        values.put(KEY_URL, article.getUrl());

        db.update(DB_TABLE, values, KEY_ID + " = " + article.getId(), null);
    }

    public void delete(Article article) {
        db.delete(DB_TABLE, KEY_ID + " = " + article.getId(), null);
    }


}
