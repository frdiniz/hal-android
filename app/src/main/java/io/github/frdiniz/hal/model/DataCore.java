package io.github.frdiniz.hal.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseCore extends SQLiteOpenHelper {
    // Database Version
    private static final int DB_VERSION = 1;

    // Database Name
    private static final String DB_NAME = "db_articles";

    // Table Name
    private static final String DB_TABLE = "articles";

    // columns Names
    private static final String KEY_ID = "_id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_URL = "url";
    private static final String KEY_DATE = "dateAdded";

    // Table create statement
    private static final String CREATE_TABLE_ARTICLE = "CREATE TABLE " + DB_TABLE + "(" +
            KEY_ID + "INTEGER PRIMARY KEY AUTOINCREMENT," +
            KEY_TITLE + "TEXT NOT NULL," +
            KEY_URL + "TEXT NOT NULL," +
            KEY_DATE + "DATETIME DEFAULT CURRENT_TIMESTAMP" + ");";

    public DataBaseCore(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating table
        db.execSQL(CREATE_TABLE_ARTICLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);

        // create new table
        onCreate(db);
    }
}
