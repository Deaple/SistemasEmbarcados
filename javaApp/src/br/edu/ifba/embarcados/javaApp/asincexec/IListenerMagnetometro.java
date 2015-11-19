package br.edu.ifba.embarcados.javaApp.asincexec;

public interface IListenerMagnetometro {
	public void notificarMovimentoMag(int graus);
	public String notificaDirecao(int graus);
}
