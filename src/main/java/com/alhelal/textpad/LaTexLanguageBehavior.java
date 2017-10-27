package com.alhelal.textpad;

import java.io.File;

public class LaTexLanguageBehavior implements LanguageBehavior
{
    public volatile static LaTexLanguageBehavior uniqueInstance;

    private LaTexLanguageBehavior()
    {
    }

    public static LaTexLanguageBehavior getUniqueInstance()
    {
        if (uniqueInstance == null)
        {
            synchronized (LaTexLanguageBehavior.class)
            {
                if (uniqueInstance == null)
                {
                    uniqueInstance = new LaTexLanguageBehavior();
                }
            }
        }
        return uniqueInstance;
    }

    @Override
    public void runCode(File file)
    {

    }

    @Override
    public void buildCode(File file)
    {

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
