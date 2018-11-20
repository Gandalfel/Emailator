package app.deleteFileDialogWindow.classes;

import app.emailTab.classes.EmailTabCreator;
import app.main.classes.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.text.Text;

public class DeleteFileDialogWindow
{
    private Main main;
    public DeleteFileDialogWindow(Main main)
    {
        this.main = main;
    }

    @FXML
    private Button cancelButton;

    @FXML
    private Button okButton;

    @FXML
    private Text fileNameText;

    @FXML
    private void okClicked(ActionEvent event)
    {
        if (main.getOpenedTabs().getTabsCount() >= 1)
        {
            Tab selectedTab = main.getTabPane().getSelectionModel().getSelectedItem();
            EmailTabCreator emailTabCreator = main.getOpenedTabs().getEmailTabCreator(Integer.parseInt(selectedTab.getId()));

            System.out.println("PATH: " + emailTabCreator.getPath());
            System.out.println("IS FILE?: " + main.isFile(emailTabCreator.getPath()));
            /*if (main.isFile(emailTabCreator.getPath()))
            {*/
                System.out.println("makapakapa");
                main.getOpenedTabs().removeEmailTabCreator(emailTabCreator);
                main.getTabPane().getTabs().removeAll(selectedTab);
            //}
            main.closeDeleteFileDialogWindowStage();
        }
    }

    @FXML
    private void cancelClicked(ActionEvent event)
    {
        main.closeDeleteFileDialogWindowStage();
    }
}
