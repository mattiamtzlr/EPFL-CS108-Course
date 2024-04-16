package JavaFX;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    /* ========================= Graphical User Interfaces with JavaFX =============================
        Important: To get JavaFX to work, a module-info.java file needs to be defined in th projects
                   src folder. Of course, JavaFX needs to be added as a library.

        Fundamentals -------------------------------------------------------------------------------
            A JavaFX GUI is composed of a number of nodes, which can be simple geometric forms,
            elements of the user interface (buttons etc.), containers, etc.

            These nodes are organised in a hierarchy called the scene graph, which is a simple tree
            graph where each node has at most one parent. Generally, a node is shown nested inside
            the element which is its parent in the tree.

            Example:
                    1 Scene                         --------------------------------------------
                         |                          |  1 & 2                                   |
                   2 SplitPane                      |  ----------------      ----------------  |
                      /      \                      |  |  3           |      |  4           |  |
              3 GridPane    GridPane 4              |  |  ----------  |      |  ----------  |  |
                 /   \      /    \          =>      |  |  |   5    |  |      |  |   7    |  |  |
              5 B   6 B    B 7   B 8                |  |  ----------  |      |  ----------  |  |
                u     u    u     u                  |  |  ----------  |      |  ----------  |  |
                t     t    t     t                  |  |  |   6    |  |      |  |   8    |  |  |
                t     t    t     t                  |  |  ----------  |      |  ----------  |  |
                o     o    o     o                  |  ----------------      ----------------  |
                n     n    n     n                  --------------------------------------------

            As the above example shows, nodes can be distinguished in different categories:
                - Base nodes are the leaves at the ends of the tree and have thus no further
                  descendants. Generally, they represent very simple elements.

                - Intermediary nodes which have a number of child nodes and one parent node.
                  These are used to organise the structure of the scene.

                - The root node, which is the top most node and is always of type Scene. Its
                  graphical representation is basically non-existant.

            The Scene object has to be passed to a Stage instance which represents a graphical
            container.
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Text text = new Text("Hello World!");
        Font font = Font.font("CaskaydiaCove NF", FontWeight.SEMI_BOLD, 45);
        text.setFont(font);

        VBox vBox = new VBox(text);
        vBox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vBox);

        primaryStage.setScene(scene);

        primaryStage.setTitle("Scene");
        primaryStage.setMinHeight(270);
        primaryStage.setMinWidth(480);

        primaryStage.show();
    }
}
