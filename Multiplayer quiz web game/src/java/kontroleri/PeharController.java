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
@ManagedBean(name = "peharController")
@ViewScoped
public class PeharController implements Serializable{
    private String[] pitanja;
    private String[] odgovori;
    private String[] odgovor1;//odgovor na prvo pitanje od 9 slova
    private String[] odgovor2;
    private String[] odgovor3;
    private String[] odgovor4;
    private String[] odgovor5;
    private String[] odgovor6;
    private String[] odgovor7;
    private String[] odgovor8;
    private String[] odgovor9;
    private String[] odgovor10;
    private String[] odgovor11;
    private String[] odgovor12;
    private String[] odgovor13;
    private boolean[] potvrdjeni_odgovori;
    private boolean[] preskoceni_odgovori;
    private boolean pocni_kliknuto;
    private int trenutni_ostvareni_poeni;
    private boolean onemoguci_odgovor1;
    private boolean onemoguci_odgovor2;
    private boolean onemoguci_odgovor3;
    private boolean onemoguci_odgovor4;
    private boolean onemoguci_odgovor5;
    private boolean onemoguci_odgovor6;
    private boolean onemoguci_odgovor7;
    private boolean onemoguci_odgovor8;
    private boolean onemoguci_odgovor9;
    private boolean onemoguci_odgovor10;
    private boolean onemoguci_odgovor11;
    private boolean onemoguci_odgovor12;
    private boolean onemoguci_odgovor13;
    private int preostalo_vreme;
    private int pitanje_koje_se_trenutno_odgovara;

    public void setPreostalo_vreme(int preostalo_vreme) {
        this.preostalo_vreme = preostalo_vreme;
    }

    public int getPreostalo_vreme() {
        return preostalo_vreme;
    }

    public void setOnemoguci_odgovor2(boolean onemoguci_odgovor2) {
        this.onemoguci_odgovor2 = onemoguci_odgovor2;
    }

    public void setOnemoguci_odgovor3(boolean onemoguci_odgovor3) {
        this.onemoguci_odgovor3 = onemoguci_odgovor3;
    }

    public void setOnemoguci_odgovor4(boolean onemoguci_odgovor4) {
        this.onemoguci_odgovor4 = onemoguci_odgovor4;
    }

    public void setOnemoguci_odgovor5(boolean onemoguci_odgovor5) {
        this.onemoguci_odgovor5 = onemoguci_odgovor5;
    }

    public void setOnemoguci_odgovor6(boolean onemoguci_odgovor6) {
        this.onemoguci_odgovor6 = onemoguci_odgovor6;
    }

    public void setOnemoguci_odgovor7(boolean onemoguci_odgovor7) {
        this.onemoguci_odgovor7 = onemoguci_odgovor7;
    }

    public void setOnemoguci_odgovor8(boolean onemoguci_odgovor8) {
        this.onemoguci_odgovor8 = onemoguci_odgovor8;
    }

    public void setOnemoguci_odgovor9(boolean onemoguci_odgovor9) {
        this.onemoguci_odgovor9 = onemoguci_odgovor9;
    }

    public void setOnemoguci_odgovor10(boolean onemoguci_odgovor10) {
        this.onemoguci_odgovor10 = onemoguci_odgovor10;
    }

    public void setOnemoguci_odgovor11(boolean onemoguci_odgovor11) {
        this.onemoguci_odgovor11 = onemoguci_odgovor11;
    }

    public void setOnemoguci_odgovor12(boolean onemoguci_odgovor12) {
        this.onemoguci_odgovor12 = onemoguci_odgovor12;
    }

    public void setOnemoguci_odgovor13(boolean onemoguci_odgovor13) {
        this.onemoguci_odgovor13 = onemoguci_odgovor13;
    }

    
    public boolean isOnemoguci_odgovor1() {
        return onemoguci_odgovor1;
    }

    public boolean isOnemoguci_odgovor2() {
        return onemoguci_odgovor2;
    }

    public boolean isOnemoguci_odgovor3() {
        return onemoguci_odgovor3;
    }

    public boolean isOnemoguci_odgovor4() {
        return onemoguci_odgovor4;
    }

