package Semestralka;
import java.util.Random;
import java.io.Serializable;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;


/**
 * Trieda {@code Ihrisko} slúži na «doplňte opis»…
 *
 * @author   «meno autora»
 * @version  «verzia alebo dátum»
 */
public class Ihrisko implements Serializable
{
    private int pocetDivakov;
    Team[] poleTeamov;
    
    public Ihrisko(int pocetTeamov){
        poleTeamov = new Team[pocetTeamov];
        this.pocetDivakov = Utilitky.pocetDivakov();
    }
    /** vrati mi pocet teamov */
    public int getPocetTeamov(){
        return poleTeamov.length;
    }
    /** tato metoda je pomocna pre losovanie a to preto lebo neviem spravit for each v jednom riadku iba skotroluje ci sa objem nachadza v poli */
    public boolean najdi(Team hladanyElement, Team[] poleTeam){
        for(Team i: poleTeam){
            if(i!=null && i.equals(hladanyElement)){
                return false;
            }
        }
        return true;
    }
    /** tato metoda nahodne rozlosuje teamy pomocou random **/
    public Team[] losovanie(){
        Team[] poleOut = new Team[poleTeamov.length];
        Random rand = new Random();
        int i = 0;
        while(true){
            int cislo = rand.nextInt(0,getPocetPrvkovBezNull(poleTeamov));
            Team nahodnyElement = poleTeamov[cislo];
            if(poleOut[i]!= nahodnyElement && najdi(nahodnyElement,poleOut)){
                poleOut[i] = nahodnyElement;
                i++;
            }
            if(i==getPocetPrvkovBezNull(poleTeamov)){
                break;
            }
        }
        return poleOut;
    }
    /** prida team do ihriska ***/
    public void addTeam(Team team){
        for(int i=0;i<poleTeamov.length;i++){
            if(poleTeamov[i]==null){
                poleTeamov[i] = team;
                break;
            }
        }
    }
    
    /** vrati mi pole teamov ktore su na ihrisku */
    public Team[] getPole(){
        return poleTeamov;
    }
    
    /** táto metoda vymeni stary team za novy team použiješ potom v turnaji  **/
    public void nahradTeam(Team OrigTeam,Team NewTeam){
        for(int i=0;i<poleTeamov.length;i++){
            if(poleTeamov[i] == OrigTeam){
                poleTeamov[i] = NewTeam;
                break;
            }
        }
    }
    /** tato metod vrati pocetPrvkov v poli a nebude pocitac null obejkty */
    
    public int getPocetPrvkovBezNull(Team [] pole){
        int pocitadlo = 0;
        for(Team i : pole){
            if(i!=null){
                pocitadlo++;
            }
        }
        return pocitadlo;
    }
    /** tato metoda vypise cely team */
    public String vypisTeamov(){
        StringBuilder strBuilder = new StringBuilder();
        int pocitadlo = 1;
        for(Team i: poleTeamov){
            strBuilder.append(pocitadlo+") "+i.getNazov()+"\n");
            pocitadlo++;
        }
        return strBuilder.toString();
    }
}   