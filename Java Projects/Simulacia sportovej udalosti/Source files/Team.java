package Semestralka;
import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;


/**
 * Trieda nam vytvori team kde bude pole hracov a budeme ich filtrovať, ukladať a upravovať atď.
 */
public class Team implements Serializable
{  
    private String nazov;
    Hrac[] hraci;
    private int skore;
    
    /** koštruktor */
    public Team(String nazov, int pocetHracov){
        this.nazov = nazov;
        hraci = new Hrac[pocetHracov];
        this.skore = 0;
    }
    
    /** spocita utok všetkych hracov dokopy **/
    public double getTeamObrana(){
        double vysHodnotaObrana = 0;
        for(Hrac i : hraci){
            if(i!=null){
                vysHodnotaObrana += i.getObranaPoints();
            }
        }
        return vysHodnotaObrana;
    }
     /** spocita obrana všetkych hracov dokopy **/
    public double getTeamUtok(){
        double vysHodnotaUtoku = 0;
        for(Hrac i : hraci){
            if(i!=null){
                vysHodnotaUtoku += i.getObranaPoints();
            }
        }
        return vysHodnotaUtoku;
    }
    /** tato metoda nám prida hrača do pola hračov **/
    public boolean addHraca(Hrac hrac){
        for(int i=0;i<hraci.length;i++){
            if(hraci[i] == null ){
                hraci[i] = hrac;
                return true;
            }
        }
        return false;
    }
    /** tato metoda nám vrati pole hracov ktory su schopny dať tj. bez brankara */
    public Hrac[] poleGoalAbleHracov(){
        Hrac[] poleOut = new Hrac[hraci.length-1];
        int x = 0;
        for(Hrac i: hraci){
            if(i!=null && i instanceof GoalAble ){
                poleOut[x++] = i;
            }
            }
        return poleOut;    
    }
    
    /** tato metoda nam vrati cisto iba utocnikov */
    public Utocnik[] getUtocnikov(){
        Utocnik[] poleOut = new Utocnik[3];
        int ikrement = 0;
        for(Hrac i: hraci){
            if(i!=null && i instanceof Utocnik){
                poleOut[ikrement++] = ((Utocnik)i);
            }
        }
        return poleOut;
    }
    /** tato metoda nam vrati cisto iba obrancov */
    public Obranca[] getObrancov(){
        Obranca[] poleOut = new Obranca[2];
        int ikrement = 0;
        for(Hrac i: hraci){
            if(i!=null && i instanceof Obranca){
                poleOut[ikrement++] = ((Obranca)i);
            }
        }
        return poleOut;
    }
    /** pomocou tejto metody vieme upravať skore daneho temau */
    public void setSkore(int skore){
        this.skore = skore;
    }
    /** resetSkore  */
    public void resetSkore(){
        this.skore = 0;
    }
    
    public void addSkore(){
        skore++;
    }
    
    public int getSkore(){
       return skore;
    }
    
