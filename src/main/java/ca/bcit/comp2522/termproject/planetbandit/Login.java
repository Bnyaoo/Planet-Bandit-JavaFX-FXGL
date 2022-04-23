package ca.bcit.comp2522.termproject.planetbandit;

import com.almasb.fxgl.animation.Animation;
import com.almasb.fxgl.animation.Interpolators;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.core.util.EmptyRunnable;
import com.almasb.fxgl.dsl.FXGL;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Represents a Login window and connects to a database.
 *
 * @author Benny and Prab
 * @version 2022
 */
public final class Login extends FXGLMenu {
    private static final int SIZE = 150;
    private static final int H_GAP = 10;
    private static final int V_GAP = 10;
    private static final int FONT_SIZE = 12;
    private static final int GRID_FOUR = 4;
    private static final double DURATION = 0.66;

    private final Animation<?> animation;

    /**
     * Constructs a login screen.
     */
    public Login() {
        super(MenuType.MAIN_MENU);

        getContentRoot().setTranslateX(FXGL.getAppWidth() / 2.0 - SIZE);
        getContentRoot().setTranslateY(FXGL.getAppHeight() / 2.0 - SIZE);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(H_GAP);
        grid.setVgap(V_GAP);

        Text sceneTitle = new Text("Please enter your username and password.\n (New inputs will be added.)");
        sceneTitle.setFont(Font.font(FONT_SIZE));
        grid.add(sceneTitle, 0, 0, 2, 1);

        Label userName = new Label("Username:");
        grid.add(userName, 0, 1);

        TextField newUsername = new TextField();
        grid.add(newUsername, 1, 1);

        Label password = new Label("Password:");
        grid.add(password, 0, 2);

        PasswordField newPassword = new PasswordField();
        grid.add(newPassword, 1, 2);

        Button login = new Button("Login");
        login.setAlignment(Pos.BOTTOM_RIGHT);
        grid.add(login, 1, GRID_FOUR);
        login.setOnAction(actionEvent -> {
            try {
                boolean success = checkLogin(newUsername.getText(), newPassword.getText());
                if (success) {
                    System.out.println("successfully logged in");
                    fireNewGame();
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        });

        getContentRoot().getChildren().add(grid);

        getContentRoot().setScaleX(0);
        getContentRoot().setScaleY(0);

        animation = FXGL.animationBuilder()
                .duration(Duration.seconds(DURATION))
                .interpolator(Interpolators.EXPONENTIAL.EASE_OUT())
                .scale(getContentRoot())
                .from(new Point2D(0, 0))
                .to(new Point2D(1, 1))
                .build();

        System.out.println("constructed Login");
    }

    private static boolean checkLogin(final String newUsername, final String newPassword)
            throws ClassNotFoundException, SQLException {
        // We register the Driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        System.out.println("am I running?");

        // We identify the driver, the rdbms, the host, the port, and the schema name
        final String url = "jdbc:mysql://localhost:3306/planetbandit?enabledTLSProtocols=TLSv1.2";

        // We need to send a user and a password when we try to connect!
        final Properties connectionProperties = new Properties();
        connectionProperties.put("user", "comp2522");
        connectionProperties.put("password", "I was born in 1973");

        // We establish a connection...
        final Connection connection = DriverManager.getConnection(url, connectionProperties);
        if (connection != null) {
            System.out.println("Successfully connected to MySQL database test");
        }

        // Create a statement to send on the connection...
        assert connection != null;
        Statement stmt = connection.createStatement();

        PreparedStatement check = connection.prepareStatement(
                "SELECT * FROM Users WHERE user_id = ? AND password = ?");
        check.setString(1, newUsername);
        check.setString(2, newPassword);

        if (!check.executeQuery().next()) {

            // Use a prepared statement to insert the data collected from the login box to the database
            PreparedStatement insert = connection.prepareStatement(
                    "INSERT INTO users (user_ID, password) VALUES(?,?)");

            // insert the login form values into the database
            insert.setString(1, newUsername);
            insert.setString(2, newPassword);

            int i = insert.executeUpdate();
            System.out.println(i + "Records inserted");
        }
        return true;
    }

    @Override
    public void onCreate() {
        animation.setOnFinished(EmptyRunnable.INSTANCE);
        animation.stop();
        animation.start();
    }

    @Override
    protected void onUpdate(final double tpf) {
        animation.onUpdate(tpf);
    }
}
