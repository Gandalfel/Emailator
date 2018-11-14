package ma;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class MaWriter
{
    /** * * * * * * * * * * * * * * * * * *
     *     ~~ MaWriter CONSTRUCTOR ~~     *
     * @param name - name of file         *
     * @param path - path to file         *
     * @throws IOException                *
     * Constructor of the MaWriter class. *
     * * * * * * * * * * * * * * * * * * */
    private String pathOfFile = "";
    private FileWriter mainFileWriter;

    public MaWriter(String path) throws IOException
    {
        this.pathOfFile = path;

        FileWriter mainFile = new FileWriter(pathOfFile);
        mainFile.close();
    }

    public String getPath()
    {
        return pathOfFile;
    }


    /** * * * * * * * * * * * * * *
     *     ~~ WRITE AUTHOR ~~     *
     * @param var - name of author*
     * * * * * * * * * * * * * * */
    private String author = "";

    public void writeAuthor(String var)
    {
        author = var;
    }

    public String getAuthor()
    {
        //email: xxx@gmail.com
        return author;
    }




    /** * * * * * * * * * * * * * * * *
     *      ~~ WRITE RECIPIENT ~~     *
     * @param var - name of recipient *
     * * * * * * * * * * * * * * * * */
    private String recipient = "";

    public void writeRecipient(String var)
    {
        recipient = var;
    }

    public String getRecipient()
    {
        //email: yyy@gmail.com
        return recipient;
    }




    /** * * * * * * * * * * * * * * * *
     *        ~~ WRITE DATE ~~        *
     * The method is for setting date.*
     * * * * * * * * * * * * * * * * */
    private String date = "";

    public void writeDate()
    {
        LocalDate localDate = LocalDate.now();
        date = String.valueOf(localDate.getDayOfMonth() + "-" + localDate.getMonthValue() + "-" + localDate.getYear());
    }

    public String getDate()
    {
        //date: (dd-mm-yyyy)
        return date;
    }




    /** * * * * * * * * * * * * * * *
     *      ~~ WRITE TIME ~~        *
     * The method is for write time.*
     * * * * * * * * * * * * * * * */
    private String time = "";

    public void writeTime()
    {
        LocalTime localTime = LocalTime.now();
        time = localTime.getHour() + "-" + localTime.getMinute() + "-" + localTime.getSecond();
    }

    public String getTime()
    {
        //time: (hh-mm-ss)
        return time;
    }




    /** * * * * * * * * * * * * * * *
     *      ~~ WRITE TOPIC ~~       *
     * @param var - content of topic*
     */
    private String topic = "";

    public void writeTopic(String var)
    {
        topic = var;
    }

    public String getTopic()
    {
        //topic: xxxxx
        return topic;
    }




    /** * * * * * * * * * * * * * * *
     *      ~~ WRITE CONTENT ~~      *
     * @param var - content of email *
     * * * * * * * * * * * * * * *  */
    private String content = "";

    public void writeContent(String var)
    {
        content = var;
    }

    public String getContent()
    {
        //content: Hello my friend...
        return content;
    }




    /** * * * * * * * * * * * * * * * * * * * *
     *             ~~ WRITE ALL ~~             *
     * @throws IOException                     *
     * The method write all parameters to file.*
     * * * * * * * * * * * * * * * * * * * * * */
    public void writeAll() throws IOException
    {
        String author = getAuthor();
        String recipient = getRecipient();
        String date = getDate();
        String time = getTime();
        String topic = getTopic();
        String contentOfEmail = getContent();

        mainFileWriter = new FileWriter(pathOfFile);
        mainFileWriter.write("author:"+author+System.getProperty("line.separator") + "recipient:"+recipient+System.getProperty("line.separator") + "date:"+date+System.getProperty("line.separator") + "time:"+time+System.getProperty("line.separator") + "topic:"+topic+System.getProperty("line.separator") + "content:"+content+System.getProperty("line.separator"));
        mainFileWriter.flush();
        mainFileWriter.close();
        //System.out.println("author"+author+System.getProperty("line.separator") + "recipient"+recipient+System.getProperty("line.separator") + "date"+date+System.getProperty("line.separator") + "time"+time+System.getProperty("line.separator") + "topic"+topic+System.getProperty("line.separator") + "content"+content+System.getProperty("line.separator"));
    }
}
