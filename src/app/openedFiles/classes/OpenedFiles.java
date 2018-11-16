package app.openedFiles.classes;

import app.main.classes.Main;

import java.io.File;
import java.util.ArrayList;

public class OpenedFiles
{
    private Main main;

    public OpenedFiles(Main main)
    {
        this.main = main;
    }

    private ArrayList<String> list = new ArrayList <>();

    public void addFile(String path)
    {
        list.add(path);
        System.out.println("--ADD--");
        System.out.println("size: " + list.size());
        System.out.println("list" + list);
        System.out.println("path" + path);
    }

    public boolean checkFile(String path)
    {
        for (int i = 0; i <= list.size()-1; i++)
        {
            System.out.println(i);
            if (list.get(i).equals(path))
            {
                System.out.println(list.get(i));
                return true;
            }
        }
        return false;
    }

    public void removeFile(String path)
    {
        System.out.println("--REMOVE-- AFTER REMOVE");
        System.out.println("size: " + list.size());
        System.out.println("list" + list);
        System.out.println("path" + path);
        for(int i = 0; i <= list.size(); i++)
        {
            if(list.get(i).equals(path))
            {
                File file = new File(list.get(i));
                file.delete();
                list.remove(list.get(i));
            }
            else if(list.indexOf(list.get(i)) == list.size())
            {
                System.out.println("SOMETHING WORK WRONG IN removeFile() in OpenedFiles class");
            }
        }
        System.out.println("--REMOVE-- BEFORE REMOVE");
        System.out.println("size: " + list.size());
        System.out.println("list" + list);
        System.out.println("path" + path);
    }
}
