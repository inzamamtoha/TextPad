package com.alhelal.textpad;

import java.io.File;

public class PythonLanguageBehavior implements LanguageBehavior
{
    public volatile static PythonLanguageBehavior uniqueInstance;

    private PythonLanguageBehavior()
    {
    }

    public static PythonLanguageBehavior getUniqueInstance()
    {
        if (uniqueInstance == null)
        {
            synchronized (PythonLanguageBehavior.class)
            {
                if (uniqueInstance == null)
                {
                    uniqueInstance = new PythonLanguageBehavior();
                }
            }
        }
        return uniqueInstance;
    }

    public void runCode(File file)
    {
        System.out.println("Compiling Python file");
    }

    @Override
    public void buildCode(File file)
    {
        System.out.println("Building Python file");
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
