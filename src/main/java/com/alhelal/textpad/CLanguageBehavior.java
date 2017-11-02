package com.alhelal.textpad;

import org.apache.commons.io.FilenameUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

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

    public BufferedReader runCode(File file)
    {
        String objectFileName = FilenameUtils.removeExtension(file.getName());
        String objectFilePath = file.getParent() + "/" + objectFileName;
        buildCode(file);
        BufferedReader bufferedReader;// = new BufferedReader(null);
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

    public Map<BufferedReader, String> map()
    {
        Map<BufferedReader, String> m = new HashMap<>();
        m.put(new BufferedReader(null), "jhe");
        return m;
    }

    public BufferedReader buildCode(File file)
    {
        String dirname = file.getParent();
        String filePath = file.getPath();
        String fileName = file.getName();
        String objectFileName = FilenameUtils.removeExtension(fileName);
        String command = "gcc " + filePath + " -o " + dirname + "/" + objectFileName;
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

    public void setHighlightableText()
    {

    }

    public void setAutoCompletableText()
    {

    }
}
