/*
 * Executor.cpp

 *Testa a comunicacao com o arduino, retornando
 *e exibinindo valores dos eixos do acelerometro
 *Created on: 15 de out de 2015
 *Author: Isaac
 *Este arquivo não é utilizado na versão Release do software(pois nesta versão é gerada a biblioteca compartilhada)
 */

//

#include <iostream>
#include "Comunicacao.h"

using namespace std;
short graus;

int main(int argc, char **argv) {
	//criar uma instancia da classe de comunicaao
	Comunicacao com = Comunicacao((char*)"COM3");
	if (com.iniciar() == EXIT_SUCCESS) {
		char ci, cf; //
		//Eixos eixos; //removi para usar a conf do Mag
		graus = 0;
		while (true) {
			//realizar a leitura do caractere I (Inicio)
			int resultado = com.ler((char*) &ci, sizeof(ci));
			if ((resultado == EXIT_SUCCESS) && (ci == 'I')) {
				//se a leitura dos eixos ocorrer bem
				//ler o caractere 'F' no final
				resultado = com.ler((char*) &graus, sizeof(graus));
				if (resultado == EXIT_SUCCESS) {
					resultado = com.ler((char*) &cf, sizeof(cf));
					if ((resultado == EXIT_SUCCESS) && (cf == 'F')) {
						cout<<"Graus = "<<graus<<endl;
					}
				}
			}
			Sleep(100); //mudanca de 100 para 50 ms, a leitura dos graus estava com delay alto demais
		}
	}
	return EXIT_SUCCESS;
}
