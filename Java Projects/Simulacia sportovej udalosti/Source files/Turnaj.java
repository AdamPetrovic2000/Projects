package Semestralka;
/**
 * Trieda Turnaj bude nám bude spustac celý turnaj je v nej metoda main() v ktorej bude aj konzola,1- spusti turnaj,2- moznost vytvorenia nového tymu ktory nahrady
 * jeden z povodnych, 3-vypis hracov z jedneho tymu, 4-end. dalej kazda kazda z tychto casti bude mat svoje podcasti kde v turnaji sa budu ziskat najlepsieho
 * hraca daného zapasu a na konci budú aj najelpsi hraci zo vsetkych teamov,v moznosti vytvorenia bude taktiez mozne vratit spat povodne(defaulte) teamy.
 */
public class Turnaj
{
    public static void main(String[] args){
        while(true){
            Utilitky.clearConsoleNormal();
            System.out.println("1-Spustit simulaciu, 2-vytvorit team, 3-vypis teamov, 4-quit");
            int input = Utilitky.inputInt();
            switch(input){
                case 1:
                    Ihrisko ihrisko = Utilitky.loadIhrisko();
                    Team[] rozLosTeam = ihrisko.losovanie();
                    Utilitky.clearConsoleNormal();
                    System.out.println("Teamy na turnaji: "+ "\n" +ihrisko.vypisTeamov());
                    Utilitky.pauzaSek();
                    System.out.println("prvy zapas:");
                    Utilitky.space(1);
                    System.out.println(rozLosTeam[0].getNazov()+" vs "+rozLosTeam[1].getNazov());
                    Utilitky.space(1); //------------------------------------------------------------------- kecy a vypisovanie
                    Team vyhernyTeam1 = Zapas.celyZapas(rozLosTeam[0],rozLosTeam[1]); // prvý zapas 
                    while(true){
                        Utilitky.space(1);
                        System.out.println("1-ukaz najlepšieho hrača zapasu, 2-continue");
                        Utilitky.space(1);
                        int input1 = Utilitky.inputInt();
                        switch(input1){
                            case 1:
                                Hrac bestPlayer = Zapas.bestHracTeamu(vyhernyTeam1);
                                Utilitky.vypis("team "+ vyhernyTeam1.getNazov()+" hrac: "+bestPlayer.getCeleMeno()+" pocet bodov: "+((GoalAble)bestPlayer).getBody());
                                System.out.println("2-continue");
                                int input2 = 0;
                                do{ input2 = Utilitky.inputInt(); } while(input2 != 2);
                                input1 = input2;
                                break;
                            case 2:
                                break;
                            default:
                                System.out.println("zadal si zle cislo");
                        }
                        if(input1==2){
                            break;
                        }
                    }
                    vyhernyTeam1.resetSkore();
                    Utilitky.pauzaSek();
                    Utilitky.clearConsoleNormal();
                    System.out.println("druhy zapas:");
                    Utilitky.space(1);
                    System.out.println(rozLosTeam[2].getNazov()+" vs "+rozLosTeam[3].getNazov());
                    Team vyhernyTeam2 = Zapas.celyZapas(rozLosTeam[2],rozLosTeam[3]); // prvý zapas 
                    while(true){
                        Utilitky.space(1);
                        System.out.println("1-ukaz najlepšieho hrača zapasu, 2-continue");
                        Utilitky.space(1);
                        int input1 = Utilitky.inputInt();
                        switch(input1){
                            case 1:
                                Hrac bestPlayer = Zapas.bestHracTeamu(vyhernyTeam2);
                                Utilitky.vypis("team "+ vyhernyTeam2.getNazov()+" hrac: "+bestPlayer.getCeleMeno()+" pocet bodov: "+((GoalAble)bestPlayer).getBody());
                                System.out.println("2-continue");
                                int input2 = 0;
                                do{ input2 = Utilitky.inputInt(); } while(input2 != 2);
                                input1 = input2;
                                break;
                            case 2:
                                break;
                            default:
                                System.out.println("zadal si zle cislo");
                        }
                        if(input1==2){
                            break;
                        }
                    }
                    vyhernyTeam2.resetSkore();
                    Utilitky.pauzaSek();
                    Utilitky.clearConsoleNormal();
                    System.out.println("Vyherny zapas:");
                    Utilitky.space(1);
                    System.out.println(vyhernyTeam1.getNazov()+" vs "+vyhernyTeam2.getNazov());
                    Team finalVyhernyTeam = Zapas.celyZapas(vyhernyTeam1,vyhernyTeam2); // prvý zapas 
                     while(true){
                        Utilitky.space(1);
                        System.out.println("1-ukaz najlepšieho hrača celkovo, 2-najlepsi hraci zo vsetkych teamov, 3-end");
                        Utilitky.space(1);
                        int input1 = Utilitky.inputInt();
                        switch(input1){
                            case 1:
                                Hrac bestPlayer = Zapas.bestHracTeamu(finalVyhernyTeam);
                                Utilitky.vypis("team "+ finalVyhernyTeam.getNazov()+", hrac: "+bestPlayer.getCeleMeno()+", pocet bodov: "+((GoalAble)bestPlayer).getBody());
                                break;
                            case 2:
                                System.out.println(Zapas.bestHracizTeamov(ihrisko));
                                break;
                            case 3:
                                break;
                            default:
                                System.out.println("zadal si zle cislo");
                        }
                        if(input1==3){
                            break;
                        }
                    }
                    break; //---------------------------------------------------------------------prva cast// hraj---------------------------------------
                
                case 2:
                    Ihrisko ihrisko1 = Utilitky.loadIhrisko();
                    while(true){
                        Utilitky.clearConsoleNormal();
                        System.out.println("Vytvorenie teamu");
                        System.out.println("1-continue, 2- nacitaj defaultne teamy, 3-back to menu");
                        int input2 = Utilitky.inputInt();
                        switch(input2){
                            case 1:
                                Team novyTeam = Team.novyTeam();
                                System.out.println(novyTeam.vypisVsetkychHracov());
                                System.out.println("1-uloz team, 2-zahod team a back to menu");
                                while(true){
                                    int input1 = Utilitky.inputInt();
                                    System.out.println("1-uloz team, 2-zahod team a vrati spat");
                                    switch(input1){
                                        case 1:
                                            Utilitky.space(1);
                                            System.out.println("Vyber team ktory chces nahradit :");
                                            System.out.println(ihrisko1.vypisTeamov());
                                            Team[] pole = ihrisko1.getPole();
                                            while(true){
                                                System.out.println("Vyber team ktory chces nahradit :");
                                                int inputTeam = Utilitky.inputInt();
                                                if(inputTeam>=1 && inputTeam<=4){
                                                    pole[inputTeam-1] = novyTeam;
                                                    Utilitky.saveIhrisko(ihrisko1);
                                                    System.out.println("team pridany!");
                                                    Utilitky.pauzaSek();
                                                    Utilitky.pauzaSek();
                                                    input1 = 2;
                                                    break;
                                                }
                                                else{
                                                    System.out.println("zadal si zle cislo");
                                                }
                                            }
                                            break;
                                        case 2:
                                            novyTeam = null;
                                            System.out.println("team vymazany");
                                            Utilitky.pauzaSek();
                                            input1 = 2;
                                            break;
                                        default:
                                            System.out.println("zadal si zle cislo");
                                    }
                                    if(input1 == 2){
                                    break;
                                    }
                                }
                                break;
                            case 2:
                                VytvorTeam.loadDefaulTeam();
                                System.out.print("boli nacitane");
                                Utilitky.pauzaSek();
                                Utilitky.pauzaSek();
                                Utilitky.pauzaSek();
                                input2 = 3;
                                break;
                            case 3:
                                break;
                            default:
                                System.out.println("zadal si zle cislo");
                                break;
                            }
                        if(input2 == 3){
                            break;
                        }
                    }
                    break;//-----------------------------------Vytvor hraca-------------------------------------------------------
                case 3:
                    Ihrisko ihrisko2 = Utilitky.loadIhrisko();
                    System.out.println("vyber team ktory chces vypisat: ");
                    Utilitky.space(1);
                    System.out.println(ihrisko2.vypisTeamov());
                    int inputVyberTeamu = Utilitky.inputInt();
                    Team[] pole = ihrisko2.getPole();
                    if(inputVyberTeamu>=1 && inputVyberTeamu<=4){
                        System.out.println(pole[inputVyberTeamu-1].vypisVsetkychHracov());
                    }
                    else{
                        System.out.println("zadal si zle vraciam spat do menu");
                        Utilitky.pauzaSek();
                        Utilitky.pauzaSek();
                        break;
                    }
                    Utilitky.pauzaSek();
                    System.out.println("1-back to menu");
                    while(true){
                        int inputEnd = Utilitky.inputInt();
                        if(inputEnd==1){
                            break;
                        }
                        else{
                            System.out.println("zadal si zle skus znova");
                            continue;
                        }
                    }
                    break;//----------------------------------------Vypis hracov------------------------------------------------------
                case 4: 
                    System.out.println("vypinam");
                    break;
                default:
                    System.out.println("zadal si zle cislo");
            }
            if(input == 4){
                break;
            }
        }
    }
}
