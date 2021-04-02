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
public class Pehar {
    int idP;
    String pitanje;
    String odgovor;
    int grupa;
    java.util.Date datum;
    private int odigran;

    public Pehar(int idP, String pitanje, String odgovor, int grupa, Date datum) {
        this.idP = idP;
        this.pitanje = pitanje;
        this.odgovor = odgovor;
        this.grupa = grupa;
        this.datum = datum;
    }
    
    public Pehar(int idP,String pitanje,String odgovor,int grupa){
        this.idP = idP;
        this.pitanje = pitanje;
        this.odgovor = odgovor;
        this.grupa = grupa;
        this.datum=null;
        this.odigran=0;
    }

    public int getOdigran() {
        return odigran;
    }

    public void setOdigran(int odigran) {
        this.odigran = odigran;
    }

    
    
    
    public void setIdP(int idP) {
        this.idP = idP;
    }

    public void setPitanje(String pitanje) {
        this.pitanje = pitanje;
    }

    public void setOdgovor(String odgovor) {
        this.odgovor = odgovor;
    }

    public void setGrupa(int grupa) {
        this.grupa = grupa;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    
    
    public int getIdP() {
        return idP;
    }

    public String getPitanje() {
        return pitanje;
    }

    public String getOdgovor() {
        return odgovor;
    }

    public int getGrupa() {
        return grupa;
    }

    public Date getDatum() {
        return datum;
    }
    
    
}
