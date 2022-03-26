package ca.bcit.comp2522.termproject.planetbandit;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;


public class Block {


    private static Image blockTop = new Image("DirtBlock/images/block(2).png");
    private static Image blockBottom = new Image("DirtBlock/images/block(5).png");
    private int width, height, posX, posY;

    private static final int WIDTH = 16;
    private static final int HEIGHT = 16;


    public Block(AnchorPane gamePane, int width, int height, int posX, int posY) {
        this.width = width;
        this.height = height;
        this.posX = posX;
        this.posY = posY;

        for (int i=0; i<width; i++) {
            for (int j=0; j<height; j++) {
                ImageView imageView;
                if (j==0)
                    imageView = new ImageView(blockTop);
                else
                    imageView = new ImageView(blockBottom);
                imageView.setLayoutX(posX + i * WIDTH);
                imageView.setLayoutY(posY + j * HEIGHT);
                gamePane.getChildren().add(imageView);
            }
        }
    }

    public int getWidth(){
        return WIDTH * width;
    }

    public int getHeight() {
        return HEIGHT * height;
    }

    public int getPosX() {
        return posX;
    }
    public int getPosY() {
        return posY;
    }
}

