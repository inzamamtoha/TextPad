package com.alhelal.textpad;

public class JavaFile extends EditableFile
{
    public JavaFile()
    {
        languageBehavior = JavaLanguageBehavior.getUniqueInstance();
    }
}
