/*
 * Extern.cpp
 *
 * Externalizacao das rotinas de acesso
 * ao acelerometro
 *
 *  Created on: 15/10/2015
 *      Author: Luis Paulo
 */
#include "Extern.h"
#include "Comunicacao.h"

short graus;

Comunicacao com = NULL;

int iniciar(char* porta) {
	com = Comunicacao(porta);
	return com.iniciar();
}

int ler() {
	char ci, cf;
	// realizar a leitura do caractere 'I' (Inicio)
	int resultado = com.ler((char*) &ci, sizeof(ci));

	if ((resultado == EXIT_SUCCESS) && (ci == 'I')) {
		// se a leitura de 'I' ocorrer bem, ler os eixos
		resultado = com.ler((char*)&graus, sizeof(graus));
		//se a leitura dos eixos ocorrer bem, le o caractere 'F' (Final)
		if (resultado == EXIT_SUCCESS) {
			resultado = com.ler((char*)&cf, sizeof(cf));
			if ((resultado == EXIT_SUCCESS) && (cf == 'F')) {
				resultado = EXIT_SUCCESS;
			}
		}
	}
	return resultado;
}

int getGraus(){
	return graus;
}
int finalizar() {
	return com.finalizar();
}
