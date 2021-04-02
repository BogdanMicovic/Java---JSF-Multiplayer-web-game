/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author HP
 */
@ManagedBean(name="zaboravljenaLozinka")
@ViewScoped
public class ZaboravljenjaLozinkaController {
    static String korime;
    static String jmbg;
   static String pitanje;
    static String odgovor; //tacan odgovor
    static String odgovor_korisnika; // odgovor koji je on uneo
    static String nova_lozinka;

    public void setNova_lozinka(String nova_lozinka) {
        this.nova_lozinka = nova_lozinka;
    }

    public String getNova_lozinka() {
        return nova_lozinka;
    }

    public void setOdgovor_korisnika(String odgovor_korisnika) {
        this.odgovor_korisnika = odgovor_korisnika;
    }

    public String getOdgovor_korisnika() {
        return odgovor_korisnika;
    }

    public void setPitanje(String pitanje) {
        this.pitanje = pitanje;
    }

    public void setOdgovor(String odgovor) {
        this.odgovor = odgovor;
    }

    public String getPitanje() {
        return pitanje;
    }

    public String getOdgovor() {
        return odgovor;
    }

    public void setKorime(String korime) {
        this.korime = korime;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getKorime() {
        return korime;
    }

    public String getJmbg() {
        return jmbg;
    }
    
    public String potvrdi(){
       // SessionFactory sf=HibernateUtil.getSessionFactory();
        System.out.println("Usao u potvrdi() funkciju klase ZaboravljenaLozinkaController");
        //     if(sf==null){
          //   System.out.println("SF==NULL!");
            // }
              // Session session=sf.openSession();
              Session session=HibernateUtil.getSession();
               Transaction t=session.beginTransaction();
               Query q2=session.createSQLQuery("SELECT * FROM `jsf_projekat`.`korisnici` WHERE korime=:korime");
               q2.setParameter("korime", korime);
               List<Object[]> rows=q2.list();
    t.commit();
    if(rows!=null && !rows.isEmpty()){
     for(Object[] row:rows){
         System.out.println("Usao  u for petlju metode potvrdi() klase ZaboravljenaLozinkaController");
     pitanje=row[9].toString();
     odgovor=row[10].toString();
     }
     System.out.println("Pitanje:");
     System.out.println(pitanje);
     System.out.println("Odgovor");
     System.out.println(odgovor);
    }
    return "zaboravljena_lozinka1";
    }
    
    public String poklapa_se_odgovor(){
                System.out.println("Usao u poklapa_se_odgovor() funkciju klase ZaboravljenaLozinkaController");
                System.out.println("Pitanje:");
     System.out.println(pitanje);
     System.out.println("Odgovor");
     System.out.println(odgovor);
    if(odgovor.equals(odgovor_korisnika)){
         System.out.println("ODGOVOR JE TACAN");
        return "zaboravljena_lozinka3";
    }
    else{
    return "login";
    }
    }
    
    public String promenjena_lozinka(){//funkcija koja se poziva kada se izvrsi promena lozinke
                     System.out.println("Usao u promenjena_lozinka() funkciju klase ZaboravljenaLozinkaController");

      //  SessionFactory sf=HibernateUtil.getSessionFactory();
        //     if(sf==null){
          //   System.out.println("SF=NULL!");
            // }
             //  Session session=sf.openSession();
             Session session=HibernateUtil.getSession();
               Transaction t=session.beginTransaction();
              Query q2=session.createSQLQuery("UPDATE `jsf_projekat`.`korisnici`\n" +
"SET `lozinka`=:lozinka WHERE  `korime`=:korime");
                q2.setParameter("lozinka", nova_lozinka);
                q2.setParameter("korime", korime);
                q2.executeUpdate();
                  t.commit();  // 
        
        return "login";
    }
}
