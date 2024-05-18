package T_06_JavaFX;

import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    /* ========================= Graphical User Interfaces with JavaFX =============================

        Important: To get JavaFX to work, a module-info.java file needs to be defined in the
                   projects src folder. Of course, JavaFX needs to be added as a library.

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
            To facilitate the observer design pattern, JavaFX provides the following concepts and
            classes:

            Properties:
                A JavaFX property is an observable object containing a unique value. A property can
                be read-only or read/write.
                An especially useful property is the class SimpleObjectProperty, inheriting from the
                class ObjectProperty which can be used as follows:

                    ObjectProperty<String> p = new SimpleObjectProperty<>();
                    p.addListener((o, oV, nV) -> System.out.println(nV));

                    p.set("hello");     // prints "hello"
                    p.set("world");     // prints "world"

                As addListener takes a parameter of type ChangeListener which is a functional
                interface, a lambda function with arguments o (observable), oV (old value) and
                nV (new value) can be passed.


            Observable Collections:
                As properties can only store one unique value, JavaFX also provides classes
                representing observable collections, which inherit from the normal java.util
                collections. For example, the method sort of ObservableList only notifies the
                observers once the sorting has finished completely.


            Beans (what):
                To combine multiple properties in a class with the goal of making all of its
                attributes observable, a convention called JavaFX beans is used. It specifies:
                    - Every class attribute is represented by a property

                    - Every property corresponding to an attribute needs to be accessible via a
                      method named the same as the property followed by the word "Property".

                    - The value stored by the property also needs to be accessible via a method
                      named the same as the property prefixed by the word "get" or "is".

                    - If the property is read/write, a method to change its value needs to be
                      available.

                Below is an example how a bean representing a person could look like:

                    public final class Person {
                          public ObjectProperty<String> firstNameProperty() { … }
                          public String getFirstName() { … }
                          public void setFirstName(String newFirstName) { … }

                          public ReadOnlyIntegerProperty ageProperty() { … }
                          public int getAge() { … }
                    }

                Notice, that there are no classic attributes as they are all represented by
                properties.


            Links:
                As the code which handles the connection between the observable model and the
                graphical interface is almost always the same, it is provided in the JavaFX library.

                For this, JavaFX offers the notion of data binding which ensures that the value of
                one property is always equal to that of another. These links may be uni- or
                bidirectional.

                Unidirectional links:
                    A given property is observed and once its value changes, it is copied into
                    another property.
                    A unidirectional link can be established via the method bind, which is
                    applied to the property - meaning the one whose value is always copied from
                    the other - and its argument is the source property. Its usage is shown below.

                    After a property has been defined as a destination through a unidirectional
                    link, it may no longer be directly modified using its set method.

                Bidirectional links:
                    Two properties observe each other and once on changes its value the other
                    does so as well to reflect the change.
                    A bidirectional link can be established similarly to a unidirectional link
                    using the method bindBidirectional. At the moment of the binding, the value
                    of the second property (passed as the argument) is copied into that of the
                    first. After that, they are treated symmetrically. See below for an example.



        Event Handling -----------------------------------------------------------------------------
            A program with a GUI usually waits until the user interacts with it, reacts to the
            interaction and then goes back to waiting again. When a program is forced to react we
            speak of an event.

            This leads to the notion of event-driven programming (oh god LazyMon flashbacks). At
            the heart of event-based programming is the so-called event loop, which is provided
            by the JavaFX library. It is sequential, meaning an event has to be completed before
            the next one can start.

            To handle events, JavaFX provides event handlers for specific situations. The event
            loop is executed on a separate thread named the JavaFX Application Thread. As this
            introduces some multi-threaded fuckery, some rules need to be followed, in particular,
            any manipulation of JavaFX nodes that are part of a graph already attached to a Scene
            object must be performed from the JavaFX application thread, as must any creation of a
            Scene or Stage object.

            Event handlers are implemented by the generic and functional interface EventHandler,
            whose type parameter specifies the type of event handled by the handler. An event
            handler is attached to and event source (often a node) and its methods are called
            every time an event is produced.

            A simple example of an event and its handler can be found in the class ClickyButton.

            Once an event has started, JavaFX collects information about it in an object passed to
            the event handler which is itself called an event.

            The most used events are:
                - MouseEvent
                - TouchEvent
                - KeyEvent
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

        // ------------------------------------------------------------------simple property example
        ObjectProperty<String> p = new SimpleObjectProperty<>();
        p.addListener((o, oV, nV) -> System.out.println(nV));

        p.set("hello");     // prints "hello"
        p.set("world");     // prints "world"

        // ------------------------------------------------------------------ unidirectional link
        ObjectProperty<String> p1 = new SimpleObjectProperty<>();
        ObjectProperty<String> p2 = new SimpleObjectProperty<>();

        p1.addListener((o, oV, nV) -> System.out.println(nV));

        p2.set("hello");    // prints nothing as p2 has no listener
        System.out.println("--- now binding p1 to p2 ---");
        // binds the value of p2 to that of p1 (and copies it a first time, thus prints "hello").
        p1.bind(p2);
        p2.set("world");    // prints "world" as p2 is bound to p1

        // raises an exception, as p1 is defined as a unidirectional link destination
        // p1.set("test");

        // ------------------------------------------------------------------ bidirectional link
        ObjectProperty<String> p3 = new SimpleObjectProperty<>();
        ObjectProperty<String> p4 = new SimpleObjectProperty<>();

        p3.addListener((o, oV, nV) -> System.out.println(nV));

        p4.set("hello");    // prints nothing
        System.out.println("--- now binding p3 to p4 bidirectionally ---");
        // prints "hello" as the value in p4 is copied a first time and thus registered as change
        p3.bindBidirectional(p4);
        p4.set("world");    // prints "world"
        p3.set("yes");      // not illegal, prints "yes"

        primaryStage.setScene(scene);

        primaryStage.setTitle("Scene");
        primaryStage.setMinHeight(270);
        primaryStage.setMinWidth(480);

        primaryStage.show();
    }
}
