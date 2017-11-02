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

import java.util.Collection;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author alhelal
 */
public class Editor
{

    private static final String[] KEYWORDS = new String[]{
            "abstract", "assert", "boolean", "break", "byte",
            "case", "catch", "char", "class", "const",
            "continue", "default", "do", "double", "else",
            "enum", "extends", "final", "finally", "float",
            "for", "goto", "if", "implements", "import",
            "instanceof", "int", "interface", "long", "native",
            "new", "package", "private", "protected", "public",
            "return", "short", "static", "strictfp", "super",
            "switch", "synchronized", "this", "throw", "throws",
            "transient", "try", "void", "volatile", "while"
    };
    private static final String KEYWORD_PATTERN = "\\b(" + String.join("|", KEYWORDS) + ")\\b";
    private static final String PAREN_PATTERN = "\\(|\\)";
    private static final String BRACE_PATTERN = "\\{|\\}";
    private static final String BRACKET_PATTERN = "\\[|\\]";
    private static final String SEMICOLON_PATTERN = "\\;";
    private static final String STRING_PATTERN = "\"([^\"\\\\]|\\\\.)*\"";
    private static final String COMMENT_PATTERN = ";[^\n]*";

    private static final Pattern PATTERN = Pattern.compile(
            "(?<KEYWORD>" + KEYWORD_PATTERN + ")"
                    + "|(?<PAREN>" + PAREN_PATTERN + ")"
                    + "|(?<BRACE>" + BRACE_PATTERN + ")"
                    + "|(?<BRACKET>" + BRACKET_PATTERN + ")"
                    + "|(?<SEMICOLON>" + SEMICOLON_PATTERN + ")"
                    + "|(?<STRING>" + STRING_PATTERN + ")"
                    + "|(?<COMMENT>" + COMMENT_PATTERN + ")"
    );
    WordInDocument currentWord = new WordInDocument();
    private CodeArea code;
    private Intellisense intellisense;
    private VirtualizedScrollPane<CodeArea> editorPane;
    private String filename;

    public Editor(Stage parent)
    {
        code = new CodeArea();
        editorPane = new VirtualizedScrollPane(code);

        intellisense = new Intellisense();
        setCodeAreaProperties(parent);


    }

    private static StyleSpans<Collection<String>> computeHighlighting(String text)
    {
        Matcher matcher = PATTERN.matcher(text);
        int lastKwEnd = 0;
        StyleSpansBuilder<Collection<String>> spansBuilder
                = new StyleSpansBuilder<>();
        while (matcher.find())
        {
            String styleClass =
                    matcher.group("KEYWORD") != null ? "keyword" :
                            matcher.group("PAREN") != null ? "paren" :
                                    matcher.group("BRACE") != null ? "brace" :
                                            matcher.group("BRACKET") != null ? "bracket" :
                                                    matcher.group("SEMICOLON") != null ? "semicolon" :
                                                            matcher.group("STRING") != null ? "string" :
                                                                    matcher.group("COMMENT") != null ? "comment" :
                                                                            null; /* never happens */
            assert styleClass != null;
            assert styleClass != null;
            spansBuilder.add(Collections.emptyList(), matcher.start() - lastKwEnd);
            spansBuilder.add(Collections.singleton(styleClass), matcher.end() - matcher.start());
            lastKwEnd = matcher.end();
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
                this.getClass().getResource("/com/alhelal/resource/editor_style.css").toExternalForm());
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
