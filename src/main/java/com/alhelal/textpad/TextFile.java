package com.alhelal.textpad;

public class TextFile extends EditableFile
{
    public TextFile(Options option)
    {
        System.out.println("TextFile constractor is called");
        if (option == null)
        {
            System.out.println("option is null");
        }
        super.options = option;
        languageBehavior = new TextFileBehavior();
    }
}
