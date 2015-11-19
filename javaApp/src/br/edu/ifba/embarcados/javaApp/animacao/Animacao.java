package br.edu.ifba.embarcados.javaApp.animacao;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Animacao extends JFrame{
	private static final long serialVersionUID = 1L;
	BufferedImage backBuffer; 
	int FPS = 10;
	Sprite sprt_bussola = new Sprite(8,0,0);
	ImageIcon fundo = new ImageIcon("imagens/fundo.png");
	
	/*public Animacao(int g){
		graus = g;
	}
	*/
	
	public int verificaPosicao(int graus){
		int saida = 0;
		//if(graus < 0)//if((graus < 0)|| (graus > 360))
		//	saida = -1;
		//else{
			if (graus < 45 && graus > 0){
	            saida = 1;//direcao = "Nordeste";
			} else if (graus  <= 90 && graus > 45){
	            saida = 2;//direcao = "Leste";
			} else if (graus  <= 135 && graus > 90){
	            saida = 3;//direcao = "Sudeste";
			} else if (graus  <= 180 && graus > 135){ 
	            saida = 4;//direcao = "Sul";
			} else if (graus  <= 225 && graus > 180){ 
	            saida = 5;//direcao = "Sudoeste";
			} else if (graus  <= 270 && graus > 225){
	            saida = 6; //direcao = "Oeste";
			} else if (graus  <= 315 && graus > 270){
	            saida = 7;//direcao = "Noroeste"; 
			} else if (graus  <= 360 && graus > 315){
	            saida = 0;//direcao = "Norte";       
			}
		//}
		return saida;
	}
	
	public void desenharJanela(int posicao){
		Graphics g = getGraphics();
		Graphics bbg = backBuffer.getGraphics();
		bbg.drawImage(fundo.getImage(),0,0,this);
		bbg.drawImage(sprt_bussola.imagens[posicao].getImage(), sprt_bussola.x, sprt_bussola.y, this);
		sprt_bussola.animar();
		g.drawImage(backBuffer, 0, 0, this); //fim	
	}
	
	public void	inicializacao(){
		setTitle("Bussola Digital - Magnetometro");
		setSize(500, 500);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	
		backBuffer = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
		sprt_bussola.imagens[0] = new ImageIcon("imagens/Norte.png");
		sprt_bussola.imagens[1] = new ImageIcon("imagens/Nordeste.png");
		sprt_bussola.imagens[2] = new ImageIcon("imagens/Leste.png");
		sprt_bussola.imagens[3] = new ImageIcon("imagens/Sudeste.png");
		sprt_bussola.imagens[4] = new ImageIcon("imagens/Sul.png");
		sprt_bussola.imagens[5] = new ImageIcon("imagens/Sudoeste.png");
		sprt_bussola.imagens[6] = new ImageIcon("imagens/Oeste.png");
		sprt_bussola.imagens[7] = new ImageIcon("imagens/Noroeste.png");
	}
	
	public void executar(int g){
		inicializacao();
		System.out.println("Desenhando -  graus = "+g);
		desenharJanela(verificaPosicao(g));
	}
}	
