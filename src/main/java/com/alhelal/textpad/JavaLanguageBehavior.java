package com.alhelal.textpad;

import org.apache.commons.io.FilenameUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

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

    public BufferedReader runCode(File file)
    {
        String objectFileName = FilenameUtils.removeExtension(file.getName());
        String objectFilePath = file.getParent() + "/" + objectFileName;
        String command = "java " + objectFilePath;
        buildCode(file);
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

    public BufferedReader buildCode(File file)
    {
        System.out.println("Building starting Java file");
        String dirname = file.getParent();
        String filePath = file.getPath();
        String fileName = file.getName();
        String command = "javac " + filePath;
        System.out.println("command=" + command);
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
            System.out.println("Building finished Java file");
            return stdInput;
        }
        catch (IOException io)
        {
            System.out.println(io);
            return null;
        }
    }

    public void setHighlightableText()
    {

    }

    public void setAutoCompletableText()
    {

    }
}
