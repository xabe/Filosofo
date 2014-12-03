package com.xabe.filosofo.semaphore;

import java.util.concurrent.Semaphore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Cinco filósofos alrededor de una mesa pasan su vida comiendo o pensando. 
 * Cada filósofo tiene un plato de arroz y un palillo a la izquierda de su plato. 
 * Cuando un filósofo quiere comer, cogerá los dos palillos de cada lado del plato y comerá. 
 * @author Chabir Atrahouch
 *
 */
public class Main {

	private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
	
	public static void main(String[] args) {

		int totalFilosofos = 5;
		
		Semaphore [] palillos = new Semaphore[totalFilosofos];
		
		int i;
		
		for (i = 0; i < totalFilosofos; i++) {
			palillos[i] = new Semaphore(1);
        }
		LOGGER.info("Credo el array de semaforos");
		
		
		LOGGER.info("Credo el array de filósofos");
		for (i = 0; i < totalFilosofos; i++) {
           new Thread(new Filosofo(i, totalFilosofos, palillos)).start();
        }
	}

}
