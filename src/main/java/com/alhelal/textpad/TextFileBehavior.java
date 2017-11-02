package com.alhelal.textpad;

import java.io.BufferedReader;
import java.io.File;

public class TextFileBehavior implements LanguageBehavior
{
    @Override
    public BufferedReader runCode(File file)
    {
        System.out.println("I am text file. No need to compile");
//        return new BufferedReader(null);
        return null;
    }

    @Override
    public BufferedReader buildCode(File file)
    {
        System.out.println("I am text file. No need to build");
        return new BufferedReader(null);
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
