package app.fileCreator.classes;

import app.emailTab.classes.EmailTab;
import app.emailTab.classes.EmailTabCreator;
import app.main.classes.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import java.io.IOException;

import static app.login.classes.Login.getMainStage;
import static app.start.Start.getIcon;

public class FileCreator
{
    private EmailTab emailTab = new EmailTab();
    private Main main;

    public FileCreator(Main main)
    {
        this.main = main;
    }

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button helpButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Button finishButton;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField pathTextField;

    @FXML
    private Label errorLabel;

    private String nameOfFile = "";
    private String pathOfFile = "";

    public String getPath()
    {
        return pathTextField.getText() + "\\" + nameTextField.getText();
    }

    @FXML
    private void cancelButtonClicked(MouseEvent event)
    {
        if (isHelperFromCreatorOpened())
        {
            helperStage.close();
            main.closeFileCreator();
        }
        else
        {
            main.closeFileCreator();
        }
    }

    EmailTabCreator emailTabCreator;

    @FXML
    private void finishButtonClicked(ActionEvent event) throws IOException {
        this.nameOfFile = nameTextField.getText();
        this.pathOfFile = pathTextField.getText();

        String nameOfFile = this.nameOfFile;
        String pathOfFile = this.pathOfFile;

        if (nameOfFile.length() <= 1)
        {
            errorLabel.setText("*too few characters");
        }
        else if (nameOfFile.length() > 20)
        {
            errorLabel.setText("*too many characters");
        }
        else if (nameOfFile.contains("\""))
        {
            errorLabel.setText("*do not use apostrophe ---> \"");
        }
        else if (nameOfFile.contains("\\"))
        {
            errorLabel.setText("*do not use back slash ---> \\");
        }
        else if (nameOfFile.contains("/"))
        {
            errorLabel.setText("*do not use slash ---> /");
        }
        else if (nameOfFile.contains(":"))
        {
            errorLabel.setText("*do not use colon ---> :");
        }
        else if (nameOfFile.contains("*"))
        {
            errorLabel.setText("*do not use star ---> *");
        }
        else if (nameOfFile.contains(">"))
        {
            errorLabel.setText("*do not use right sharp bracket ---> >");
        }
        else if (nameOfFile.contains("<"))
        {
            errorLabel.setText("*do not use left sharp bracket ---> <");
        }
        else if (nameOfFile.contains("?"))
        {
            errorLabel.setText("*do not use question mark ---> ?");
        }
        else if (nameOfFile.contains("|"))
        {
            errorLabel.setText("*do not use vertical line ---> |");
        }
        else
        {
            emailTabCreator = new EmailTabCreator(main, nameTextField.getText());
            emailTabCreator.createTabForCreator(pathTextField.getText() + "\\" + nameTextField.getText());
            main.closeFileCreator();
            main.getOpenedTabs().addType("created");
        }
    }

    private static Stage helperStage;
    private static boolean isHelperOpened = false;

    @FXML
    private void helpButtonClicked(MouseEvent event) throws IOException
    {
        if (isHelperOpened == false)
        {
            isHelperOpened = true;
            helperStage = new Stage();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource("/app/fileCreator/fxml/helper.fxml"));
            AnchorPane anchorPane = loader.load();
            Scene scene = new Scene(anchorPane);
            scene.getStylesheets().add(
                    getClass().getClassLoader().getResource("stylesheets/mainStylesheet.css").toString());
            helperStage.initOwner(main.getFileCreatorStage());
            helperStage.initOwner(getMainStage());
            helperStage.initModality(Modality.WINDOW_MODAL);
            helperStage.setScene(scene);
            helperStage.setResizable(false);
            helperStage.getIcons().add(getIcon());
            helperStage.show();
        }

        helperStage.setOnCloseRequest(new EventHandler<WindowEvent>()
        {
            public void handle(WindowEvent we)
            {
                isHelperOpened = false;
            }
        });
    }

    public static boolean isHelperFromCreatorOpened()
    {
        return isHelperOpened;
    }

    public static void closeHelperFromFileCreator()
    {
        helperStage.close();
    }
}
