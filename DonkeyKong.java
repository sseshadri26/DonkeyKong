import javafx.application.Application;
import javafx.stage.Stage;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.*;
import javafx.scene.media.AudioClip;
import java.net.URL;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.event.*;
import javafx.scene.input.*;
import javafx.scene.text.*;


public class DonkeyKong extends Application implements EventHandler<InputEvent>

{
	GraphicsContext gc;
	AnimateObjects animate;
	int x=0;
	int dir=1;
	Image trooper;

	public static void main(String[] args)
	{
		launch();
	}
	public void start(Stage stage)
	{

		stage.setTitle("Donkey Kong");
		Group root = new Group();
		Canvas canvas = new Canvas(800, 400);
		root.getChildren().add(canvas);
		Scene scene = new Scene(root);
		scene.addEventHandler(KeyEvent.KEY_PRESSED,this);
		scene.addEventHandler(MouseEvent.MOUSE_CLICKED, this);

		stage.setScene(scene);

		gc = canvas.getGraphicsContext2D();
		trooper = new Image("trooper.jpg");
		gc.drawImage( trooper, 180, 100 );
		animate = new AnimateObjects();
		animate.start();
		stage.show();
		URL resource = getClass().getResource("test.wav");
		AudioClip clip = new AudioClip(resource.toString());
		clip.play();
	}

	public void handle(final InputEvent event)
	{
		if (event instanceof KeyEvent)
		{

			if (((KeyEvent)event).getCode() == KeyCode.LEFT )
				x-=5;

			if (((KeyEvent)event).getCode() == KeyCode.RIGHT )
				x+=5;
		}
		if (event instanceof MouseEvent)
		{
			System.out.println( ((MouseEvent)event).getX() );
			System.out.println( ((MouseEvent)event).getY() );
		}

	}

	public class AnimateObjects extends AnimationTimer{
		public void handle(long now)
		{
			if (x>400)
				dir=-1;
			if (x<-200)
				dir=1;
			//x+=(dir*8);

			gc.drawImage(trooper, 180+x, 100);
			Rectangle2D rect1 = new Rectangle2D( 400,100,100,100 );
			gc.fillRect(400,100,100,100);
			Rectangle2D rect2 = new Rectangle2D( 180+x,100,trooper.getWidth(),trooper.getHeight() );
			if (rect1.intersects(rect2))
				System.out.println("HIT");

			if (x>-50)
				{
					//all the code for drawing your image on the screen goes here
				}
			else
			{
				// we are going to display Game over if the user moves 50 pixels to the left
				gc.setFill( Color.YELLOW); //Fills the text in yellow
				gc.setStroke( Color.BLACK ); //Changes the outline the black
				gc.setLineWidth(1); //How big the black lines will be
				Font font = Font.font( "Arial", FontWeight.NORMAL, 48 );
				gc.setFont( font );
				gc.fillText( "Game Over", 100, 50 ); //draws the yellow part of the text
				gc.strokeText( "Game Over", 100, 50 ); //draws the outline part of the text
			}

		}

	}


}