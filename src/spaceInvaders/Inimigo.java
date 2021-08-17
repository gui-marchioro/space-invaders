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

public class Inimigo extends Atributos {
		
	private BufferedImage imageInimigo;
	
	public Inimigo(int x, int y){	
		super(x,y);
		
		try {
			imageInimigo = ImageIO.read(new File("Imagens/nave2.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Erro de abertura de imagem do inimigo");
			e.printStackTrace();
		}
	}
	
	public void print(Graphics2D g) {
	
		g.drawImage(imageInimigo, getX(), getY(), getX()+70, getY()+70, 0, 0, imageInimigo.getWidth(),imageInimigo.getHeight(),null);

}
	
	public void movimentaInimigo(){ // Desce a posição dos inimigos
		
		setY(getY()+10);
	}
	
	public Tiro atirar() {
		
		Tiro tiro = new Tiro(getX() + 33, getY()); // 
		return tiro;
	}

	
}