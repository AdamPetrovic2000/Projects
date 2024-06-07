package Semestralka;
import java.util.Random;


/**
 * Trieda je pomocna a nudzová v pripadaže mi zmizne subor s uloženimi teamami
 *
 * @author   «meno autora»
 * @version  «verzia alebo dátum»
 */
public class VytvorTeam
{

    public static void main(String[] args) throws Exception
    {
        Team team1 = new Team("HK MT",6);
        Team team2 = new Team("HK ZA",6);
        Team team3 = new Team("HK KE",6);
        Team team4 = new Team("HK PP",6);
        //String meno, String priezvisko, int vek, int cisloDresu, double obranaPoints, double utokPoints)
        team1.addHraca(new Brankar("Vladimír","GLOSÁR",24,1,80,0));
        team1.addHraca(new Obranca("Michal","MURČEK",30,12,60,20));
        team1.addHraca(new Obranca("Christián","PAVLAS",26,14,70,20));
        team1.addHraca(new Utocnik("Adam","PAULÍNY",22,20,20,70));
        team1.addHraca(new Utocnik("František","POLIAČEK",30,18,20,60));
        team1.addHraca(new Utocnik("Jakub","NEMEC",25,23,30,55));
        
        team2.addHraca(new Brankar("Patrik","ROMANČÍK",28,2,90,0));
        team2.addHraca(new Obranca("Petr","ŠIDLÍK",29,10,60,40));
        team2.addHraca(new Obranca("Adrián","SLOBODA",26,9,50,50));
        team2.addHraca(new Utocnik("Tomáš","TOMEK",34,6,30,90));
        team2.addHraca(new Utocnik("Ján","NAHÁLKA",23,24,20,70));
        team2.addHraca(new Utocnik("David","RŮŽIČKA",35,89,10,100));
        
        team3.addHraca(new Brankar("Oliver","JOKEĽ",31,3,95,0));
        team3.addHraca(new Obranca("Matej","JURČO",20,27,70,20));
        team3.addHraca(new Obranca("Andrej","KOŠARIŠŤAN",28,29,70,40));
        team3.addHraca(new Utocnik("Patrik","LAMPER",29,17,10,110));
        team3.addHraca(new Utocnik("Martin","GERNÁT",29,71,40,80));
        team3.addHraca(new Utocnik("Ervín","HALÁSZ",26,87,15,95));
        
        team4.addHraca(new Brankar("Nicholas","BONDRA",25,2,100,0));
        team4.addHraca(new Obranca("Miloš","BUBELA",30,13,70,45));
        team4.addHraca(new Obranca("Andrej","KUKUČA",23,36,80,20));
        team4.addHraca(new Utocnik("Jakub","MELIŠKO",26,78,40,80));
        team4.addHraca(new Utocnik("Roman","RYCHLÍK",21,56,50,60));
        team4.addHraca(new Utocnik("Dávid","SKOKAN",33,67,30,90));
        Ihrisko ik1 =  new Ihrisko(4);
        ik1.addTeam(team1);
        ik1.addTeam(team2);
        ik1.addTeam(team3);
        ik1.addTeam(team4);
        
        Utilitky.saveIhrisko(ik1);
        
    
    }
    /** tato metoda tu je len pre istotu a budem v nej mat uložených hračov **/
    public static void loadDefaulTeam(){
        Ihrisko ihriskoOut = new Ihrisko(4);
        
        Team team1 = new Team("HK MT",6);
        Team team2 = new Team("HK ZA",6);
        Team team3 = new Team("HK KE",6);
        Team team4 = new Team("HK PP",6);
        //String meno, String priezvisko, int vek, int cisloDresu, double obranaPoints, double utokPoints)
        team1.addHraca(new Brankar("Vladimír","GLOSÁR",24,1,80,0));
        team1.addHraca(new Obranca("Michal","MURČEK",30,12,60,20));
        team1.addHraca(new Obranca("Christián","PAVLAS",26,14,70,20));
        team1.addHraca(new Utocnik("Adam","PAULÍNY",22,20,20,70));
        team1.addHraca(new Utocnik("František","POLIAČEK",30,18,20,60));
        team1.addHraca(new Utocnik("Jakub","NEMEC",25,23,30,55));
        
        team2.addHraca(new Brankar("Patrik","ROMANČÍK",28,2,90,0));
        team2.addHraca(new Obranca("Petr","ŠIDLÍK",29,10,60,40));
        team2.addHraca(new Obranca("Adrián","SLOBODA",26,9,50,50));
        team2.addHraca(new Utocnik("Tomáš","TOMEK",34,6,30,90));
        team2.addHraca(new Utocnik("Ján","NAHÁLKA",23,24,20,70));
        team2.addHraca(new Utocnik("David","RŮŽIČKA",35,89,10,100));
        
        team3.addHraca(new Brankar("Oliver","JOKEĽ",31,3,95,0));
        team3.addHraca(new Obranca("Matej","JURČO",20,27,70,20));
        team3.addHraca(new Obranca("Andrej","KOŠARIŠŤAN",28,29,70,40));
        team3.addHraca(new Utocnik("Patrik","LAMPER",29,17,10,110));
        team3.addHraca(new Utocnik("Martin","GERNÁT",29,71,40,80));
        team3.addHraca(new Utocnik("Ervín","HALÁSZ",26,87,15,95));
        
        team4.addHraca(new Brankar("Nicholas","BONDRA",25,2,100,0));
        team4.addHraca(new Obranca("Miloš","BUBELA",30,13,70,45));
        team4.addHraca(new Obranca("Andrej","KUKUČA",23,36,80,20));
        team4.addHraca(new Utocnik("Jakub","MELIŠKO",26,78,40,80));
        team4.addHraca(new Utocnik("Roman","RYCHLÍK",21,56,50,60));
        team4.addHraca(new Utocnik("Dávid","SKOKAN",33,67,30,90));
        
        ihriskoOut.addTeam(team1);
        ihriskoOut.addTeam(team2);
        ihriskoOut.addTeam(team3);
        ihriskoOut.addTeam(team4);
        
        Utilitky.saveIhrisko(ihriskoOut);
        
    }
}
