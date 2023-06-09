package Controller;

import java.util.concurrent.Semaphore;

public class Bilheteria extends Thread {
	public static int quantTotIngressos = 100; 
	private int idpessoa;
	private int templogin;
	private int tempcompra;
	private int quantingressos;
	private Semaphore semaforo;
	
	public Bilheteria (int idpessoa, Semaphore semaforo) {
		this.idpessoa = idpessoa;
		this.templogin = 0;
		this.tempcompra = 0;
		this.quantingressos = 0;
		this.semaforo = semaforo;
		
	}
	public void run() {
		if(Login()) {
			if(Compra()) {
			try {
				semaforo.acquire();
				ValidaCompra();
			}catch (InterruptedException e) {
			 e.printStackTrace();
			}finally {
				semaforo.release();
			}
		}
		
		}
	}
	public boolean Login() {
		this.templogin = aleatorio(500,2001);
		
		try {
			sleep(this.templogin);
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if(this.templogin >= 1000) {
			System.out.println("O usuario " +idpessoa + " recebeu timeout ao tentar logar após " + this.templogin +" ms");
			return false;
		}else {
			System.out.println("O usuario " +idpessoa + " conseguiu fazer o logar após " + this.templogin +" ms");
			this.quantingressos = aleatorio(1,5);
			return true;
		}
	}
	
	private int aleatorio(int min, int max) {
		int aleatorio = min + (int)(Math.random()*(max -min));
		return aleatorio;
		
	}

	public boolean Compra() {
		this.tempcompra = aleatorio(1000,301);
		
		try {
			sleep(this.tempcompra);
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if(this.templogin >= 2500) {
			System.out.println("O usuario " +idpessoa + " teve a sessão finalizada após" + this.tempcompra +" ms");
			return false;
		}
		
		return true;
	
	}
	
	public boolean ValidaCompra() {
		if(quantTotIngressos >= this.quantingressos) {
			quantTotIngressos = quantTotIngressos - this.quantingressos; 
			System.out.println("O usuario " +idpessoa + " conseguiu efetuar a compra de " +this.quantingressos + "ingressos");
			return true; 
		}else {
			System.out.println("O usuario " +idpessoa + " não conseguiu efetuar a compra de " +this.quantingressos + "ingressos");
			return false;
			
			
		}
	} 
	
	
}
	
	


