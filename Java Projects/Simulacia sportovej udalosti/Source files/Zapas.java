package Semestralka;
import java.util.Random;


/**
 * Trieda zapas nam vytvara zapas pre 2 teamy zahrnuje vypocet pravdepodobnosti dat gol a taktiež aj jednotlive časti zapasu a aj
 * samotny celyzapas metody su staticke lebo bolo by zbytočne vytvarat konstruktor pre takuto triedu a chcem mat pristup k tomu 
 * všade bez nutnosti vytvarat objekt.
 *
 * @author   Adam Petrovic
 * @version  
 */
public class Zapas
{   
    /** táto metoda bude davať šancu na gol jednemu z dvoch timov, pravdepodnosť bude zavisiet od sily timu
       na input pojdu 2 timy a von pojde jeden team na ktorý vyšla pravdepobnosť**/
    
    public static Team sancaNaGol(Team A, Team B){
        Random rand = new Random();
        Team teamOut;
        Team lepsi,horsi;
        double sucet = A.getTeamPower()+B.getTeamPower();
        if(A.getTeamPower()>B.getTeamPower()){
            lepsi = A;
            horsi = B;
        }
        else{
            lepsi = B;
            horsi = A;
        }
        double nahodneCislo = rand.nextDouble(0,sucet);
        if(nahodneCislo<=horsi.getTeamPower()){
            teamOut = horsi;
        }
        else{
            teamOut = lepsi;
        }
        return teamOut; 
    }
    /** vypocet pravedpodnosti obrancu dat gol /7%// */
    public static boolean sancaObrancu(){
        Random rand = new Random();
        int nahodneCislo = rand.nextInt(1,14);
         if(nahodneCislo == 1){
             return true;
        }
        return false;
    }
    
    /** vypocet pravdepodnost utocnika dať //14%// */
    public static boolean sancaUtocnika(){
        Random rand = new Random();
        int nahodneCislo = rand.nextInt(1,7);
         if(nahodneCislo == 1){
             return true;
        }
        return false;
    }
    /**nahodne cislo pre obrancu **/
    public static int obranaCislo(){
        Random rand = new Random();
        return rand.nextInt(0,2);
    }
    /**nahodne cislo pre obrancu*/
    public static int utokCislo(){
        Random rand = new Random();
        return rand.nextInt(0,3);
    }
    
    /** tato metoda iba porovna ktorý team vyhral a vypiše celkové skore a v pripade že je rezima tak zovola metodu overTime*/
    public static Team vitaz(Team A, Team B){
        if(A.getSkore()>B.getSkore()){
            Utilitky.pauzaSek();    
            Utilitky.space(1);
            Utilitky.clearConsoleNormal();
            Utilitky.vypis("vyhral " + A.getNazov()+" "+A.getSkore()+":"+B.getSkore());
            return A;
        }
        else if(A.getSkore()<B.getSkore()){
            Utilitky.pauzaSek();
            Utilitky.space(1);
            Utilitky.clearConsoleNormal();
            Utilitky.vypis("vyhral " + B.getNazov()+" "+B.getSkore()+":"+A.getSkore());
            return B;
        }
        else{
            return overTime(A,B,A.getSkore(),B.getSkore());
        }
    }
    
    /** iba ziská inforamcie odhladom stavu golov + nejaky krajši vypis a uvod pre dalšiu tretinu **/
    public static void stavPoTretine(int cisloTretina, Team A, Team B){
        System.out.println("Koniec "+cisloTretina+" tretiny");
        Utilitky.pauzaSek();
        Utilitky.pauzaSek();
        Utilitky.clearConsoleNormal();
        System.out.println("Stav po "+cisloTretina+" tretine"+"\n"+A.getNazov()+" "+B.getNazov());
        System.out.println( "   "+A.getSkore()+" : "+B.getSkore());
        System.out.println("začina sa "+(cisloTretina+1) +" tretina");
        Utilitky.space(1);
        Utilitky.odpocitavanie();
    }
    
