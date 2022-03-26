package ca.bcit.comp2522.termproject.planetbandit;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class Controller {

    private Scene gameScene;

    private boolean LeftKeyPressed;
    private boolean RightKeyPressed;
    private boolean UpKeyPressed;
    private boolean mouseClicked;
    private boolean debugClicked;

    public Controller(Scene gameScene) {
        this.gameScene = gameScene;
        setControls();
    }

    private void setControls () {

        gameScene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Yes");
                mouseClicked = true;
            }
        });
        gameScene.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mouseClicked = false;
            }
        });

        gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.LEFT) LeftKeyPressed = true;
                if (event.getCode() == KeyCode.RIGHT) RightKeyPressed = true;
                if (event.getCode() == KeyCode.UP) UpKeyPressed = true;
                if (event.getCode() == KeyCode.DIGIT1) debugClicked = true;
            }
        });
        gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.LEFT) LeftKeyPressed = false;
                if (event.getCode() == KeyCode.RIGHT) RightKeyPressed = false;
                if (event.getCode() == KeyCode.UP) UpKeyPressed = false;
                if (event.getCode() == KeyCode.DIGIT1) debugClicked = false;
            }
        });
    }

    public boolean isLeftKeyPressed() {
        return LeftKeyPressed;
    }
    public boolean isRightKeyPressed() {
        return RightKeyPressed;
    }
    public boolean isUpKeyPressed() { return UpKeyPressed; }
    public boolean getMouseState() {
        return mouseClicked;
    }
    public boolean isDebugClicked() {
        return debugClicked;
    }
}
