package app.login.classes;

import app.openedTabs.classes.OpenedTabs;
import app.main.classes.Main;
import app.start.Start;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

import static app.start.Start.getIcon;

public class Login
{
    private Start start;

    public Login(Start start)
    {
        this.start = start;
    }

    @FXML
    private TextField loginTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Button signInButton;

    private String login = "";
    private String password = "";

    private static Stage stage = new Stage();
    private OpenedTabs openedTabs;

    @FXML
    private void signInButtonAction(MouseEvent event) throws IOException
    {
        if (loginTextField.getText().equals(login) && passwordTextField.getText().equals(password))
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource("/app/main/fxml/main.fxml"));
            loader.setController(new Main(this));
            VBox vBox = loader.load();
            Scene scene = new Scene(vBox);
            scene.getStylesheets().add(
                    getClass().getClassLoader().getResource("stylesheets/mainStylesheet.css").toString());

            stage.setScene(scene);
            stage.show();
            stage.getIcons().add(getIcon());

            openedTabs = new OpenedTabs(this);

            start.closeLogin();
        }
    }

    public OpenedTabs getOpenedTabs()
    {
        return openedTabs;
    }

    public void closeMain()
    {
        stage.close();
    }

    public static Stage getMainStage()
    {
        return stage;
    }
}
