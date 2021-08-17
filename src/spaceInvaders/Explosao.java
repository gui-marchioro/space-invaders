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

public class Explosao extends Atributos {
	
private BufferedImage image;
private int x;//variavel que controla o deslocamento da imagem
private int tam;//tamanho da imagem
	
	public Explosao(int x, int y){	
		super(x,y);
		x=0;
		tam=128;
		
		
		try {
			image = ImageIO.read(new File("Imagens/explosao.png"));//Abertura da imagem
		} catch (IOException e) {
			System.out.println("Erro de abertura de imagem do inimigo");
			e.printStackTrace();
		}
	}
	
	public void print(Graphics2D g) {//printa a explosao
	
		g.drawImage(image, getX()-70, getY()-70, getX()+tam, getY()+tam, x, 0, x+tam,image.getHeight(),null);
		x+=128;//vai deslocando a imagem para direita a cada frame
}
}
