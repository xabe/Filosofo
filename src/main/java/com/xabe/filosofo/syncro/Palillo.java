package com.xabe.filosofo.syncro;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Palillo {

	private static final Logger LOGGER = LoggerFactory.getLogger(Palillo.class);
	private int posicionFilosofo;
	private boolean enUso;

	public Palillo(int posicionFilosofo) {
		this.posicionFilosofo = posicionFilosofo;
	}
	
	public synchronized void usar() {
		if (enUso) 
		{
			LOGGER.info("El palillo " + posicionFilosofo+ " esta en uso, espera");
		} 
		else 
		{
			enUso = true;
			LOGGER.info("Se esta usando el palillo " + posicionFilosofo);
		}
	}

	public synchronized void dejar() {
		enUso = false;
		LOGGER.info("el palillo " + posicionFilosofo + " esta ahora disponible");
	}

}
