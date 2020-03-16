package it.polito.tpd.indovinanumero.model;

import java.security.InvalidParameterException;
import java.util.*;

public class Model {

	private final int NMAX = 100;
	private final int TMAX = 8;
	private int segreto;
	private int tentativiFatti;
	private boolean inGioco;
	
	private Set<Integer> tentativi;
	
	public Model() {

		this.tentativiFatti = 0;
		this.inGioco  = false;
	}
	
	public void novaPartita() {
		//gestione dell'inizio di una nuova partita - Logica del gioco
    	this.segreto = (int)(Math.random() * NMAX) + 1;
    	this.tentativiFatti = 0;
    	this.inGioco = true; 
    	this.tentativi = new HashSet<Integer>();
	}
	
	public int tentativo(int tentativo) {
		//controllo se la partita è in corso
		if(!inGioco) {
			throw new IllegalStateException("La partita e' gia' terminata!\n");
		}
		
		//controllo se il tenatativo sia valido (compreso nell'intervallo)
		if(!tentativoValido(tentativo)) {
			throw new InvalidParameterException("Devi inserire un numero che non hai ancora provato tra 1 e "+NMAX+"\n");
		}
		
		//se arriviamo qui, il tentativo e' valido
    	this.tentativiFatti ++;
 

    	this.tentativi.add(tentativo);
    	
    	if(this.tentativiFatti==TMAX) {
    		this.inGioco=false;
 
    	}
		
    	if(tentativo == this.segreto) {
    		this.inGioco = false;
    		return 0;
    	}
    	
    	if(tentativo<this.segreto) {
    		return -1;
    	}else {
    		return 1;
    	}
	}

	private boolean tentativoValido(int tentativo) {
		if(tentativo<1 || tentativo>NMAX) {
			return false;
		}else {
			if(this.tentativi.contains(tentativo)) {
	    		return false;
	    	}
		}
		return true;
	}

	public int getTentativiFatti() {
		return tentativiFatti;
	}

	public int getTMAX() {
		// TODO Auto-generated method stub
		return TMAX;
	}
	
	
}
