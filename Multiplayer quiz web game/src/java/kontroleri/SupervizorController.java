/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri;

import beans.ZanGeoSupervizor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
@ManagedBean(name="Supervizor")
@ViewScoped
public class SupervizorController implements Serializable{
    private String naziv_anagrama;
    private String resenje_anagrama;
    private String pojam;
    private String kategorija;
    String[] pitanja=new String[13];
    String[] odgovori=new String[13];
    private int max_grupa=0;
    private List<ZanGeoSupervizor> geografija=new ArrayList<ZanGeoSupervizor>();

    public void setGeografija(List<ZanGeoSupervizor> geografija) {
        this.geografija = geografija;
    }

    public List<ZanGeoSupervizor> getGeografija() {
        return geografija;
    }
    
    
    public void setPitanja(String[] pitanja) {
        this.pitanja = pitanja;
    }

    public void setOdgovori(String[] odgovori) {
        this.odgovori = odgovori;
    }

    public String[] getPitanja() {
        return pitanja;
    }

    public String[] getOdgovori() {
        return odgovori;
    }
    
    
    public String getKategorija() {
        return kategorija;
    }

    public void setKategorija(String kategorija) {
        this.kategorija = kategorija;
    }
    
    
    public String getPojam() {
        return pojam;
    }

    public void setPojam(String pojam) {
        this.pojam = pojam;
    }
    
    public void setNaziv_anagrama(String naziv_anagrama) {
        this.naziv_anagrama = naziv_anagrama;
    }

    public void setResenje_anagrama(String resenje_anagrama) {
        this.resenje_anagrama = resenje_anagrama;
    }

    public String getNaziv_anagrama() {
        return naziv_anagrama;
    }

    public String getResenje_anagrama() {
        return resenje_anagrama;
    }
    
    @PostConstruct
        public void init(){
            for(int i=0;i<13;i++){
            pitanja[i]="";
            odgovori[i]="";
            }
            
            // Session session=HibernateUtil.getSession();
    //Transaction t=session.beginTransaction();
    SessionFactory sf=HibernateUtil.getSessionFactory();
              Session session=sf.openSession();
              Transaction t=session.beginTransaction();
    Query q1=session.createSQLQuery("SELECT * FROM jsf_projekat.zangeosuperv");
     List<Object[]> rows=q1.list();
     for(Object[]row:rows){
     int idP=new Integer(row[1].toString()).intValue();
     String kat=row[2].toString();
     String odg=row[3].toString();
     ZanGeoSupervizor z=new ZanGeoSupervizor(idP, kat, odg);
     geografija.add(z);
//     t.commit();
     if(session!=null && session.isOpen()){
            session.close();
            }
     }
            
            
        }
    public void unesi_anagram(){
    Session session=HibernateUtil.getSession();
    Transaction t=session.beginTransaction();
    Query q=session.createSQLQuery("INSERT INTO `jsf_projekat`.`anagrami1`\n" +
"(`naziv`,\n" +
"`resenje`)\n" +
"VALUES (:naziv , :resenje )");
    q.setParameter("naziv", naziv_anagrama);
    q.setParameter("resenje", resenje_anagrama);
    q.executeUpdate();
    t.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Uspesno ste uneli anagram"));

    }
    
    public void unesi_pehar(){
    /*
        for(int i=0;i<6;i++){
    char[] pitanje1=pitanja[i].toCharArray();
    char[] pitanje2=pitanja[i+1].toCharArray();
    
     boolean dobro_pitanje =  upoedi_stringove(pitanje1,pitanje2, pitanje1.length,  pitanje2.length);
    if(dobro_pitanje==false){
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Pitanje "+i+" i naredno pitanje se ne razlikuju za 1"));
        break;
    }
    }
    for(int j=6;j<12;j++){
    char[] pitanje1=pitanja[j].toCharArray();
    char[] pitanje2=pitanja[j+1].toCharArray();
    boolean dobro_pitanje =  upoedi_stringove(pitanje2,pitanje1, pitanje2.length,  pitanje1.length);
    if(dobro_pitanje==false){
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Pitanje "+j+" i naredno pitanje se ne razlikuju za 1"));
        break;
    }
    }
    */
    for(int k=0;k<6;k++){
    char[] odgovor1=odgovori[k].toCharArray();
    char[] odgovor2=odgovori[k+1].toCharArray();
    boolean dobro_pitanje =  upoedi_stringove(odgovor1,odgovor2, odgovor1.length,  odgovor2.length);
    if(dobro_pitanje==false){
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Pitanje "+k+" i naredno pitanje se ne razlikuju za 1"));
        break;
    }
    }
    
    for(int m=6;m<12;m++){
    char[] odgovor1=odgovori[m].toCharArray();
    char[] odgovor2=odgovori[m+1].toCharArray();
    boolean dobro_pitanje =  upoedi_stringove(odgovor2,odgovor1, odgovor2.length,  odgovor1.length);
    if(dobro_pitanje==false){
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Pitanje "+m+" i naredno pitanje se ne razlikuju za 1"));
        break;
    }
    }
    
    for(int p=0;p<13;p++){
        
    Session session=HibernateUtil.getSession();
    Transaction t=session.beginTransaction();
    Query q1=session.createSQLQuery("SELECT * FROM  `jsf_projekat`.`pehari1`");
    List<Object[]> rows=q1.list();
     int max_id=0;
     int max_grupa=0;
     for(Object[] row:rows){
     if(new Integer(row[0].toString()).intValue()>max_id){
     max_id=new Integer(row[0].toString()).intValue();
     
     }
     if(new Integer(row[3].toString()).intValue()>max_grupa){
     max_grupa=new Integer(row[3].toString()).intValue();
     }
     }
     max_id++;
     if(this.max_grupa==0){
     max_grupa++;
     this.max_grupa=max_grupa;
     }
     
     
     Query q2=session.createSQLQuery("INSERT INTO `jsf_projekat`.`pehari1`\n" +
"(`idP`,\n" +
"`pitanje`,\n" +
"`odgovor`,\n" +
"`grupa`)\n" +
"VALUES (:idP , :pitanje , :odgovor , :grupa )");
     q2.setParameter("idP", max_id);
     q2.setParameter("pitanje", this.pitanja[p]);
     q2.setParameter("odgovor", this.odgovori[p]);
     q2.setParameter("grupa",max_grupa );
     q2.executeUpdate();
     t.commit();
    
    System.out.println("Usao u for");
    System.out.println(max_id);
    System.out.println(this.pitanja[p]);
    System.out.println(this.odgovori[p]);
    System.out.println(max_grupa);
    }
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Uspesno ste uneli novi pehar"));

    
    }
    
