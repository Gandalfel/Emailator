package app.emailTab.classes;

import app.main.classes.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import ma.MaReader;
import ma.MaWriter;

import java.io.IOException;
import java.util.Random;

public class EmailTabCreator
{
    private int id;
    private Main main;
    private String name;
    private EmailTab emailTab = new EmailTab(main);
    private String path;
    private String openedOrCreated = "";

    public EmailTabCreator(Main main, String name)
    {
        this.main = main;
        this.name = name;

        Random random = new Random();
        id = random.nextInt();
    }

    private Tab tab;
    private MaReader maReader;

    public void openNoNameTab() throws IOException
    {
        path = "any path";
        EmailTab emailTab = new EmailTab(main);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/app/EmailTab/fxml/emailTab.fxml"));
        loader.setController(emailTab);
        tab = loader.load();
        tab.setText(name);

        emailTab.notEditable();
        emailTab.setAuthorTextField(main.getLogin().getWrittenLogin());

        main.setTab(tab);
        main.getOpenedTabs().addEmailTabCreator(this);
        openedOrCreated = "default";
    }

    public void createTabForOpener(String path) throws IOException
    {
        this.path = path;
        maReader = new MaReader(path);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/app/EmailTab/fxml/emailTab.fxml"));
        loader.setController(emailTab);
        tab = loader.load();
        tab.setText(name);

        emailTab.notEditable();
        emailTab.setAuthorTextField(main.getLogin().getWrittenLogin());
        emailTab.setRecipientTextField(maReader.readRecipient());
        emailTab.setTopicTextField(maReader.readTopic());
        emailTab.setContent(maReader.readContent());

        main.setTab(tab);
        main.getOpenedTabs().addEmailTabCreator(this);
        openedOrCreated = "created";
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

        main.getOpenedTabs().addEmailTabCreator(this);

        main.setTab(tab);
        openedOrCreated = "opened";
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

    public String getOpenedOrCreated()
    {
        return openedOrCreated;
    }

    public int getId()
    {
        return id;
    }
}
