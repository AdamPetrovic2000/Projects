package Semestralka;
import java.io.Serializable;


/**
 * Trieda {@code Brankar} vytvorenie objektu brankara 
 *
 * @author   «meno autora»
 * @version  «verzia alebo dátum»
 */
public class Brankar extends Hrac implements Serializable
{
    public Brankar(String meno, String priezvisko, int vek, int cisloDresu, double obranaPoints, double utokPoints){
    super(meno,priezvisko,vek,cisloDresu,"Brankar",obranaPoints,utokPoints);
    }
    
    public Brankar(){
    super("meno","priezvisko",42,24,"Brankar",100,0);
    }
    
}
