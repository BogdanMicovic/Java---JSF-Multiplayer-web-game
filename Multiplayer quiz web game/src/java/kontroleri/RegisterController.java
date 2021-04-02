/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri;

import beans.Korisnik;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import korisnik.Korisnici2;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author HP
 */
@ManagedBean
@SessionScoped
@Named(value = "registerController")
public class RegisterController  implements Serializable{
    private String korime;
    private String ime;
    private String prezime;
    private String lozinka;
    private String pitanje;
    private String odgovor;
    private String JMBG;
    private String pol;
    private String email;
    private String potvrda;
    private String zanimanje;
    
    public void setZanimanje(String zanimanje) {
        this.zanimanje = zanimanje;
    }

    public String getZanimanje() {
        return zanimanje;
    }
    

    public String getPotvrda() {
        return potvrda;
    }

    public void setPotvrda(String potvrda) {
        this.potvrda = potvrda;
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

    public String getJMBG() {
        return JMBG;
    }

    public String getPol() {
        return pol;
    }

    public String getEmail() {
        return email;
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

    public void setJMBG(String JMBG) {
        this.JMBG = JMBG;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public  String register(){
        //Korisnik korisnik=new Korisnik(korime, ime, prezime, lozinka, pitanje, odgovor, Long.parseLong(JMBG), pol, email, 1);
      /*  Korisnici2 korisnik=new Korisnici2(Long.parseLong(JMBG), korime, lozinka, ime, prezime, new Byte("1"), email,zanimanje, pol, pitanje,  odgovor);
        SessionFactory    sf=HibernateUtil.getSessionFactory();
        Session session=sf.openSession();
        session.beginTransaction();
        session.save(korisnik);
        session.getTransaction().commit();
        sf.close();
        */
      Long jmbg=Long.parseLong(JMBG);
     
      byte tip=0;
      Byte b=new Byte(tip);
      System.out.println(korime);
      System.out.println(lozinka);
      System.out.println(ime);
      System.out.println(prezime);
      System.out.println(b);
      System.out.println(email);
      System.out.println(zanimanje);
      System.out.println(pol);
      System.out.println(pitanje);
      System.out.println(odgovor);
      if(lozinka.equals(potvrda)){
      
      Korisnici2 korisnik=new Korisnici2(jmbg, korime, lozinka, ime, prezime, tip, email, zanimanje, pol, pitanje, odgovor);
       Session session=HibernateUtil.getSession();
       Transaction t=session.beginTransaction();
       Query q=session.createSQLQuery("INSERT INTO `jsf_projekat`.`zahtevi`\n" +
"(`jmbg`,\n" +
"`korime`,\n" +
"`lozinka`,\n" +
"`ime`,\n" +
"`prezime`,\n" +
"`tip`,\n" +
"`email`,\n" +
"`zanimanje`,\n" +
"`pol`,\n" +
"`pitanje`,\n" +
"`odgovor`)\n" +
"VALUES (:jmbg , :korime , :lozinka , :ime , :prezime , :tip , :email , :zanimanje , :pol , :pitanje , :odgovor)");
       q.setParameter("jmbg", jmbg);
       q.setParameter("korime", korime);
       q.setParameter("lozinka", lozinka);
       q.setParameter("ime", ime);
       q.setParameter("prezime", prezime);
       q.setParameter("tip", 0);
       q.setParameter("email", email);
       q.setParameter("zanimanje", zanimanje);
       q.setParameter("pol", pol);
       q.setParameter("pitanje", pitanje);
       q.setParameter("odgovor", odgovor);
       q.executeUpdate();
       
       t.commit();
      
      }
      return "index";
    }

}