package Semestralka;
import java.io.Serializable;


/**
 * Trieda {@code Obranca} slúži na vytvorenie obrancu, ktory može dat gol a asistenciuz
 * preto ma atributy pocetGolov ,pocetasistenci
 *
 * @author   «meno autora»
 * @version  «verzia alebo dátum»
 */
public class Obranca extends Hrac implements GoalAble,Serializable
{
    private int pocetGolov;
    private int pocetasistenci;
    
    public Obranca(String meno, String priezvisko, int vek, int cisloDresu, double obranaPoints, double utokPoints){
    super(meno,priezvisko,vek,cisloDresu,"Obranca",obranaPoints,utokPoints);
    this.pocetGolov = 0;
    this.pocetasistenci = 0;
    }
    public Obranca(){
        super("meno","priezvisko",28,10,"Obranca",20,80);
        this.pocetGolov = 0;
        this.pocetasistenci = 0;
    }
    
    
    public int getPocetGolov(){
        return pocetGolov;
    }
    
    public void addGol(){
        this.pocetGolov+=1;
    }
    
    public void addAsistencia(){
        this.pocetasistenci++;
    }
    public int getPocetAsistenci(){
        return pocetasistenci;
    }
    
    public int getBody(){
        return pocetGolov+pocetasistenci;
    }
    
}
