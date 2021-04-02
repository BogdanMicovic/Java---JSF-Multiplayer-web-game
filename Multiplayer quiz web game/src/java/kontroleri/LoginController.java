/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri;

import beans.Korisnik;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.FacesComponent;
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
@ManagedBean
@SessionScoped
@Named(value = "loginController")
public class LoginController implements Serializable{
    String korime;
    String lozinka;
    private  Korisnici2 korisnik;
    private static int poeni; // Poeni koje je korisnik osvojio u jednoj partiji tj. kada zavrsi sve igre
    private static String korisnickoIme;
    static{
    poeni=0;
    korisnickoIme="";
    }
    
    public static String getKorisnickoIme(){
    return korisnickoIme;
    }
    
    public static void setKorisnickoIme(String kor_name){
    korisnickoIme=kor_name;
    }
    
 public static void dodajPoene(int dodatni_poeni){
 poeni+=dodatni_poeni;
 }
    public static int getPoeni() {
        return poeni;
    }

    public static void setPoeni(int poeni) {
        LoginController.poeni = poeni;
    }
   
   
    
    public void setKorisnik(Korisnici2 korisnik) {
        this.korisnik = korisnik;
    }

    public Korisnici2 getKorisnik() {
        return korisnik;
    }

    public String getKorime() {
        return korime;
    }

    

    public void setKorime(String korime) {
        this.korime = korime;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    @PostConstruct
        public void init(){
        System.out.println("Uaso u init za LoginController");
        }
    
    public String login() throws IOException{
         System.out.println("USAO U LOGIN");
         korisnickoIme=korime;
    //SessionFactory sf=HibernateUtil.getSessionFactory();
   //Session session=sf.openSession();
   Session session=HibernateUtil.getSession();
    Transaction t=session.beginTransaction();
    Query q=session.createSQLQuery("SELECT * FROM `jsf_projekat`.`korisnici` WHERE korime=:korime");
    //Mozda treba da se prosledi 1 u setLong umesto 0
    q.setParameter("korime", korime);
    List<Object[]> rows=q.list();
    t.commit();
    if(rows!=null && !rows.isEmpty()){
    for(Object[] row:rows){
        Long l=new Long(row[0].toString());
        System.out.println(l);
        korisnik=new Korisnici2(Long.parseLong(row[0].toString()), row[1].toString(), row[2].toString(), row[3].toString(), row[4].toString(), (byte) row[5], row[6].toString(), row[7].toString(), row[8].toString(), row[9].toString(), row[10].toString());
       
        if(korisnik.getTip()==1){
             //sf.close();
        return "takmicar";
        }
        else if(korisnik.getTip()==2){
           // if(sf!=null && !sf.isClosed()){
               // session.close();
               // sf.close();
             //   System.out.println("DOBRA SESIJAAA");
           // }
             
            System.out.println("Usao  u login za administratora ");
            FacesContext.getCurrentInstance().getExternalContext().redirect("faces/administrator.xhtml");
            return "";
//return "administrator";
        }
        else if(korisnik.getTip()==3){
              //sf.close();
        return "supervizor";
        }
        else if(korisnik.getTip()==4){
              //sf.close();
        return "gost";
        }
     
    }
    }
    
    System.out.println("LoginGreska");
    
    //sf.close();
    return "login";
    }
    
}
