package com.alhelal.textpad;

public class TextFileBehavior implements LanguageBehavior
{
    @Override
    public void runCode(String path)
    {
        System.out.println("I am text file. No need to compile");
    }

    @Override
    public void buildCode()
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