    /** tretina rozdelim obrancov a utocnik do poli a potom ich nahodne vyberám že ktorý da gól spolu so vseobencoou
        pravdepodobnostou dať gol a vypujem tychto hracov meno atď a pridavam im gol a astienice aby som nakonci mohol spravit nejaky najelpši hrač zapasu
        a celkovo jedna tretina trva 7 iteracii a su v nej zakomponovane sanča na utok sanca na gól atď.
       */
    public static String tretina(Team A, Team B){
        Obranca[] obranciA = A.getObrancov();     // dam si ich do pola aby som ich mohol vyberat že kto z nich da gol 
        Obranca[] obranciB = B.getObrancov();
        Utocnik[] utocniciA = A.getUtocnikov();
        Utocnik[] utocniciB = B.getUtocnikov();
        for(int i=0;i<7;i++){
            Team teamMoznostDatGol = sancaNaGol(A,B);                // tu ideme na pravdepobnosť 
            boolean sancaObrancuSkorovat = sancaObrancu();
            boolean sancaUtocnikaSkorovat = sancaUtocnika();
            System.out.println(Utilitky.kecyPriUtoku(teamMoznostDatGol));// tu dorobiš este assitenciu niekedy
            Utilitky.pauzaSek();
            if(teamMoznostDatGol.equals(A)){
                if(sancaUtocnika()== true){
                    int cislo = utokCislo();// generujem radnom cislo 
                    Utilitky.vypis("team "+A.getNazov()+" dava gol!!!");
                    
                    Utilitky.space(1);
                    System.out.println("gol : ");
                    Utilitky.vypisHraca(utocniciA[cislo]);
                    Utocnik nahodny = utocniciA[cislo];
                    nahodny.addGol();
                    
                     
                    Hrac hrac = assistencia(nahodny,teamMoznostDatGol); // na vstup ide hrac a team v ktorom je 
                    System.out.println("assistencia : ");
                    Utilitky.vypisHraca(hrac);
                    ((GoalAble)hrac).addAsistencia();
                    
                    teamMoznostDatGol.addSkore();
                    
                    Utilitky.space(1);
                    Utilitky.vypis("     Stav");
                    Utilitky.vypisSkore(A,B);
                    Utilitky.vypis("--------------------");
                }
                else if(sancaObrancu()==true){
                    int cislo = obranaCislo(); // generujem radnom cislo 
                    Utilitky.vypis("team "+A.getNazov()+" dava gol!!!");
                    
                    Utilitky.space(1);
                    System.out.println("gol : ");
                    Utilitky.vypisHraca(obranciA[cislo]);
                    Obranca nahodny = obranciA[cislo];
                    nahodny.addGol();
                    
                    Hrac hrac = assistencia(nahodny,teamMoznostDatGol);
                    System.out.println("assistencia : ");
                    Utilitky.vypisHraca(hrac);
                    ((GoalAble)hrac).addAsistencia();
                    
                    teamMoznostDatGol.addSkore();
                    
                    Utilitky.space(1);
                    Utilitky.vypis("     Stav");
                    Utilitky.vypisSkore(A,B);
                    Utilitky.vypis("--------------------");
                }
                else{
                    System.out.println(Utilitky.kecyPriNedaniGolu(A,B));
                }
            }
            else if(teamMoznostDatGol.equals(B)){
                if(sancaUtocnika()== true){
                    int cislo = utokCislo();
                    Utilitky.vypis("team "+B.getNazov()+" dava gol!!!");
                    
                    Utilitky.space(1);
                    System.out.println("gol : ");
                    Utilitky.vypisHraca(utocniciB[cislo]);
                    Utocnik nahodny = utocniciB[cislo];
                    nahodny.addGol();
                    
                   
                    Hrac hrac = assistencia(nahodny,teamMoznostDatGol);
                    System.out.println("assistencia : ");
                    Utilitky.vypisHraca(hrac);
                    ((GoalAble)hrac).addAsistencia();
                    
                    teamMoznostDatGol.addSkore();
                    
                    Utilitky.space(1);
                    Utilitky.vypis("     Stav");
                    Utilitky.vypisSkore(A,B);
                    Utilitky.vypis("--------------------");
                }
                else if(sancaObrancu()==true){
                    int cislo = obranaCislo();
                    Utilitky.vypis("team "+B.getNazov()+" dava gol!!!");
                   
                    Utilitky.space(1);
                    System.out.println("gol : ");
                    Utilitky.vypisHraca(obranciB[cislo]);
                    Obranca nahodny = obranciB[cislo];
                    nahodny.addGol();
                    
                    Hrac hrac = assistencia(nahodny,teamMoznostDatGol);
                    System.out.println("assistencia : ");
                    Utilitky.vypisHraca(hrac);
                    ((GoalAble)hrac).addAsistencia();
                    
                    teamMoznostDatGol.addSkore();
                    
                    Utilitky.space(1);
                    Utilitky.vypis("     Stav");
                    Utilitky.vypisSkore(A,B);
                    Utilitky.vypis("--------------------");
                }
                else{
                    System.out.println(Utilitky.kecyPriNedaniGolu(B,A));
                }
            }
            Utilitky.pauzaSek();
            System.out.println();
        }
        return " ";
    }
    
