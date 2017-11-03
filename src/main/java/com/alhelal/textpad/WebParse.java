package com.alhelal.textpad;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * <pre>
 *     author: Md.Inzamam-Ul-Haque
 * </pre>
 */

public class WebParse
{
    public ArrayList<String> questions = new ArrayList<>();
    public ArrayList<String> questionLinks = new ArrayList<>();
    private String searchURL = "";
    private String searchKeyUrl = "";
    private String linkButName = "";
    private String where;

    WebParse(String where)
    {
        this.where = where;
        if (where.equalsIgnoreCase("stackoverflow"))
        {
            //https://stackoverflow.com/search?q=java+array+exception
            searchURL = "https://stackoverflow.com/search?tab=relevance&q=";
        }
        else if (where.equalsIgnoreCase("google"))
        {
            //https://www.google.com/search?q=java+array
            searchURL = "https://www.google.com/search?q=";
        }
    }

    private String processSearchKey(String key)
    {
        String temp = "";
        StringTokenizer strTok = new StringTokenizer(key, " ");
        while (strTok.hasMoreTokens())
        {
            temp += strTok.nextToken();
            if (!strTok.hasMoreTokens())
            {
                break;
            }
            temp += "+";
        }
        return temp;
    }

    private void getSearchResult(Document document)
    {
        Elements links = document.select("a[href]");
        String lnk = "";
        if (where.equalsIgnoreCase("google"))
        {
            for (Element link : links)
            {
                lnk = link.attr("href");
                if (lnk.startsWith("http"))
                {
                    questions.add(link.text());
                    questionLinks.add(lnk);
                    //System.out.println("text : " + link.text());
                }
            }
        }
        else
        {
            for (Element link : links)
            {
                if (link.text().startsWith("Q:") || link.text().startsWith("A:"))
                {
                    // System.out.println("\nlink : " + link.attr("href"));
                    lnk = "https://stackoverflow.com" + link.attr("href");
                    questions.add(link.text());
                    questionLinks.add(lnk);
                    //System.out.println("text : " + link.text());
                }
            }
        }

    }

    private void connectURL(String url) throws IOException
    {
        try
        {
            Document document = Jsoup.connect(url).get();
            String title = document.title(); //1
            System.out.println("Page title is: " + title);
            getSearchResult(document);
            document = null;
        }
        catch (Exception e)
        {
            System.out.println("There is no Internet connection\n" +
                    "Try:\n" +
                    "1.Checking the network cables, modem, and router\n" +
                    "2.Reconnecting to Wi-Fi");
        }
    }

    public void searchResult(String searchKey) throws IOException
    {
        searchKeyUrl = searchURL + processSearchKey(searchKey);
        System.out.println(searchKeyUrl);
        connectURL(searchKeyUrl);
        //showQuestionList();
    }
}