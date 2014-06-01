package gameTest;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.lwjgl.util.Timer;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class main extends BasicGame
{
	
	Image imgBack;
	Image pad1;
	Image pad2;
	Image ball;
	float pad1x,pad1y;
	float pad2x,pad2y;
	static int width,height;
	static float xspeed = 3;
	static float yspeed = 3;
	float xmouse,ymouse;
	float ballx,bally;
	int score = 0;
	int scoreCom = 0;
	int padsizex = 80;
	int padsizey = 20;
	int fail;
	
	Timer timer;
	
	String test = "";
	
	float runningTime = 0;
	
	float lastframe = 0;
	float lastframe2 = 0;
	float lastframe3 = 0;
	float lastframe4 = 0;
	float lastframe5 = 0;
	
	boolean direction;
	String bool = "";
	
	public main(String gamename)
	{
		super(gamename);
		width=640;
		height=480;
		timer = new Timer();
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		
		imgBack = new Image("c:/development/img/back3.png");
		pad1 = new Image("c:/development/img/pad2.png");
		pad2 = new Image("c:/development/img/pad2.png");
		ballx=width/2;
		bally=height/2;
		
		lastframe=timer.getTime();
		
		ball = new Image("c:/development/img/ball1.png");
		
		pad2x = ballx;
		
		fail = 0;
		
		direction = false;
		
	}

	@Override
	public void update(GameContainer gc, int i) throws SlickException {
		
		Input input = gc.getInput();
		
		pad1x = (float) input.getMouseX();
		pad1y = (float) input.getMouseY();
		
		// Skip this for failRate method
		//pad2x = ballx-20;
		
		
		
		//lastframe = timer.getTime();
		
		timer.tick();
		
		test = timer.toString();
		
//		runningTime=timer.getTime();
//		
//		if (timer.getTime()>runningTime+0) {
//			
//			moveBall();
//			runningTime=0;
//			
//		}
		
		float elapsed = timer.getTime() - lastframe;
//		lastframe = timer.getTime();
		

		if (elapsed >= 0.01) {
		    moveBall();
		    lastframe = timer.getTime();
		}
		//moveBall(1000);
//		private void moveBall(long delay) {
//		ballx = ballx + xspeed;
//		bally = bally + yspeed;
//		}
		
		if ((ballx > width) || (ballx < 0)) {
		    xspeed = xspeed * -1;
		  }
		
		if ((bally > height) || (bally <0))
			yspeed = yspeed * -1;
		
		boolean isHit = false;
		
		 int hit = (int) Math.abs((pad1x)-(ballx+15));
		  if ((bally < 25) && (hit<40)) {
		    //println("Treff");
			//isHit = true;
			
			float elapsed2 = timer.getTime() - lastframe2;
			
			if (elapsed2>1) {
				score+=50;
				lastframe2 = timer.getTime();
			}
		    yspeed = yspeed * -1; 
		    //delay(1000);
		  }
		  
		  if (bally < 10) {
		    //yspeed = yspeed * -1;
		    //println("Bom");
		    score-=50;
		    ballx=width/2;
		    bally=height/2;
		    yspeed = yspeed * -1;
		    delay(1200);
		  }
		  if ((isHit) && (pad1y>30)) {
			  //score+=50;
			  isHit=false;
		  }
		  else;
		
		
		  // Computer Pad
		  
		  int hit2 = (int) Math.abs((pad2x)-(ballx+15));
		  if ((bally > height-40) && (hit2 < 40)) {
		    //println("Treff");
			//isHit = true;
			
			float elapsed3 = timer.getTime() - lastframe3;
			
			if (elapsed3>1) {
				scoreCom+=50;
				lastframe3 = timer.getTime();
			}
		    yspeed = yspeed * -1; 
		    //delay(1000);
		  }
		  
		  if (bally > height-20) {
		    //yspeed = yspeed * -1;
		    //println("Bom");
		    scoreCom-=50;
		    ballx=width/2;
		    bally=height/2;
		    yspeed = yspeed * -1;
		    //delay(1200);
		  }
		  
		  failRate();
		  
	}
	
//	private void moveBall(long delay) {
//		delay(delay);
//		
//		ballx = ballx + xspeed;
//		bally = bally + yspeed;
//		
//	}
	
	private void moveBall() {
		ballx = ballx + xspeed;
		bally = bally + yspeed;
		
	}
	
	private void failRate() {
		//int fail = 0;
		
		int specter = width/5;
		
		float elapsed4 = timer.getTime() - lastframe4;
//		lastframe = timer.getTime();
		
//		for (int i=0;i<specter;i++) {
//			if (elapsed4 >= 0.11) {
//			    pad2x = ballx - (float)specter;
//			    lastframe4 = timer.getTime();
//			}
//		}
//		
//		while(true) {
//			if (elapsed4 >= 0.11) {
//				pad2x = ballx - 5;
//				lastframe = timer.getTime();
//			}
//			break;
//		}
		
		float elapsed5 = timer.getTime() - lastframe5;
		
		
		
		if (elapsed5 >= 1) {
			Random randDirection = new Random();
			direction = randDirection.nextBoolean();
			lastframe5 = timer.getTime();
		}
		
//		Random randDirection = new Random();
//		Boolean direction = randDirection.nextBoolean();
//		bool = direction.toString();
		
		if (elapsed4 >= 0.01) {
			if (fail<specter&&fail>(0-specter)) {
				if (direction)
					fail++;
				else
					fail--;
			}
			else if (fail>specter)fail--;
			else if (fail<(0-specter))fail++;
			//else fail--;
			
			//pad2x = (ballx + (float)(fail));
			lastframe4 = timer.getTime();
		}
		pad2x = (ballx + (float)(fail));
		//pad2x = ballx - fail;
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		g.drawString("Howdy!", 100, 100);
		//imgBack.draw();
//		boolean backDrawn = false;
//		if(!backDrawn) {
//		//imgBack.draw(0, 0, width, height);
//		//g.setBackground(Color.red);
//		backDrawn=true;
//		}
		imgBack.draw(0, 0, width, height);
		pad1.draw(pad1x-50,10,padsizex,padsizey);
		pad2.draw(pad2x, height-30,padsizex,padsizey);
		g.drawString(pad1x + " : " + pad1y, 200, 200);
		String textScore = Integer.toString(score);
		String textScore2 = Integer.toString(scoreCom);
		g.drawString("Score : " + textScore, 25, height-45);
		g.drawString("Computer Score : " + textScore2, 25, height-65);
		//g.setColor(Color.black);
		//g.fillOval(ballx, bally, 25, 25);
		Graphics oneBall = new Graphics();
		ball.draw(ballx, bally);
//		oneBall.setColor(Color.black);
//		oneBall.fillOval(ballx, bally, 25, 25);
		
		//g.drawString(bool, 400, 400);
		
		//g.drawString(test, 25, height-25);
		
		
		
	}

	public static void main(String[] args)
	{
		try
		{
			AppGameContainer appgc;
			appgc = new AppGameContainer(new main("JPong"));
			appgc.setDisplayMode(width, height, false);
			appgc.start();
		}
		catch (SlickException ex)
		{
			Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	void delay(long delay)
	{
	  long time = System.currentTimeMillis();
	  while(System.currentTimeMillis() - time <= delay);
	}
	
}