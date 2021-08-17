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

public class Nave extends Atributos{
	
	private BufferedImage image;
	private int velocidade;	//velocidade da nave
	private boolean delayTiro;  //delay e tempo do tiro ajudam a minimizar o número
	private int tempoTiro;			  //de tiros por segundo que podem ser desparados
	
	
	public Nave(int x, int y) {//Construtor abre a imagem da nave
				
		super(x,y);
		velocidade = 3;
		tempoTiro = 0;
		delayTiro = true;
		
		try {
			image = ImageIO.read(new File("Imagens/nave.png"));//Abertura da imagem
		} catch (IOException e) {
			System.out.println("Erro de abertura de imagem da nave");
			e.printStackTrace();
		}
		
		
		
	}
	public Tiro atirar() {//Função que define o tiro da nave
		
		delayTiro = false;
		Tiro tiro = new Tiro(getX()+25, 540);
		return tiro;
	}

	public void print(Graphics2D g) {//Printa a nave
				   //img,pos_x,pos_y,tam_x,tam_y,        img_inteira
		g.drawImage(image, getX(), getY(), getX()+60, getY()+60, 0, 0, image.getWidth(),image.getHeight(),null);
		
	}
	
	public void move(int mov) {//Movimentacao, recebe -1 ou 1 do keylistener
		if(mov==1) { //Move direita
			if(getX()<=1297) // Testa se a nave atingiu o limite direito da tela       
			setX(getX() + velocidade);
		}
		else if(mov==-1) { //Move esquerda
			if(getX()>=-2) // Testa se a nave atingiu o limite esquerdo da tela		
			setX(getX()- velocidade);
		}
			
		if(tempoTiro >= 20) { // Controla a quantidade de tiro da nave
			delayTiro = true;
			tempoTiro = 0;
		}
		
		tempoTiro++;
	}
	
	public boolean delayTiro(){
		
		return delayTiro; 
		
	}

}
