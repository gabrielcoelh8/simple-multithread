package sisdis.example;

public class BarraDeProgresso implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i=0; i< 20; i++) {
			System.out.println("== Barra: "+i);
		}
	}
}
