package mx.ivajotha.space.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import mx.ivajotha.space.R;

public class MySqliteHelper extends SQLiteOpenHelper {

    private final static int DATABASE_VERSION = 1;
    private final static String DATABASE_NAME           = "Spacedb";
    public static final String  TABLE_NAME              = "favApod";
    public static final String  COLUMN_ID               = BaseColumns._ID;
    public static final String  COLUMN_TITLE            = "title";
    public static final String  COLUMN_DATE             = "date";
    public static final String  COLUMN_URL              = "url";


    public static final String CREATE_TABLE = "create table "+TABLE_NAME+
            "("+COLUMN_ID+" integer primary key autoincrement,"+
            COLUMN_TITLE+" text null,"+
            COLUMN_DATE+ " text null,"+
            COLUMN_URL+ " text null)";

    public MySqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
