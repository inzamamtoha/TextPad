package com.alhelal.textpad;

public interface LanguageBehavior
{
    public void runCode(String path);
    public void buildCode();
    public void setHighlightableText();
    public void setAutoCompletableText();
}
