package br.edu.ifba.embarcados.javaApp.asincexec;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifba.embarcados.javaApp.animacao.Animacao;
import br.edu.ifba.embarcados.javaApp.conector.FabricaConectores;
import br.edu.ifba.embarcados.javaApp.conector.IComunicacaoSensores;

public class AsincExec implements Runnable {
	//Método com interface runnable
	private String porta;
	private boolean continuar;
	Animacao anim = new Animacao();
	
	private List<IListenerMagnetometro> listeners;
	
	public void addListener(IListenerMagnetometro listener){
		listeners.add(listener);
	}
	
	public AsincExec(String porta){
		this.porta=porta;
		listeners = new ArrayList<IListenerMagnetometro>();
	}
	
	public void setContinuar(boolean continuar){
		this.continuar = continuar;
	}
	
	@Override
	public void run(){
		IComunicacaoSensores conector = FabricaConectores.getConector();
		if (conector.iniciar(porta)==0){
			continuar = true;
			
			while(continuar){
				conector.ler();
				try {
					notificar(conector.getGraus());
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			conector.finalizar();
		}
	}
	
	private void notificar(int graus) throws InterruptedException{
		for(IListenerMagnetometro listener : listeners){
			if(graus>360){
				Thread.sleep(500);
				System.out.println("Valor acima de 360! graus = "+graus);
			}	
			else{
				// FIXME a tela de animacao deveria implementar o listener!!!
				listener.notificarMovimentoMag(graus);
				System.out.println(listener.notificaDirecao(graus));
				anim.executar(graus);
			}
		}
	}
}
