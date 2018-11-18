package app.main.classes;

import app.deleteFileDialogWindow.classes.DeleteFileDialogWindow;
import app.emailSender.classes.EmailSender;
import app.emailTab.classes.EmailTabCreator;
import app.fileCreator.classes.FileCreator;
import app.fileOpener.classes.FileOpener;
import app.openedFiles.classes.OpenedFiles;
import app.openedTabs.classes.OpenedTabs;
import app.login.classes.Login;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.*;
import ma.MaWriter;

import java.io.File;
import java.io.IOException;
import static app.fileCreator.classes.FileCreator.closeHelperFromFileCreator;
import static app.fileCreator.classes.FileCreator.isHelperFromCreatorOpened;
import static app.fileOpener.classes.FileOpener.closeHelperFromFileOpener;
import static app.fileOpener.classes.FileOpener.isHelperFromOpenerOpened;
import static app.start.Start.getIcon;

public class Main
{
    private Login login;

    public Main(Login login)
    {
        this.login = login;
    }

    @FXML
    private MenuBar menuBar = new MenuBar();

    @FXML
    private VBox vBox;

    @FXML
    private MenuItem emailItem;

    @FXML
    private MenuItem openFileMenuItem;

    @FXML
    private MenuItem exitItem;

    @FXML
    private StackPane stackPaneLeftPanelOfSplitPane;

    @FXML
    private AnchorPane anchorPaneFromSplitPane;

    @FXML
    private TabPane tabPane;

    @FXML
    private MenuItem senEmail;

    @FXML
    private MenuItem saveMenuItem;

    @FXML
    private MenuItem saveAsMenuItem;

    @FXML
    private MenuItem deleteItem;

    @FXML
    private Menu closeTheTabItem = new Menu();

    @FXML
    private void closeTheTabClicked(ActionEvent event)
    {
        Tab selectedTab = getTabPane().getSelectionModel().getSelectedItem();
        EmailTabCreator emailTabCreator = getOpenedTabs().getEmailTabCreator(Integer.parseInt(selectedTab.getId()));

        tabPane.getTabs().removeAll(selectedTab);
    }

    private Stage deleteFileDialogWindowStage;

    @FXML
    private void deleteClicked(ActionEvent event) throws IOException
    {
        if (this.getOpenedTabs().size() >= 1)
        {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/app/deleteFileDialogWindow/fxml/deleteFileDialogWindow.fxml"));
            loader.setController(new DeleteFileDialogWindow(this));
            AnchorPane anchorPane = loader.load();
            Scene scene = new Scene(anchorPane);
            scene.getStylesheets().add(
                    getClass().getClassLoader().getResource("stylesheets/mainStylesheet.css").toString());
            deleteFileDialogWindowStage = new Stage();
            deleteFileDialogWindowStage.initOwner(login.getMainStage());
            deleteFileDialogWindowStage.initModality(Modality.APPLICATION_MODAL);
            deleteFileDialogWindowStage.setScene(scene);
            deleteFileDialogWindowStage.getIcons().add(getIcon());
            deleteFileDialogWindowStage.show();
        }
    }

    public void closeDeleteFileDialogWindowStage()
    {
        deleteFileDialogWindowStage.close();
    }

    private EmailSender emailSender;

    @FXML
    private void saveClicked(ActionEvent event) throws IOException
    {
        if(login.getOpenedTabs().size() >= 1)
        {
            Tab selectedTab = getTabPane().getSelectionModel().getSelectedItem();
            EmailTabCreator emailTabCreator = getOpenedTabs().getEmailTabCreator(Integer.parseInt(selectedTab.getId()));

            if ( getOpenedTabs().getType(Integer.parseInt(emailTabCreator.getTab().getId())).equals("opened"))
            {
                MaWriter maWriter = new MaWriter(emailTabCreator.getMaReader().getPath());
                maWriter.writeAuthor(emailTabCreator.getEmailTab().getAuthor());
                maWriter.writeRecipient(emailTabCreator.getEmailTab().getRecipient());
                maWriter.writeDate();
                maWriter.writeTime();
                maWriter.writeTopic(emailTabCreator.getEmailTab().getTopic());
                maWriter.writeContent(emailTabCreator.getEmailTab().getContent());
                maWriter.writeAll();
            }
            else if (getOpenedTabs().getType(Integer.parseInt(emailTabCreator.getTab().getId())).equals("created"))
            {
                MaWriter maWriter = new MaWriter(emailTabCreator.getMaWriter().getPath());
                maWriter.writeAuthor(emailTabCreator.getEmailTab().getAuthor());
                maWriter.writeRecipient(emailTabCreator.getEmailTab().getRecipient());
                maWriter.writeDate();
                maWriter.writeTime();
                maWriter.writeTopic(emailTabCreator.getEmailTab().getTopic());
                maWriter.writeContent(emailTabCreator.getEmailTab().getContent());
                maWriter.writeAll();
            }
            else
            {
                /*
                zrób aby to sie wyświetlało w konsoli programu
                 */
                System.out.println("ERROR IN 82 line in Main class");
            }
        }
    }

