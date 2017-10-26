package com.alhelal.textpad;

public class CplusFile extends EditableFile
{
    public CplusFile()
    {
        languageBehavior = CLanguageBehavior.getUniqueInstance();
    }
}
