/*
Author: Ahmad
CSC 201-004N
Assignment 16
Problem 16.2
(Select geometric figures)
Write a program that draws various figures, as shown
in Figure 16.36b. The user selects a figure from a radio button and uses a check
box to specify whether it is filled
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class homework16  extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Text text = new Text(50, 50, "JavaFX");
        text.setFont(Font.font("Arial", 20));

        ShapePane panel = new ShapePane();

        primaryStage.setScene(new Scene(panel));
        primaryStage.setTitle("Text Panel");
        primaryStage.show();
    }


    private class ShapePane extends BorderPane {

        private ShapePane() {

            // create 3 shapes
            Shape[] shapes = new Shape[3];
            shapes[1] = new Rectangle(200, 100);        //Rectangle
            shapes[0] = new Circle(50);                       //Circle
            shapes[2] = new Ellipse(45, 30);          //Ellipse

            // Center pane which displays shapes
            StackPane centerPane = new StackPane();
            setCenter(centerPane);

            // set shapes original settings
            for (Shape s : shapes) {
                s.setFill(Color.TRANSPARENT);
                s.setStroke(Color.BLACK);
            }

            // creates radio buttons
            RadioButton[] rbButtons = new RadioButton[3];
            rbButtons[0] = new RadioButton("Circle");
            rbButtons[1] = new RadioButton("Rectangle");
            rbButtons[2] = new RadioButton("Ellipse");

            // Creates Fill checkbox and add set target
            CheckBox cbFill = new CheckBox("Fill in");
            cbFill.setOnAction(e-> {
                Shape shape = (Shape)centerPane.getChildren().get(0);
                if (cbFill.isSelected()) {
                    shape.setFill(Color.BLACK);
                } else {
                    shape.setFill(Color.TRANSPARENT);
                }
            });

            // Create Pane that stores Radio buttons and checkbox
            HBox bottomBox = new HBox(10);
            bottomBox.getChildren().addAll(rbButtons);
            bottomBox.getChildren().add(cbFill);
            setBottom(bottomBox);

            // bottomPane original settings
            bottomBox.setAlignment(Pos.CENTER);
            bottomBox.setPadding(new Insets(1, 10, 1, 10));


            ToggleGroup squad = new ToggleGroup();
            for (int i = 0; i < rbButtons.length; i++) {
                final int index = i;
                rbButtons[i].setToggleGroup(squad);
                rbButtons[i].setOnAction(e-> {

                    if (shapes[index].getFill() != Color.TRANSPARENT) {
                        cbFill.setSelected(true);
                    } else {
                        cbFill.setSelected(false);
                    }
                    centerPane.getChildren().clear();
                    centerPane.getChildren().add(shapes[index]);

                });
            }
            setPrefHeight(200);

        }
    }
}
