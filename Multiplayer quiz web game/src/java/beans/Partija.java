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
public class Partija implements Comparable<Partija>{
    
    private int idPartije;
    private int poeni;
    private String korime;
    private java.util.Date datum;

    public Partija(int idPartije, int poeni, String korime, Date datum) {
        this.idPartije = idPartije;
        this.poeni = poeni;
        this.korime = korime;
        this.datum = datum;
    }

    public void setIdPartije(int idPartije) {
        this.idPartije = idPartije;
    }

    public void setPoeni(int poeni) {
        this.poeni = poeni;
    }

    public void setKorime(String korime) {
        this.korime = korime;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }
    

    public int getIdPartije() {
        return idPartije;
    }

    public int getPoeni() {
        return poeni;
    }

    public String getKorime() {
        return korime;
    }

    public Date getDatum() {
        return datum;
    }

    @Override
    public int compareTo(Partija p) {
        return this.datum.compareTo(p.getDatum());
    }

    
    
    
}
