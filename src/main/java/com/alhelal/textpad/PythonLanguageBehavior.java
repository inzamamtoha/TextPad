/*
* @author : alhelal
* */

package com.alhelal.textpad;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

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

    public BufferedReader runCode(File file)
    {
        String command = "python " + file.getPath();
        BufferedReader bufferedReader;
        try
        {
            Process process = Runtime.getRuntime().exec(command);
            bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            return bufferedReader;
        }
        catch (IOException io)
        {
            System.out.println(io);
            return null;
        }
    }

    @Override
    public BufferedReader buildCode(File file)
    {
        String command = "python " + file.getPath();
        BufferedReader stdInput;
        try
        {
            Process process = Runtime.getRuntime().exec(command);
            stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader stdErr = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String compileResult;
            while ((compileResult = stdInput.readLine()) != null)
                System.out.println(compileResult);

            while ((compileResult = stdErr.readLine()) != null)
                System.out.println(compileResult);
            return stdInput;
        }
        catch (IOException io)
        {
            System.out.println(io);
            return null;
        }
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
