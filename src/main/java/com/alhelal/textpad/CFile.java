package com.alhelal.textpad;

public class CFile extends EditableFile
{
    String path;

    public CFile(String path)
    {
        this.path = path;
    }

    public CFile()
    {
        languageBehavior = CLanguageBehavior.getUniqueInstance();
    }
}
