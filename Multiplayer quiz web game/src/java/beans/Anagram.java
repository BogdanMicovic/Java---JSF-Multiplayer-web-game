/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.Date;



/**
 *
 * @author HP
 */
public class Anagram implements java.io.Serializable{
    private String naziv;
    private String resenje;
    private Date datum;
    private int odigran;

    public void setOdigran(int odigran) {
        this.odigran = odigran;
    }

    public int getOdigran() {
        return odigran;
    }

    public String getNaziv() {
        return naziv;
    }

    public String getResenje() {
        return resenje;
    }

    public Date getDatum() {
        return datum;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setResenje(String resenje) {
        this.resenje = resenje;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Anagram(String naziv, String resenje, Date datum) {
        this.naziv = naziv;
        this.resenje = resenje;
        this.datum = datum;
    }
    
    public Anagram(String naziv,String resenje){
    this.naziv=naziv;
    this.resenje=resenje;
    this.datum=null;
    this.odigran=0;
    }
}
