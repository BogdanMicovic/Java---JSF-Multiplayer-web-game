/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri;

import date.DatesConversion;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
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

@ManagedBean(name = "zanimljivaGeografijaController")
@ViewScoped
public class ZanimljivaGeografijaController implements Serializable{
    private int trenutni_ostvareni_poeni=LoginController.getPoeni();
    private String zadato_slovo;
    private String drzava;
    private String grad;
    private String reka;
    private String jezero;
    private String planina;
    private String zivotinja;
    private String biljka;
    private String grupa;
    private int counter;
    private String preostalo_vreme; //counter.toString()
    private int poziv_iz_decrementa=0;
    private boolean pocni;//onemogucava da se dugme za generisanje broja klikne vise puta
    private String[] nazivi;

    public int getTrenutni_ostvareni_poeni() {
        return trenutni_ostvareni_poeni;
    }

    public void setTrenutni_ostvareni_poeni(int trenutni_ostvareni_poeni) {
        this.trenutni_ostvareni_poeni = trenutni_ostvareni_poeni;
    }
    
    
    public int getPoziv_iz_decrementa() {
        return poziv_iz_decrementa;
    }

    public void setPoziv_iz_decrementa(int poziv_iz_decrementa) {
        this.poziv_iz_decrementa = poziv_iz_decrementa;
    }
    
    
    public String getPreostalo_vreme() {
        return preostalo_vreme;
    }

    public void setPreostalo_vreme(String preostalo_vreme) {
        this.preostalo_vreme = preostalo_vreme;
    }
    
    
    @PostConstruct
        public void init(){
            zadato_slovo="";
            drzava="";
            grad="";
            reka="";
            jezero="";
            planina="";
            zivotinja="";
            biljka="";
            grupa="";
            counter=120;
            preostalo_vreme="";
            pocni=false;
            nazivi=new String[8];
            nazivi[0]="drzave";
            nazivi[1]="gradovi";
            nazivi[2]="reke";
            nazivi[3]="jezera";
            nazivi[4]="planine";
            nazivi[5]="zivotinje";
            nazivi[6]="biljke";
            nazivi[7]="grupe";
        }

    public void setZadato_slovo(String zadato_slovo) {
        this.zadato_slovo = zadato_slovo;
    }

    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public void setReka(String reka) {
        this.reka = reka;
    }

    public void setJezero(String jezero) {
        this.jezero = jezero;
    }

    public void setPlanina(String planina) {
        this.planina = planina;
    }

    public void setZivotinja(String zivotinja) {
        this.zivotinja = zivotinja;
    }

    public void setBiljka(String biljka) {
        this.biljka = biljka;
    }

    public void setGrupa(String grupa) {
        this.grupa = grupa;
    }

    public String getZadato_slovo() {
        return zadato_slovo;
    }

    public String getDrzava() {
        return drzava;
    }

    public String getGrad() {
        return grad;
    }

    public String getReka() {
        return reka;
    }

    public String getJezero() {
        return jezero;
    }

    public String getPlanina() {
        return planina;
    }

    public String getZivotinja() {
        return zivotinja;
    }

    public String getBiljka() {
        return biljka;
    }

    public String getGrupa() {
        return grupa;
    }
        
        public void pocni(){
            if(pocni==false){
                pocni=true;
         Random r = new Random();
        char c = (char)(r.nextInt(26) + 'a');
        char c1=(char) (c-32);
        zadato_slovo+=c1;
        preostalo_vreme=new Integer(counter).toString();
            }
        
        }
        
        public void decrement() throws IOException{
            if(pocni==true){
            counter--;
        preostalo_vreme=new Integer(counter).toString();
            }
        if(counter==0){
        potvrdi(1);
        }
        }
        
        public String potvrdi(int poziv_iz_decrementa) throws IOException{
          //Session session=HibernateUtil.getSession();
          //Transaction t=session.beginTransaction();
          //session.close();
          
       //   Session s=HibernateUtil.getSession();
        // if(s!=null && s.isOpen()){
        // s.close();
         //}
          for(int i=0;i<8;i++){
              SessionFactory sf=HibernateUtil.getSessionFactory();
              Session session=sf.openSession();
              Transaction t=session.beginTransaction();
              System.out.println("Usao u for petlju");
          Query q=session.createSQLQuery("SELECT * FROM jsf_projekat."+nazivi[i]+" where naziv=:naziv");
          if(i==0){
          q.setParameter("naziv",drzava);
          }
           if(i==1){
          q.setParameter("naziv",grad);
          }
           if(i==2){
          q.setParameter("naziv",reka);
          }
            if(i==3){
          q.setParameter("naziv",jezero);
          }
             if(i==4){
          q.setParameter("naziv",planina);
          }
              if(i==5){
          q.setParameter("naziv",zivotinja);
          }
               if(i==6){
          q.setParameter("naziv",biljka);
          }
                if(i==7){
          q.setParameter("naziv",grupa);
          }
           List<Object[]> rows=q.list();
          
          if(rows.size()>0){
          LoginController.dodajPoene(2);
          }
          else{
           System.out.println("Uneti odgovor ne postoji");
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Jedan ili vise unetih odgovora ne postoje u bazi"));

           Query q1=session.createSQLQuery("SELECT * from jsf_projekat.partije");
           List<Object[]> rows1=q1.list();
           int max_idPartije=0;
           
           for(Object[] row1:rows1){
               Integer i1= new Integer(row1[0].toString());
           if(max_idPartije<i1.intValue()){
             max_idPartije=i1.intValue();
           }
           }
           max_idPartije++;//id ove partije-za 1 vec od max id-ja
           Query q2=session.createSQLQuery("INSERT INTO `jsf_projekat`.`zangeosuperv`\n" +
"(`idPartije`,\n" +
"`kategorija`,\n" +
"`odgovor`)\n" +
"VALUES (:idPartije, :kategorija, :odgovor)");
           
           q2.setParameter("idPartije", max_idPartije);
           String kategorija=nazivi[i];
           q2.setParameter("kategorija", kategorija);
           String odgovor="";
           if(i==0){
           odgovor=drzava;
           }
            if(i==1){
           odgovor=grad;
           }
             if(i==2){
           odgovor=reka;
           }
              if(i==3){
           odgovor=jezero;
           }
               if(i==4){
           odgovor=planina;
           }
                if(i==5){
           odgovor=zivotinja;
           }
                 if(i==6){
           odgovor=biljka;
           }
                  if(i==7){
           odgovor=grupa;
           }
           q2.setParameter("odgovor", odgovor);
           q2.executeUpdate();
           System.out.println("MAX_idPartije je:");
           System.out.println(max_idPartije);
         
          }
        
         
            t.commit();
            if(session!=null && session.isOpen()){
            session.close();
            }
                
          }
                       FacesContext.getCurrentInstance().getExternalContext().redirect("faces/pehar.xhtml");
                       return "";
        }
}
