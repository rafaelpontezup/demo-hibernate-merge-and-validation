package br.com.zup.edu.demomergewithvalidation.albums;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // it does NOT work with IDENTITY
    private Long id;

    @NotBlank
    @Size(min = 1, max = 60)
    private String name;

    @Size(min = 1)
    @OneToMany(mappedBy = "album", cascade = {
            CascadeType.PERSIST, CascadeType.MERGE
    })
    private List<Image> images = new ArrayList<>();

    @Deprecated
    public Album(){}

    public Album(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public List<Image> getImages() {
        return images;
    }

    /**
     * Adds one or more new images
     */
    public void addImages(Image...newImages) {
        Arrays.stream(newImages).forEach((newImage -> {
            this.images.add(newImage);
            newImage.setAlbum(this);
        }));
    }

}
