/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri;

import beans.Partija;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author HP
 */

@ManagedBean(name="pregledRezultata")
@ViewScoped
public class PregledRezultataController implements Serializable{
    
    private List<Partija> rezultati=new ArrayList<Partija>();
    private List<Partija> prvih_deset_rezultata=new ArrayList<Partija>();

    public void setPrvih_deset_rezultata(List<Partija> prvih_deset_rezultata) {
        this.prvih_deset_rezultata = prvih_deset_rezultata;
    }

    public List<Partija> getPrvih_deset_rezultata() {
        return prvih_deset_rezultata;
    }
    
    public void setRezultati(List<Partija> rezultati) {
        this.rezultati = rezultati;
    }

    public List<Partija> getRezultati() {
        return rezultati;
    }
    
    @PostConstruct
        public void init(){
        Session session=HibernateUtil.getSession();
        Transaction t=session.beginTransaction();
        Query q=session.createSQLQuery("SELECT * FROM `jsf_projekat`.`partije`");
        List<Object[]> rows=q.list();
        for(Object[] row:rows){
            System.out.println("Prolaz kroz listu");
            int idPartije=0;
         int poeni=0;
         String korime="";
         java.sql.Date datum;
         idPartije=new Integer(row[0].toString()).intValue();
         poeni=new Integer(row[1].toString()).intValue();
         korime=row[2].toString();
         //java.sql.Date datum1=(java.sql.Date)row[3];
         datum=(java.sql.Date)row[3];
         java.util.Date datum_pravi= new java.util.Date(datum.getTime());
         java.util.Date danasnji_datum=new java.util.Date();
         SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
         if(fmt.format(datum_pravi).equals(fmt.format(danasnji_datum))){
              System.out.println("Usao u if");
         Partija p=new Partija(idPartije, poeni, korime, datum_pravi);
         rezultati.add(p);
         }
        
        }
        if(rezultati.size()>1){
         Collections.sort(rezultati);
         }
         
         int i=0;
         for(Partija p1:rezultati){
         if(i<10){
         prvih_deset_rezultata.add(p1);
         }
         i++;
         }
         if(prvih_deset_rezultata.size()>1){
         Collections.sort(prvih_deset_rezultata);
         }
        
        }
}
