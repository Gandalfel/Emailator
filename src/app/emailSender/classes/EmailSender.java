package app.emailSender.classes;

import app.emailTab.classes.EmailTabCreator;
import app.main.classes.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.text.Text;

import javax.mail.MessagingException;

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
    private void sendButtonClicked(ActionEvent event) throws MessagingException
    {
        Tab selectedTab = main.getTabPane().getSelectionModel().getSelectedItem();
        EmailTabCreator emailTabCreator = main.getOpenedTabs().getEmailTabCreator(selectedTab);

        main.closeEmailSenderStage();
        new Email(main.getLogin().getWrittenLogin(), main.getLogin().getWrittenPassword(),
                emailTabCreator.getEmailTab().getRecipient(), emailTabCreator.getEmailTab().getTopic(),
                emailTabCreator.getEmailTab().getContent());
    }

    public void setEmailTextField(String s)
    {
        emailTextField.setText(s);
    }
}
