package com.alhelal.textpad;

import java.io.File;

public interface LanguageBehavior
{
    public void runCode(File file);

    public void buildCode(File file);

    public void setHighlightableText();

    public void setAutoCompletableText();
}
