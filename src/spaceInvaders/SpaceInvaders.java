/**
 * Guilherme Marchioro
 * Marcelo Ferreira 
 * Pedro Machado
 */
package spaceInvaders;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;


public class SpaceInvaders extends JPanel implements Runnable,KeyListener{
	
	private Font msgFont = new Font("Impact", Font.BOLD, 20);//String do score e do marcador de vidas
	private Font msgFimFont = new Font("Impact", Font.BOLD, 50);//String para o fim do jogo
	private Background bg;//plano de fundo
	private Logo logo;//logotipo space invaders
	private boolean podejogar = false;//condicao para inicio do jogo
	private boolean pause = false;//boolean para pausar
	private int aux = 0;//auxiliar para o menu de pause
	private int vidas; // Vidas do player 
	private Random gerador; // Gera números aleatórios para os testes com os tiros dos inimigos

	private Nave nave;
	private int dir_movimento = 0; // Direção no inicio do jogo(nave parada)
	private int vel_Inimigos = 100;//velocidade com que os inimigos descem
	
	private ArrayList<Tiro> tiros; // Armazena os tiros
	private ArrayList<Tiro> tirosInimigos;// Armazena os tiros dos inimigos
	private ArrayList<Inimigo> inimigos;// Armazena os inimigos
	private ArrayList<Explosao> explosoes;//Armazena as explosoes
	
	private int timer = 0; // incrementa uma variável para controlar o tempo de movimentação dos inimigos
	private int score = 0;//incrementa o score
	
	private Font scoreFont = new Font("Courier New", Font.BOLD, 26); //Fonte para o score
	
	private boolean perdeuJogo = false; //boolean para condicao de vitoria ou derrota
	private boolean ganhouJogo=false;
	private boolean fimJogo=false;//Boolean para o loop do jogo
	private boolean instrucoes=false;//boolean para o menu de instrucoes
	
	Thread loop; // Thread do jogo
	
	public SpaceInvaders () {
		
		gerador = new Random();
	    vidas = 3;//Inicia o jogo com 3 vidas

		nave = new Nave(643,550); //Posição centralizada da nave
		tiros = new ArrayList<Tiro>();
		tirosInimigos = new ArrayList<Tiro>();
		inimigos = new ArrayList<Inimigo>();
		explosoes = new ArrayList<Explosao>();
		loop = new Thread(this);
		bg = new Background();
		logo = new Logo();
		
	for(int x = 0; x < 5; x++) {	//Printa inimigos em 5 linhas e 17 colunas
		for(int i = 0; i < 17; i++) {				
			inimigos.add(new Inimigo((i)*80,(x+1)*60));
		}
	}
		
		loop.start();//inicia o loop do jogo
		
	}

	@Override
	public void run() {

		while(fimJogo==false) {
			
			update(); //atualiza o jogo
			repaint();	//chama o metodo paintComponent
			sleep(); 
			
			
		}
		
		
	}
	
