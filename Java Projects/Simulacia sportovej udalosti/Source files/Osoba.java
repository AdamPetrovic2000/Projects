package Semestralka;
import java.io.Serializable;


/**
 * Abstrakta trieda na vytvorenie osoby ktora bude meno, priezivkso ,atď
 *
 * @author   «meno autora»
 * @version  «verzia alebo dátum»
 */
public abstract class Osoba implements Serializable
{
    private int vek;
    private String meno,priezvisko;
     
    public Osoba(String meno, String priezvisko, int vek){
        this.meno = meno;
        this.priezvisko = priezvisko;
        this.vek = vek;
    }
    public Osoba(){
        this("No","Name",-1);
    }
    public String getMeno(){
        return meno;
    }
    public String getPriezvisko(){
        return priezvisko;
    }
    public String getCeleMeno(){
        return meno +" " + priezvisko;
    }
    public void setMeno(String meno){
        this.meno = meno;
    }
    public void setPriezvisko(){
        this.priezvisko = priezvisko;
    }
}
