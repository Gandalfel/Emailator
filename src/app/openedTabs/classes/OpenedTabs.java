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

    private ArrayList<EmailTabCreator> list = new ArrayList<>();

    public boolean checkFile(String path)
    {
        for (int i = 0; i <= list.size()-1; i++)
        {
            if (list.get(i).getPath().equals(path))
            {
                return true;
            }
        }
        return false;
    }

    public void addEmailTabCreator(EmailTabCreator emailTabCreator)
    {
        list.add(emailTabCreator);
    }

    public EmailTabCreator getEmailTabCreator(int id)
    {
        EmailTabCreator etc = null;
        for (int i = 0; i <= list.size(); i++)
        {
            if (list.get(i).getId() == id)
            {
                etc = list.get(id);
                break;
            }
        }
        return etc;
    }

    public void removeEmailTabCreator(EmailTabCreator emailTabCreator)
    {
        for (int i = 0; i <= list.size(); i++)
        {
            if (list.get(i).getId() == emailTabCreator.getId())
            {
                list.remove(i);
                break;
            }
        }
    }

    public EmailTabCreator getEmailTabCreator(Tab tab)
    {
        EmailTabCreator etc = null;
        for (int i = 0; i <= list.size(); i++)
        {
            if (list.get(i).getTab().getId()==tab.getId())
            {
                etc = list.get(i);
                break;
            }
        }
        return etc;
    }

    public int size()
    {
        return list.size();
    }
}
