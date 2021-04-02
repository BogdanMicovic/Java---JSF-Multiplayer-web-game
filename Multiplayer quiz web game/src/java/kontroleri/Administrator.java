/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri;

import beans.Anagram;
import beans.Pehar;
import date.DatesConversion;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
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
@ManagedBean(name="Administrator")
@ViewScoped
//@Named(value = "Administrator")
public class Administrator implements Serializable{
    List<Korisnici2> korisnici;
    List<Anagram> anagrami;
    List<Pehar> pehari;
    List<Pehar> pehar_koji_se_ucitava=new ArrayList<Pehar>();
    java.util.Date uneti_pehar_datum;

    public void setUneti_pehar_datum(java.util.Date uneti_pehar_datum) {
        this.uneti_pehar_datum = uneti_pehar_datum;
    }

    public java.util.Date getUneti_pehar_datum() {
        return uneti_pehar_datum;
    }

    public void setPehari(List<Pehar> pehari) {
        this.pehari = pehari;
    }

    public List<Pehar> getPehari() {
        return pehari;
    }
    
    
    public void setAnagrami(List<Anagram> anagrami) {
        this.anagrami = anagrami;
    }

    public List<Anagram> getAnagrami() {
        return anagrami;
    }

    public List<Korisnici2> getKorisnici() {
        return korisnici;
    }

    public void setKorisnici(List<Korisnici2> korisnici) {
        this.korisnici = korisnici;
    }
   
    @PostConstruct
        public void init(){
            //ucitavanje zahteva za registraciju
            System.out.println("Usao u init funkciju administratora");
            korisnici=new ArrayList<Korisnici2>();
       // SessionFactory sf=HibernateUtil.getSessionFactory();
       // if(sf==null){
        //System.out.println("SF=NULL!");
        //}
        // Session session=sf.openSession();
        Session session=HibernateUtil.getSession();
        Transaction t=session.beginTransaction();
        
        Query q=session.createSQLQuery("SELECT * FROM `jsf_projekat`.`zahtevi`");
        List<Object[]> rows=q.list();
        for(Object[] row:rows){
        Korisnici2 k=new Korisnici2(Long.parseLong(row[0].toString()), row[1].toString(), row[2].toString(), row[3].toString(), row[4].toString(), (byte) row[5], row[6].toString(), row[7].toString(), row[8].toString(), row[9].toString(), row[10].toString());
        korisnici.add(k);
        }
       
       // sf.close();
       
       
       //ucitavanje baza za igru dana
       //izmeni from uslov i navedi cele baze ***********
       Query q1=session.createSQLQuery("SELECT * FROM `jsf_projekat`.`anagrami1`");
       List<Object[]> rows1=q1.list();
       System.out.println("Broj anagrama:");
              System.out.println(rows1.size());
        anagrami=new ArrayList<Anagram>();
        for(Object[] row1:rows1){
            System.out.println("Naziv:");
            System.out.println(row1[0].toString());
            System.out.println("Resenje:");
            System.out.println(row1[1].toString());
            String naziv=row1[0].toString();
            String resenje=row1[1].toString();
       Anagram a=new Anagram(naziv,resenje);
       if(a!=null){
       anagrami.add(a);
       }
        }
        
        pehari=new ArrayList<Pehar>();
        Query q2=session.createSQLQuery("SELECT * FROM `jsf_projekat`.`pehari1`");
        List<Object[]> rows2=q2.list();
        for(Object[]row2:rows2){
        int idP=new Integer(row2[0].toString());
        String pitanje=row2[1].toString();
        String odgovor=row2[2].toString();
        int grupa=new Integer(row2[3].toString());
        
        Pehar pehar=new Pehar(idP, pitanje, odgovor, grupa);
        pehari.add(pehar);
        }
        
        
        
        t.commit();
        }
        
