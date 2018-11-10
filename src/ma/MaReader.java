package ma;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MaReader
{
    private String pathOfFile = "";
    private File fileReader;

    public MaReader(String path) throws IOException
    {
        pathOfFile = path;
        StringBuilder sb = new StringBuilder("");
        sb.append(path.charAt(path.length() - 3));
        sb.append(path.charAt(path.length() - 2));
        sb.append(path.charAt(path.length() - 1));

        if (String.valueOf(sb).equals(".ma"))
        {
            File fileReader = new File(path);
        }
        else if (!String.valueOf(sb).equals(".ma"))
        {
            System.out.println("CRITICAL ERROR: CHECKED FILE IS NOT A .MA FILE");
        }

        fileReader = new File(pathOfFile);
    }

    public String getPath()
    {
        return pathOfFile;
    }


    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  *
     *                             ~~ READ AUTHOR ~~                            *
     *                                                                          *
     * The method can read date from .ma file and returning author using String.*
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */
    private String author = "";

    public String readAuthor() throws IOException, StringIndexOutOfBoundsException
    {
        Scanner scanner = new Scanner(fileReader);
        final String AUTHOR_LABEL = "author";
        String[] line = scanner.nextLine().split(":");
        if (line[0].equals(AUTHOR_LABEL) )
        {
            author = line[1];
        }
        return author;
    }



    /** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     *                             ~~ READ RECIPIENT ~~                             *
     *                                                                              *
     * The method can read date from .ma file and returning recipient using String. *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    private String recipient = "";

    public String readRecipient() throws IOException, StringIndexOutOfBoundsException
    {
        Scanner scanner = new Scanner(fileReader);
        final String RECIPIENT_LABEL = "recipient";
        String[] line = scanner.nextLine().split(":");
        line = scanner.nextLine().split(":");
        if (line[0].equals(RECIPIENT_LABEL) )
        {
            recipient = line[1];
        }
        return recipient;
    }



    /** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     *                              ~~ READ DATE ~~
     *                                                                          *
     * The method can read date from .ma file and returning date using String.  *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    private String date = "default";

    public String readDate() throws IOException, StringIndexOutOfBoundsException
    {
        Scanner scanner = new Scanner(fileReader);
        final String DATE_LABEL = "date";
        String[] line = scanner.nextLine().split(":");
        line = scanner.nextLine().split(":");
        line = scanner.nextLine().split(":");
        if (line[0].equals(DATE_LABEL) )
        {
            date = line[1];
        }
        return date;
    }



    /** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     *                              ~~ READ TIME ~~                             *
     *                                                                          *
     * The method can read date from .ma file and returning time using String.  *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    private String time = "default";

    public String readTime() throws IOException, StringIndexOutOfBoundsException
    {
        Scanner scanner = new Scanner(fileReader);
        final String TIME_LABEL = "time";
        String[] line = scanner.nextLine().split(":");
        line = scanner.nextLine().split(":");
        line = scanner.nextLine().split(":");
        line = scanner.nextLine().split(":");
        if (line[0].equals(TIME_LABEL) )
        {
            time = line[1];
        }
        return time;
    }



    /** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     *                               ~~READ TOPIC ~~                            *
     *                                                                          *
     * The method can read date from .ma file and returning topic using String. *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    private String topic = "";

    public String readTopic() throws IOException, StringIndexOutOfBoundsException
    {
        Scanner scanner = new Scanner(fileReader);
        final String TOPIC_LABEL = "topic";
        String[] line = scanner.nextLine().split(":");
        line = scanner.nextLine().split(":");
        line = scanner.nextLine().split(":");
        line = scanner.nextLine().split(":");
        line = scanner.nextLine().split(":");
        if (line[0].equals(TOPIC_LABEL) )
        {
            topic = line[1];
        }
        return topic;
    }



    /** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     *                                  ~~ READ CONTENT ~~                                  *
     *                                                                                      *
     * The method can read date from .ma file and returning content of email using String.  *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    private String content = "";

    public String readContent() throws IOException, StringIndexOutOfBoundsException
    {
        Scanner scanner = new Scanner(fileReader);
        final String CONTENT_LABEL = "content";
        String[] line = scanner.nextLine().split(":");
        line = scanner.nextLine().split(":");
        line = scanner.nextLine().split(":");
        line = scanner.nextLine().split(":");
        line = scanner.nextLine().split(":");
        line = scanner.nextLine().split(":");
        if (line[0].equals(CONTENT_LABEL) )
        {
            content = line[1];
        }
        return content;
    }

    public static String getText(String var)
    {

        String result = "";

        Pattern pattern = Pattern.compile("<[^>]*>");
        Matcher matcher = pattern.matcher(var);
        final StringBuffer text = new StringBuffer(var.length());

        while (matcher.find()) {
            matcher.appendReplacement(
                    text,
                    " ");
        }

        matcher.appendTail(text);

        result = text.toString().trim();

        return result;
    }
}
