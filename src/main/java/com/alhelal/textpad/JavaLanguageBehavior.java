package com.alhelal.textpad;

public class JavaLanguageBehavior implements LanguageBehavior
{
    public void runCode(String path)
    {
        System.out.println("Compiling Java file");
    }
    public void buildCode()
    {
        System.out.println("Building Java file");
    }
    public void setHighlightableText()
    {

    }
    public void setAutoCompletableText()
    {

    }
}