    public String getNazov(){
        return nazov;
    }
    /** scita a vrati utok point + obrana points dokopy */    
    public double getTeamPower(){
        return getTeamObrana()+getTeamUtok();
    }
    /** tato metoda nam vrati String kde budu vsetci hraci ktory mozu dat gol tj. obrancovia,utocnici */
    public String vypis(){
        StringBuilder strBuilder = new StringBuilder();
        for(Hrac i: hraci ){
            if(i!=null && i instanceof GoalAble ){
                strBuilder.append(i.getInfo()+" počet golov: "+((GoalAble)i).getPocetGolov()+"\n");
            }
        }
        return strBuilder.toString();
    }
    /** tato metoda mi vypiše uplne vŠetkych hračov */
    public String vypisVsetkychHracov(){
        StringBuilder strBuilder = new StringBuilder();
        for(Hrac i: hraci ){
            if(i!=null){
                strBuilder.append(i.getCeleMeno()+", "+i.getPoziciuHraca()+", body utok: "+i.getUtokPoints()+", body obrana: "+i.getObranaPoints()+"\n");
            }
        }
        return strBuilder.toString();
    }
    /** tato mteoda najde a vrati brankara */
    public Hrac getBrankar(){
        for(Hrac i: hraci){
            if(i!=null && i instanceof Brankar){
                return i;
            }
        }
        return null;
    }
    /** tato metoda je pre vytvorenie noveho teamu  a vytovri nam nového brankara */
    public static Brankar novyBrankar(){
        Utilitky.vypis("zadaj meno");
        String meno = Utilitky.inputString();
        if(meno.equals("1")){     //Cheaty
            return new Brankar();
        }
        Utilitky.vypis("zadaj priezvisko");
        String priezvisko = Utilitky.inputString();
        Utilitky.vypis("zadaj vek");
        int vek = Utilitky.inputInt();
        Utilitky.vypis("zadaj cislo dresu");
        int cisloDresu = Utilitky.inputInt();
        Utilitky.vypis("zadaj pocet obrannych bodov");
        double obranaPoints = Utilitky.inputDouble();
        Brankar brankarOut = new Brankar(meno,priezvisko,vek,cisloDresu,obranaPoints,0.0);
        return brankarOut;
    }
    /** tato metoda je pre vytvorenie noveho teamu  a vytovri nam nového obrancu */
    public static Obranca novyObranca(){
        Utilitky.vypis("zadaj meno");
        String meno = Utilitky.inputString();
        if(meno.equals("1")){       //CHeaty
              return new Obranca();
        }
        Utilitky.vypis("zadaj priezvisko");
        String priezvisko = Utilitky.inputString();
        Utilitky.vypis("zadaj vek");
        int vek = Utilitky.inputInt();
        Utilitky.vypis("zadaj cislo dresu");
        int cisloDresu = Utilitky.inputInt();
        Utilitky.vypis("zadaj pocet obrannych bodov");
        double obranaPoints = Utilitky.inputDouble();
        Utilitky.vypis("zadaj pocet utokovych bodov");
        double utokPoints = Utilitky.inputDouble();
        Obranca obrancaOut = new Obranca(meno,priezvisko,vek,cisloDresu,obranaPoints,utokPoints);
        return obrancaOut;
    }
    /** tato metoda je pre vytvorenie noveho teamu  a vytovri nam nového utocnika */
    public static Utocnik novyUtocnik(){
        Utilitky.vypis("zadaj meno");
        String meno = Utilitky.inputString();
        if(meno.equals("1")){
            return new Utocnik();
        }
        Utilitky.vypis("zadaj priezvisko");
        String priezvisko = Utilitky.inputString();
        Utilitky.vypis("zadaj vek");
        int vek = Utilitky.inputInt();
        Utilitky.vypis("zadaj cislo dresu");
        int cisloDresu = Utilitky.inputInt();
        Utilitky.vypis("zadaj pocet obrannych bodov");
        double obranaPoints = Utilitky.inputDouble();
        Utilitky.vypis("zadaj pocet utokovych bodov");
        double utokPoints = Utilitky.inputDouble();
        Utocnik utocnikOut = new Utocnik(meno,priezvisko,vek,cisloDresu,obranaPoints,utokPoints);
        return utocnikOut;
    }
    /** v tejto metode uz vytvaram kompletne nový tema a využivame ostatne metoda a rovno aj pridavam do pola Team[]  */ 
    public static Team novyTeam(){
        Utilitky.vypis("zadaj nazov tvojho noveho teamu");
        String nazov = Utilitky.inputString();
        Team teamOut = new Team(nazov,6);
        Utilitky.vypis("1) vytvor brankara");
        teamOut.addHraca(novyBrankar());
        Utilitky.vypis("2) vytvor prveho obrancu");
        teamOut.addHraca(novyObranca());
        Utilitky.vypis("vytvor druheho obrancu");
        teamOut.addHraca(novyObranca());
        Utilitky.vypis("3) vytvor prveho utocnik");
        teamOut.addHraca(novyUtocnik());
        Utilitky.vypis("vytvor druheho utocnik");
        teamOut.addHraca(novyUtocnik());
        Utilitky.vypis("vytvor tretieho utocnik");
        teamOut.addHraca(novyUtocnik());
        return teamOut;
    }
    }
    
    
    
    
