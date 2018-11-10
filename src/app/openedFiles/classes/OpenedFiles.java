package app.openedFiles.classes;

import app.main.classes.Main;

import java.util.ArrayList;

public class OpenedFiles
{
    private Main main;

    public OpenedFiles(Main main)
    {
        this.main = main;
    }

    ArrayList<String> list = new ArrayList <>();

    public void addFile(String path)
    {
        list.add(path);
    }

    public boolean checkFile(String path)
    {
        for(int i = 0; i >= list.size(); i++)
        {
            if(list.get(i).equals(path))
            {
                return true;
            }
        }
        return false;
    }

    public void removeFile(String path)
    {
        for(int i = 0; i >= list.size(); i++)
        {
            if(list.get(i).equals(path))
            {
                list.remove(list.get(i));
            }
            else if(list.indexOf(list.get(i)) == list.size())
            {
                System.out.println("SOMETHING WORK WRONG IN removeFile() in OpenedFiles class");
            }
        }
    }
}