    public boolean isOnemoguci_odgovor5() {
        return onemoguci_odgovor5;
    }

    public boolean isOnemoguci_odgovor6() {
        return onemoguci_odgovor6;
    }

    public boolean isOnemoguci_odgovor7() {
        return onemoguci_odgovor7;
    }

    public boolean isOnemoguci_odgovor8() {
        return onemoguci_odgovor8;
    }

    public boolean isOnemoguci_odgovor9() {
        return onemoguci_odgovor9;
    }

    public boolean isOnemoguci_odgovor10() {
        return onemoguci_odgovor10;
    }

    public boolean isOnemoguci_odgovor11() {
        return onemoguci_odgovor11;
    }

    public boolean isOnemoguci_odgovor12() {
        return onemoguci_odgovor12;
    }

    public boolean isOnemoguci_odgovor13() {
        return onemoguci_odgovor13;
    }
    
    
    public boolean getOnemoguci_odgovor1() {
        return onemoguci_odgovor1;
    }

    public void setOnemoguci_odgovor1(boolean onemoguci_odgovor1) {
        this.onemoguci_odgovor1 = onemoguci_odgovor1;
    }
    
    
    public void setOdgovor13(String[] odgovor13) {
        this.odgovor13 = odgovor13;
    }

    public String[] getOdgovor13() {
        return odgovor13;
    }
    

    public void setOdgovor1(String[] odgovor1) {
        this.odgovor1 = odgovor1;
    }

    public void setOdgovor2(String[] odgovor2) {
        this.odgovor2 = odgovor2;
    }

    public void setOdgovor3(String[] odgovor3) {
        this.odgovor3 = odgovor3;
    }

    public void setOdgovor4(String[] odgovor4) {
        this.odgovor4 = odgovor4;
    }

    public void setOdgovor5(String[] odgovor5) {
        this.odgovor5 = odgovor5;
    }

    public void setOdgovor6(String[] odgovor6) {
        this.odgovor6 = odgovor6;
    }

    public void setOdgovor7(String[] odgovor7) {
        this.odgovor7 = odgovor7;
    }

    public void setOdgovor8(String[] odgovor8) {
        this.odgovor8 = odgovor8;
    }

    public void setOdgovor9(String[] odgovor9) {
        this.odgovor9 = odgovor9;
    }

    public void setOdgovor10(String[] odgovor10) {
        this.odgovor10 = odgovor10;
    }

    public void setOdgovor11(String[] odgovor11) {
        this.odgovor11 = odgovor11;
    }

    public void setOdgovor12(String[] odgovor12) {
        this.odgovor12 = odgovor12;
    }

    public String[] getOdgovor1() {
        return odgovor1;
    }

    public String[] getOdgovor2() {
        return odgovor2;
    }

    public String[] getOdgovor3() {
        return odgovor3;
    }

    public String[] getOdgovor4() {
        return odgovor4;
    }

    public String[] getOdgovor5() {
        return odgovor5;
    }

    public String[] getOdgovor6() {
        return odgovor6;
    }

    public String[] getOdgovor7() {
        return odgovor7;
    }

    public String[] getOdgovor8() {
        return odgovor8;
    }

    public String[] getOdgovor9() {
        return odgovor9;
    }

    public String[] getOdgovor10() {
        return odgovor10;
    }

    public String[] getOdgovor11() {
        return odgovor11;
    }

    public String[] getOdgovor12() {
        return odgovor12;
    }
    

    public int getTrenutni_ostvareni_poeni() {
        return trenutni_ostvareni_poeni;
    }

    public void setTrenutni_ostvareni_poeni(int trenutni_ostvareni_poeni) {
        this.trenutni_ostvareni_poeni = trenutni_ostvareni_poeni;
    }

    
    
    
    public String[] getPitanja() {
        return pitanja;
    }

    public void setPitanja(String[] pitanja) {
        this.pitanja = pitanja;
    }
    
