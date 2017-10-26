package com.alhelal.textpad;

public class TextFile extends EditableFile
{
    public TextFile(Options options)
    {
        super.options = options;
        languageBehavior  = new TextFileBehavior();
    }
}
