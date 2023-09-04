package sisdis.example;

public class GeradorRelatorio implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i=0; i< 50; i++) {
			System.out.println("Linha: "+i);
		}
	}

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.out.println("Inicio da Aplicacao");
		
		//Criacao do objeto executavel
		GeradorRelatorio relatorio = new GeradorRelatorio();
		BarraDeProgresso barra = new BarraDeProgresso();
		
		//Criacao da Thread
		Thread threadRelatorio = new Thread(relatorio);
		Thread threadBarra = new Thread(barra);
		
		//Inicio da execucao da Thread
		threadRelatorio.start();
		threadBarra.start();
		
		//Faca o teste, execute com as duas linhas abaixo comentadas,
		//depois, remova os comentarios e rode o programa novamente,
		//compare as saidas!
		threadRelatorio.join();
		threadBarra.join();
		
		System.out.println("Fim da Aplicacao");
	}

}
