package br.com.zup.edu.demomergewithvalidation.albuns;

import br.com.zup.edu.demomergewithvalidation.albums.Album;
import br.com.zup.edu.demomergewithvalidation.albums.Image;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Commit
@Transactional
@SpringBootTest
public class NewAlbumTest {

    @Autowired
    private EntityManager manager;

    /**
     * It seems like it has to do with those issues:
     *  - https://bugzilla.redhat.com/show_bug.cgi?id=1245082
     *  - https://hibernate.atlassian.net/browse/HHH-9751
     *  - https://hibernate.atlassian.net/browse/HHH-9979
     *  - https://hibernate.atlassian.net/browse/HHH-9137
     *  - https://stackoverflow.com/a/65324950/1240480
     */
    @Test
    public void shouldCreateANewAlbumWithImages_onMerge() {

        Album newAlbum = new Album("Zup Edu"); // it works when using SEQUENCE instead of IDENTITY
        newAlbum.addImages(
                new Image("jordi.png"),
                new Image("rponte.png")
        );

        manager.merge(newAlbum); // throws a ConstraintViolationException due to @Size violation

        assertEquals(2, newAlbum.getImages().size());
    }

    @Test
    public void shouldCreateANewAlbumWithImages_onPersist() {

        Album newAlbum = new Album("Zup Edu");
        newAlbum.addImages(
                new Image("jordi.png"),
                new Image("rponte.png")
        );

        manager.persist(newAlbum); // it works!!

        assertEquals(2, newAlbum.getImages().size());
    }

    @Test
    public void shouldCreateANewAlbumWithImages_onFlush() {

        Album newAlbum = new Album("Zup Edu");
        newAlbum.addImages(
                new Image("jordi.png"),
                new Image("rponte.png")
        );

        manager.flush(); // it works!!

        assertEquals(2, newAlbum.getImages().size());
    }
}
