/*
* @author : alhelal
* */

package com.alhelal.textpad;

import org.apache.commons.io.FilenameUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class CplusLanguageBehavior implements LanguageBehavior
{
    public volatile static CplusLanguageBehavior uniqueInstance;

    private CplusLanguageBehavior(){}

    public static CplusLanguageBehavior getUniqueInstance()
    {
        if (uniqueInstance == null)
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

    public BufferedReader runCode(File file)
    {
        System.out.println("C++ run method called");
        String objectFileName = FilenameUtils.removeExtension(file.getName());
        String objectFilePath = file.getParent() + "/" + objectFileName;
        buildCode(file);
        BufferedReader bufferedReader;
        try
        {
            Process process = Runtime.getRuntime().exec(objectFilePath);
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
        System.out.println("Building starting C++ file");
        String dirname = file.getParent();
        String filePath = file.getPath();
        String fileName = file.getName();
        String objectFileName = FilenameUtils.removeExtension(fileName);
        String command = "g++ " + filePath + " -o " + dirname + "/" + objectFileName;
        BufferedReader stdInput;
        try
        {
            Process process = Runtime.getRuntime().exec(command);
            stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader stdErr = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            return stdInput;
        }
        catch (IOException io)
        {
            System.out.println(io);
            return null;
        }
    }

    public void setHighlightableText(){}

    public void setAutoCompletableText(){}
}
