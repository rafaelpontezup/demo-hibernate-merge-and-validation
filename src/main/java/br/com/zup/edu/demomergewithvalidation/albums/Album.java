package br.com.zup.edu.demomergewithvalidation.albums;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // it does NOT work with IDENTITY
    private Long id;

    @Size(min = 1)
    @OneToMany(mappedBy = "album", cascade = {
            CascadeType.PERSIST, CascadeType.MERGE
    })
    private List<Image> images = new ArrayList<>();

    public Long getId() {
        return id;
    }
    public List<Image> getImages() {
        return images;
    }

    /**
     * Adds a new image
     */
    public void addImage(Image newImage) {
        this.images.add(newImage);
        newImage.setAlbum(this);
    }

}
