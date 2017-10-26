package com.alhelal.textpad;

import java.io.File;

public class CplusLanguageBehavior implements LanguageBehavior
{
    public volatile static CplusLanguageBehavior uniqueInstance;
    private CplusLanguageBehavior()
    {
    }
    public static CplusLanguageBehavior getUniqueInstance()
    {
        if(uniqueInstance == null)
        {
            synchronized (CplusLanguageBehavior.class)
            {
                if (uniqueInstance == null)
                {
                    uniqueInstance = new CplusLanguageBehavior();
                }
            }
        }
        return uniqueInstance;
    }

    public void runCode(File file)
    {
        System.out.println("Compiling C++ file");
    }

    public void buildCode(File file)
    {
        System.out.println("Building C++ file");
    }

    public void setHighlightableText()
    {

    }

    public void setAutoCompletableText()
    {

    }
}
