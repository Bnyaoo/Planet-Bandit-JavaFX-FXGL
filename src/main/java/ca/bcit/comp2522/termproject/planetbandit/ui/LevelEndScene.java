package ca.bcit.comp2522.termproject.planetbandit.ui;

import com.almasb.fxgl.animation.AnimationBuilder;
import com.almasb.fxgl.animation.Interpolators;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.scene.SubScene;
import com.almasb.fxgl.texture.Texture;
import com.almasb.fxgl.ui.FontFactory;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * Represents end scene upon level completion.
 * @author Benny Chao and Prab
 * @version 2022
 */
public class LevelEndScene extends SubScene {

    /**
     * Constant value 0.25.
     */
    public static final double ZERO_POINT_TWO_FIVE = 0.25;
    /**
     * Constant value 0.7.
     */
    public static final double ZERO_POINT_SEVEN = 0.7;
    /**
     * Constant value 0.9.
     */
    public static final double ZERO_POINT_NINE = 0.9;
    /**
     * Constant value 1.25.
     */
    public static final double ONE_POINT_TWO_FIVE = 1.25;
    /**
     * Constant value 1.5.
     */
    public static final double ONE_POINT_FIVE = 1.5;
    /**
     * Constant value 3.5.
     */
    public static final double THREE_POINT_FIVE = 3.5;
    /**
     * Constant value 11.0.
     */
    public static final double DECIMAL_ELEVEN = 11.0;
    /**
     * Constant value 16.0.
     */
    public static final double DECIMAL_SIXTEEN = 16.0;
    /**
     * Constant value 15.
     */
    public static final int FIFTEEN = 15;
    /**
     * Constant value 25.
     */
    public static final int TWENTY_FIVE = 25;
    /**
     * Constant value 28.
     */
    public static final int TWENTY_EIGHT = 25;
    /**
     * Constant value 52.
     */
    public static final int FIFTY_TWO = 52;
    /**
     * Constant value -20.
     */
    public static final int NEGATIVE_TWENTY = -20;
    /**
     * Constant value 52.
     */
    public static final int NEGATIVE_FIVE_HUNDRED = -500;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 250;
    private final BooleanProperty isAnimationDone = new SimpleBooleanProperty(false);

    private final Text textUserTime = FXGL.getUIFactoryService().newText("", Color.WHITE, 24.0);
    private final HBox gradeBox = new HBox();

    /**
     * Constructs an end scene.
     */
    public LevelEndScene() {
        Rectangle bg = new Rectangle(WIDTH, HEIGHT, Color.color(0, 0, 0, ZERO_POINT_SEVEN));
        bg.setStroke(Color.GOLDENROD);
        bg.setStrokeWidth(ONE_POINT_FIVE);
        bg.setEffect(new DropShadow(TWENTY_EIGHT, Color.color(0, 0, 0, ZERO_POINT_NINE)));

        VBox.setVgrow(gradeBox, Priority.ALWAYS);

        Text textContinue = FXGL.getUIFactoryService().newText("Tap to continue", DECIMAL_ELEVEN);

        textContinue.visibleProperty().bind(isAnimationDone);

        FXGL.animationBuilder(this)
                .repeatInfinitely()
                .autoReverse(true)
                .scale(textContinue)
                .from(new Point2D(1, 1))
                .to(new Point2D(ONE_POINT_TWO_FIVE, ONE_POINT_TWO_FIVE));
                //.buildAndPlay();


        VBox vbox = new VBox(FIFTEEN, textUserTime, gradeBox, textContinue);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(TWENTY_FIVE));

        StackPane root = new StackPane(bg, vbox);
        root.setTranslateX((FXGL.getAppWidth() - WIDTH) / 2.0);
        root.setTranslateY((FXGL.getAppHeight() - HEIGHT) / 2.0);

        Text textLevel  = new Text();
        textLevel.textProperty().bind(FXGL.getip("level").asString("Level %d"));
        FontFactory fontFactory = FXGL.getAssetLoader().loadFont("level_font.ttf");
        textLevel.setFont(fontFactory.newFont(FIFTY_TWO));
        textLevel.setRotate(NEGATIVE_TWENTY);
        textLevel.setFill(Color.ORANGE);
        textLevel.setStroke(Color.BLACK);
        textLevel.setStrokeWidth(THREE_POINT_FIVE);

        textLevel.setTranslateX(root.getTranslateX() - textLevel.getLayoutBounds().getWidth() / 2);
        textLevel.setTranslateY(root.getTranslateY() + TWENTY_FIVE);

        getContentRoot().getChildren().setAll(root, textLevel);

        getInput().addAction(new UserAction("Close Level End Scene") {
            @Override
            protected void onActionBegin() {
                if (!isAnimationDone.getValue()) {
                    return;
                }
                FXGL.getSceneService().popSubScene();
            }
        }, MouseButton.PRIMARY);
    }

    /**
     * Returns a windows with stage completion time, along with a "star" rating.
     */
    public void onLevelFinish() {
        isAnimationDone.setValue(false);

        Duration useTime = Duration.seconds(FXGL.getd("levelTime"));

        LevelTimeData timeData = FXGL.geto("levelTimeData");

        textUserTime.setText(String.format("Your time %.2f second!", useTime.toSeconds()));

        gradeBox.getChildren().setAll(
                new Grade(Duration.seconds(timeData.star1), useTime),
                new Grade(Duration.seconds(timeData.star2), useTime),
                new Grade(Duration.seconds(timeData.star3), useTime)

        );

        for (int i = 0; i < gradeBox.getChildren().size(); i++) {
            AnimationBuilder builder = FXGL.animationBuilder(this)
                    .delay(Duration.seconds(i * ZERO_POINT_TWO_FIVE))
                    .duration(Duration.seconds(ZERO_POINT_TWO_FIVE))
                    .interpolator(Interpolators.EXPONENTIAL.EASE_OUT());

            if (i == gradeBox.getChildren().size() - 1) {
                builder = builder.onFinished(() -> isAnimationDone.setValue(true));
            }
            builder.translate(gradeBox.getChildren().get(i))
                    .from(new Point2D(0, NEGATIVE_FIVE_HUNDRED))
                    .to(new Point2D(0, 0));
                    //.buildAndPlay();
        }

        FXGL.getSceneService().pushSubScene(this);

    }

    private static class Grade extends VBox {

        private static final Texture STAR_EMPTY = FXGL.texture("star_empty.png", 65, 72);
        private static final Texture STAR_FULL = FXGL.texture("star_full.png", 65, 72);

        public Grade(final Duration gradeTime, final Duration userTime) {
            super(FIFTEEN);
            HBox.setHgrow(this, Priority.ALWAYS);
            setAlignment(Pos.CENTER);

            if (userTime.lessThanOrEqualTo(gradeTime)) {
                getChildren().add(STAR_FULL.copy());
            } else {
                getChildren().add(STAR_EMPTY.copy());
            }
            getChildren().add(FXGL.getUIFactoryService().newText(String.format("<%.2f", gradeTime.toSeconds()), DECIMAL_SIXTEEN));
        }
    }

    /**
     * Returns number of "stars" dependent on Player's performance.
     */
    public static class LevelTimeData {

        private final double star1;
        private final double star2;
        private final double star3;

        /**
         * Constructs up to three stars for LevelTimeData.
         *
         * @param star1 in seconds
         * @param star2 in seconds
         * @param star3 in seconds
         */
        public LevelTimeData(final double star1, final double star2, final double star3) {
            this.star1 = star1;
            this.star2 = star2;
            this.star3 = star3;
        }
    }

}
