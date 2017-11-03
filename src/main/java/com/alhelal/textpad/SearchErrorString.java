package com.alhelal.textpad;

import java.util.ArrayList;

/**
 * <pre>
 *     author: Md.Inzamam-Ul-Haque
 * </pre>
 */

public interface SearchErrorString
{
    public void makeString(String line);

    public boolean hasErrorList();

    public String getError();
}

class KeyString implements SearchErrorString
{
    private int size;
    private ArrayList<String> eList;

    KeyString()
    {
        size = 0;
        eList = new ArrayList<String>();
    }

    public void makeString(String line)
    {
        eList.add(line);
    }

    @Override
    public boolean hasErrorList()
    {
        if (size < eList.size())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public String getError()
    {
        size++;
        return eList.get(size);

    }
}