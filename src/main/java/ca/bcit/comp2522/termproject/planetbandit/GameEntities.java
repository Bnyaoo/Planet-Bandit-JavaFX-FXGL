package ca.bcit.comp2522.termproject.planetbandit;

import javafx.scene.layout.AnchorPane;

public class GameEntities {

    public AnchorPane gamePane;
    public Player player;
    public Block[] blocks;
    public int levelWidth = 2000;

    public GameEntities(AnchorPane gamePane){
        this.gamePane = gamePane;
        blocks = new Block[10];
        addGameItems();
    }

    private void addGameItems() {
        player = new Player();
        player.getPlayerImage().setLayoutX(20);
        player.getPlayerImage().setLayoutY(150);
        gamePane.getChildren().add(player.getPlayerImage());

        blocks[0] = new Block(gamePane, 5, 5, 0, 266);
        blocks[1] = new Block(gamePane, 7, 8, 80, 152+66);
        blocks[2] = new Block(gamePane, 6, 2, 240, 170);
        blocks[4] = new Block(gamePane, 5, 2, 420, 180);
        blocks[5] = new Block(gamePane, 50, 10, 500, 266);
//        blocks[6] = new Block(gamePane, 10, 10, 370, 400);
//        blocks[7] = new Block(gamePane, 10, 10, 410, 420);


        player.getPlayerImage().layoutXProperty().addListener((obs, old, newValue) -> {
            int offset = newValue.intValue();

            if (offset > 300 && offset < levelWidth - 300) {
                gamePane.setLayoutX(300 - offset);
            }
        });
    }


}

