/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri;

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

@ManagedBean(name = "petPutaPetController")
@ViewScoped
public class PetPutaPetController implements Serializable{
    
    private String[][] matrica=new String[5][5];
    private String[][] matrica_koja_se_prikazuje=new String[5][5];
    private int preostalo_pokusaja=10;
    private String uneto_slovo="";
    private int counter=20;
    private int ostvareni_poeni=LoginController.getPoeni();
    private List<String> pokusaji=new ArrayList<String>();

    public void setPokusaji(List<String> pokusaji) {
        this.pokusaji = pokusaji;
    }

    public List<String> getPokusaji() {
        return pokusaji;
    }
    
    
    public int getOstvareni_poeni() {
        return ostvareni_poeni;
    }

    public void setOstvareni_poeni(int ostvareni_poeni) {
        this.ostvareni_poeni = ostvareni_poeni;
    }
    
    
    public void setPreostalo_pokusaja(int preostalo_pokusaja) {
        this.preostalo_pokusaja = preostalo_pokusaja;
    }

    public void setUneto_slovo(String uneto_slovo) {
        this.uneto_slovo = uneto_slovo;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public int getPreostalo_pokusaja() {
        return preostalo_pokusaja;
    }

    public String getUneto_slovo() {
        return uneto_slovo;
    }

    public int getCounter() {
        return counter;
    }
    
    
    public String[][] getMatrica_koja_se_prikazuje() {
        return matrica_koja_se_prikazuje;
    }

    public void setMatrica_koja_se_prikazuje(String[][] matrica_koja_se_prikazuje) {
        this.matrica_koja_se_prikazuje = matrica_koja_se_prikazuje;
    }
    
    @PostConstruct
        public void init(){
           for(int i=0;i<5;i++){
           for(int j=0;j<5;j++){
           
           matrica_koja_se_prikazuje[i][j]=" ";
           }
           }
           
           for(int k=0;k<5;k++){
           for(int m=0;m<5;m++){
           
           Random r = new Random();
           char c = (char)(r.nextInt(26) + 'a');
           int temp;
            temp = (int) c;
            temp = temp - 32;
            c = (char) temp;
            String s=String.valueOf(c);
            matrica[k][m]=s;
            System.out .print(" ");
            System.out.print(s);
           }
           }
           
        }
    
        public void decrement() throws IOException{
        counter--;
        if(counter==0){
        potvrdi();
        }
        }
        
    public void ne_radi_nista(){
    
    }
    
    public void potvrdi() throws IOException{
        boolean isto_slovo=false;
    
    counter=20;
    if(preostalo_pokusaja==0){
    System.out.println("GOTOVO");
                 FacesContext.getCurrentInstance().getExternalContext().redirect("faces/zanimljiva_geografija.xhtml");

    }
    char slovo=uneto_slovo.charAt(0);
    if(Character.isLowerCase(slovo)){
    uneto_slovo=uneto_slovo.toUpperCase();
    }
    //if(pokusaji sadrzi uneto slovo ispisi poruku o gresci)
    for(String p:pokusaji){
    if(p.equals(uneto_slovo)){
                     FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Ne mozete uneti isto slovo"));

         isto_slovo=true;
    }
    }
    if(isto_slovo==false){
        preostalo_pokusaja--;
     pokusaji.add(uneto_slovo);
    for(int i=0;i<5;i++){
           for(int j=0;j<5;j++){
           
          if(uneto_slovo.equals(matrica[i][j])){
           matrica_koja_se_prikazuje[i][j]=matrica[i][j];
           LoginController.dodajPoene(1);
           ostvareni_poeni++;
          }
           }
           }
    
    for(int k=0;k<5;k++){
         
    }
    }
   
    }
}
