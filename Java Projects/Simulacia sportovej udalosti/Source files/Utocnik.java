package Semestralka;
import java.io.Serializable;


/**
 * Trieda {@code Utocnik} vytvorenie utocnika ktory može dat gol a asistenciuz
 * preto ma atributy pocetGolov ,pocetasistenci
 *
 *
 * @author   «meno autora»
 * @version  «verzia alebo dátum»
 */
public class Utocnik extends Hrac implements GoalAble,Serializable
{
    private int pocetGolov;
    private int pocetasistenci;
    
    public Utocnik(String meno, String priezvisko, int vek, int cisloDresu, double obranaPoints, double utokPoints){
        super(meno,priezvisko,vek,cisloDresu,"Utocnik",obranaPoints,utokPoints);
        this.pocetGolov = 0;
        this.pocetasistenci = 0;
    }
    public Utocnik(){
        super("meno","priezvisko",42,20,"Utocnik",20,100);
        this.pocetGolov = 0;
        this.pocetasistenci = 0;
    }
    public void addGol(){
        pocetGolov+=1;
    }
    public void addAsistencia(){
        pocetasistenci++;
    } 
    public int getPocetGolov(){
        return pocetGolov;
    }
    public int getPocetAsistenci(){
        return pocetasistenci;
    }
    
    public int getBody(){
        return pocetGolov+pocetasistenci;
    }
}
