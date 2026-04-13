package ui;
import io.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


// THE BUILDER! WOOOOO - all the action happens here, making panes and text and input fields and buttons - lots of buttons
public class SceneBuild {
    public void buildUI(Stage primaryStage) {
//        CUSTOM FONT CUZ DUH!!
        Font CuteDino = Font.loadFont(getClass().getResourceAsStream("/Cute Dino.ttf"), 24);


//        COUNT THE CLICKING
        Text clickCount = new Text("Times clicked: " + ClickerCounter.getCount());
        clickCount.setFont(CuteDino);
        clickCount.setStroke(Color.WHITE);
        var textPane = new Pane();
        textPane.getChildren().add(clickCount);

//        COOKIE IMAGE
        Image image = new Image("/new_cookie.png");
        ImageView imageBox = new ImageView();
        imageBox.setImage(image);
        imageBox.setFitWidth(100);
        imageBox.setPreserveRatio(true);
        imageBox.setSmooth(true);
        imageBox.setCache(true);

//        CLICKER COUNTER
        Button cookieClicker = new Button("CLICK THE COOKIE!");
        cookieClicker.setFont(Font.font("Comic Sans", 15));
//        THEME CHANGER
        Button themeButton = new Button("Switch to Dark");
        themeButton.setFont(Font.font("Comic Sans", 12));
//        MENU BUTTON
        var menuButton = new Button("Menu");
        menuButton.setFont(Font.font("Comic Sans", 10));

//        COOKIE IN A BOX
        var graphicsPane = new StackPane();
        graphicsPane.getChildren().add(imageBox);

//        CLICKER IN A BOX
        var clickerPane = new Pane();
        clickerPane.getChildren().add(cookieClicker);

//        THEME BUTTON IN A BOX
        var themePane = new Pane();
        themePane.getChildren().add(themeButton);

//        MENU BUTTON IN A BOX
        var menuPane = new Pane();
        menuPane.getChildren().add(menuButton);


//        BACKGROUND
        var backgroundColour = new BackgroundFill(Color.web("#FDBCB4"), CornerRadii.EMPTY, Insets.EMPTY);
        var trueBackground = new Background(backgroundColour);


//        ROOT PANE
        var rootPane = new Pane();
        rootPane.setBackground(trueBackground);
        rootPane.getChildren().addAll(graphicsPane, clickerPane, themePane, textPane, menuPane);


//        MA SCENE
        var scene = new Scene(rootPane, 400, 400, Color.web("#FDBCB4"));




//        COOKIE POSITIONING
        rootPane.applyCss();
        rootPane.layout();
        var cookieWidth = graphicsPane.getWidth();
        var cookieHeight = graphicsPane.getHeight();
        var rootWidth = rootPane.getWidth();
        var rootHeight = rootPane.getHeight();
        var xPOS = ((rootWidth - cookieWidth) / 2);
        var yPOS = ((rootHeight - cookieHeight) / 2);
        graphicsPane.relocate(xPOS, yPOS);

//        CLICKER POSITIONING
        cookieClicker.applyCss();
        cookieClicker.layout();
        var clickerWidth = clickerPane.getWidth();
        var clickerHeight = clickerPane.getHeight();
        var clickerXPOS = ((rootWidth - clickerWidth) / 2);
        var clickerYPOS = ((rootHeight - clickerHeight) - 20);
        clickerPane.relocate(clickerXPOS, clickerYPOS);


//        THEME POSITIONING
        themeButton.applyCss();
        themeButton.layout();
//        var themeWidth = themePane.getWidth();
        var themeHeight = themePane.getHeight();
        var themeXPOS = 20;
        var themeYPOS = ((rootHeight - themeHeight) - 20);
        themePane.relocate(themeXPOS, themeYPOS);

//        TEXT POSITIONING
        clickCount.applyCss();
        var textWidth = textPane.getWidth();
//        var textHeight = textPane.getHeight();
        var textXPOS = ((rootWidth - textWidth) / 2);
        var textYPOS = 30;
        textPane.relocate(textXPOS, textYPOS);

//        MENU POSITIONING - MAIN SCENE
        menuButton.applyCss();
        menuButton.layout();
        menuButton.relocate(20, 20);


//        AND ACTION
        primaryStage.setTitle("The bestest cookie clicker ev-UH!");
        primaryStage.setScene(scene);
        primaryStage.show();



        //        MENU SCENE!!!
        var menuFill = new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY);
        var menuBackground = new Background(menuFill);

        // menu exit button
        var exitButton = new Button("X");
        exitButton.setFont(Font.font("Comic Sans", 10));
        var exitButtonPane = new Pane();
        exitButtonPane.getChildren().add(exitButton);

        // menu text
        var menuText = new Text("Would you like to save your data?");
        menuText.setFont(Font.font("Comic Sans", 16));
        var menuTextPane = new Pane();
        menuTextPane.getChildren().add(menuText);

        // menu input field
        var textBox = new TextField();
        textBox.setPromptText("Enter file path here!");
        textBox.setMinWidth(Region.USE_PREF_SIZE);
        textBox.setPrefColumnCount(20);
        var inputPane = new Pane();
        inputPane.getChildren().add(textBox);
        var inputButton = new Button("Submit");
        var submitPane = new Pane();
        submitPane.getChildren().add(inputButton);

        // root pane
        var menuRoot = new Pane();
        menuRoot.setBackground(menuBackground);
        menuRoot.getChildren().addAll(exitButtonPane, menuTextPane, inputPane, submitPane);

        // the scene
        var menuScene = new Scene(menuRoot, 400, 400);



        //        EXIT MENU BUTTON
        exitButton.setOnMouseClicked(new MenuExit(primaryStage, scene));


        String fileName = textBox.getText();
        int cookieClickCount = ClickerCounter.getCount();

//       COOKIE PULSE!!
        imageBox.setOnMouseClicked(new ImageClick(imageBox));
//        COUNTER!!!
        cookieClicker.setOnMouseClicked(new ClickerCounter.clickerCounter(clickCount));
//        THEME BUTTON
        themeButton.setOnMouseClicked(new ThemeSwapper(rootPane, backgroundColour, themeButton));
//        MENU BUTTON
        menuButton.setOnMouseClicked(new MainMenu(primaryStage, menuScene, menuRoot, menuTextPane, inputPane, submitPane, exitButtonPane));
//        INPUT BUTTON!!
        inputButton.setOnMouseClicked(new FileSubmission(cookieClickCount, fileName));
    }
}