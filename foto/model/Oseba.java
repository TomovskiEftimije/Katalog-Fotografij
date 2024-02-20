package foto.model;

import java.io.Serializable;

public class Oseba implements Serializable {

    private Integer id;

    private String ime;

    private String priimek;

    @Override
    public String toString() {
        return "Oseba [id=" + id + ", ime=" + ime +
            ", priimek=" + priimek + "]";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPriimek() {
        return priimek;
    }

    public void setPriimek(String priimek) {
        this.priimek = priimek;
    }

}
