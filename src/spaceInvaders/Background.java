/**
 * Guilherme Marchioro
 * Marcelo Ferreira 
 * Pedro Machado
 */
package spaceInvaders;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Background {
	private BufferedImage image;
	private int x,y,velocidade;//coordenadas de inicio da imagem e velocidade de movimento
	private int timer=0;//contador para auxiliar na atualização da imagem
	
	public Background () {
		
		x=-300;
		y=-200;
		velocidade = 1;
		
		try {
			image = ImageIO.read(new File("Imagens/terra1.jpg"));//Abertura da imagem
		} catch (IOException e) {
			System.out.println("Erro abertura da imagem");
			e.printStackTrace();
		}
	}
	public void print(Graphics2D g) {//Printa o Background
					//img,posx,posy,tamx,tamy,        img_inteira
		g.drawImage(image, x, y,x+2000, y+1150, 0, 0, image.getWidth(),image.getHeight(),null);

	}

	public void att() {//atualiza o estado do background
		
		if(timer <=300) {//durante 6 segundos a imagem se desloca para esquerda
			x-=velocidade;
		}
		else if(timer>305 && timer<605) {//durante 6 segundos a imagem se desloca para direita
			x+=velocidade;
			
		}
		if(timer>610) {//reinicia o ciclo
			timer=0;
		}
		timer++;
	}

}
