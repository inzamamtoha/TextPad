package com.alhelal.textpad;

import java.io.File;

public class JavaLanguageBehavior implements LanguageBehavior
{
    public volatile static JavaLanguageBehavior uniqueInstance;

    private JavaLanguageBehavior()
    {
    }

    public static JavaLanguageBehavior getUniqueInstance()
    {
        if (uniqueInstance == null)
        {
            synchronized (JavaLanguageBehavior.class)
            {
                if (uniqueInstance == null)
                {
                    uniqueInstance = new JavaLanguageBehavior();
                }
            }
        }
        return uniqueInstance;
    }

    public void runCode(File file)
    {
        System.out.println("Compiling Java file");
    }

    public void buildCode(File file)
    {
        System.out.println("Building Java file");
    }

    public void setHighlightableText()
    {

    }

    public void setAutoCompletableText()
    {

    }
}
