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


public class Logo {

	private BufferedImage image;
	private int x,y,tamX,tamY;

	public Logo () {
		x=430;
		y=100;
		tamX=500;
		tamY=250;

			
		try {
			image = ImageIO.read(new File("Imagens/logo.png"));
		} catch (IOException e) {
			System.out.println("Erro abertura da imagem");
			e.printStackTrace();
		}
	}
	public void print(Graphics2D g) {//Printa a nave
		//img,posx,posy,tamx,tamy,        img_inteira
		g.drawImage(image, x, y,x+tamX, y+tamY, 0, 0, image.getWidth(),image.getHeight(),null);
	}
}
