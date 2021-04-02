/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri;

import beans.Partija;
import java.io.Serializable;
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

@ManagedBean(name="Gost")
@ViewScoped
public class GostController implements Serializable{
    
    private List<Partija> najbolje_partije_u_prethodnih_dvadeset;
    private List<Partija> najbolje_partije_u_prethodnom_mesecu; 

    public void setNajbolje_partije_u_prethodnih_dvadeset(List<Partija> najbolje_partije_u_prethodnih_dvadeset) {
        this.najbolje_partije_u_prethodnih_dvadeset = najbolje_partije_u_prethodnih_dvadeset;
    }

    public void setNajbolje_partije_u_prethodnom_mesecu(List<Partija> najbolje_partije_u_prethodnom_mesecu) {
        this.najbolje_partije_u_prethodnom_mesecu = najbolje_partije_u_prethodnom_mesecu;
    }

    public List<Partija> getNajbolje_partije_u_prethodnih_dvadeset() {
        return najbolje_partije_u_prethodnih_dvadeset;
    }

    public List<Partija> getNajbolje_partije_u_prethodnom_mesecu() {
        return najbolje_partije_u_prethodnom_mesecu;
    }
    
    
    @PostConstruct
        public void init(){
            najbolje_partije_u_prethodnih_dvadeset=new ArrayList<Partija>();
            najbolje_partije_u_prethodnom_mesecu=new ArrayList<Partija>();
        Session session=HibernateUtil.getSession();
        Transaction t=session.beginTransaction();
        Query q=session.createSQLQuery("SELECT * FROM `jsf_projekat`.`partije`");
        List<Object[]> rows=q.list();
        for(Object[] row:rows){
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
          int dan=danasnji_datum.getDate();
          java.util.Date datum_ovog_meseca=new java.util.Date();
          datum_ovog_meseca.setTime(datum_ovog_meseca.getTime() + (long)(-dan)*1000*60*60*24);//datum ovog meseca
         danasnji_datum.setTime(danasnji_datum.getTime() +(long)(-20)*1000*60*60*24);//ovo je sada datum od pre 30 dana
         if(datum_pravi.after(danasnji_datum)){
          Partija p=new Partija(idPartije, poeni, korime, datum_pravi);
          najbolje_partije_u_prethodnih_dvadeset.add(p);
         }
         if(datum_pravi.after(datum_ovog_meseca)){
             Partija p1=new Partija(idPartije, poeni, korime, datum_pravi);
         najbolje_partije_u_prethodnom_mesecu.add(p1);
         }
         if(najbolje_partije_u_prethodnih_dvadeset.size()>1){
             Collections.sort(najbolje_partije_u_prethodnih_dvadeset);
         }
         if(najbolje_partije_u_prethodnom_mesecu.size()>1){
         Collections.sort(najbolje_partije_u_prethodnom_mesecu);
         }
        }
         t.commit();
        }
    
}
