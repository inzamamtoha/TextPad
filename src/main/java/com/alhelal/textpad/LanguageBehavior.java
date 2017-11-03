/*
* @author : alhelal
* */

package com.alhelal.textpad;

import java.io.BufferedReader;
import java.io.File;

public interface LanguageBehavior
{
    BufferedReader runCode(File file);

    BufferedReader buildCode(File file);

    void setHighlightableText();

    void setAutoCompletableText();
}
