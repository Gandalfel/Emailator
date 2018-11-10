package app.emailTab.classes;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class EmailTab
{
    @FXML
    private TextField authorTextField = new TextField();

    @FXML
    private TextField recipientTextField = new TextField();

    @FXML
    private TextField topicTextField = new TextField();

    @FXML
    private TextArea textArea = new TextArea();

    private String author = "";
    private String recipient = "";
    private String topic = "";
    private String content = "";

    public void setAuthorTextField(String author)
    {
        this.author = author;
        authorTextField.setText(this.author);
    }

    public void setRecipientTextField(String recipient)
    {
        this.recipient = recipient;
        recipientTextField.setText(this.recipient);
    }

    public void setTopicTextField(String topic)
    {
        this.topic = topic;
        topicTextField.setText(this.topic);
    }

    public void setContent(String content)
    {
        this.content = content;
        textArea.setText(this.content);
    }

    public String getAuthor()
    {
        return authorTextField.getText();
    }

    public String getRecipient()
    {
        return recipientTextField.getText();
    }

    public String getTopic()
    {
        return topicTextField.getText();
    }

    public String getContent()
    {
        return textArea.getText();
    }
}
