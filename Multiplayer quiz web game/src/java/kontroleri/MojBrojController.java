/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri;

import com.fathzer.soft.javaluator.DoubleEvaluator;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author HP
 */
@ManagedBean(name = "moj_brojController")
@ViewScoped
public class MojBrojController implements Serializable{
    
    private int trenutni_ostvareni_poeni=LoginController.getPoeni();
    private int broj_kliktanja_stop; // broj koji nam govori koliko puta je potrebno kliknuti dugme stop
    private int broj1=0;
    private int broj2=0;
    private int broj3=0;
    private int broj4=0;
    private int broj5=0;
    private int broj6=0;
    private int broj7=0;
    private List<String> izraz;
    private String string_izraz;        
    private String plus;
    private String puta;
    private String podeljeno;
    private String minus;
    private String otvorena_zagrada;
    private String zatvorena_zagrada;
    private int selektovan_broj1; //vrednosti koje se prosledjuju funkciji selektovan_broj da bi se 
    private int selektovan_broj2; //znalo koje je dugme kliknuto jer se svako dugme sa zadatim brojm
    private int selektovan_broj3;//sme kliknuti samo jednom
    private int selektovan_broj4;
    private int selektovan_broj6;
    private int selektovan_broj7;
    private boolean[] pritisnuto_dugme ;
    private int counter;
    private int stop_counter; //broji koliko puta je pritisnut stop da bi se startovao tajmer
    private int isteklo_vreme; //prosledjuje se funkciji potvrdi za slucaj kada je isteklo vreme

    public int getTrenutni_ostvareni_poeni() {
        return trenutni_ostvareni_poeni;
    }

    public void setTrenutni_ostvareni_poeni(int trenutni_ostvareni_poeni) {
        this.trenutni_ostvareni_poeni = trenutni_ostvareni_poeni;
    }

    
    
    
    public int getIsteklo_vreme() {
        return isteklo_vreme;
    }

    public void setIsteklo_vreme(int isteklo_vreme) {
        this.isteklo_vreme = isteklo_vreme;
    }
    
    public void setCounter(int counter) {
        this.counter = counter;
    }

    public int getCounter() {
        return counter;
    }
    
    public void setSelektovan_broj1(int selektovan_broj1) {
        this.selektovan_broj1 = selektovan_broj1;
    }

    public void setSelektovan_broj2(int selektovan_broj2) {
        this.selektovan_broj2 = selektovan_broj2;
    }

    public void setSelektovan_broj3(int selektovan_broj3) {
        this.selektovan_broj3 = selektovan_broj3;
    }

    public void setSelektovan_broj4(int selektovan_broj4) {
        this.selektovan_broj4 = selektovan_broj4;
    }

    public void setSelektovan_broj6(int selektovan_broj6) {
        this.selektovan_broj6 = selektovan_broj6;
    }

    public void setSelektovan_broj7(int selektovan_broj7) {
        this.selektovan_broj7 = selektovan_broj7;
    }

    public int getSelektovan_broj1() {
        return selektovan_broj1;
    }

    public int getSelektovan_broj2() {
        return selektovan_broj2;
    }

    public int getSelektovan_broj3() {
        return selektovan_broj3;
    }

    public int getSelektovan_broj4() {
        return selektovan_broj4;
    }

    public int getSelektovan_broj6() {
        return selektovan_broj6;
    }

    public int getSelektovan_broj7() {
        return selektovan_broj7;
    }
    

    public void setPlus(String plus) {
        this.plus = plus;
    }

    public void setPuta(String puta) {
        this.puta = puta;
    }

    public void setPodeljeno(String podeljeno) {
        this.podeljeno = podeljeno;
    }

    public void setMinus(String minus) {
        this.minus = minus;
    }

    public void setOtvorena_zagrada(String otvorena_zagrada) {
        this.otvorena_zagrada = otvorena_zagrada;
    }

