package br.edu.ifba.embarcados.javaApp.animacao;

import javax.swing.ImageIcon;

public class Sprite {
	ImageIcon imagens[]; //vetor que armazena os sprites
	int x = 0;
	int y = 0;
	int imagem = 0;
	int controlaVelocidade = 0;
	int velocidade = 5;
	
	public Sprite(int numeroImagens, int x,int y){
		imagens = new ImageIcon[numeroImagens];
		this.x = x;
		this.y = y;
	}
	
	public void animar(){
		imagem += 1;
		if(imagem == imagens.length){ 
			imagem = 0; 
		}
	}
}
