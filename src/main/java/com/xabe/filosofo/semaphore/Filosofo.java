package com.xabe.filosofo.semaphore;

import java.util.concurrent.Semaphore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Filosofo implements Runnable {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(Filosofo.class);

	private int posicionFilosofo;
	private int totalFilosofos;
	private Semaphore[] palillos;

	public Filosofo(int posicionFilosofo, int totalFilosofos,Semaphore[] palillos) {
		this.totalFilosofos = totalFilosofos;
		this.posicionFilosofo = posicionFilosofo;
		this.palillos = palillos;
	}

	private void comer(){
		//Primero cogemos el palillo de la izquierda
		if(palillos[getPalilloIzquierda()].tryAcquire())
		{
			//comprobamos que el palillo de la derecha 
			if(palillos[getPalilloDerecha()].tryAcquire())
			{
				
                try 
                {
                    //Simulamos un tiempo para tarda de comer
                	LOGGER.info("El filósofo " + posicionFilosofo + " está comiendo.");
                    Thread.sleep((int)(Math.random()*1000));
                } catch (InterruptedException e) {
                    LOGGER.error("Error para comer el filosofo "+posicionFilosofo+" : " + e.getMessage(),e);
                }
                LOGGER.info("El filósofo " + posicionFilosofo + " ha terminado de comer");
                LOGGER.info("\tLibera los palillos " + getPalilloIzquierda() + " y " + getPalilloDerecha());
                // liberamos el de la derecha
                palillos[getPalilloDerecha()].release();
            }
            // liberamos el de la izquierda
			palillos[getPalilloIzquierda()].release();
		}
		else
		{
			LOGGER.info("El filosofo "+posicionFilosofo+" esta hambriento");
		}
	}
	
	
	private void pensar(){
		try
		{
			LOGGER.info("El filósofo " + posicionFilosofo + " está pensado.");
            Thread.sleep((int)(Math.random()*1000));
		}catch(InterruptedException e){
			  LOGGER.error("Error para pensar el filosofo "+posicionFilosofo+" : " + e.getMessage(),e);
		}
	}
	
	public void run() {
		while(true){
			pensar();
			comer();
		}
	}

	public int getPalilloDerecha() {
		return (posicionFilosofo + 1) % totalFilosofos;
	}

	public int getPalilloIzquierda() {
		return posicionFilosofo;
	}

}
