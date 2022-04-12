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

    @Test
    public void shouldPersistANewAlbumWithItsImages() {

        Album newAlbum = new Album();
        newAlbum.addImage(new Image());

        manager.persist(newAlbum); // it works!!

        assertEquals(1, newAlbum.getImages().size());
    }

    /**
     * It seems like it has to do with this issue:
     * https://bugzilla.redhat.com/show_bug.cgi?id=1245082
     */
    @Test
    public void shouldMergeANewAlbumWithItsImages() {

        Album newAlbum = new Album();
        newAlbum.addImage(new Image());

        manager.merge(newAlbum); // throws a ConstraintViolationException due to @Size violation

        assertEquals(1, newAlbum.getImages().size());
    }
}
