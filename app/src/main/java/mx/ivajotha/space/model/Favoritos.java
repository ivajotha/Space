package mx.ivajotha.space.model;

/**
 * Created by jonathan on 12/09/16.
 */
public class Favoritos {
    public int id;
    public String nombrefav;
    public String imagefav;
    public String datefav;

    public Favoritos (int id, String nombrefav, String imagefav, String datefav){
        this.id = id;
        this.nombrefav = nombrefav;
        this.imagefav = imagefav;
        this.datefav = datefav;
    }

}
