/**
 * Guilherme Marchioro
 * Marcelo Ferreira 
 * Pedro Machado
 */
package spaceInvaders;

import java.awt.Color;
import java.awt.Graphics2D;

public class Tiro extends Atributos {
		
	private int velocidade; //Quanto em y que o tiro sobe cada vez que o loop do jogo é percorrido
	
	public Tiro(int x, int y) {
		
		super(x,y);
		velocidade  = 5;
	}
	
	public void print(Graphics2D g) { // Printa o tiro
		setY(getY()- velocidade);						
		g.setColor(Color.PINK);
		g.fillRect(getX()-5, getY()+20, 4, 25);
		g.fillRect(getX()+5, getY()+20, 4, 25);
	}
	public void printInimigos(Graphics2D g) { // Printa o tiro
		setY(getY() + velocidade);						
		g.setColor(Color.GREEN);
		g.fillRect(getX()-5, getY()+20, 4, 25);
		g.fillRect(getX()+5, getY()+20, 4, 25);
	}
}
