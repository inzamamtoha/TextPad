/*
* @author : alhelal
* */

package com.alhelal.textpad;

import org.apache.commons.io.FilenameUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

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

    public BufferedReader runCode(File file)
    {
        System.out.println("Building starting LaTeX file");
        String objectFileName = FilenameUtils.removeExtension(file.getName()) + ".pdf";
        String objectFilePath = file.getParent() + "/" + objectFileName;
        String command = "evince " + objectFilePath;
        buildCode(file);
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
            System.out.println("Building finished LaTeX file");
            return stdInput;
        }
        catch (IOException io)
        {
            System.out.println(io);
            return null;
        }
    }

    public BufferedReader buildCode(File file)
    {
        System.out.println("Building starting LaTeX file");
        String dirname = file.getParent();
        String filePath = file.getPath();
        String fileName = file.getName();
        String command = "pdflatex -no-shell-escape -output-directory=" + dirname + " " + filePath;
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
            System.out.println("Building finished LaTeX file");
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