	private void update() { // Método que testa todas as condições e posições 
		
		bg.att(); // Mexe o background
		
		
		if(podejogar==true) {//se pressionar espaço no menu do jogo
			
			timer++;
			nave.move(dir_movimento); // Atualiza a direção de movimento da nave
		
			for(int a = 0; a <inimigos.size(); a++) { // Testa randômicamente quais inimigos podem atirar
				if(gerador.nextInt(10000)>9980) {
					tirosInimigos.add(inimigos.get(a).atirar());
				}
			}
			
			if(inimigos.size() == 0) //Testa se ainda existem inimigos e acaba o jogo
			{
				ganhouJogo = true;
				podejogar = false;
			}
			for(int b = 0; b < tiros.size(); b++) //Remove os tiros que ja saíram do limite do mapa
			{
				if(tiros.get(b).getY()<-60) // Limite superior
					tiros.remove(tiros.get(b)); 
					
			}
			
			for(int c = 0; c < tirosInimigos.size(); c++) //Remove os tiros dos inimigos que ja saíram do limite do mapa
			{
				if(tirosInimigos.get(c).getY() > 620) // Limite inferior do mapa
					tirosInimigos.remove(tirosInimigos.get(c)); 
					
			}
		
			if(timer>=vel_Inimigos) {							  // Utiliza-se da variável timer para controlar
				for(int d = 0; d < inimigos.size(); d++) {// a velocidade que os inimigos descem no mapa			
					inimigos.get(d).movimentaInimigo();
				}
				
					timer = 0; 
				}
			for(int z = 0; z < tirosInimigos.size(); z++) { // Testa sem um tiro inimigo atingiu o player
				
				if(tirosInimigos.get(z).getX() > nave.getX() - 10 && tirosInimigos.get(z).getX()<nave.getX() +60
						&& tirosInimigos.get(z).getY() + 40 == nave.getY()) {
					
					explosoes.add(new Explosao((nave.getX())-10,nave.getY()));//adiciona as explosoes
					
					tirosInimigos.remove(tirosInimigos.get(z));
					vidas--;
					if(vidas == 0) {
						perdeuJogo = true;
						podejogar=false;
					}
				}
			}
			
			for(int i = 0; i < inimigos.size(); i++) {  // Testa se os inimigos passaram do limite do mapa
				
				if(inimigos.get(i).getY() >= 500) {
					perdeuJogo = true;
					podejogar=false;
				}
				
				for(int x = 0; x < tiros.size(); x++) {// Testa as posições dos tiros com as posições
														// dos inimigos
					if(tiros.get(x).getX() >= inimigos.get(i).getX() && tiros.get(x).getX() <= inimigos.get(i).getX() + 70 
						&& tiros.get(x).getY() <= inimigos.get(i).getY() && tiros.get(x).getY() >= inimigos.get(i).getY() -100){
						
						explosoes.add(new Explosao(inimigos.get(i).getX(),inimigos.get(i).getY()));
						
						inimigos.remove(inimigos.get(i));
						i--;
						tiros.remove(tiros.get(x));
						x--;
						score+=10;					
																				
					}
				}
			}
		}			
}

