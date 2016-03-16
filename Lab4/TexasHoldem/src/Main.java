import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Created by wu on 3/14/16.
 */
public class Main extends Application{


    public static void main(String[] args){launch(args);}

    Stage window;
    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setResizable(false);
        window.setTitle("Texas Holdem Poker");


//        BorderPane layout = new BorderPane();
//        Scene scene = new Scene(layout,1000, 600);
        Group root = new Group();
        Scene scene = new Scene(root);
        Canvas canvas = new Canvas(1000, 600);
        root.getChildren().add(canvas);

        GraphicsContext gc = canvas.getGraphicsContext2D();
        Image bgImg = new Image("images/background.jpg");
        gc.drawImage(bgImg,0,0);
          Image card = new Image("images/QS.png");
        gc.drawImage(card, 60, 50);

        window.setScene(scene);


        window.show();



    }
}