    @PostConstruct
        public void init(){
            pitanja=new String[14];
            pitanje_koje_se_trenutno_odgovara=0;
            onemoguci_odgovor1=false;
            onemoguci_odgovor2=false;
            onemoguci_odgovor3=false;
            onemoguci_odgovor4=false;
            onemoguci_odgovor5=false;
            onemoguci_odgovor6=false;
            onemoguci_odgovor7=false;
            onemoguci_odgovor8=false;
            onemoguci_odgovor9=false;
            onemoguci_odgovor10=false;
            onemoguci_odgovor11=false;
            onemoguci_odgovor12=false;
            onemoguci_odgovor13=false;
            odgovori=new String[14];
            preostalo_vreme=30;
            pitanja[0]="Neko pitanje";
            pocni_kliknuto=false;
            trenutni_ostvareni_poeni=LoginController.getPoeni();
            odgovor1=new String[9];
            odgovor2=new String[8];
            odgovor3=new String[7];
            odgovor4=new String[6];
            odgovor5=new String[5];
            odgovor6=new String[4];
            odgovor7=new String[3];
            odgovor8=new String[4];
            odgovor9=new String[5];
            odgovor10=new String[6];
            odgovor11=new String[7];
            odgovor12=new String[8];
            odgovor13=new String[9];
            preskoceni_odgovori=new boolean[14];
            potvrdjeni_odgovori=new boolean[14];
            for(int i=0;i<14;i++){
            potvrdjeni_odgovori[i]=false;
            preskoceni_odgovori[i]=false;
            }
        }
        
