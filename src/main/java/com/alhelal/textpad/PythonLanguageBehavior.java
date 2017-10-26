package com.alhelal.textpad;

public class PythonLanguageBehavior implements LanguageBehavior
{
    public void runCode(String path)
    {
        System.out.println("Compiling Python file");
    }

    @Override
    public void buildCode()
    {
        System.out.println("Building Python file");
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
