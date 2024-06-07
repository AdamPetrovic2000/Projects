package Semestralka;
import java.io.Serializable;


/**
 * Trieda Hrac dedi od osoby  a doplnuje o cislo atributy cisloDresu a PoziciaHraca, obranaPoints, utokPoints, a gettery a settery spojene s nimi
 *
 * @author   «meno autora»
 * @version  «verzia alebo dátum»
 */
public class Hrac extends Osoba implements Serializable
{
    private int cisloDresu;
    private String poziciaHraca;
    private  double obranaPoints, utokPoints;
    
    public Hrac(String meno, String priezvisko, int vek, int cisloDresu, String poziciaHraca, double obranaPoints, double utokPoints){
        super(meno,priezvisko,vek);
        this.cisloDresu = cisloDresu;
        this.poziciaHraca = poziciaHraca;
        this.utokPoints = utokPoints;
        this.obranaPoints = obranaPoints;
    }
    public Hrac(){
        super();
        this.cisloDresu = -1;
        this.poziciaHraca ="Nemam";
        this.utokPoints = -1;
        this.obranaPoints = -1;
    }
    public int getCisloDresu(){
        return cisloDresu;
    }
    public void setCisloDresu(int cisloDresu){
        this.cisloDresu = cisloDresu;
    }
    public String getPoziciuHraca(){
        return poziciaHraca;
    }
    public void setPoziciuHraca(String poz){
        this.poziciaHraca = poz;
    }
    public double getObranaPoints(){
        return obranaPoints;
    }
    public double getUtokPoints(){
        return utokPoints;
    }
    public String getInfo(){
        return getCeleMeno()+" "+cisloDresu;
    }
}
