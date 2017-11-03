package com.alhelal.textpad;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * author: Md.Inzamam-Ul-Haque
 */
public interface SuggestionInterfaces
{
    public void showSuggestion(BufferedReader errorBuffer, int maxSize);
}


class suggestionAdapter implements SuggestionInterfaces
{
    ArrayList<String> errorList = new ArrayList<String>();

    public void errorParseC(BufferedReader bf)
    {
        String line;
        SearchErrorString searchErrorString = new KeyString();
        String key = "error:";
        try
        {

            while ((line = bf.readLine()) != null)
            {
                if (line.contains(key))
                {
                    errorList.add(line);
                    searchErrorString.makeString(line);
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }

    public void showSuggestion(BufferedReader errorBuffer, int maxSize)
    {
        errorParseC(errorBuffer);
        SuggestBoxMain suggestBoxMain = new SuggestBoxMain();
        try
        {
            suggestBoxMain.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}