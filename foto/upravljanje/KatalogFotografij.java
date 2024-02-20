package foto.upravljanje;

import foto.exception.AlreadyExistsException;
import foto.exception.NotFoundException;
import foto.model.Fotografija;
import foto.model.Oseba;
import foto.model.Oznaka;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.*;


public class KatalogFotografij {

    private Map<Integer, Oseba> osebaMap = new HashMap<>();

    private Map<Integer, Oznaka> oznakaMap = new HashMap<>();

    private Map<Integer, Fotografija> fotografijaMap = new HashMap<>();

    public static void main(String[] args) {
        KatalogFotografij katalog = new KatalogFotografij();

        BufferedReader reader =
            new BufferedReader(new InputStreamReader(System.in));

        try {

            zanka:
            for (; ; ) {
                System.out.print("foto> ");
                String command = reader.readLine();

                Integer osebaId;
                Integer oznakaId;
                Integer fotoId;

                switch (command) {
                    case "izhod":
                        break zanka;

                    case "dodaj osebo":

                        try {
                            System.out.print("id: ");
                            osebaId = Integer.parseInt(reader.readLine());

                            System.out.print("ime: ");
                            String ime = reader.readLine();

                            System.out.print("priimek: ");
                            String priimek = reader.readLine();

                            katalog.dodajOsebo(osebaId, ime, priimek);
                        } catch (NumberFormatException e) {
                            System.out.println("Napaka: ID mora biti stevilka.");
                            continue zanka;
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (AlreadyExistsException e) {
                            System.out.println(e.getMessage());
                            continue zanka;
                        }
                        break;

                    case "izpisi osebe":
                        katalog.izpisiOsebe();
                        break;

                    case "dodaj oznako":

                        System.out.print("id: ");
                        try {
                            oznakaId = Integer.parseInt(reader.readLine());

                            System.out.print("opis: ");
                            String opis = reader.readLine();

                            katalog.dodajOznako(oznakaId, opis);

                        } catch (NumberFormatException e) {
                            System.out.println("Napaka: ID mora biti stevilka.");
                            continue zanka;
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (AlreadyExistsException e) {
                            System.out.println(e.getMessage());
                            continue zanka;
                        }
                        break;

                    case "izpisi oznake":
                        katalog.izpisiOznake();
                        break;

                    case "dodaj foto":
                        try {
                            System.out.print("id: ");
                            fotoId = Integer.parseInt(reader.readLine());

                            System.out.print("opis: ");
                            String fotoOpis = reader.readLine();

                            System.out.print("sirina: ");
                            Integer fotoSirina = Integer.parseInt(reader.readLine());

                            System.out.print("visina: ");
                            Integer fotoVisina = Integer.parseInt(reader.readLine());

                            katalog.dodajFotografijo(
                                fotoId, fotoSirina, fotoVisina, fotoOpis,
                                new ArrayList<>(), new ArrayList<>());
                        } catch (NumberFormatException e) {
                            System.out.println("Napaka: ID, širina in visina morata biti stevilki.");
                            continue zanka;
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (AlreadyExistsException e) {
                            System.out.println(e.getMessage());
                            continue zanka;
                        }
                        break;

                    case "dodaj osebo na foto":
                        try {
                            System.out.print("id fotografije: ");
                            fotoId = Integer.parseInt(reader.readLine());
                            System.out.print("id osebe: ");
                            osebaId = Integer.parseInt(reader.readLine());

                            katalog.dodajOseboNaFotografijo(fotoId, osebaId);
                        } catch (NumberFormatException e) {
                            System.out.println("Napaka: ID mora biti stevilka.");
                            continue zanka;
                        } catch (NotFoundException e) {
                            System.out.println(e.getMessage());
                            continue zanka;
                        }
                        break;

                    case "dodaj oznako na foto":
                        try {
                            System.out.print("id fotografije: ");
                            fotoId = Integer.parseInt(reader.readLine());
                            System.out.print("id oznake: ");
                            oznakaId = Integer.parseInt(reader.readLine());

                            katalog.dodajOznakoNaFotografijo(fotoId, oznakaId);
                        } catch (NumberFormatException e) {
                            System.out.println("Napaka: ID mora biti stevilka.");
                            continue zanka;
                        } catch (NotFoundException e) {
                            System.out.println(e.getMessage());
                            continue zanka;
                        }
                        break;

                    case "izpisi foto":
                        katalog.izpisiFotografije();
                        break;

                    case "shrani":
                        try {
                            katalog.shraniStanje();
                            System.out.println("Stanje je bilo uspesno shranjeno");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "preberi stanje":
                        try {
                            katalog.preberiStanje();
                            System.out.println("Stanje je bilo uspesno prebrano iz datoteke");
                        } catch (IOException | ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "brisi osebo":
                        System.out.print("ID osebe: ");
                        Integer id = Integer.parseInt(reader.readLine());

                        try {
                            katalog.brisanjeOsebe(id);
                            System.out.println("Oseba je bila uspesno izbrisana.");
                        } catch (NotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case "brisi oznako":
                        System.out.print("ID oznake: ");
                        id = Integer.parseInt(reader.readLine());

                        try {
                            katalog.brisanjeOznake(id);
                            System.out.println("Oznaka je bila uspesno izbrisana.");
                        } catch (NotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case "brisi foto":
                        System.out.print("ID fotografije: ");
                        id = Integer.parseInt(reader.readLine());

                        try {
                            katalog.brisanjeFotografije(id);
                            System.out.println("Fotografija je bila uspesno izbrisana.");
                        } catch (NotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    default:
                        System.out.println("Napasen ukaz. Mozni ukazi:");
                        System.out.println("izhod");
                        System.out.println("dodaj osebo");
                        System.out.println("izpisi osebe");
                        System.out.println("dodaj oznako");
                        System.out.println("izpisi oznake");
                        System.out.println("dodaj foto");
                        System.out.println("dodaj osebo na foto");
                        System.out.println("dodaj oznako na foto");
                        System.out.println("izpisi foto");
                        System.out.println("shrani");
                        System.out.println("preberi stanje");
                        System.out.println("brisi osebo");
                        System.out.println("brisi oznako");
                        System.out.println("brisi foto");
                        continue zanka;
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void dodajOsebo(Integer id, String ime, String priimek) throws AlreadyExistsException {
        if (id <= 0) {
            throw new IllegalArgumentException("Napaka: ID mora biti pozitiven.");
        }
        if (osebaMap.containsKey(id)) {
            throw new AlreadyExistsException("Napaka: Oseba s tem ID-jem že obstaja.");
        }
        Oseba o = new Oseba();
        o.setId(id);
        o.setIme(ime);
        o.setPriimek(priimek);

        this.osebaMap.put(o.getId(), o);
    }

    public void dodajOznako(Integer id, String opis) throws AlreadyExistsException {
        if (id <= 0) {
            throw new IllegalArgumentException("Napaka: ID mora biti pozitiven.");
        }
        if (oznakaMap.containsKey(id)) {
            throw new AlreadyExistsException("Napaka: Oznaka s tem ID-jem že obstaja.");
        }
        Oznaka o = new Oznaka();
        o.setId(id);
        o.setOpis(opis);

        this.oznakaMap.put(o.getId(), o);
    }

    public void dodajFotografijo(
        Integer id, Integer sirina, Integer visina, String opis,
        List<Oseba> osebaList, List<Oznaka> oznakaList) throws AlreadyExistsException {
        if (id <= 0) {
            throw new IllegalArgumentException("Napaka: ID mora biti pozitiven.");
        }
        if (sirina <= 0 || visina <= 0) {
            throw new IllegalArgumentException("Napaka: Širina in višina morata biti pozitivni števili.");
        }
        if (fotografijaMap.containsKey(id)) {
            throw new AlreadyExistsException("Napaka: Fotografija s tem ID-jem že obstaja.");
        }
        Fotografija f = new Fotografija();
        f.setId(id);
        f.setSirina(sirina);
        f.setVisina(visina);
        f.setOpis(opis);
        f.setOsebaList(osebaList);
        f.setOznakaList(oznakaList);

        this.fotografijaMap.put(f.getId(), f);
    }

    public void dodajOseboNaFotografijo(
        Integer fotoId, Integer osebaId) throws NotFoundException {

        Fotografija f = fotografijaMap.get(fotoId);
        if (f == null) {
            throw new NotFoundException("Napaka: Fotografija s tem ID-jem ne obstaja.");
        }
        Oseba o = osebaMap.get(osebaId);
        if (o == null) {
            throw new NotFoundException("Napaka: Oseba s tem ID-jem ne obstaja.");
        }

        f.getOsebaList().add(o);
    }

    public void dodajOznakoNaFotografijo(
        Integer fotoId, Integer oznakaId) throws NotFoundException {

        Fotografija f = fotografijaMap.get(fotoId);
        if (f == null) {
            throw new NotFoundException("Napaka: Fotografija s tem ID-jem ne obstaja.");
        }

        Oznaka o = oznakaMap.get(oznakaId);
        if (o == null) {
            throw new NotFoundException("Napaka: Oznaka s tem ID-jem ne obstaja.");
        }

        f.getOznakaList().add(o);
    }

    public void izpisiOsebe() {
        System.out.println("OSEBE:");
        List<Oseba> osebe = new ArrayList<>(this.getOsebaMap().values());
        Collections.sort(osebe, Comparator.comparing(Oseba::getPriimek));

        for (Oseba o : osebe) {
            System.out.println(o);
        }
        System.out.println("-----");
    }

    public void izpisiOznake() {
        System.out.println("OZNAKE:");
        List<Oznaka> oznake = new ArrayList<>(this.getOznakaMap().values());
        Collections.sort(oznake, Comparator.comparing(Oznaka::getOpis));

        for (Oznaka o : oznake) {
            System.out.println(o);
        }
        System.out.println("-----");
    }

    public void izpisiFotografije() {
        System.out.println("FOTOGRAFIJE:");
        List<Fotografija> fotografije = new ArrayList<>(this.getFotografijaMap().values());
        Collections.sort(fotografije, Comparator.comparingInt(f -> f.getOsebaList().size()));
        for (Fotografija f : fotografije) {
            System.out.println(f);
        }
        System.out.println("-----");
    }

    public void shraniStanje() throws IOException {
        try (
            FileOutputStream fos = new FileOutputStream("Shramba.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
        ) {
            oos.writeObject(this.getOsebaMap());
            oos.writeObject(this.getOznakaMap());
            oos.writeObject(this.getFotografijaMap());
        }
    }

    public void preberiStanje() throws IOException, ClassNotFoundException {
        try (
            FileInputStream fis = new FileInputStream("Shramba.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
        ) {
            Map<Integer, Oseba> osebaMap = (Map<Integer, Oseba>) ois.readObject();
            Map<Integer, Oznaka> oznakaMap = (Map<Integer, Oznaka>) ois.readObject();
            Map<Integer, Fotografija> fotografijaMap = (Map<Integer, Fotografija>) ois.readObject();

            this.osebaMap.putAll(osebaMap);
            this.oznakaMap.putAll(oznakaMap);
            this.fotografijaMap.putAll(fotografijaMap);
        }
    }

    public void brisanjeOsebe(Integer id) throws NotFoundException {

        if (!osebaMap.containsKey(id)) {
            throw new NotFoundException("Napaka: Oseba s tem ID-jem ne obstaja.");
        }

        for (Fotografija fotografija : fotografijaMap.values()) {
            fotografija.getOsebaList().removeIf(oseba -> oseba.getId().equals(id));
        }

        osebaMap.remove(id);
    }

    public void brisanjeOznake(Integer id) throws NotFoundException {

        if (!oznakaMap.containsKey(id)) {
            throw new NotFoundException("Napaka: Oznaka s tem ID-jem ne obstaja.");
        }

        for (Fotografija fotografija : fotografijaMap.values()) {
            fotografija.getOznakaList().removeIf(oznaka -> oznaka.getId().equals(id));
        }

        oznakaMap.remove(id);
    }

    public void brisanjeFotografije(Integer id) throws NotFoundException {

        if (!fotografijaMap.containsKey(id)) {
            throw new NotFoundException("Napaka: Fotografija s tem ID-jem ne obstaja.");
        }

        fotografijaMap.remove(id);
    }

    public Map<Integer, Oseba> getOsebaMap() {
        return osebaMap;
    }

    public Map<Integer, Oznaka> getOznakaMap() {
        return oznakaMap;
    }

    public Map<Integer, Fotografija> getFotografijaMap() {
        return fotografijaMap;
    }

}
