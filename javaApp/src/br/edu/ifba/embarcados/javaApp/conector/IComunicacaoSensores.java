package br.edu.ifba.embarcados.javaApp.conector;

import com.sun.jna.Library;

public interface IComunicacaoSensores extends Library{

	public int iniciar(String porta);
	public int ler();
	int getGraus();
	public int finalizar();
}
