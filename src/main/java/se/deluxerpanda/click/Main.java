package se.deluxerpanda.click;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Main extends javafx.application.Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view.fxml"));
            Parent root = loader.load();
            Countdown controller = loader.getController();
            Scene scene = new Scene(root);
            scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    if (Countdown.stop_mus_chek == false){

                    switch(event.getCode()) {
                        case S:
                            controller.getNumberX();
                            break;
                        default:
                            break;
                    }
                    }
                }
            });
            primaryStage.setScene(scene);
            primaryStage.setTitle("Auto Clicker - Beta");
            primaryStage.centerOnScreen();
          //  primaryStage.setScene(new Scene(root));
            primaryStage.setResizable(false);
            primaryStage.setMaximized(false);
            primaryStage.setAlwaysOnTop(true);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
