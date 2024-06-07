package Semestralka;
import java.util.Random;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.util.Scanner;


/**
 * Trieda Utilitky je trieda kde budu metodu ktore sa nebudu chciet opakovane pisat, ci su to vypisoveho typu, nacitavanie aťd.
 *
 * 
 */
public class Utilitky{
    /** metoda ktora mi uspi program vytvorena kvoli tomu aby som nemusel vŠade kde ju použivam pisat try/catch */
    public static void pauzaSek(){
        try{
            Thread.sleep(1000);
        }
        catch(Exception e){
            System.err.println("nieco je zle : "+e);
        }
        }
    /** metoda ktora mi zacne pocitatc od 1 po 3 na zaciatku tretiny */
    public static void odpocitavanie(){
        for(int i=1; i<4;i++){
            System.out.println(i);
            Utilitky.pauzaSek();
        }
        Utilitky.pauzaSek();
    }
    /** tato metoda mi vypise hracov */
    public static void vypisHraca(Hrac hrac){
        System.out.println(hrac.getCeleMeno()+", čislo dresu: "+hrac.getCisloDresu());
    }
    /** metoda kde na vstup ide cislo ktore mi da tolko prazdnych printov /metoda pre krajsi vypis **/
    public static void space(int pocetMedzier){
        for(int i=0; i<=pocetMedzier; i++){
            System.out.println();
        }
    }
    /** metoda pre print **/
    public static void vypis(String text){
        System.out.println(text);
    }
    /** vygeneruje nahodny pocet divakov */
    public static int pocetDivakov(){
        Random rand = new Random();
        int pocet = rand.nextInt(1000,2000);
        return pocet-pocet%100;
    }
    /** metoda ktora mi načita Objekt ihriska kde su ulozene teamy aj hraci dokopy a ich potom len vyberam pomocou metod z ihriska */
    public static Ihrisko loadIhrisko(){
        File subor = new File("Semestralka/Team/teams.obj");
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(subor));
        
            try
            {
                Ihrisko ihrisko1 = (Ihrisko)ois.readObject();
                return ihrisko1;
            }
            catch (ClassNotFoundException cnfe)
            {
                cnfe.printStackTrace();
            }
            ois.close();
        }
        catch(java.io.IOException ioe){
            ioe.printStackTrace();
            System.err.println("Subor neexistuje");
        }
        return null;
    }
    /** metoda pre ukladanie objektu Ihriska aby som ho mohol potom nacitavať alebo prepisovať **/
    public static void saveIhrisko(Ihrisko ihrisko){
        File subor = new File("Semestralka/Team/teams.obj");
        try{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(subor));
            oos.writeObject(ihrisko);
            oos.close();
        }        
        catch (java.io.IOException ioe){
            ioe.printStackTrace();
            System.err.println("Subor neexistuje");
        }
    }
    /** táto metoda mi clearne mi konzolu**/
    public static void clearConsoleNormal(){
      System.out.print('\u000C');
    }
   
    /** nahodne mi vyberie nejaku hlasku pre utok tie vstupy su len kvoli ziskaniu nazvu daneho teamu **/
    public static String kecyPriUtoku(Team team){
        Random rand = new Random(); 
        int cislo = rand.nextInt(1,4);
        switch(cislo){
            case 1:
                return "Team " +team.getNazov()+" utoči na branku ma sanču dat gol!";
            case 2:
                return "Utocnici teamu "+ team.getNazov()+ " prechadzajú za modru čiaru maju sanču skorovať";
            case 3:
                return "Team " + team.getNazov()+" sa dostal do utočneho pasma";
            default:
                return "def" ;
        }
    }
    /** nahodne mi vyberie nejaku hlasku pre obranu tie vstupy su len kvoli ziskaniu nazvu daneho teamu **/
    public static String kecyPriNedaniGolu(Team utok,Team obrana){
        Random rand = new Random(); 
        int cislo = rand.nextInt(1,5);
        switch(cislo){
            case 1:
                return "Perfektna defenziva teamu "+obrana.getNazov()+" zabranili superovi vystrelit na branu" ;
            case 2:
                return "Vyborna praca brankara teamu "+obrana.getNazov()+" zmaril superovi sanču dat gol!";
            case 3:
                return "Obranci teamu "+obrana.getNazov()+" stopli superovu ofenzivu";
            case 4:
                return "Vyborna akcia teamu " + utok.getNazov()+ " avšak skore ostava nezmene";
            default:
                return "def";
        }
    }
    /** tato metdoa mi vrati len skore danych teamov pre vypis **/
    public static void vypisSkore(Team A, Team B){
        System.out.println(A.getNazov()+" "+A.getSkore()+":"+B.getSkore()+" "+B.getNazov());
    }
    /** metoda ktorá mi bude brat input aby som nemusel stale vytvarat novy objekt Scanner */
    public static int inputInt(){
        Scanner sc = new Scanner(System.in);
        try{
            int input = sc.nextInt();
            return input;
        }
        catch(java.util.InputMismatchException e){
            System.err.println("zle si zadal");
            return -1;
        }
    }
    /** táto metoda presne to iste ako inputInt() az na to ze tu si pytam string ta je String**/
    public static String inputString(){
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        return input;
    }
    /** táto metoda presne to iste ako inputInt() az na to ze tu si pytam string ta je double**/
    public static double inputDouble(){
        Scanner sc = new Scanner(System.in);
        try{
            double input = sc.nextDouble();
            return input;
        }
        catch(java.util.InputMismatchException e){
            System.err.println("zle si zadal");
            return -1.0;
        }
    }
    }
    

