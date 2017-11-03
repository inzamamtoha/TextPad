/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alhelal.textpad;

import javafx.beans.value.ObservableValue;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.fxmisc.flowless.VirtualizedScrollPane;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.fxmisc.richtext.PopupAlignment;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;

import java.io.*;
import java.text.BreakIterator;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author alhelal
 */
public class NormalTextEditor implements Editable
{
    private CodeArea code;
    private Intellisense intellisense;
    private VirtualizedScrollPane<CodeArea> editorPane;
    private String filename;
    WordInDocument currentWord = new WordInDocument();

    private static final Set<String> dictionary = new HashSet<String>();

    public NormalTextEditor(Stage parent, String keywordsPaht)
    {

        try
        {
//            InputStream input = getClass().getResourceAsStream("spellchecking.dict");
            InputStreamReader input = new InputStreamReader(new FileInputStream(keywordsPaht));
            BufferedReader br = new BufferedReader(input);
            {
                String line;
                while ((line = br.readLine()) != null)
                {
                    dictionary.add(line);
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        // load the sample document
        //InputStream input2 = getClass().getResourceAsStream("spellchecking.txt");
        code = new CodeArea();
        editorPane = new VirtualizedScrollPane(code);

        intellisense = new Intellisense(keywordsPaht);
        setCodeAreaProperties(parent);
    }


    private static StyleSpans<Collection<String>> computeHighlighting(String text)
    {

        StyleSpansBuilder<Collection<String>> spansBuilder = new StyleSpansBuilder<>();

        BreakIterator wb = BreakIterator.getWordInstance();
        wb.setText(text);

        int lastIndex = wb.first();
        int lastKwEnd = 0;
        while (lastIndex != BreakIterator.DONE)
        {
            int firstIndex = lastIndex;
            lastIndex = wb.next();

            if (lastIndex != BreakIterator.DONE
                    && Character.isLetterOrDigit(text.charAt(firstIndex)))
            {
                String word = text.substring(firstIndex, lastIndex).toLowerCase();
                if (!dictionary.contains(word))
                {
                    spansBuilder.add(Collections.emptyList(), firstIndex - lastKwEnd);
                    spansBuilder.add(Collections.singleton("underlined"), lastIndex - firstIndex);
                    lastKwEnd = lastIndex;
                }
                System.err.println();
            }
        }
        spansBuilder.add(Collections.emptyList(), text.length() - lastKwEnd);

        return spansBuilder.create();
    }

    public Intellisense getIntellisense()
    {
        return intellisense;
    }

    private void setCodeAreaProperties(Stage owner)
    {
        code.setWrapText(true);
        code.setParagraphGraphicFactory(LineNumberFactory.get(code));
        code.getStylesheets().add(
                this.getClass().getResource("/com/alhelal/resource/spellchecking.css").toExternalForm());
        code.setPopupWindow(intellisense.getStage());
        code.setPopupAlignment(PopupAlignment.SELECTION_BOTTOM_RIGHT);
        code.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {

            try
            {
                String wordToCheck = getWord(code, code.getCaretPosition());

                if (intellisense.exists(wordToCheck))
                {
                    intellisense.show(owner);
                    intellisense.selectItem(wordToCheck);
                }
                else
                {
                    intellisense.hide();
                }
            }

            catch (Exception ex)
            {
                intellisense.hide();

            }
        });
        code.setOnKeyReleased(event ->
                {
                    if (intellisense.isShowing())
                    {
                        if (event.getCode().equals(KeyCode.DOWN))
                        {
                            intellisense.goDown();
                        }
                        else if (event.getCode().equals(KeyCode.UP))
                        {
                            intellisense.goUp();
                        }
                        else if (event.getCode().equals(KeyCode.TAB))
                        {
                            code.replaceText(currentWord.startPosition, currentWord.caretPosition,
                                    intellisense.getSelectedItem() + " ");
                            code.requestFollowCaret();


                        }
                        else if (event.getCode().equals(KeyCode.ESCAPE))
                        {
                            intellisense.hide();
                        }
                    }
                }
        );

        code.richChanges()
                .filter(ch -> !ch.getInserted().equals(ch.getRemoved())) // XXX
                .subscribe(change -> {
                    code.setStyleSpans(0, computeHighlighting(code.getText()));
                });

    }


    private String getWord(CodeArea txt, int caretPosition)
    {
        int position = caretPosition;


        while (position >= 1)
        {
            String comparedText = "";
            try
            {
                comparedText = txt.getText(position - 1, position);
            }
            catch (IndexOutOfBoundsException ex)
            {

                caretPosition -= 2;
                position -= 2;
                comparedText = txt.getText(position - 1, position);
            }
            finally
            {
                if (comparedText.equals(" ") ||
                        comparedText.equals("\t") ||
                        comparedText.equals("\n") ||
                        comparedText.equals("\r"))

                {
                    currentWord.word = txt.getText(position, caretPosition);
                    currentWord.caretPosition = caretPosition;
                    currentWord.startPosition = position;
                    return currentWord.word;
                }
                position--;
            }
        }
        currentWord.word = txt.getText();
        currentWord.caretPosition = txt.getText().length();
        currentWord.startPosition = 0;
        return txt.getText();


    }

    public CodeArea getEditArea()
    {
        return code;
    }

    public String assemble()
    {
        //String[] options = new String(){"/usr/bin/nasm","-f","elf64",filename.toString()};
        return "";
    }

    @Override
    public void setHighlightableText()
    {

    }

    @Override
    public void setAutoCompletableText()
    {

    }

    class WordInDocument
    {
        private String word;
        private int caretPosition;
        private int startPosition;

        public WordInDocument()
        {
            word = "";
            caretPosition = 0;
            startPosition = 0;
        }
    }

}