	public void paintComponent(Graphics g2) {	//Função que printa os elementos
		super.paintComponent(g2);//Limpa a tela
		
		Graphics2D g = (Graphics2D) g2.create();
				
				//Ligar o anti aliasing		
				g.setRenderingHint(
					    RenderingHints.KEY_ANTIALIASING,
					    RenderingHints.VALUE_ANTIALIAS_ON);
				g.setRenderingHint(
					    RenderingHints.KEY_TEXT_ANTIALIASING,
					    RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		nave.print(g);	//Printa a nave do player no meio da tela
		bg.print(g);   // Printa o background
		
		if(podejogar == true) {		
			nave.print(g);	//Printa a nave do player no meio da tela		
			for(int i = 0; i < tiros.size(); i++) {	 //Printa os tiros		
				tiros.get(i).print(g);
			}
			for(int l = 0; l < tirosInimigos.size(); l++) {	 //Printa os tiros		
				tirosInimigos.get(l).printInimigos(g);
			}
				
			for(int i = 0; i < inimigos.size(); i++) {	//Printa os inimigos			
				inimigos.get(i).print(g);
			}
			
			for(int l = 0; l < explosoes.size(); l++) {	 //Printa as explosoes	
				explosoes.get(l).print(g);
			}
			
			g.setColor(Color.WHITE);//printa o score
			g.setFont(msgFont);
			g.drawString("Score = "+ score ,20,30);
			
			g.setColor(Color.WHITE);//printa o num de vidas
			g.setFont(msgFont);
			g.drawString("Vidas = "+ vidas ,20,60);

		}	
		else if(instrucoes==true) {//abre o menu de instrucoes se pressionar "I"
			logo.print(g);
			g.setColor(Color.WHITE);
			g.setFont(msgFont);
			g.drawString("Pressione \"A\" para ir para esquerda",510,450);	
			
			g.setColor(Color.WHITE);
			g.setFont(msgFont);
			g.drawString("Pressione \"D\" para ir para direita",520,470);
			
			g.setColor(Color.WHITE);
			g.setFont(msgFont);
			g.drawString("Pressione \"J\" para atirar",550,490);
			
			g.setColor(Color.WHITE);
			g.setFont(msgFont);
			g.drawString("Pressione \"ESC\" para voltar a tela inicial",490,590);
		}
		else if(perdeuJogo==true) {//Printa a mensagem de derrota
			logo.print(g);
			g.setColor(Color.RED);
			g.setFont(msgFimFont);
			g.drawString("VOCÊ PERDEU!!!",510,450);	
			
			g.setColor(Color.WHITE);
			g.setFont(msgFimFont);
			g.drawString("Score = "+ score ,550,520);
		}
		else if(ganhouJogo==true) {//Printa a mensagem de vitoria
			logo.print(g);
			g.setColor(Color.GREEN);
			g.setFont(msgFimFont);
			g.drawString("VOCÊ GANHOU!!!",510,450);

			g.setColor(Color.WHITE);
			g.setFont(msgFimFont);
			g.drawString("Score = "+ score*vidas ,550,520);
		}
		else if(pause==true && aux==1) {//pausa o jogo
			logo.print(g);
			g.setColor(Color.white);
			g.setFont(msgFont);
			g.drawString("JOGO PAUSADO - PRESSIONE ESPAÇO PARA CONTINUAR",445,500);
		}
		else if(podejogar==false) {//Menu inicial
			logo.print(g);
			g.setColor(Color.white);
			g.setFont(msgFont);
			g.drawString("PRESSIONE \"ESPAÇO\" PARA JOGAR",518,500);
			
			g.setColor(Color.white);
			g.setFont(msgFont);
			g.drawString("PRESSIONE \"I\" PARA INSTRUÇÕES",520,530);
		}
		
			
		
	}
	
	private void sleep() {
		try {
		Thread.sleep(20);	//velocidade de atualizacao do jogo de 20 ms
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void keyPressed(KeyEvent e) {//KeyListener 
		
		if(ganhouJogo==true || perdeuJogo==true) {}//trava no menu de vitoria ou derrota
		
		else if(podejogar==true) {
			if(e.getKeyCode() == KeyEvent.VK_D) {	//D vai para direita
				dir_movimento = 1;
			}
			if(e.getKeyCode() == KeyEvent.VK_A) {	//A vai para esquerda
				dir_movimento = -1;
			}
			if(e.getKeyCode() == KeyEvent.VK_J && nave.delayTiro()) {	// J atira
				tiros.add(nave.atirar());
			}
			if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {//ESC abre o menu de pause
				pause=true;
				podejogar=false;
				aux=1;				
			}
		}
		
		else if(podejogar==false) {//Espaco inicia, ou continua o jogo
			if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			podejogar = true;	
		}
		}
		if(e.getKeyCode() == KeyEvent.VK_I) {//I abre o menu de instrucoes
			instrucoes = true;
		}
		/*if(e.getKeyCode() == KeyEvent.VK_V) {////CHEAT
			//ganhouJogo = true;
			//podejogar=false;
			vidas++;
		}*/
		if(instrucoes == true) {//ESC volta pra tela inicial, quando esta no menu de instrucoes
			if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				instrucoes=false;
			}
		}		
		
	}
	

	public void keyReleased(KeyEvent e) { //Se nenhuma tecla estiver sendo pressionada, a nave para
		if(e.getKeyCode() == KeyEvent.VK_D) {
			dir_movimento = 0;
		}
		if(e.getKeyCode() == KeyEvent.VK_A) {
			dir_movimento = 0;
		}
		
	}

	public int getScore() {
		return score;
	}
		
	public void keyTyped(KeyEvent e) {}
}
