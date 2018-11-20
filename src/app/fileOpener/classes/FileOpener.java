package app.fileOpener.classes;

import app.emailTab.classes.EmailTab;
import app.emailTab.classes.EmailTabCreator;
import app.main.classes.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
        if (!main.getOpenedTabs().checkFile(nameTextField.getText()))
        {
            if(selectedFile != null)
            {
                try
                {
                    System.out.println("FIRST IFFFFF");
                    emailTabCreator = new EmailTabCreator(main, selectedFile.getName().trim());
                    emailTabCreator.createTabForOpener(nameTextField.getText().trim());
                    main.closeFileOpener();
                }
                catch (NullPointerException e)
                {
                    errorLabel.setText("Selected file does not exist");
                }
            }
        }
        else if(main.getOpenedTabs().checkFile(selectedFile.getName()))
        {
            if (main.getOpenedTabs().getTabsCount() >= 1)
            {
                System.out.println("TWICE IFFFFFF");
                Tab selectedTab = main.getTabPane().getSelectionModel().getSelectedItem();
                EmailTabCreator emailTabCreator = main.getOpenedTabs().getEmailTabCreator(Integer.parseInt(selectedTab.getId()));

                SingleSelectionModel<Tab> selectionModel = main.getTabPane().getSelectionModel();
                selectionModel.select(emailTabCreator.getTab());
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

    public void notEditable()
    {
        nameTextField.setEditable(false);
        nameTextField.setMouseTransparent(true);
        nameTextField.setFocusTraversable(false);
    }
}