    public void setZatvorena_zagrada(String zatvorena_zagrada) {
        this.zatvorena_zagrada = zatvorena_zagrada;
    }

    public String getPlus() {
        return plus;
    }

    public String getPuta() {
        return puta;
    }

    public String getPodeljeno() {
        return podeljeno;
    }

    public String getMinus() {
        return minus;
    }

    public String getOtvorena_zagrada() {
        return otvorena_zagrada;
    }

    public String getZatvorena_zagrada() {
        return zatvorena_zagrada;
    }

    public List<String> getIzraz() {
        return izraz;
    }

    public String getString_izraz() {
        return string_izraz;
    }

    public void setIzraz(List<String> izraz) {
        this.izraz = izraz;
    }

    public void setString_izraz(String string_izraz) {
        this.string_izraz = string_izraz;
    }
            
    public int getBroj1() {
        return broj1;
    }

    public void setBroj1(int broj1) {
        this.broj1 = broj1;
    }

    public void setBroj2(int broj2) {
        this.broj2 = broj2;
    }

    public void setBroj3(int broj3) {
        this.broj3 = broj3;
    }

    public void setBroj4(int broj4) {
        this.broj4 = broj4;
    }

    public void setBroj5(int broj5) {
        this.broj5 = broj5;
    }

    public void setBroj6(int broj6) {
        this.broj6 = broj6;
    }

    public void setBroj7(int broj7) {
        this.broj7 = broj7;
    }

    public int getBroj2() {
        return broj2;
    }

    public int getBroj3() {
        return broj3;
    }

    public int getBroj4() {
        return broj4;
    }

    public int getBroj5() {
        return broj5;
    }

    public int getBroj6() {
        return broj6;
    }

    public int getBroj7() {
        return broj7;
    }

    public void setBroj_kliktanja_stop(int broj_kliktanja_stop) {
        this.broj_kliktanja_stop = broj_kliktanja_stop;
    }

    public int getBroj_kliktanja_stop() {
        return broj_kliktanja_stop;
    }

   
    
       @PostConstruct
        public void init(){
            System.out.println("Usao u init funkciju klase MojBrojController");
            System.out.println(new Integer(5).toString());
            broj_kliktanja_stop=7;
            izraz=new ArrayList<String>();
            string_izraz="";
            selektovan_broj1=1;
            selektovan_broj2=2;
            selektovan_broj3=3;
            selektovan_broj4=4;
            selektovan_broj6=6;
            selektovan_broj7=7;
            plus="+";
            minus="-";
            podeljeno="/";
            puta="*";
            pritisnuto_dugme=new boolean[8];
            counter=120;
            stop_counter=0;
            isteklo_vreme=0;
            for(int i=0;i<8;i++){
            pritisnuto_dugme[i]=false;
            }
        }
        
        public void stop(){//funkcija koja generise nove brojeve
        System.out.println("Usao u funkciju stop() klase MojBrojController");
        stop_counter++;
        if(stop_counter==7){
        counter=60;
        }
        if(broj_kliktanja_stop>0){
           Random r = new Random();
            int low = 1;
            int high = 10;
            
            switch(broj_kliktanja_stop){
            case 7:
                broj_kliktanja_stop--;
                 broj1 = r.nextInt(high-low) + low;
                break;
                case 6:
                      broj_kliktanja_stop--;
                 broj2 = r.nextInt(high-low) + low;
                break;
                case 5:
                      broj_kliktanja_stop--;
                    broj3 = r.nextInt(high-low) + low;
                break;
                case 4:
                      broj_kliktanja_stop--;
                    broj4 = r.nextInt(high-low) + low;
                break;
                case 3:
                      broj_kliktanja_stop--;
                      Random r3 = new Random();
            int low3 = 1;
            int high3 = 1000;
            int result3=r.nextInt(high3-low3) +low3;
                    broj5=result3;
                break;
                case 2:
                      broj_kliktanja_stop--;
                      Random r2 = new Random();
            int low2 = 1;
            int high2 = 4;
            int result2=r.nextInt(high2-low2) +low2;
                   if(result2==1){
                   broj6=10;
                   }
                   if(result2==2){
                   broj6=15;
                   }
                   if(result2==3){
                   broj6=20;
                   }
                break;
                case 1:
                      broj_kliktanja_stop--;
                    Random r1 = new Random();
            int low1 = 1;
            int high1 = 5;
            int result1=r.nextInt(high1-low1) +low1;
            if(result1==1){
            broj7=25;
            }
            if(result1==2){
            broj7=75;
            }
            if(result1==3){
            broj7=50;
            }
            if(result1==4){
            broj7=100;
            }
                break;
        }
           }
            
        }
        
