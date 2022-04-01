package ca.bcit.comp2522.termproject.planetbandit;

import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Displays the splash screen onto a stage.
 *
 * @author Prab and Benny
 * @version 2022
 */
public class DisplaySplashScreen {
    private final Image splashImage = new Image("welcomeScreen.png");

    /**
     * Launches the splash screen onto a stage.
     *
     * @param stage a Stage object
     */
    public void start(final Stage stage) {
        new SplashScreen(stage, splashImage, this::showGame);
    }

    /**
     * Displays the actual game screen once we are done displaying the splash screen.
     */
    private void showGame() {
        MainMenu mainMenu = new MainMenu();
        GamePlay gamePlay = new GamePlay();
        mainMenu.start(gamePlay.getStage());
    }
}
