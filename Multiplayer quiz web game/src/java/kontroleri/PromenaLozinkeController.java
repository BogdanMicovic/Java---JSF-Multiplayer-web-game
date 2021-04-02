/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author HP
 */
@ManagedBean(name="promenaLozinke")
@ViewScoped
public class PromenaLozinkeController {
    String korime;
    String stara_lozinka;
    String nova_lozinka;
    String nova_lozinka_potvrda;

    public String getKorime() {
        return korime;
    }

    public String getStara_lozinka() {
        return stara_lozinka;
    }

    public String getNova_lozinka() {
        return nova_lozinka;
    }

    public String getNova_lozinka_potvrda() {
        return nova_lozinka_potvrda;
    }

    public void setKorime(String korime) {
        this.korime = korime;
    }

    public void setStara_lozinka(String stara_lozinka) {
        this.stara_lozinka = stara_lozinka;
    }

    public void setNova_lozinka(String nova_lozinka) {
        this.nova_lozinka = nova_lozinka;
    }

    public void setNova_lozinka_potvrda(String nova_lozinka_potvrda) {
        this.nova_lozinka_potvrda = nova_lozinka_potvrda;
    }
    
    public String promeni_lozinku(){
       //SessionFactory sf=HibernateUtil.getSessionFactory();
         //    if(sf==null){
           //  System.out.println("SF=NULL!");
             //}
               //Session session=sf.openSession();
               Session session = HibernateUtil.getSession();
               Transaction t=session.beginTransaction();
               Query q1=session.createSQLQuery("SELECT lozinka FROM  jsf_projekat.korisnici WHERE korime=:korime");
               q1.setParameter("korime", korime);
                List<Object[]> rows=q1.list();
               
                //Object[] row=rows.get(0);
                //System.out.println(row[0].toString());
                //String lozinka=row[0].toString();
                String lozinka=rows.toString();
              lozinka=  lozinka.replace("[", "").replace("]", "");
                
                System.out.println("Lozinka ko je menja je: ");
                System.out.print(lozinka);
                if(rows!=null){
                    System.out.println("Nova lozinka:");
                    System.out.print(nova_lozinka);
                    System.out.println("Nova lozinka potvrda:");
                    System.out.print(nova_lozinka_potvrda);
                if(lozinka.equals(stara_lozinka)){
                if(nova_lozinka.equals(nova_lozinka_potvrda)){
                Query q2=session.createSQLQuery("UPDATE `jsf_projekat`.`korisnici`\n" +
"SET `lozinka`=:lozinka WHERE  `korime`=:korime");
                q2.setParameter("lozinka", nova_lozinka);
                q2.setParameter("korime", korime);
                q2.executeUpdate();
                  t.commit();  // 
                          
                          
                         // sf.close();
                }
                else{
                FacesMessage msg = new FacesMessage("Pogresna potvrda lozinke");
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                FacesContext.getCurrentInstance().addMessage(null, msg);
                }
                }
                else{
                FacesMessage msg = new FacesMessage("Uneli ste pogresnu lozinku");
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                FacesContext.getCurrentInstance().addMessage(null, msg);
                }
                }
                else{
                FacesMessage msg = new FacesMessage("Korisnik ne postoji u bazi");
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                FacesContext.getCurrentInstance().addMessage(null, msg);
                }
                
               
               
        return "index";
    }
    
}
