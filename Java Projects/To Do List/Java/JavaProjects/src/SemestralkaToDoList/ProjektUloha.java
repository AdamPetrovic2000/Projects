package SemestralkaToDoList;

import java.io.Serializable;
import java.util.Calendar;


/** tato metoda bude dedit od ulohy ale nebude mat vyuzite urcite jej vlastostnosti 
 * a bude pridana o atributy x,y ktore mi budu udavať suradnice pre projekt 
 *  
 *  **/
public class ProjektUloha extends Uloha implements Serializable {
	
	private int x;
	private int y;
	
	
	public ProjektUloha(String sprava, Calendar datumCas,int x, int y) {
		super(sprava,datumCas,0,false,null);
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setXandY(int x, int y) {
		this.x = x;
		this.y = y;
	}

}