    @FXML
    private void saveAsClicked(ActionEvent event) throws IOException
    {
        if(login.getOpenedTabs().size() >= 1)
        {
            Tab selectedTab = getTabPane().getSelectionModel().getSelectedItem();
            EmailTabCreator emailTabCreator = getOpenedTabs().getEmailTabCreator(Integer.parseInt(selectedTab.getId()));

            FileChooser fileChooser1 = new FileChooser();
            fileChooser1.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter(".ma (files for Emailator", "*.ma")
            );
            File hereIsSavedFile = fileChooser1.showSaveDialog(login.getMainStage());

            if(hereIsSavedFile != null)
            {
                MaWriter maWriter = new MaWriter(hereIsSavedFile.getPath());
                maWriter.writeAuthor(emailTabCreator.getEmailTab().getAuthor());
                maWriter.writeRecipient(emailTabCreator.getEmailTab().getRecipient());
                maWriter.writeTopic(emailTabCreator.getEmailTab().getTopic());
                maWriter.writeContent(emailTabCreator.getEmailTab().getContent());
                maWriter.writeDate();
                maWriter.writeTime();
                maWriter.writeAll();
            }
            else
            {
                System.out.println("hereIsSavedFile" + hereIsSavedFile);
            }
        }
    }

    private Stage sendEmailStage;

    @FXML
    private void sendEmailClicked(Event event) throws IOException
    {
        if(login.getOpenedTabs().size() >= 1)
        {
            sendEmailStage = new Stage();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource("/app/emailSender/fxml/emailSender.fxml"));
            loader.setController(emailSender = new EmailSender(this));
            AnchorPane anchorPane = loader.load();
            Scene scene = new Scene(anchorPane);
            scene.getStylesheets().add(
                    getClass().getClassLoader().getResource("stylesheets/mainStylesheet.css").toString());
            sendEmailStage.setScene(scene);
            sendEmailStage.setResizable(false);
            sendEmailStage.initOwner(login.getMainStage());
            sendEmailStage.initModality(Modality.APPLICATION_MODAL);
            sendEmailStage.getIcons().add(getIcon());

            Tab selectedTab = getTabPane().getSelectionModel().getSelectedItem();
            EmailTabCreator emailTabCreator = getOpenedTabs().getEmailTabCreator(Integer.parseInt(selectedTab.getId()));
            emailSender.setEmailTextField(emailTabCreator.getEmailTab().getAuthor());

            sendEmailStage.show();
        }
    }

    public void closeEmailSenderStage()
    {
        sendEmailStage.close();
    }


    @FXML
    private void exit(ActionEvent event)
    {
        login.closeMain();
        Platform.exit();
    }

    public void setTab(Tab tab)
    {
        tabPane.getTabs().add(tab);
    }

    private Stage fileCreatorStage;
    private FileCreator fileCreator;

    @FXML
    private void fileCreator(ActionEvent event) throws IOException
    {
        fileCreatorStage = new Stage();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/app/fileCreator/fxml/fileCreator.fxml"));
        loader.setController(fileCreator = new FileCreator(this));
        AnchorPane anchorPane = loader.load();
        Scene scene = new Scene(anchorPane);
        scene.getStylesheets().add(
                getClass().getClassLoader().getResource("stylesheets/mainStylesheet.css").toString());

        fileCreatorStage.initOwner(login.getMainStage());
        fileCreatorStage.initModality(Modality.APPLICATION_MODAL);
        fileCreatorStage.setScene(scene);
        fileCreatorStage.setResizable(false);
        fileCreatorStage.getIcons().add(getIcon());
        fileCreator.notEditable();
        fileCreatorStage.show();

        fileCreatorStage.setOnCloseRequest(new EventHandler<WindowEvent>()
        {
            public void handle(WindowEvent we)
            {
                if (isHelperFromCreatorOpened())
                {
                    closeHelperFromFileCreator();
                }
            }
        });
    }

    public void closeFileCreator()
    {
        fileCreatorStage.close();
    }

    public Stage getFileCreatorStage()
    {
        return fileCreatorStage;
    }

    private Stage fileOpenerStage;
    private FileOpener fileOpener;
    @FXML
    private void fileOpener(ActionEvent event) throws IOException
    {
        fileOpenerStage = new Stage();
        fileOpener = new FileOpener(this);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/app/fileOpener/fxml/fileOpener.fxml"));
        loader.setController(fileOpener);
        AnchorPane anchorPane = loader.load();
        Scene scene = new Scene(anchorPane);
        scene.getStylesheets().add(
                getClass().getClassLoader().getResource("stylesheets/mainStylesheet.css").toString());

        fileOpenerStage.initOwner(login.getMainStage());
        fileOpenerStage.initModality(Modality.APPLICATION_MODAL);
        fileOpenerStage.setScene(scene);
        fileOpenerStage.setResizable(false);
        fileOpenerStage.getIcons().add(getIcon());
        fileOpener.notEditable();
        fileOpenerStage.show();

        fileOpenerStage.setOnCloseRequest(new EventHandler<WindowEvent>()
        {
            public void handle(WindowEvent we)
            {
                if (isHelperFromOpenerOpened())
                {
                    closeHelperFromFileOpener();
                }
            }
        });
    }

    public void closeFileOpener()
    {
        fileOpenerStage.close();
    }

    public Stage getFileOpenerStage()
    {
        return fileOpenerStage;
    }

    public void closeTab(int index)
    {
        tabPane.getTabs().remove(index);
    }

    public TabPane getTabPane()
    {
        return tabPane;
    }

    public OpenedTabs getOpenedTabs()
    {
        return login.getOpenedTabs();
    }

    private OpenedFiles openedFiles = new OpenedFiles(this);

    public OpenedFiles getOpenedFiles()
    {
        return openedFiles;
    }

    public boolean isFile(String path)
    {
        File file = null;
        try
        {
            file = new File(path);
        }
        catch (NullPointerException e) {}
        finally
        {
            try
            {
                if (file.exists())
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
            catch (NullPointerException e) {return false;}
        }
    }

    public Login getLogin()
    {
        return login;
    }
}
