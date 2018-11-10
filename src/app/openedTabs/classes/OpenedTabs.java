package app.openedTabs.classes;

import app.emailTab.classes.EmailTabCreator;
import app.login.classes.Login;
import javafx.scene.control.Tab;

import java.util.ArrayList;

public class OpenedTabs
{
    private Login login;

    public OpenedTabs(Login login)
    {
        this.login = login;
    }

    private ArrayList<EmailTabCreator> listOfEmailTabCreator = new ArrayList<>();
    private ArrayList<String> listOfIds = new ArrayList<>();
    private ArrayList<String> listOpenedOrCreated = new ArrayList<>();
    private int lastId = -1;

    public void addEmailTabCreator(EmailTabCreator emailTabCreator)
    {
        listOfEmailTabCreator.add(emailTabCreator);
    }

    public void addType(String s)
    {
        if (s.equals("opened") || s.equals("created"))
        {
            listOpenedOrCreated.add(s);
        }
    }

    public String getType(int id)
    {
        for (int i = 0; i <= listOpenedOrCreated.size(); i++)
        {
            if (listOfIds.get(i).equals(String.valueOf(id)))
            {
                return listOpenedOrCreated.get(id);
            }
        }
        return null;
    }

    public void addIdOfTab(Tab tab)
    {
        tab.setId(String.valueOf(lastId+1));
        lastId++;
        listOfIds.add(tab.getId());
    }

    public EmailTabCreator getEmailTabCreator(int id)
    {
        EmailTabCreator etc = null;
        for (int i = 0; i <= listOfIds.size(); i++)
        {
            if (listOfIds.get(i).equals(String.valueOf(id)))
            {
                etc = listOfEmailTabCreator.get(id);
                break;
            }
        }
        return etc;
    }

    public int size()
    {
        return listOfEmailTabCreator.size();
    }
}
