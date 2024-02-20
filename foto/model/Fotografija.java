package foto.model;

import java.io.Serializable;
import java.util.List;

public class Fotografija implements Serializable {

    private Integer id;

    private Integer sirina;

    private Integer visina;

    private String opis;

    private List<Oseba> osebaList;

    private List<Oznaka> oznakaList;

    @Override
    public String toString() {
        return "Fotografija [id=" + id + ", sirina=" + sirina
            + ", visina=" + visina + ", opis=" + opis + ", osebaList="
            + osebaList + ", oznakaList=" + oznakaList + "]";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSirina() {
        return sirina;
    }

    public void setSirina(Integer sirina) {
        this.sirina = sirina;
    }

    public Integer getVisina() {
        return visina;
    }

    public void setVisina(Integer visina) {
        this.visina = visina;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public List<Oseba> getOsebaList() {
        return osebaList;
    }

    public void setOsebaList(List<Oseba> osebaList) {
        this.osebaList = osebaList;
    }

    public List<Oznaka> getOznakaList() {
        return oznakaList;
    }

    public void setOznakaList(List<Oznaka> oznakaList) {
        this.oznakaList = oznakaList;
    }

}
