package ca.bcit.comp2522.termproject.planetbandit.ui;

import com.almasb.fxgl.dsl.FXGL;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class LoadingScene extends com.almasb.fxgl.app.scene.LoadingScene {

    public LoadingScene() {

        Rectangle bg  = new Rectangle(getAppWidth(),getAppHeight(), Color.AZURE);

        Text text = FXGL.getUIFactoryService().newText("Loading Level", Color.BLACK, 46.0);
        //Text text =  new Text("Loading level");
        //text.setFill(Color.BLACK);
        //text.setFont(Font.font(46));

        FXGL.centerText(text,getAppWidth()/2.0,getAppHeight()/2.0);

        HBox box  = new HBox(5);
        for (int i = 0; i < 3; i++) {
            Text textDot = FXGL.getUIFactoryService().newText(".", Color.BLACK,46.0);
            box.getChildren().add(textDot);

            FXGL.animationBuilder(this)
                    .duration(Duration.seconds(1.0))
                    .autoReverse(true)
                    .delay(Duration.seconds(i*0.5))
                    .repeatInfinitely()
                    .fadeIn(textDot);
                    //.buildAndPlay();
        }

        getContentRoot().getChildren().setAll(bg, text, box);

    }
}

