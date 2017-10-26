package com.alhelal.textpad;

import java.io.File;

public class TextFileBehavior implements LanguageBehavior
{
    @Override
    public void runCode(File file)
    {
        System.out.println("I am text file. No need to compile");
    }

    @Override
    public void buildCode(File file)
    {
        System.out.println("I am text file. No need to build");
    }

    @Override
    public void setHighlightableText()
    {

    }

    @Override
    public void setAutoCompletableText()
    {

    }
}