        public void pocni(){
         if(pocni_kliknuto==false){
             pocni_kliknuto=true;
             pitanje_koje_se_trenutno_odgovara=1;
             Session session=HibernateUtil.getSession();
             Transaction t=session.beginTransaction();
             Query q=session.createSQLQuery("SELECT * FROM `jsf_projekat`.`pehari`");
             List<Object[]> rows=q.list();
             int broj_pitanja=rows.size();
             int broj_grupa=broj_pitanja / 13;
             Random r=new Random();
             int odabrana_grupa=r.nextInt(broj_grupa)+1;
             System.out.println("Odabrana grupa je:");
             System.out.println(odabrana_grupa);
             Query q1=session.createSQLQuery("SELECT * FROM `jsf_projekat`.`pehari` where grupa=:grupa");
             q1.setParameter("grupa", odabrana_grupa);
             List<Object[]> rows1=q1.list();
             if(rows1.size()==0){
             System.out.println("Nista nije procitao");
             }
             int i=0;
             for(Object[] row1:rows1){
             pitanja[i+1]=row1[1].toString();
             odgovori[i+1]=row1[2].toString();
             i++;
             }
             System.out.println("Pitanja su: ");
             for(int j=1;j<14;j++){
             System.out.println(pitanja[j]);
             }
             t.commit();
             
         }
        }
        public void potvrdi(int odgovoreno_pitanje,int potvrdi_preskoci) throws IOException{//1 za potvrdi, 2 za preskoci
        if(potvrdjeni_odgovori[odgovoreno_pitanje]==false){
            System.out.println("Usao u funkciju potvrdi");
        potvrdjeni_odgovori[odgovoreno_pitanje]=true;
        if(potvrdi_preskoci==1){
            if(preostalo_vreme!=0){
            preostalo_vreme=30;
            pitanje_koje_se_trenutno_odgovara++;
            }
        String odgovor="";
        if(odgovoreno_pitanje==1){
            onemoguci_odgovor1=true;
        for(String str:odgovor1){
        odgovor+=str;
        }
        }
        if(odgovoreno_pitanje==2){
            onemoguci_odgovor2=true;
        for(String str:odgovor2){
        odgovor+=str;
        }
        }
        if(odgovoreno_pitanje==3){
            onemoguci_odgovor3=true;
        for(String str:odgovor3){
        odgovor+=str;
        }
        }
        if(odgovoreno_pitanje==4){
            onemoguci_odgovor4=true;
        for(String str:odgovor4){
        odgovor+=str;
        }
        }
        if(odgovoreno_pitanje==5){
            onemoguci_odgovor5=true;
        for(String str:odgovor5){
        odgovor+=str;
        }
        }
        if(odgovoreno_pitanje==6){
            onemoguci_odgovor6=true;
        for(String str:odgovor6){
        odgovor+=str;
        }
        }
        if(odgovoreno_pitanje==7){
            onemoguci_odgovor7=true;
        for(String str:odgovor7){
        odgovor+=str;
        }
        }
        if(odgovoreno_pitanje==8){
            onemoguci_odgovor8=true;
        for(String str:odgovor8){
        odgovor+=str;
        }
        }
        if(odgovoreno_pitanje==9){
            onemoguci_odgovor9=true;
        for(String str:odgovor9){
        odgovor+=str;
        }
        }
        if(odgovoreno_pitanje==10){
            onemoguci_odgovor10=true;
        for(String str:odgovor10){
        odgovor+=str;
        }
        }
        if(odgovoreno_pitanje==11){
            onemoguci_odgovor11=true;
        for(String str:odgovor11){
        odgovor+=str;
        }
        }
        if(odgovoreno_pitanje==12){
            onemoguci_odgovor12=true;
        for(String str:odgovor1){
        odgovor+=str;
        }
        }
        if(odgovoreno_pitanje==13){
            onemoguci_odgovor13=true;
        for(String str:odgovor13){
        odgovor+=str;
        }
        }
        if(odgovor.equals(odgovori[odgovoreno_pitanje])){
            LoginController.dodajPoene(2);
            trenutni_ostvareni_poeni+=2;
        }
        
        }
        else{
        if(odgovoreno_pitanje==1){
            onemoguci_odgovor1=true;
        }
        if(odgovoreno_pitanje==2){
            onemoguci_odgovor2=true;
        }
        if(odgovoreno_pitanje==3){
            onemoguci_odgovor3=true;
        }
        if(odgovoreno_pitanje==4){
            onemoguci_odgovor4=true;
        }
        if(odgovoreno_pitanje==5){
            onemoguci_odgovor5=true;
        }
        if(odgovoreno_pitanje==6){
            onemoguci_odgovor6=true;
        }
        if(odgovoreno_pitanje==7){
            onemoguci_odgovor7=true;
        }
        if(odgovoreno_pitanje==8){
            onemoguci_odgovor8=true;
        }
        if(odgovoreno_pitanje==9){
            onemoguci_odgovor9=true;
        }
        if(odgovoreno_pitanje==10){
            onemoguci_odgovor10=true;
        }
        if(odgovoreno_pitanje==11){
            onemoguci_odgovor11=true;
        }
        if(odgovoreno_pitanje==12){
            onemoguci_odgovor12=true;
        }
        if(odgovoreno_pitanje==13){
            onemoguci_odgovor13=true;
        }
        
        }
        
        }
        if(odgovoreno_pitanje==13){
       SessionFactory sf=HibernateUtil.getSessionFactory();
              Session session=sf.openSession();
              Transaction t=session.beginTransaction();
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
              
            Query q=session.createSQLQuery("INSERT INTO `jsf_projekat`.`partije`\n" +
"(`idPartije`,\n" +
"`poeni`,\n" +
"`korime`,\n" +
"`datum`)\n" +
"VALUES (:idPartije , :poeni , :korime , :datum )");
            q.setParameter("idPartije", max_idPartije);
            q.setParameter("poeni", LoginController.getPoeni());
            q.setParameter("korime", LoginController.getKorisnickoIme());
            java.util.Date datum=new java.util.Date();
            java.sql.Date sql_datum=DatesConversion.convertUtilToSql(datum);
            q.setParameter("datum", sql_datum);
            q.executeUpdate();
            t.commit();
            
            if(session!=null && session.isOpen()){
            session.close();
            }
            FacesContext.getCurrentInstance().getExternalContext().redirect("faces/pregled_rezultata.xhtml");

        }
        }
       
        public void preskoci(int preskoceno_pitanje){
        
        }
        
        public void decrement() throws IOException{
            if(pocni_kliknuto==true){
              preostalo_vreme--;
        if(preostalo_vreme==0){
            potvrdi(pitanje_koje_se_trenutno_odgovara, 1);
            preostalo_vreme=30;
            pitanje_koje_se_trenutno_odgovara++;
        }
            
            }
      
        }
   
}
