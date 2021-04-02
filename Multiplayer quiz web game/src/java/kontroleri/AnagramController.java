/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri;

import date.DatesConversion;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author HP
 */
@ManagedBean(name = "anagramController")
@ViewScoped
public class AnagramController implements Serializable{
    private String zadati_anagram;
   private  String resenje; // korisnicko resenje anagrama
    private String zadati_anagram1;
    private String resenje1; 
    private int preostalo_vreme=60;

    public int getPreostalo_vreme() {
        return preostalo_vreme;
    }

    public void setPreostalo_vreme(int preostalo_vreme) {
        this.preostalo_vreme = preostalo_vreme;
    }
    
    public void setResenje1(String resenje1) {
        this.resenje1 = resenje1;
    }

    public String getResenje1() {
        return resenje1;
    }

    public void setZadati_anagram1(String zadati_anagram1) {
        this.zadati_anagram1 = zadati_anagram1;
    }

    public String getZadati_anagram1() {
        return zadati_anagram1;
    }
    
    @PostConstruct
        public void init(){
            System.out.println("Usao u init funkciju AnagramControllera");
            Session session=HibernateUtil.getSession();
            Transaction t=session.beginTransaction();
            Query q=session.createSQLQuery("SELECT *  FROM `jsf_projekat`.`anagrami` WHERE datum=:danasnjiDatum");
            java.util.Date datum=new  Date();// danasnji java.util datum
            java.sql.Date danasnji_datum_sql=DatesConversion.convertUtilToSql(datum);// danasnji sql datum
            q.setParameter("danasnjiDatum", danasnji_datum_sql);
            List<Object[]> rows=q.list();
            t.commit();
            int number_of_anagrams=rows.size();
            System.out.println("Broj anagrama:");
            System.out.println(number_of_anagrams);
            Random rand=new Random();
            int number=rand.nextInt(number_of_anagrams);
            int number1=(number+1)% number_of_anagrams;
            Object[] row=rows.get(number);
            zadati_anagram=row[0].toString();
            row=rows.get(number1);
            zadati_anagram1=row[0].toString();
        }

        public String potvrda_anagrama() throws IOException{ //poziva se kada korisnik unese svoje resenje anagrama
           Session session=HibernateUtil.getSession();
           Transaction t=session.beginTransaction();
           Query q=session.createSQLQuery("SELECT * FROM `jsf_projekat`.`anagrami` WHERE naziv=:naziv OR naziv=:naziv1");
            q.setParameter("naziv", zadati_anagram);
            q.setParameter("naziv1",zadati_anagram1 );
            List<Object[]> rows=q.list();
             t.commit();
            for(Object[] row:rows){
            if(resenje.equals(row[1].toString()) || resenje1.equals(row[1].toString())){
            LoginController.dodajPoene(10);
            }
            }
            System.out.println("Trenutni poeni takmicara:");
            System.out.println(LoginController.getPoeni());
           
                        FacesContext.getCurrentInstance().getExternalContext().redirect("faces/moj_broj.xhtml");

            return "";
        }
        
        public void decrement() throws IOException{
             System.out.println("decrementing....");
        preostalo_vreme--;
        if(preostalo_vreme==0){
        potvrda_anagrama();
        }
        }
        
    public void setZadati_anagram(String zadati_anagram) {
        this.zadati_anagram = zadati_anagram;
    }

    public void setResenje(String resenje) {
        this.resenje = resenje;
    }

    public String getZadati_anagram() {
        return zadati_anagram;
    }

    public String getResenje() {
        return resenje;
    }
}
