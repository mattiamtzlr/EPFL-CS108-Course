package JavaFX;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.AccessibleRole;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoginForm extends Application {

    /*
        Simple example
     */
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Label nameL = new Label("Name:");
        TextField nameF = new TextField();

        Label pwL = new Label("Password:");
        PasswordField pwF = new PasswordField();

        Button connectB = new Button("Login");

        GridPane grid = new GridPane(2, 2);
        grid.addRow(0, nameL, nameF);
        grid.addRow(1, pwL, pwF);
        grid.add(connectB, 0, 2, 2, 1);

        GridPane.setHalignment(connectB, HPos.CENTER);

        grid.setAlignment(Pos.CENTER);
        Scene scene = new Scene(grid);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Login Form");
        primaryStage.show();
    }
}
