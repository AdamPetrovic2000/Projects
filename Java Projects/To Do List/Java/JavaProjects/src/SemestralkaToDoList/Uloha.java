package SemestralkaToDoList;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.UUID;
/**
 *  Tato trieda mi uroby spravi objekt, z ktorého budem brat udaje a vykreslovat v GUI
 */

public class Uloha implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String sprava ;
	private int prioritaUlohy;
	private Calendar datumCas;
	private UUID ID;
	private boolean upozornenie;
	private NotifikacieSounds notifikaciaSound;
	private boolean isChecked;
	private boolean isNotExpired;

	
	public Uloha() {
		this.sprava = "idem";
		
	}
	
	/** konštruktor **/
	public Uloha(String sprava, Calendar datumCas, int priorita, boolean upozornenie, NotifikacieSounds notifikaciaSound) {	
		this.ID = UUID.randomUUID();
		this.sprava = sprava;
		this.prioritaUlohy = priorita;
		this.datumCas = datumCas;
		this.upozornenie = upozornenie;
		this.notifikaciaSound = notifikaciaSound;
		this.isChecked = false;
		this.isNotExpired = true;
		
	}
	public String getSprava() {
		return sprava;
	}
	
	public Calendar getDatumCas() {
		return datumCas;
	}
	
	public String getID() {
		return ID.toString();
	}
	
	public int getPrioritaUlohy() {
		return prioritaUlohy;
	}
	public NotifikacieSounds getNotifikaciaSound() {
		return notifikaciaSound;
	}
	public boolean getUpozornenie() {
		return upozornenie;
	}
	public boolean getIsChecked() {
		return isChecked;
	}
	public void setIsChecked(boolean logHodnota) {
		this.isChecked = logHodnota;
	}
	/** tato metoda mi vrati krajsi datum pomocou triedy SimpleDate */ 
	public String getSimpleDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH:mm");
		String krajsiDatum = dateFormat.format(datumCas.getTime());
		return krajsiDatum;
		
	}
	/** tato metoda mi prida k sprave ze je EXPIRED */
	public void setSpravaToExpired() {
		this.sprava = this.sprava+" "+"- EXPIRED";
		setExpiredToFalse();
	}
	
	public void setExpiredToFalse() {
		this.isNotExpired = false;
	}
	public boolean getisNotExpired() {
		return isNotExpired;
	}
	public void setPriorita(int cisloPriority) {
		this.prioritaUlohy = cisloPriority;
	}
	/** tato metoda mi vrati data pre export do txt **/
	public String getDataForExport() {
		if(isChecked)
			return sprava+", "+getSimpleDate()+", Splnena"+", "+prioritaToString(getPrioritaUlohy());
		else {
			return sprava+", "+getSimpleDate()+", Nesplnena"+", "+prioritaToString(getPrioritaUlohy());
		}
	}
	/** tato metoda mi premeni prioritu na stringovu podobu **/
	public String prioritaToString(int priorita) {
		switch (priorita) {
		case 1: 
			return "Veľmi doležite";
		case 2:
			return "Doležite";
		default:
			return "Menej doležite";
		}
		
	}
}
