package app.fileOpener.classes;

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
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
import java.io.IOException;
import java.io.InvalidClassException;
import java.lang.management.RuntimeMXBean;

import static app.login.classes.Login.getMainStage;
import static app.start.Start.getIcon;

public class FileOpener
{
    protected Main main;

    public FileOpener(Main main)
    {
        this.main = main;
    }

    @FXML
    private Button helpButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Button finishButton;

    @FXML
    private TextField nameTextField;

    @FXML
    private Label errorLabel;

    @FXML
    private Button fileChooserButton;

    private Stage stageFileChooser;

    private File selectedFile;

    @FXML
    private void openFileChooser(MouseEvent event)
    {
        stageFileChooser = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose .ma file");

        File file = new File("C:/Users/Kuba/Desktop");
        fileChooser.setInitialDirectory(file);

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter(".ma (files for Emailator", "*.ma")
        );
        selectedFile = fileChooser.showOpenDialog(main.getFileOpenerStage());
        if (selectedFile != null)
        {
            nameTextField.setText(String.valueOf(selectedFile));
        }
    }

    @FXML
    private void cancelButtonClicked(MouseEvent event)
    {
        if (isHelperFromOpenerOpened())
        {
            helperStage.close();
            main.closeFileOpener();
        }
        else
        {
            main.closeFileOpener();
        }
    }

    private EmailTabCreator emailTabCreator;

    @FXML
    private void finishButtonClicked(ActionEvent event) throws IOException
    {
        if(selectedFile != null)
        {
            try
            {
                emailTabCreator = new EmailTabCreator(main, selectedFile.getName());
                emailTabCreator.createTabForOpener(nameTextField.getText());
                main.closeFileOpener();
                main.getOpenedTabs().addType("opened");
            }
            catch (NullPointerException e)
            {
                errorLabel.setText("Selected file does not exist");
            }
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
            helperStage.initOwner(main.getFileOpenerStage());
            helperStage.initOwner(getMainStage());
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

    public static boolean isHelperFromOpenerOpened()
    {
        return isHelperOpened;
    }

    public static void closeHelperFromFileOpener()
    {
        helperStage.close();
    }
}
