/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author HP
 */
@Entity
public class Korisnik {
    
    private String korime;
    private String ime;
    private String prezime;
    private String lozinka;
    private String pitanje;
    private String odgovor;
    @Id
    private long JMBG;
    private String pol;
    private String email;
    private int tip;

    public Korisnik(String korime, String ime, String prezime, String lozinka, String pitanje, String odgovor, long JMBG, String pol, String email, int tip) {
        this.korime = korime;
        this.ime = ime;
        this.prezime = prezime;
        this.lozinka = lozinka;
        this.pitanje = pitanje;
        this.odgovor = odgovor;
        this.JMBG = JMBG;
        this.pol = pol;
        this.email = email;
        this.tip = tip;
    }

    public void setKorime(String korime) {
        this.korime = korime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public void setPitanje(String pitanje) {
        this.pitanje = pitanje;
    }

    public void setOdgovor(String odgovor) {
        this.odgovor = odgovor;
    }

    public void setJMBG(long JMBG) {
        this.JMBG = JMBG;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTip(int tip) {
        this.tip = tip;
    }

    public String getKorime() {
        return korime;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getLozinka() {
        return lozinka;
    }

    public String getPitanje() {
        return pitanje;
    }

    public String getOdgovor() {
        return odgovor;
    }

    public long getJMBG() {
        return JMBG;
    }

    public String getPol() {
        return pol;
    }

    public String getEmail() {
        return email;
    }

    public int getTip() {
        return tip;
    }
    
}
