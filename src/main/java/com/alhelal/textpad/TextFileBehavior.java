package com.alhelal.textpad;

import java.io.BufferedReader;
import java.io.File;

public class TextFileBehavior implements LanguageBehavior
{
    private volatile static TextFileBehavior uniqueInstance;
    private TextFileBehavior(){}

    public static TextFileBehavior getUniqueInstance()
    {
        if (uniqueInstance == null)
        {
            synchronized (TextFileBehavior.class)
            {
                if (uniqueInstance == null)
                {
                    uniqueInstance = new TextFileBehavior();
                }
            }
        }
        return uniqueInstance;
    }

    @Override
    public BufferedReader runCode(File file)
    {
        System.out.println("I am text file. No need to compile");
//        return new BufferedReader(null);
        return null;
    }

    @Override
    public BufferedReader buildCode(File file)
    {
        System.out.println("I am text file. No need to build");
        return new BufferedReader(null);
    }

    @Override
    public void setHighlightableText()
    {

    }

    @Override
    public void setAutoCompletableText()
    {

    }
}
