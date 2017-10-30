package com.alhelal.textpad;

import org.apache.commons.io.FilenameUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class CLanguageBehavior implements LanguageBehavior
{
    public volatile static CLanguageBehavior uniqueInstance;

    private CLanguageBehavior()
    {
    }

    public static CLanguageBehavior getUniqueInstance()
    {
        if (uniqueInstance == null)
        {
            synchronized (CLanguageBehavior.class)
            {
                if (uniqueInstance == null)
                {
                    uniqueInstance = new CLanguageBehavior();
                }
            }
        }
        return uniqueInstance;
    }

    public void runCode(File file)
    {
        String dirName = file.getPath();
        String objectFileName = FilenameUtils.removeExtension(file.getName());
        String objectFilePath = file.getParent() + "/" + objectFileName;
        buildCode(file);
        try
        {
            Process process = Runtime.getRuntime().exec("./a.out");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            System.out.println(bufferedReader.readLine());
            System.out.println("run successed");
        }
        catch (IOException io)
        {
            System.out.println(io);
        }
    }

    public void buildCode(File file)
    {
        String dirname = file.getParent();
        String filePath = file.getPath();
        String fileName = file.getName();
        String objectFileName = FilenameUtils.removeExtension(fileName);
        String command = "gcc " + filePath;// + " -o " + dirname + "/" + objectFileName;
        try
        {
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader stdErr = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String compileResult;
            while ((compileResult = stdInput.readLine()) != null)
                System.out.println(compileResult);

            while ((compileResult = stdErr.readLine()) != null)
                System.out.println(compileResult);
        }
        catch (IOException io)
        {
            System.out.println(io);
        }
    }

    public void setHighlightableText()
    {

    }

    public void setAutoCompletableText()
    {

    }
}
