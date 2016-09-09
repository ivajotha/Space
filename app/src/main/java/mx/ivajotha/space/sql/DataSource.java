package mx.ivajotha.space.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import mx.ivajotha.space.model.ModelFavoritos;

/**
 * Created by jonathan on 09/09/16.
 */
public class DataSource {

    private final SQLiteDatabase db;

    public DataSource(Context context) {
        MySqliteHelper helper = new MySqliteHelper(context);
        db=helper.getWritableDatabase();
    }


/*
    public static final String  COLUMN_ID               = BaseColumns._ID;
    public static final String  COLUMN_TITLE            = "title";
    public static final String  COLUMN_DATE             = "date";
    public static final String  COLUMN_URL              = "url";

     */

    public Boolean searchFav(ModelFavoritos modelFavoritos)
    {
        Boolean existUser = false;
        int count;

        String myFav = modelFavoritos.mfav_url;
        String selection = MySqliteHelper.COLUMN_URL + "=?";
        String[] selectionArgs = {myFav};
        Cursor c = db.query(MySqliteHelper.TABLE_NAME, null, selection, selectionArgs, null, null, null, null);
        count = c.getCount();

        if (count > 0 )
            existUser = true;

        return existUser;
    }


    //Guardar
    public void saveFav(ModelFavoritos modelFavoritos){
        ContentValues contentValues = new ContentValues();
        contentValues.put(MySqliteHelper.COLUMN_TITLE,modelFavoritos.mfav_title);
        contentValues.put(MySqliteHelper.COLUMN_DATE,modelFavoritos.mfav_date);
        contentValues.put(MySqliteHelper.COLUMN_URL,modelFavoritos.mfav_url);
        db.insert(MySqliteHelper.TABLE_NAME,null,contentValues);
    }

    //Eliminar
    public void deleteFav(ModelFavoritos modelFavoritos){

        if(modelFavoritos!=null){
            db.delete(MySqliteHelper.TABLE_NAME,MySqliteHelper.COLUMN_ID+"=?",new String[]{String.valueOf(modelFavoritos.id)});
        }

    }

    //Obtiene mis favoritos
    public List<ModelFavoritos> getAllItems(){
        List<ModelFavoritos> modelFavoritosList = new ArrayList<>();

            Cursor cursor=db.query(MySqliteHelper.TABLE_NAME,null,null,null,null,null,null);

        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_ID));
            String listTitle = cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_TITLE));
            String listDate = cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_DATE));
            String listUrl = cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_URL));
            ModelFavoritos modelFavorites = new ModelFavoritos(id,listTitle,listDate,listUrl);
            modelFavoritosList.add(modelFavorites);
        }

        return modelFavoritosList;
    }

}