        public void podesi_datum_anagrama(String naziv,java.util.Date datum){
        Session session=HibernateUtil.getSession();
        Transaction t=session.beginTransaction();
        String resenje=null;
        Query q2=session.createSQLQuery("SELECT * FROM `jsf_projekat`.`anagrami1` WHERE naziv=:naziv");
        q2.setParameter("naziv", naziv);
        List<Object[]> rows2=q2.list();
        for(Object[] row2:rows2){
        resenje=row2[1].toString();
        }
        System.out.println("Resenje anagrama: ");
        System.out.println(resenje);
        Query q=session.createSQLQuery("SELECT * FROM `jsf_projekat`.`anagrami` WHERE naziv=:naziv ");
        q.setParameter("naziv", naziv);
        List<Object[]> rows=q.list();
        if(rows.size()>0){
        //anagram sa zadatim nazivom vec postoji u bazi anagrami sto zanci da mu je vec dodeljen neki datum
        for(Object[] row:rows){
         Date datum_anagrama_iz_baze=(Date)row[2];
         int anagram_odigran=Integer.parseInt(row[3].toString());
         java.util.Date danasnji_datum=new java.util.Date();// vraca vreme pozivanja
         Date danasnji_sql_datum=DatesConversion.convertUtilToSql(danasnji_datum);
         java.util.Date datum_anagram =new java.util.Date(datum_anagrama_iz_baze.getTime());
         SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
         if((fmt.format(danasnji_datum).equals(fmt.format(datum_anagram))&& anagram_odigran==0) || datum_anagrama_iz_baze.compareTo(danasnji_sql_datum)>0){
             // Sme se promeniti datum anagrama
             Query q4=session.createSQLQuery("UPDATE `jsf_projekat`.`anagrami`\n" +
"SET `datum` =:noviDatumAnagrama WHERE `naziv` =:nazivAnagrama");
             Date novi_datum_anagrama =DatesConversion.convertUtilToSql(datum);
             q4.setParameter("nazivAnagrama", naziv);
             q4.setParameter("noviDatumAnagrama", novi_datum_anagrama);
             q4.executeUpdate();
              t.commit();
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Uspesno ste promenili datum anagrama"));

         }
         else{//Ne sme se promeniti datum anagrama -> Ubaci ispisivanje poruke u tom slucaju
                  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Anagram je danas odigran ili je zadat datum iz proslosti"));

         
         }
             }
       
        }
        else{
        //anagram sa zadatim nazivom ne postoji u bazi anagrami sto znaci da mu nije dodeljen ni jedan datum
        Query q1=session.createSQLQuery("INSERT INTO `jsf_projekat`.`anagrami`\n" +
"(`naziv`,\n" +
"`resenje`,\n" +
"`datum`,\n" +
"`odigran`)\n" +
"VALUES (:naziv, :resenje, :datum, :odigran)");
        q1.setParameter("naziv", naziv);
        q1.setParameter("resenje", resenje);
        java.sql.Date sql_datum=DatesConversion.convertUtilToSql(datum);
        q1.setParameter("datum", sql_datum);
        q1.setParameter("odigran", 0);
        q1.executeUpdate();
         t.commit();
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Uspesno ste uneli novi anagram"));

        }
        
        }
        
        
        
