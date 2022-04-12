package br.com.zup.edu.demomergewithvalidation.albums;

import javax.persistence.*;

@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // it does NOT work with IDENTITY
    private Long id;

    @ManyToOne
    private Album album;

    public Long getId() {
        return id;
    }
    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
}