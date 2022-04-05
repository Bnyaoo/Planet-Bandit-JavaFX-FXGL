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
     * @throws Exception if the user's login credentials is in invalid
     */
    private void showGame() throws Exception {
//        MainMenu mainMenu = new MainMenu();
        LoginApp loginApp = new LoginApp();
        GamePlay gamePlay = new GamePlay();
        loginApp.start(gamePlay.getStage());
    }
}
