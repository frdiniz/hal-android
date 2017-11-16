package io.github.frdiniz.hal.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ArticleDAO {

    // init
    private SQLiteDatabase db;

    public ArticleDAO(Context context) {
        DataCore auxDB = new DataCore(context);
        db = auxDB.getWritableDatabase();
    }

    // CRUD methods
    public void create(Article article) {
        ContentValues values = new ContentValues();
        values.put(DataCore.KEY_TITLE, article.getTitle());
        values.put(DataCore.KEY_URL, article.getUrl());

        db.insert(DataCore.DB_TABLE, null, values);
    }

    public List<Article> read() {
        List<Article> list = new ArrayList<Article>();
        String[] columns = new String[]{ DataCore.KEY_ID, DataCore.KEY_TITLE, DataCore.KEY_URL };

        Cursor cursor = db.query(DataCore.DB_TABLE, columns, null, null, null, null, DataCore.KEY_TITLE + " ASC");

        if(cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {
                Article art = new Article();
                art.setId(cursor.getInt(0));
                art.setTitle(cursor.getString(1));
                art.setUrl(cursor.getString(2));
                list.add(art);

            } while (cursor.moveToNext());
        }

        cursor.close();
        return(list);
    }

    public void update(Article article) {
        ContentValues values = new ContentValues();
        values.put(DataCore.KEY_TITLE, article.getTitle());
        values.put(DataCore.KEY_URL, article.getUrl());

        db.update(DataCore.DB_TABLE, values, DataCore.KEY_ID + " = " + article.getId(), null);
    }

    public void delete(Article article) {
        db.delete(DataCore.DB_TABLE, DataCore.KEY_ID + " = " + article.getId(), null);
    }


}
