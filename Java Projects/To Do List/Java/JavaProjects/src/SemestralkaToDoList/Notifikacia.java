package SemestralkaToDoList;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;


/**
 * tato trieda mi bude vytvarať notifikacie pomocou zdedenej triedy TimerTask
 * */
public class Notifikacia extends TimerTask {
	
	private Uloha uloha;
	private Timer timer;
	private String id;
	private Calendar datumCas;

	
	public Notifikacia(Uloha uloha) {
		this.timer = new Timer();
		this.uloha = uloha;
		this.id = uloha.getID();
		this.datumCas = uloha.getDatumCas();
		
		zapniNotifikaciu();
	}
	
	@Override
	/** toto implementovana metoda od TimerTask 
	 * ked nastane dany cas tato kod v tejto metoda sa vykona
	 * t.j. spusti mi hudbu a a JOpitonPane mi vypise datum a zadanie
	 * ulohy //vlakno pouziva tato trieda
	 * **/
	public void run() {
		
		if(!uloha.getNotifikaciaSound().equals(NotifikacieSounds.DEFAULT)) {
			uloha.getNotifikaciaSound().playSound();
		}
		
		JOptionPane.showInternalMessageDialog(null, uloha.getSprava()+" "+uloha.getSimpleDate(),
				"information", JOptionPane.INFORMATION_MESSAGE);
	}
	
	
	/** tato metoda mi spravi notifikaciu **/
	private void zapniNotifikaciu() {
		
		if(uloha!=null) {
			timer.schedule(this,uloha.getDatumCas().getTime());
		}
	}
	
	public String getID() {
		return id;
	}
	
	public Calendar getDatumCas() {
		return datumCas;
	}
	
	
	
	

}
