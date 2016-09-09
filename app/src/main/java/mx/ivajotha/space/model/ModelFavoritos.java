package mx.ivajotha.space.model;

import java.io.Serializable;

/**
 * Created by jonathan on 09/09/16.
 */
public class ModelFavoritos implements Serializable{
    public int id;
    public String mfav_title;
    public String mfav_date;
    public String mfav_url;

/*
    public static final String  COLUMN_ID               = BaseColumns._ID;
    public static final String  COLUMN_TITLE            = "title";
    public static final String  COLUMN_DATE             = "date";
    public static final String  COLUMN_URL              = "url";

     */

    public ModelFavoritos (int id, String mfav_title, String mfav_date, String mfav_url){

        this.id = id;
        this.mfav_title = mfav_title;
        this.mfav_date = mfav_date;
        this.mfav_url = mfav_url;

    }


}
