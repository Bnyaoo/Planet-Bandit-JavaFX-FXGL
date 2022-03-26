package ca.bcit.comp2522.termproject.planetbandit;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.AnchorPane;

public class GameLoop {


    private AnchorPane gamePane;
    private Controller controller;
    private GameEntities gameEntities;
    private Player player;
    private int g_acc = 4;
    private Block ground;


    public GameLoop(AnchorPane gamePane, Controller controller, GameEntities gameEntities) {
        this.gamePane = gamePane;
        this.controller = controller;
        this.gameEntities = gameEntities;
        player = gameEntities.player;
    }
    
    public void handle(long now) {
        if (controller.isLeftKeyPressed()) {
            if (player.idleAnimation.getStatus() != Animation.Status.STOPPED) {
                player.idleAnimation.stop();
                player.runAnimation.play();
            }

            if (player.direction == 1) {
                player.getPlayerImage().setScaleX(-1);
                player.direction = -1;
            }
            //player.getPlayerImage().setLayoutX(player.getPlayerImage().getLayoutX() - 2);
            moveLeft(2);
        }
        else if (controller.isRightKeyPressed()) {
            if (player.idleAnimation.getStatus() != Animation.Status.STOPPED) {
                player.idleAnimation.stop();
                player.runAnimation.play();
            }

            if (player.direction == -1) {
                player.getPlayerImage().setScaleX(1);
                player.direction = 1;
            }
            //player.getPlayerImage().setLayoutX(player.getPlayerImage().getLayoutX() + 2);
            moveRight(2);
        }
        else {
            if (player.idleAnimation.getStatus() == Animation.Status.STOPPED) {
                player.runAnimation.stop();
                player.idleAnimation.play();
            }
        }

        if (player.grounded && controller.isUpKeyPressed()) {
            player.velocity_y = 80;
            player.grounded = false;
            ground = null;
        }

        if (!player.grounded) {
            //player.getPlayerImage().setLayoutY(player.getPlayerImage().getLayoutY() - (double)player.velocity_y/12);
            if (player.velocity_y >= 0) {
                moveUp(player.velocity_y/12);
            }
            else {
                moveDown(-player.velocity_y/12);
            }
        }

        if (controller.isDebugClicked()) {
            System.out.printf("%d,%d\n", (int)player.getPlayerImage().getLayoutX(), (int)player.getPlayerImage().getLayoutY());
        }

        gravity();

        //collision_handle();

    }

    private void gravity() {
        checkGround();
        if (!player.grounded) {
            player.velocity_y -= g_acc;
        }

        //if (player.getPlayerImage().getLayoutY() > 200) player.grounded = true;
    }

    private void moveUp(int x) {
        int px1 = (int)player.getPlayerImage().getLayoutX();
        int py1 = (int)player.getPlayerImage().getLayoutY() - x;
        int px2 = px1 + player.WIDTH-1;
        int py2 = py1 + player.HEIGHT-1;

        for (Block block : gameEntities.blocks) {
            if (block == null) continue;
            // top collision
            int bx1 = block.getPosX();
            int by1 = block.getPosY();
            int bx2 = bx1 + block.getWidth();
            int by2 = by1 + block.getHeight();

            if (py1>by1 && py1 < by2) {
                if (px2 > bx1 && px2<bx2) {
                    return;
                }
                else if (px1>bx1 && px1 < bx2) {
                    return;
                }
            }
        }
        player.getPlayerImage().setLayoutY(player.getPlayerImage().getLayoutY() - x);
    }

    private void moveDown(int x) {
        int px1 = (int)player.getPlayerImage().getLayoutX();
        int py1 = (int)player.getPlayerImage().getLayoutY() + x;
        int px2 = px1 + player.WIDTH-1;
        int py2 = py1 + player.HEIGHT-1;

        for (Block block : gameEntities.blocks) {
            if (block == null) continue;
            // top collision
            int bx1 = block.getPosX();
            int by1 = block.getPosY();
            int bx2 = bx1 + block.getWidth();
            int by2 = by1 + block.getHeight();

            if (py2>by1 && py2<by2) {
                if (px2 > bx1 && px2<bx2) {
                    player.grounded = true;
                    player.getPlayerImage().setLayoutY(( by1 + 1 - player.HEIGHT ));
                    System.out.printf("%d,%d  %d,%d\n", py1, py2, by1, by2);
                    ground = block;
                    return;
                }
                else if (px1>bx1 && px1 < bx2) {
                    player.grounded = true;
                    player.getPlayerImage().setLayoutY(( by1 + 1 - player.HEIGHT ) );
                    System.out.printf("%d,%d  %d,%d\n", py1, py2, by1, by2);
                    ground = block;
                    return;
                }
            }
        }
        player.getPlayerImage().setLayoutY(player.getPlayerImage().getLayoutY() + x);
    }

    private void checkGround() {
        if (ground == null) player.grounded = false;
        else {
            int px1 = (int)player.getPlayerImage().getLayoutX();
            int py1 = (int)player.getPlayerImage().getLayoutY() + 2;
            int px2 = px1 + player.WIDTH-1;
            int py2 = py1 + player.HEIGHT-1;

            int bx1 = ground.getPosX();
            int by1 = ground.getPosY();
            int bx2 = bx1 + ground.getWidth();
            int by2 = by1 + ground.getHeight();

            if (py2>by1 && py2<by2) {
                if (px2 > bx1 && px2<bx2) {
                    player.grounded = true;
                    return;
                }
                else if (px1>bx1 && px1 < bx2) {
                    player.grounded = true;
                    return;
                }
            }

            player.grounded = false;
        }
    }

    private void moveRight(int x) {

        int px1 = (int)player.getPlayerImage().getLayoutX()+x;
        int py1 = (int)player.getPlayerImage().getLayoutY();
        int px2 = px1 + player.WIDTH-1;
        int py2 = py1 + player.HEIGHT-1;

        for (Block block : gameEntities.blocks) {
            if (block == null) continue;
            // top collision
            int bx1 = block.getPosX();
            int by1 = block.getPosY();
            int bx2 = bx1 + block.getWidth();
            int by2 = by1 + block.getHeight();

            if (px2>bx1 && px2<bx2) {
                if (py2 > by1 && py2 < by2) {
                    return;
                } else if (py1 > by1 && py1 < by2) {
                    return;
                }
            }
        }

        player.getPlayerImage().setLayoutX(player.getPlayerImage().getLayoutX() + x);
    }

    private void moveLeft(int x) {

        int px1 = (int)player.getPlayerImage().getLayoutX() - x;
        int py1 = (int)player.getPlayerImage().getLayoutY();
        int px2 = px1 + player.WIDTH-1;
        int py2 = py1 + player.HEIGHT-1;

        for (Block block : gameEntities.blocks) {
            if (block == null) continue;

            int bx1 = block.getPosX();
            int by1 = block.getPosY();
            int bx2 = bx1 + block.getWidth();
            int by2 = by1 + block.getHeight();

            if (px1>bx1 && px1<bx2) {
                if (py2 > by1 && py2 < by2) {
                    return;
                } else if (py1 > by1 && py1 < by2) {
                    return;
                }
            }
        }

        player.getPlayerImage().setLayoutX(player.getPlayerImage().getLayoutX() - x);
    }

    public void start() {
    }
}

