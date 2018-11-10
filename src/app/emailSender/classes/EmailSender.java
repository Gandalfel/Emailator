package app.emailSender.classes;

import app.emailTab.classes.EmailTabCreator;
import app.main.classes.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.text.Text;

public class EmailSender
{
    private Main main;

    public EmailSender(Main main)
    {
        this.main = main;
    }
    @FXML
    private Button cancelButton;

    @FXML
    private Button sendButton;

    @FXML
    private Text emailTextField = new Text();

    @FXML
    private void cancelButtonClicked(ActionEvent event)
    {
        main.closeEmailSenderStage();
    }

    @FXML
    private void sendButtonClicked(ActionEvent event)
    {
        //sendEmailWithX();
        Tab selectedTab = main.getTabPane().getSelectionModel().getSelectedItem();
        EmailTabCreator emailTabCreator = main.getOpenedTabs().getEmailTabCreator(Integer.parseInt(selectedTab.getId()));

        /*System.out.println("AUTHOR: " + emailTabCreator.getEmailTab().getAuthor());
        System.out.println("RECIPIENT: " + emailTabCreator.getEmailTab().getRecipient());
        System.out.println("TOPIC: " + emailTabCreator.getEmailTab().getTopic());
        System.out.println("CONTENT: " + emailTabCreator.getEmailTab().getContent());*/
    }

    public void setEmailTextField(String s)
    {
        emailTextField.setText(s);
    }
}
