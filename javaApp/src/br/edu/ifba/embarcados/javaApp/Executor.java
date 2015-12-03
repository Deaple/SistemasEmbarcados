package br.edu.ifba.embarcados.javaApp;

import br.edu.ifba.embarcados.javaApp.asincexec.AsincExec;

public class Executor{

	public static void main(String[] args) throws InterruptedException {
		
		ListenerMagnetometro listener = new ListenerMagnetometro();
		AsincExec asinc = new AsincExec("COM3");
		asinc.addListener(listener); //notificacao do listener
				
		Thread t1 = new Thread(asinc);
		
		t1.start();
		
		for(int i=0;i<5000;i++)
			Thread.sleep(500);
			
		// FIXME faltou o asinc.setContinuar(false) para parar a thread
		
		t1.join();
	}
}
