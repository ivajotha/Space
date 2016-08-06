package mx.ivajotha.space.model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import mx.ivajotha.space.data.Photo;

//@Generated("org.jsonschema2pojo")
public class MarsPhotos {

    @SerializedName("photos")
    //@Expose
    private List<Photo> photos = new ArrayList<Photo>();

    /**
     * 
     * @return
     *     The photos
     */
    public List<Photo> getPhotos() {
        return photos;
    }

    /**
     * 
     * @param photos
     *     The photos
     */
    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

}
