package test;

import foto.exception.AlreadyExistsException;
import foto.exception.NotFoundException;
import foto.model.Fotografija;
import foto.model.Oseba;
import foto.model.Oznaka;
import foto.upravljanje.KatalogFotografij;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class KatalogFotografijTest {

    @Test
    public void testDodajOseboValid() throws AlreadyExistsException {
        KatalogFotografij katalog = new KatalogFotografij();
        katalog.dodajOsebo(1, "Janez", "Novak");
        Map<Integer, Oseba> osebe = katalog.getOsebaMap();
        assertEquals(1, osebe.size());
        assertTrue(osebe.containsKey(1));
        assertEquals("Janez", osebe.get(1).getIme());
        assertEquals("Novak", osebe.get(1).getPriimek());
    }

    @Test
    public void testDodajOseboNegativeID() {
        KatalogFotografij katalog = new KatalogFotografij();
        assertThrows(IllegalArgumentException.class, () -> {
            katalog.dodajOsebo(-1, "Janez", "Novak");
        });
    }

    @Test
    public void testDodajOseboDuplicateID() throws AlreadyExistsException {
        KatalogFotografij katalog = new KatalogFotografij();
        katalog.dodajOsebo(1, "Janez", "Novak");

        assertThrows(AlreadyExistsException.class, () -> {
            katalog.dodajOsebo(1, "Mojca", "Kovač");
        });
    }

    @Test
    public void testDodajOznakoValid() throws AlreadyExistsException {
        KatalogFotografij katalog = new KatalogFotografij();
        katalog.dodajOznako(1, "Test");
        Map<Integer, Oznaka> oznake = katalog.getOznakaMap();
        assertEquals(1, oznake.size());
        assertTrue(oznake.containsKey(1));
        assertEquals("Test", oznake.get(1).getOpis());
    }

    @Test
    public void testDodajOznakoNegativeID() {
        KatalogFotografij katalog = new KatalogFotografij();
        assertThrows(IllegalArgumentException.class, () -> {
            katalog.dodajOznako(-1, "Test");
        });
    }

    @Test
    public void testDodajOznakoDuplicateID() throws AlreadyExistsException {
        KatalogFotografij katalog = new KatalogFotografij();
        katalog.dodajOznako(1, "Test");

        assertThrows(AlreadyExistsException.class, () -> {
            katalog.dodajOznako(1, "Test 1");
        });
    }

    @Test
    public void testDodajFotografijoValid() throws AlreadyExistsException {
        KatalogFotografij katalog = new KatalogFotografij();
        List<Oseba> osebe = new ArrayList<>();
        List<Oznaka> oznake = new ArrayList<>();
        katalog.dodajFotografijo(1, 800, 600, "Opis fotografije", osebe, oznake);
        assertEquals(1, katalog.getFotografijaMap().size());
        assertTrue(katalog.getFotografijaMap().containsKey(1));
    }

    @Test
    public void testDodajFotografijoNegativeID() throws IllegalArgumentException {
        KatalogFotografij katalog = new KatalogFotografij();
        List<Oseba> osebe = new ArrayList<>();
        List<Oznaka> oznake = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> {
            katalog.dodajFotografijo(-1, 800, 600, "Opis fotografije", osebe, oznake);
        });
    }

    @Test
    public void testDodajFotografijoNegativeDimensions() throws IllegalArgumentException {
        KatalogFotografij katalog = new KatalogFotografij();
        List<Oseba> osebe = new ArrayList<>();
        List<Oznaka> oznake = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> {
            katalog.dodajFotografijo(1, -800, 600, "Opis fotografije", osebe, oznake);
        });
    }

    @Test
    public void testDodajFotografijoDuplicateID() throws AlreadyExistsException {
        KatalogFotografij katalog = new KatalogFotografij();
        List<Oseba> osebe = new ArrayList<>();
        List<Oznaka> oznake = new ArrayList<>();
        katalog.dodajFotografijo(1, 800, 600, "Opis fotografije", osebe, oznake);
        assertThrows(AlreadyExistsException.class, () -> {
            katalog.dodajFotografijo(1, 800, 600, "Opis fotografije", osebe, oznake);
        });
    }

    @Test
    public void testDodajOseboNaFotografijoValid() throws AlreadyExistsException, NotFoundException {
        KatalogFotografij katalog = new KatalogFotografij();
        katalog.dodajOsebo(1, "Janez", "Novak");

        Fotografija fotografija = new Fotografija();
        fotografija.setId(1);
        fotografija.setOsebaList(new ArrayList<>());
        katalog.getFotografijaMap().put(1, fotografija);
        katalog.dodajOseboNaFotografijo(1, 1);
        assertTrue(fotografija.getOsebaList().size() == 1);
    }

    @Test
    public void testDodajOseboNaFotografijoNotFoundFotografija() throws NotFoundException, AlreadyExistsException {
        KatalogFotografij katalog = new KatalogFotografij();
        katalog.dodajOsebo(1, "Janez", "Novak");
        assertThrows(NotFoundException.class, () -> {
            katalog.dodajOseboNaFotografijo(1, 1);
        });
    }

    @Test
    public void testDodajOseboNaFotografijoNotFoundOseba() throws NotFoundException, AlreadyExistsException {
        KatalogFotografij katalog = new KatalogFotografij();
        katalog.dodajOsebo(1, "Janez", "Novak");
        Fotografija fotografija = new Fotografija();
        fotografija.setId(1);
        katalog.getFotografijaMap().put(1, fotografija);
        assertThrows(NotFoundException.class, () -> {
            katalog.dodajOseboNaFotografijo(1, 2);
        });
    }

    @Test
    public void testBrisanjeOsebeNotFound() {
        KatalogFotografij katalog = new KatalogFotografij();
        assertThrows(NotFoundException.class, () -> {
            katalog.brisanjeOsebe(1);
        });
    }

    @Test
    public void testBrisanjeOsebeValid() throws AlreadyExistsException, NotFoundException {
        KatalogFotografij katalog = new KatalogFotografij();
        katalog.dodajOsebo(1, "Janez", "Novak");
        katalog.brisanjeOsebe(1);
        assertFalse(katalog.getOsebaMap().containsKey(1));

    }

    @Test
    public void testDodajOznakoNaFotografijoValid() throws AlreadyExistsException, NotFoundException {
        KatalogFotografij katalog = new KatalogFotografij();
        katalog.dodajOznako(1, "Narava");
        Fotografija fotografija = new Fotografija();
        fotografija.setId(1);
        fotografija.setOznakaList(new ArrayList<>());
        katalog.getFotografijaMap().put(1, fotografija);
        katalog.dodajOznakoNaFotografijo(1, 1);
        assertTrue(fotografija.getOznakaList().size() == 1);
        assertEquals("Narava", fotografija.getOznakaList().get(0).getOpis());
    }

    @Test
    public void testDodajOznakoNaFotografijoNotFoundFotografija() throws NotFoundException, AlreadyExistsException {
        KatalogFotografij katalog = new KatalogFotografij();
        katalog.dodajOznako(1, "Narava");
        assertThrows(NotFoundException.class, () -> {
            katalog.dodajOznakoNaFotografijo(1, 1);
        });
    }

    @Test
    public void testDodajOznakoNaFotografijoNotFoundOznaka() throws NotFoundException, AlreadyExistsException {
        KatalogFotografij katalog = new KatalogFotografij();
        katalog.dodajOznako(1, "Narava");
        Fotografija fotografija = new Fotografija();
        fotografija.setId(1);
        katalog.getFotografijaMap().put(1, fotografija);
        assertThrows(NotFoundException.class, () -> {
            katalog.dodajOznakoNaFotografijo(1, 2);
        });
    }


    @Test
    public void testBrisanjeOznakeNotFound() {
        KatalogFotografij katalog = new KatalogFotografij();
        assertThrows(NotFoundException.class, () -> {
            katalog.brisanjeOznake(1);
        });
    }

    @Test
    public void testBrisanjeOznakeValid() throws NotFoundException, AlreadyExistsException {
        KatalogFotografij katalog = new KatalogFotografij();
        katalog.dodajOznako(1, "Narava");
        katalog.brisanjeOznake(1);
        assertFalse(katalog.getOsebaMap().containsKey(1));
    }

    @Test
    public void testBrisanjeFotografijeNotFound() {
        KatalogFotografij katalog = new KatalogFotografij();
        assertThrows(NotFoundException.class, () -> {
            katalog.brisanjeFotografije(1);
        });
    }

    @Test
    public void testBrisanjeFotografijeValid() throws NotFoundException, AlreadyExistsException {
        KatalogFotografij katalog = new KatalogFotografij();
        katalog.dodajFotografijo(1, 800, 600, "Opis fotografije", new ArrayList<>(), new ArrayList<>());
        katalog.brisanjeFotografije(1);
        assertFalse(katalog.getFotografijaMap().containsKey(1));
        assertEquals(0, katalog.getFotografijaMap().size());
    }

}
