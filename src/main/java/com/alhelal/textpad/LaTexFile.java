package com.alhelal.textpad;

public class LaTexFile extends EditableFile
{
    public LaTexFile()
    {
        languageBehavior = LaTexLanguageBehavior.getUniqueInstance();
    }
}
