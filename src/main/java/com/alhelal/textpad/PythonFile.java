package com.alhelal.textpad;

public class PythonFile extends EditableFile
{
    public PythonFile()
    {
        languageBehavior = new PythonLanguageBehavior();
    }
}
