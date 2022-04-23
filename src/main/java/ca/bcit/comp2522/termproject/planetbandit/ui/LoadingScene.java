package ca.bcit.comp2522.termproject.planetbandit.ui;

import com.almasb.fxgl.dsl.FXGL;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * Represents a loading scene.
 * @author Benny and Prab
 * @version 2022
 */
public class LoadingScene extends com.almasb.fxgl.app.scene.LoadingScene {

    /**
     * Constant value 0.5.
     */
    public static final double ZERO_POINT_FIVE = 0.5;
    /**
     * Constant value 46.0.
     */
    public static final double DECIMAL_FORTY_SIX = 46.0;
    /**
     * Constant value 3.
     */
    public static final int THREE = 3;
    /**
     * Constant value 5.
     */
    public static final int FIVE = 5;


    /**
     * Constructs a LoadingScene.
     */
    public LoadingScene() {

        Rectangle bg  = new Rectangle(getAppWidth(), getAppHeight(), Color.AZURE);

        Text text = FXGL.getUIFactoryService().newText("Loading Level",
                Color.BLACK, DECIMAL_FORTY_SIX);

        FXGL.centerText(text, getAppWidth() / 2.0, getAppHeight() / 2.0);

        HBox box  = new HBox(FIVE);
        for (int i = 0; i < THREE; i++) {
            Text textDot = FXGL.getUIFactoryService().newText(".", Color.BLACK, DECIMAL_FORTY_SIX);
            box.getChildren().add(textDot);

            FXGL.animationBuilder(this)
                    .duration(Duration.seconds(1.0))
                    .autoReverse(true)
                    .delay(Duration.seconds(i * ZERO_POINT_FIVE))
                    .repeatInfinitely()
                    .fadeIn(textDot);
        }

        getContentRoot().getChildren().setAll(bg, text, box);

    }
}

