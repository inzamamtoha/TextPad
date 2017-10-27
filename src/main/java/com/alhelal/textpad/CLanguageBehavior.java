package com.alhelal.textpad;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;

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
        System.out.println("objectFilePath = " + objectFilePath);
        try
        {
            //Runtime.getRuntime().exec(objectFilePath);
            Runtime.getRuntime().exec("./a.out");
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
        System.out.println("command = " + command);
        try
        {
            Process p = Runtime.getRuntime().exec(command);
            System.out.println("Compiled C file");
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
