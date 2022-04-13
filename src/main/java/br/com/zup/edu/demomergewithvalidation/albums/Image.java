package br.com.zup.edu.demomergewithvalidation.albums;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // it does NOT work with IDENTITY
    private Long id;

    @NotBlank
    @Size(min = 5, max = 45)
    private String name;

    @ManyToOne
    private Album album;

    @Deprecated
    public Image(){}

    public Image(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
}