        public void selektovan_broj(int broj,int selektovan_broj){//funkcija koja se poziva kada se selektuje neki broj
          if(pritisnuto_dugme[selektovan_broj]==false){
          pritisnuto_dugme[selektovan_broj]=true;
          izraz.add(new Integer(broj).toString());
          string_izraz="";
          for(String str:izraz){
          string_izraz+=str;
          }
          }
          System.out.println("Trenutni izraz:");
          System.out.println(string_izraz);
        }
        public void selektovana_operacija(String operacija){// funkcija koja se poziva kada se selektuje neka operacija
         string_izraz="";
         izraz.add(operacija);
         for(String str:izraz){
         string_izraz+=str;
         }
         System.out.println("Trenutni izraz:");
         System.out.println(string_izraz);
        }
        
        public String potvrdi(int isteklo_vreme) throws IOException{
            try{
             double result= new DoubleEvaluator().evaluate(string_izraz);
        System.out.println("Izracunat izraz:");
        System.out.println(result);
        if(result==new Double(broj5) || isteklo_vreme==1){
       if(result==new Double(broj5)){
       LoginController.dodajPoene(10);
        System.out.println("Tacan broj");
       }
        if(result==new Double(broj5).doubleValue()|| isteklo_vreme==1){
                FacesContext.getCurrentInstance().getExternalContext().redirect("faces/zanimljiva_geografija.xhtml");

        }

        }
        else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Nije tacno resenje"));

        System.out.println("Nije dobro resenje");
        }
        
            }
            catch(java.lang.IllegalArgumentException e){
             System.out.println("Izraz nije korektan");
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Nije korektan izraz"));
        
             if(isteklo_vreme==1){
               FacesContext.getCurrentInstance().getExternalContext().redirect("faces/pet_puta_pet.xhtml");             
             }
             

            }
       
        return "";
        }
        
        public void obrisi(){// funkcija koja brise poslednji uneti karakter
        System.out.println("Usao u funkciju obrisi");
        int size=izraz.size();
        if(size>0){
             String a=izraz.get(size-1);
          izraz.remove(size-1);
       Integer b=new Integer(a);
       if(b==broj1) {
       pritisnuto_dugme[1]=false;
       }
       if(b==broj2) {
       pritisnuto_dugme[2]=false;
       }
       if(b==broj3) {
       pritisnuto_dugme[3]=false;
       }
       if(b==broj4) {
       pritisnuto_dugme[4]=false;
       }
       if(b==broj6) {
       pritisnuto_dugme[6]=false;
       }
       if(b==broj7) {
       pritisnuto_dugme[7]=false;
       }
        string_izraz="";
        for(String str:izraz){
        string_izraz+=str;
        }
        }
        
        }
        
        public void obrisi_sve(){
        System.out.println("Usao u funkciju obrisi_sve ");
         int size=izraz.size();
         for(int j=0;j<8;j++){
         pritisnuto_dugme[j]=false;
         }
        if(size>0){
            for(int i=0;i<size;i++){
            izraz.remove(0);
            }
            string_izraz="";
            for(String str:izraz){
            string_izraz+=str;
            }
        }
        }
        
        public void decrement() throws IOException{
        counter--;
        if(counter==0){
        potvrdi(1);
        }
        }
}