    public void unesi_pojam(){ // za zanimljivu geografiju
    String tabela="";
    String id="";
    if(kategorija.equals("Drzava")){
    tabela="drzave";
    id="idDrzave";
    }
    if(kategorija.equals("Grad")){
    tabela="gradovi";
    id="idGrada";
    }
    if(kategorija.equals("Reka")){
    tabela="reke";
    id="idReke";
    }
    if(kategorija.equals("Jezero")){
    tabela="jezera";
    id="idJezera";
    }
    if(kategorija.equals("Planina")){
    tabela="planine";
    id="idPlanine";
    }
    if(kategorija.equals("Grupa")){
    tabela="grupe";
    id="idgrupe";
    }
    if(kategorija.equals("Biljka")){
    tabela="biljke";
    id="idBiljke";
    }
    if(kategorija.equals("Zivotinja")){
    tabela="zivotinje";
    id="idZivotinje";
    }
    
    Session session=HibernateUtil.getSession();
    Transaction t=session.beginTransaction();
    Query q1=session.createSQLQuery("SELECT * FROM jsf_projekat."+tabela);
     List<Object[]> rows=q1.list();
     int max_id=0;
     for(Object[] row:rows){
     if(new Integer(row[0].toString()).intValue()>max_id){
     max_id=new Integer(row[0].toString()).intValue();
     }
     }
     max_id++;
     
    Query q=session.createSQLQuery("INSERT INTO jsf_projekat."+tabela+"\n" +
"(`"+id+"`,\n" +
"`naziv`)\n" +
"VALUES (:id , :pojam)");
    q.setParameter("id", max_id);
    q.setParameter("pojam", pojam);
    q.executeUpdate();
    t.commit();
    System.out.println("Uneti pojam je");
    System.out.println(pojam);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Uspesno ste uneli pojam"));

    }
    
    public boolean upoedi_stringove(char[] niz1,char[] niz2,int n1,int n2){
        int broj=0;
        int brojac=0;
        for(int i=0;i<n1;i++){
        for(int j=0;j<n2;j++){
        if(broj!=0 && niz1[i]!=niz2[j]){
        broj=0;
        }
        else{
        broj=1;
        }
        }
        if(broj==1){
        brojac++;
        }
        broj=0;
        }
        if(brojac==n2){
        return true;
        }
    return false;
    }
    
    public  void potvrdi_geografija(int idPartije, String kategorija, String odgovor){
    Session session=HibernateUtil.getSession();
    Transaction t=session.beginTransaction();
    Query q1=session.createSQLQuery("SELECT * FROM `jsf_projekat`.`partije` WHERE `idPartije` = :idP ");
    q1.setParameter("idP", idPartije);
    List<Object[]> rows=q1.list();
    for(Object[]row:rows){
    int poeni=new Integer(row[1].toString()).intValue();
    poeni+=2;
    Query q2=session.createSQLQuery("UPDATE `jsf_projekat`.`partije`\n" +
"SET `poeni` =:poeni WHERE `idPartije` =idP");
    q2.setParameter("poeni", poeni);
    q2.setParameter("idP", idPartije);
    q2.executeUpdate();
    
     Query q3=session.createSQLQuery("DELETE FROM `jsf_projekat`.`zangeosuperv` WHERE idPartije=:idP AND kategorija=:kategorija");
    q3.setParameter("idP", idPartije);
    q3.setParameter("kategorija", kategorija);
    t.commit();
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Uspesno ste dodali poene"));

    }
    
    }
    public void odbij_geografija(int idPartije, String kategorija){
    
    }
}