        public void potvdi_odbaci_zahtev(int potvrdi_odbaci,String korisnicko_ime){
        //POTVRDA ZAHTEVA-> potvrdi_odbaci=1, ODBACIVANJE ZAHTEVA-> potvrdi_odbaci=2
        System.out.println("Usao u potvdi_odbaci_zahtev");
        if(potvrdi_odbaci==1){
           for(Korisnici2 k:korisnici){
           if(k.getKorime()==korisnicko_ime){
               long jmbg=k.getJmbg();
      String korime=k.getKorime();
      String lozinka=k.getLozinka();
      String ime=k.getIme();
      String prezime=k.getPrezime();
      byte tip=1;
      String email=k.getPrezime();
      String zanimanje=k.getZanimanje();
      String pol=k.getPol();
      String pitanje=k.getPitanje();
      String odgovor=k.getOdgovor();
      System.out.println("Korisnicko ime:");
      System.out.println(korime);
          //  SessionFactory sf=HibernateUtil.getSessionFactory();
            // if(sf==null){
             //System.out.println("SF=NULL!");
             //}
               //Session session=sf.openSession();
               Session session=HibernateUtil.getSession();
               Transaction t=session.beginTransaction();
               Query q=session.createSQLQuery("INSERT INTO `jsf_projekat`.`korisnici`(`jmbg`,`korime`,`lozinka`,`ime`,`prezime`,`tip`,`email`,`zanimanje`,`pol`,`pitanje`,`odgovor`)VALUES(:jmbg, :korime, :lozinka,:ime, :prezime, :tip, :email, :zanimanje, :pol, :pitanje, :odgovor)");
               q.setParameter("jmbg", jmbg);
                q.setParameter("korime", korime);
                 q.setParameter("lozinka", lozinka);
                  q.setParameter("ime", ime);
                   q.setParameter("prezime", prezime);
                    q.setParameter("tip", 1);
                     q.setParameter("pol", pol);
                      q.setParameter("zanimanje", zanimanje);
                       q.setParameter("email", email);
                        q.setParameter("pitanje", pitanje);
                         q.setParameter("odgovor", odgovor);
                         q.executeUpdate();
                         
                         Query q1=session.createSQLQuery("DELETE FROM `jsf_projekat`.`zahtevi` WHERE korime=:korime");
                         q1.setParameter("korime", korime);
                         q1.executeUpdate();
                         t.commit();  // 
                          

                         // sf.close();
           }
           }
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Uspesno ste potvrdili zahtev"));

       // Korisnici2 k=korisnici.
        }
        else{
        for(Korisnici2 k:korisnici){
        if(k.getKorime()==korisnicko_ime){
       // SessionFactory sf=HibernateUtil.getSessionFactory();
         //    if(sf==null){
           //  System.out.println("SF=NULL!");
            // }
              // Session session=sf.openSession();
               Session session=HibernateUtil.getSession();
               Transaction t=session.beginTransaction();
               Query q1=session.createSQLQuery("DELETE FROM `jsf_projekat`.`zahtevi` WHERE korime=:korime");
                         q1.setParameter("korime", korisnicko_ime);
                         q1.executeUpdate();
                         t.commit();  // 
                          
                          
                         // sf.close();
        }
        }
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Uspesno ste odbacili zahtev"));

        }
        int pozicija=0;
        for(Korisnici2 kor:korisnici){
        if(kor.getKorime().equals(korisnicko_ime)){
        korisnici.remove(pozicija);
        System.out.println("Izbacen korisnik");
        System.out.println(korisnicko_ime);
    
        }
        pozicija++;
        }
        }
        
public void podesi_datum_pehara(int grupa,java.util.Date datum){
    System.out.println("datum:");
    System.out.println(datum.toString());
    Session session=HibernateUtil.getSession();
        Transaction t=session.beginTransaction();
        String resenje=null;
        Query q2=session.createSQLQuery("SELECT * FROM `jsf_projekat`.`pehari1` WHERE grupa=:grupa");
        q2.setParameter("grupa", grupa);
        List<Object[]> rows2=q2.list();
        for(Object[] row2:rows2){
        int idP=new Integer(row2[0].toString());
        String pitanje=row2[1].toString();
        String odgovor=row2[2].toString();
        Pehar pehar=new Pehar(idP, pitanje, odgovor, grupa);
        pehar_koji_se_ucitava.add(pehar);
        }
        
        Query q=session.createSQLQuery("SELECT * FROM `jsf_projekat`.`pehari` WHERE grupa=:grupa ");
        q.setParameter("grupa", grupa);
        List<Object[]> rows=q.list();
        if(rows.size()>0){//dati pehar vec postoji u bazi sto znaci da mu je dodeljen datum
            Object[] row=rows.get(0);
            java.sql.Date datum_pehara=(java.sql.Date)row[4];
            int pehar_odigran=Integer.parseInt(row[5].toString());
        // vraca vreme pozivanja
         java.util.Date danasnji_datum=new java.util.Date();
         Date danasnji_sql_datum=DatesConversion.convertUtilToSql(danasnji_datum);
          SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
          java.util.Date datum_pehara_util=new java.util.Date(datum_pehara.getTime());
         if((fmt.format(danasnji_datum).equals(fmt.format(datum_pehara_util)) && pehar_odigran==0 ) || datum_pehara.compareTo(danasnji_sql_datum)>0  ){
           //  for(Pehar p:pehar_koji_se_ucitava){
             
             //}
             Query q4=session.createSQLQuery("UPDATE `jsf_projekat`.`pehari`\n" +
"SET `datum` =:noviDatumPehara WHERE `grupa` =:grupa");
             java.sql.Date novi_datum_pehara =DatesConversion.convertUtilToSql(datum);
             q4.setParameter("grupa", grupa);
             q4.setParameter("noviDatumPehara", novi_datum_pehara);
             q4.executeUpdate();
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Uspesno ste promenili datum pehara"));

         }
         else{
         //Ne sme se promeniti datum pehara -> Ubaci ispisivanje poruke u tom slucaju
                  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Pehar je danas odigran ili je zadat datum iz proslosti"));

         }
         
        }
        else{
        for(Pehar p:pehar_koji_se_ucitava){
         Query q1=session.createSQLQuery("INSERT INTO `jsf_projekat`.`pehari`\n" +
"`pitanje`,\n" +
"`odgovor`,\n" +
"`grupa`)\n" +
"`datum`)\n" +
"`odigran`)\n" +                 
"VALUES ( :pitanje, :odgovor, :grupa , :datum , :odigran)");
        q1.setParameter("pitanje", p.getPitanje());
        q1.setParameter("odgovor", p.getOdgovor());
        q1.setParameter("grupa", grupa);
        java.sql.Date sql_datum=DatesConversion.convertUtilToSql(datum);
        q1.setParameter("datum", sql_datum);
        q1.setParameter("odigran", 0);
        q1.executeUpdate();
         
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Uspesno ste uneli novi pehar"));

        
        }
        
        }
        t.commit();
    
}

}
