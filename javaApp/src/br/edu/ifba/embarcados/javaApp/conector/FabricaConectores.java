package br.edu.ifba.embarcados.javaApp.conector;

import com.sun.jna.Native;
import com.sun.jna.Platform;

public class FabricaConectores {
	//deve saber se win ou linux
	public static IComunicacaoSensores getConector(){
		IComunicacaoSensores conector = null;
		
		if (Platform.isWindows()){
			conector = (IComunicacaoSensores)Native.loadLibrary("comunicacaosensores.dll", IComunicacaoSensores.class);
		
		}else if (Platform.isLinux()){
			conector = (IComunicacaoSensores)Native.loadLibrary("comunicacaosensores.so", IComunicacaoSensores.class);
			
		}
		return conector;
	}


}
