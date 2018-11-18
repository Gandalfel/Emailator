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

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static app.start.Start.getIcon;

public class Login
{
    private static Start start;

    public Login(Start start) throws IOException
    {
        this.start = start;
    }

    @FXML
    private TextField loginTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Button signInButton;

    private String writtenLogin = "";
    private String writtenPassword = "";
    private boolean isCorrectPassword;
    private boolean isCorrectLogin;

    private static Stage stage = new Stage();
    private OpenedTabs openedTabs;
    private Socket socket = new Socket("127.0.0.1", 2020);
    private DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
    private DataInputStream inputStream;

    @FXML
    private void signInButtonAction(MouseEvent event) throws IOException
    {
        inputStream = new DataInputStream(socket.getInputStream());
        writtenLogin = loginTextField.getText();
        writtenPassword = passwordTextField.getText();
        outputStream.writeUTF(loginTextField.getText());
        outputStream.writeUTF(passwordTextField.getText());

        boolean isValid = true;
        inputStream.readBoolean();

        if (isValid)
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

    public String getWrittenPassword()
    {
        return writtenPassword;
    }

    public String getWrittenLogin()
    {
        return writtenLogin;
    }
}
