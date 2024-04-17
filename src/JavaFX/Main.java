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


        Nodes --------------------------------------------------------------------------------------
            There are 4 main types of geometric nodes:
                - Geometric shapes:     Arc, Circle, Ellipse, Line, Polygon, Polyline, Rectangle
                - Bézier Curves:        CubicCurve, QuadCurve
                - Paths:                Path, SVGPath
                - Text (multi-line):    Text


            There are 3 main types of graphic nodes:
                - ImageView,    showing an image
                - MediaView,    showing a video
                - Canvas,       showing a modifiable image where simple content may be drawn.

                The difference between these types of nodes and the geometric ones, is that the
                latter may be manipulated individually which is not the case for the former.
                A line drawn on a canvas may not be modified once drawn for example.


            There are many types of control nodes all inheriting from Control:
                - Information (passive):
                    - Label, representing a simple text label, usually assigned to each input

                    - ProgressIndicator and ProgressBar, displaying info about an ongoing operation.

                - Buttons (active):
                    - Button, representing a button with one unique state, which can be pressed to
                      trigger an action.

                    - ToggleButton, RadioButton, representing buttons with two states, meaning they
                      can be either active or inactive.
                      A ToggleButton may be individually activated or deactivated (for example for
                      a settings menu), while a RadioButton is always part of a group of buttons of
                      the same type of which exactly one may be activated.

                - Atomic Value editors (active):
                    - HTMLEditor, TextArea, TextField can all be used to present and edit text, of
                      which the first supports formatting, the second allows multiple lines, and the
                      last allows only one line.

                    - Slider, allowing to change a value between to bounds

                    - Spinner, TextField with built-in selectors to change the value, needs to be
                      applied to a value within a set (e.g. integers).

                    - ChoiceBox, allowing to choose a value amidst a given set of values.

                    - ColorPicker, allowing to pick a color.

                    - DatePicker, allowing to choose a Date.

                - Composite Value editors (active):
                    - ListView, showing a list of values and offering the possibility to choose one.

                    - TableView, showing a table of values, meaning a list with multiple attributes
                      assigned to each element. As in ListView, a value can also be selected.

                    - TreeView, showing a tree of values, meaning a hierarchy between values, also
                      allows choosing a value


            There are multiple types of Panes all inheriting from Pane:
                A specialty of panes is that they do not spatially organise their child nodes,
                meaning they have to be positioned manually.

                Panes take note of the methods of Node used to set a minimal/maximal/preferred
                width/height minWidth, minHeight, maxWidth, maxHeight, prefWidth, prefHeight.

                Types of panes:
                    - BorderPane, which has 5 zones (central, top, bottom, left, right) which can
                      all be occupied by at most one child node. The sizes of the zones are given
                      by the sizes of their children, the remaining space is given to the center
                      zone.

                    - GridPane, which organises its child nodes in a grid where the dimensions of
                      the rows and the columns are given by the sizes of the child nodes.
                      It is possible to have child nodes span multiple lines and/or rows, and they
                      can also be aligned (left, right, center, etc.) differently.

                    - StackPane, which organises its child nodes by stacking them on top of each
                      other, where the first one is placed at the bottom of the stack. The elements
                      are all rendered partially transparent as to not completely hide the ones
                      below.

                    - ScrollPane, which only shows part of a node and reveals the rest on scrolling.

                    - TabPane, which has multiple tabs each showing one child node. Only one tab is
                      visible at a given time.

                    - SplitPane, which is split into multiple parts, each occupied by a different
                      child node, the partition can either be done horizontally or vertically and
                      may be changed continuously.



        Properties and Links -----------------------------------------------------------------------
            TODO
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
