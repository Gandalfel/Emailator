package app.emailTab.classes;

import app.main.classes.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import ma.MaReader;
import ma.MaWriter;

import java.io.IOException;

public class EmailTabCreator
{
    private Main main;
    private String name;
    private EmailTab emailTab = new EmailTab();
    private String path;

    public EmailTabCreator(Main main, String name)
    {
        this.main = main;
        this.name = name;
    }

    private Tab tab;
    private MaReader maReader;

    public void createTabForOpener(String path) throws IOException
    {
        this.path = path;
        maReader = new MaReader(path);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/app/EmailTab/fxml/emailTab.fxml"));
        loader.setController(emailTab);
        tab = loader.load();
        tab.setText(name);

        emailTab.setAuthorTextField(maReader.readAuthor());
        emailTab.setRecipientTextField(maReader.readRecipient());
        emailTab.setTopicTextField(maReader.readTopic());
        emailTab.setContent(maReader.readContent());

        main.getOpenedFiles().addFile(path);
        main.setTab(tab);
        main.getOpenedTabs().addIdOfTab(tab);
        main.getOpenedTabs().addEmailTabCreator(this);
    }

    private MaWriter maWriter;

    public void createTabForCreator(String path) throws IOException
    {
        this.path = path;
        maWriter = new MaWriter(path);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/app/EmailTab/fxml/emailTab.fxml"));
        loader.setController(emailTab);

        tab = loader.load();
        tab.setText(name);

        main.getOpenedTabs().addIdOfTab(tab);
        main.getOpenedTabs().addEmailTabCreator(this);

        main.getOpenedFiles().addFile(path);
        main.setTab(tab);
    }

    public MaReader getMaReader()
    {
        return maReader;
    }

    public MaWriter getMaWriter()
    {
        return maWriter;
    }

    public String getPath()
    {
        return path;
    }

    public EmailTab getEmailTab()
    {
        return emailTab;
    }

    public Tab getTab()
    {
        return tab;
    }
}
