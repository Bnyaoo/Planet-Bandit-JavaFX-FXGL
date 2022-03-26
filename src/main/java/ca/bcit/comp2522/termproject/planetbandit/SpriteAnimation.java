package ca.bcit.comp2522.termproject.planetbandit;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;


public class SpriteAnimation extends Transition {

    private final ImageView imageView;
    private final int count;
    private Image[] image;

    private int lastIndex;

    public SpriteAnimation(
            ImageView imageView,
            String imagePath,
            String imageName,
            Duration duration,
            int count) {
        this.imageView = imageView;
        this.count     = count;

        image = new Image[count];
        for (int i=0; i<count; i++) {
            String url = imagePath+"/"+imageName+Integer.toString(i) + ".png";
            image[i] = new Image(url);
        }

        setCycleDuration(duration);
        setInterpolator(Interpolator.LINEAR);
    }

    protected void interpolate(double k) {
        final int index = Math.min((int) Math.floor(k * count), count - 1);
        if (index != lastIndex) {
            // Change imageView //
            imageView.setImage(image[index]);
            lastIndex = index;
        }
    }
}
