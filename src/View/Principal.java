package View;

import java.util.concurrent.Semaphore;

import Controller.Bilheteria;

public class Principal {

	public static void main(String[] args) {
		Semaphore semaforo = new Semaphore(1);
		for (int i = 0; i < 300; i++) {
			Bilheteria show = new Bilheteria (i, semaforo);
			show.start();
			
					}
		}

}
