package model;

import controller.ControllerN;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        ControllerN controller = new ControllerN(primaryStage);
        controller.openChoice();
        if (controller.getOk()) {
            controller.openPrStage();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
