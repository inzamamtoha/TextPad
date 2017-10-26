package com.alhelal.textpad;

import javax.imageio.IIOException;
import java.io.IOException;

public class CLanguageBehavior implements LanguageBehavior
{

    public void runCode(String path)
    {
        try
        {
            Process p = Runtime.getRuntime().exec("gcc " + path);
            System.out.println("Compiled C file");
        }
        catch (IOException io)
        {
            System.out.println(io);
        }
    }

    public void buildCode()
    {
        System.out.println("Building C file");
    }

    public void setHighlightableText()
    {

    }

    public void setAutoCompletableText()
    {

    }
}
