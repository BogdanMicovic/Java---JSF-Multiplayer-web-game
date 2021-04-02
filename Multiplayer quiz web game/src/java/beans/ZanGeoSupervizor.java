/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author HP
 */
public class ZanGeoSupervizor {
    
    private int idPartije;
    private String kategorija;
    private String odgovor;

    public int getIdPartije() {
        return idPartije;
    }

    public void setIdPartije(int idPartije) {
        this.idPartije = idPartije;
    }

    public ZanGeoSupervizor(int idPartije, String kategorija, String odgovor) {
        this.idPartije = idPartije;
        this.kategorija = kategorija;
        this.odgovor = odgovor;
    }

   

    public String getKategorija() {
        return kategorija;
    }

    public String getOdgovor() {
        return odgovor;
    }

   

    public void setKategorija(String kategorija) {
        this.kategorija = kategorija;
    }

    public void setOdgovor(String odgovor) {
        this.odgovor = odgovor;
    }

    
    
    
}
