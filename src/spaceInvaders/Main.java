/**
 * Guilherme Marchioro
 * Marcelo Ferreira 
 * Pedro Machado
 */
package spaceInvaders;

import javax.swing.JFrame;

public class Main {
	
	//Resolução:
    public static final int WIDTH = 1366;
    public static final int HEIGHT = 720;
	
	public static void main(String[] args){

		JFrame janela = new JFrame("Space Invaders");//Janela do jogo
		SpaceInvaders si = new SpaceInvaders();//Painel que roda o jogo

		si.setBounds(0, 0, WIDTH, HEIGHT);//Resolução do painel
		
        janela.setSize(WIDTH,HEIGHT);//Resolução da janela
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Aplicação para quando é fechada   
        janela.setLocationRelativeTo(null);//Abre a janela no meio da tela
        janela.setLayout(null);
        janela.setVisible(true);
        
        janela.getContentPane().add(si);//Adiciona o painel do jogo na janela
        janela.addKeyListener(si);//Adiciona o KeyListener do jogo
        
		
	}

}