    /** dam iba do cyklu 3 tretin plus metody stav po tretine a vitaz **/
    public static Team celyZapas(Team A, Team B){
        System.out.println("Zacina sa zapas!");
        Utilitky.odpocitavanie();
        
        System.out.println();
        for(int i=1; i<4; i++){
            if(i<3){
                tretina(A, B);
                stavPoTretine(i,A,B);
                System.out.println();
                Utilitky.pauzaSek();
            }
        }
        Zapas.tretina(A,B);
        return Zapas.vitaz(A,B);
    }
    
    /** tato metoda robí to že ak dojde v k remize tak sa táto metoda spusit a bude bežat dokym jeden z timov nedá gól ako aj v
       v realnom hokeji;
       /poživa sa presne to iste ako v metode tretina az na to že namiesto foru sa požíva while ktorý ak je skore z
       z prechadzajucich tretin mensie ako nové z teamu A or teamu B tak to breakne/
       **/
    public static Team overTime(Team A, Team B, int SkoreTeamAPred, int SkoreTeamBPred){
        Utilitky.vypis("ideme do overTime-U");
        Utilitky.pauzaSek();
        
        Utilitky.clearConsoleNormal();
        Utilitky.space(1);
        Utilitky.odpocitavanie();
        Utilitky.space(1);
        Obranca[] obranciA = A.getObrancov();     // dam si ich do pola aby som ich mohol vyberat že kto z nich da gol 
        Obranca[] obranciB = B.getObrancov();
        Utocnik[] utocniciA = A.getUtocnikov();
        Utocnik[] utocniciB = B.getUtocnikov();
        while(true){
            Team teamMoznostDatGol = sancaNaGol(A,B);                // tu ideme na pravdepobnosť 
            boolean sancaObrancuSkorovat = sancaObrancu();
            boolean sancaUtocnikaSkorovat = sancaUtocnika();
            System.out.println("Team "+ teamMoznostDatGol.getNazov()+" utoči na branku moze dat gol");// tu dorobiš este assitenciu niekedy
            Utilitky.pauzaSek();
            if(teamMoznostDatGol.equals(A)){
                if(sancaUtocnika()== true){
                    int cislo = utokCislo();// generujem radnom cislo 
                    Utilitky.vypis("team "+A.getNazov()+" dava gol!!!");
                    
                    Utilitky.space(1);
                    System.out.println("gol : ");
                    Utilitky.vypisHraca(utocniciA[cislo]);
                    Utocnik nahodny = utocniciA[cislo];
                    nahodny.addGol();
                    
                     
                    Hrac hrac = assistencia(nahodny,teamMoznostDatGol); // na vstup ide hrac a team v ktorom je 
                    System.out.println("assistencia : ");
                    Utilitky.vypisHraca(hrac);
                    ((GoalAble)hrac).addAsistencia();
                    
                    teamMoznostDatGol.addSkore();
                    
                    Utilitky.space(1);
                    Utilitky.vypis("     Stav");
                    Utilitky.vypisSkore(A,B);
                    Utilitky.vypis("--------------------");
                }
                else if(sancaObrancu()==true){
                    int cislo = obranaCislo(); // generujem radnom cislo 
                    Utilitky.vypis("team "+A.getNazov()+" dava gol!!!");
                    
                    Utilitky.space(1);
                    System.out.println("gol : ");
                    Utilitky.vypisHraca(obranciA[cislo]);
                    Obranca nahodny = obranciA[cislo];
                    nahodny.addGol();
                    
                    Hrac hrac = assistencia(nahodny,teamMoznostDatGol);
                    System.out.println("assistencia : ");
                    Utilitky.vypisHraca(hrac);
                    ((GoalAble)hrac).addAsistencia();
                    
                    teamMoznostDatGol.addSkore();
                    
                    Utilitky.space(1);
                    Utilitky.vypis("     Stav");
                    Utilitky.vypisSkore(A,B);
                    Utilitky.vypis("--------------------");
                }
                else{
                    System.out.println(Utilitky.kecyPriNedaniGolu(A,B));
                }
            }
            else if(teamMoznostDatGol.equals(B)){
                if(sancaUtocnika()== true){
                    int cislo = utokCislo();
                    Utilitky.vypis("team "+B.getNazov()+" dava gol!!!");
                    
                    Utilitky.space(1);
                    System.out.println("gol : ");
                    Utilitky.vypisHraca(utocniciB[cislo]);
                    Utocnik nahodny = utocniciB[cislo];
                    nahodny.addGol();
                    
                   
                    Hrac hrac = assistencia(nahodny,teamMoznostDatGol);
                    System.out.println("assistencia : ");
                    Utilitky.vypisHraca(hrac);
                    ((GoalAble)hrac).addAsistencia();
                    
                    teamMoznostDatGol.addSkore();
                    
                    Utilitky.space(1);
                    Utilitky.vypis("     Stav");
                    Utilitky.vypisSkore(A,B);
                    Utilitky.vypis("--------------------");
                }
                else if(sancaObrancu()==true){
                    int cislo = obranaCislo();
                    Utilitky.vypis("team "+B.getNazov()+" dava gol!!!");
                   
                    Utilitky.space(1);
                    System.out.println("gol : ");
                    Utilitky.vypisHraca(obranciB[cislo]);
                    Obranca nahodny = obranciB[cislo];
                    nahodny.addGol();
                    
                    Hrac hrac = assistencia(nahodny,teamMoznostDatGol);
                    System.out.println("assistencia : ");
                    Utilitky.vypisHraca(hrac);
                    ((GoalAble)hrac).addAsistencia();
                    
                    teamMoznostDatGol.addSkore();
                    
                    Utilitky.space(1);
                    Utilitky.vypis("     Stav");
                    Utilitky.vypisSkore(A,B);
                    Utilitky.vypis("--------------------");
                }
                else{
                    System.out.println(Utilitky.kecyPriNedaniGolu(B,A));
                }
            }
            Utilitky.pauzaSek();
            System.out.println();
            if(A.getSkore()>SkoreTeamAPred || B.getSkore()>SkoreTeamBPred){
                vitaz(A,B);
                break;   // ak sa zmeni skore oproti povodnemu tak to breakne;
            }
        }
        if(A.getSkore()>B.getSkore()){
            return A;
        }
        else{
            return B;
        }
    }
    /** assistencia hrača musi byt to iný hrač ako ten co dá gól---> vyberie pomocou random dalšieho hráča **/
    public static Hrac assistencia(Hrac hracKtoryDavaGol,Team teamHracaKtoryDavaGol){
        Hrac hracOut = hracKtoryDavaGol;
        Hrac[] poleHracov = teamHracaKtoryDavaGol.poleGoalAbleHracov();
        Random rand = new Random();
        do{
            int cislo = rand.nextInt(0,5);
            hracOut = poleHracov[cislo];
        }
        while(hracOut.equals(hracKtoryDavaGol));
        return hracOut;
    }
    /** resetne skóre aby bolo ciste a pripravene na dalši zapas**/
    public static void resetSkore(Team team){
        team.resetSkore();
    }
    /** tato metoda najde najlepsie hráča pomocou boubble sortu **/
    public static Hrac bestHracTeamu(Team team){
        Hrac[] pole = team.poleGoalAbleHracov();
        Hrac temp;
        for(int i=0;i<pole.length;i++){
            for(int j=1;j<pole.length-i;j++)
                if(  ((GoalAble)pole[j]).getBody()<((GoalAble)pole[j-1]).getBody()){
                    temp = pole[j-1];
                    pole[j-1] = pole[j];
                    pole[j] = temp;
            }
        }
        return pole[pole.length-1];
    }
    /*
    public static Team bestTeamPodlaHracov(Ihrisko ihrisko){
        Team[] pole = ihrisko.getPole();
        int ikrement = 0;
        
        Team temp;
        for(int i=0;i<pole.length;i++){
            for(int j=1;j<pole.length-i;j++)
                if( ((GoalAble)bestHracTeamu(pole[j])).getBody() < ((GoalAble)bestHracTeamu(pole[j-1])).getBody()){ //hladam najlepsich hracov z tymu                                                                                                  
                    temp = pole[j-1];                                                                                // a podla toho to sortujem
                    pole[j-1] = pole[j];
                    pole[j] = temp;
            }
        }
        return pole[pole.length-1];
    }
        */
       
    /** tato metoda mi najde najlepšich hračov z každeho teamu 
     *  na vstup ide ihrisko kde na nachadzaju vsetky teamy a hraci
     *  a vrati mi len vypis lebo nepotrebujem s nimi nic viac robiť
     */   
    public static String bestHracizTeamov(Ihrisko ihrisko){
        Team[] pole = ihrisko.getPole();
        int ikrement = 0;
        StringBuilder strBuilder = new StringBuilder();
        for(Team i:pole){
            strBuilder.append("Team : "+i.getNazov()+" "+bestHracTeamu(i).getCeleMeno()+" pocet bodov: "+((GoalAble)bestHracTeamu(i)).getBody()+"\n");
        }
        return strBuilder.toString();
    }
}
