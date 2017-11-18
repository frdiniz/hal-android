package io.github.frdiniz.hal.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataCore extends SQLiteOpenHelper {
    // Database Version
    private static final int DB_VERSION = 2;

    // Article Database Name
    private static final String DB_NAME = "db_articles";

    // Article Table Name
    public static final String DB_TABLE = "articles";

    // Article Table columns Names
    public static final String KEY_ID = "_id";
    public static final String KEY_TITLE = "title";
    public static final String KEY_DESC = "description";
    public static final String KEY_SITE = "site";
    public static final String KEY_IMG = "image";
    public static final String KEY_URL = "url";
    public static final String KEY_DATE = "dateAdded";

    // Table create statement
    private static final String CREATE_TABLE_ARTICLE = "CREATE TABLE " + DB_TABLE + "(" +
            KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            KEY_TITLE + " TEXT NOT NULL," +
            KEY_DESC + " TEXT," +
            KEY_SITE + " TEXT NOT NULL," +
            KEY_IMG + " BLOB," +
            KEY_URL + " TEXT NOT NULL," +
            KEY_DATE + " DATETIME DEFAULT CURRENT_TIMESTAMP" + ");";

    public DataCore(Context context) {
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
