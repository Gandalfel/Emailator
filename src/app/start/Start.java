package app.start;

import app.login.classes.Login;
import app.main.classes.Main;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;

public class Start extends Application
{
    public static void main(String[] args)
    {
        launch();
    }

    private Stage stage = new Stage();
    private static Image icon = new Image(Start.class.getResourceAsStream("/img/icon.png"));
    private Login login = new Login(this);

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/app/login/fxml/login.fxml"));
        loader.setController(login);
        VBox vBox = loader.load();
        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.getIcons().add(icon);
        stage.show();
    }

    public Login getLogin()
    {
        return login;
    }

    public void closeLogin()
    {
        stage.close();
    }

    public static Image getIcon()
    {
        return icon;
    }
}
