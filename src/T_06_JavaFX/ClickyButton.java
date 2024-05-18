package T_06_JavaFX;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ClickyButton extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Button button = new Button("Click Me!");
        Font font = Font.font("CaskaydiaCove NF", FontWeight.SEMI_BOLD, 45);
        button.setFont(font);
        button.setStyle("border-radius: 10px;");

        // adding an event listener to the button
        button.setOnMouseClicked(e -> {
            switch (e.getButton()) {
                case PRIMARY   -> System.out.print("Button clicked with left button!");
                case SECONDARY -> System.out.print("Button clicked with right button!");
            }
            if (e.isAltDown())
                System.out.print(" ALT key is also pressed!");

            System.out.println();
        });

        VBox vBox = new VBox(button);
        vBox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vBox);

        primaryStage.setScene(scene);

        primaryStage.setTitle("Scene");
        primaryStage.setMinHeight(270);
        primaryStage.setMinWidth(480);

        primaryStage.show();
    }
}
