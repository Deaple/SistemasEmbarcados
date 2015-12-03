package br.edu.ifba.embarcados.javaApp;

import br.edu.ifba.embarcados.javaApp.asincexec.IListenerMagnetometro;

// FIXME seria a classe grafica (de exibicao da bussola) que deveria implementar o listener
public class ListenerMagnetometro implements IListenerMagnetometro{
	
	@Override
	public void notificarMovimentoMag(int graus){
		System.out.print("graus = "+graus+" Direcao = ");
	}

	@Override
	public String notificaDirecao(int graus) {

		String direcao = "Norte";	
		if (graus <= 45 && graus >= 0){
            direcao = "Nordeste";
		} else if (graus  <= 90 && graus > 45){
            direcao = "Leste";
		} else if (graus  <= 135 && graus > 90){
            direcao = "Sudeste";
		} else if (graus  <= 180 && graus > 135){ 
            direcao = "Sul";
		} else if (graus  <= 225 && graus > 180){ 
            direcao = "Sudoeste";
		} else if (graus  <= 270 && graus > 225){
            direcao = "Oeste";
		} else if (graus  <= 315 && graus > 270){
            direcao = "Noroeste"; 
		} else if ((graus  <= 360 && graus > 315)||(graus >= 0 && graus < 40)){
            direcao = "Norte";       
		} 
		return direcao;
	}

}
