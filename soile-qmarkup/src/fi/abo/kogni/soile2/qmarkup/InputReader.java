package fi.abo.kogni.soile2.qmarkup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.lang3.StringUtils;

import fi.abo.kogni.soile2.qmarkup.typespec.MalformedCommandException;


public class InputReader {
    
    public InputReader(StringBuilder sb) {
        this(new StringReader(sb.toString()));
    }

    public InputReader(StringBuffer sb) {
        this(new StringReader(sb.toString()));
    }

    public InputReader(String s) {
        this(new StringReader(s));
    }

    public InputReader(Reader r) {
        
        this.reader = new BufferedReader(r);
        this.listeners = new ArrayList<>();
        this.buffer = new StringBuilder();
        this.storage = new StringBuilder();
        this.storageIndex = -1;
        this.previousChar = 'x';
        this.currentChar = 'x';
        this.cbstack = 0;
        this.endOfInput = false;
        this.lineNumber = 0;
        
    }
    
    public boolean endOfStream() {
        return this.endOfInput;
    }

    public void processInput() throws MalformedCommandException {
        String line = readLine();
        while (hasMoreInput()) {
            lineNumber += 1;
            System.out.println(lineNumber);
            if (line.length() > 1 && line.charAt(0) == COMMAND_BEGIN) {
                String command = readCommand(line);
                //System.out.println(Integer.toString(lineNumber) + " " + command);

                String remaining = line.substring(command.length() + 1);
                ArrayList<String> args = readArguments(remaining);
                notifyAboutCommand(command, args);
            } else if (isComment(line)) {
                ;   // Do nothing. Ignore comments.
            } else {
                notifyAboutText(line);
            }
            line = readLine();
        }
        notifyAboutFinish();
    }
    
    public InputReader addListener(QuestionnaireProcessor listener) {
        listeners.add(listener);
        return this;
    }
    
    private void notifyAboutFinish() {
        Iterator<QuestionnaireProcessor> it = listeners.iterator();
        while (it.hasNext()) {
            it.next().finish();
        }
    }
    
    private void notifyAboutText(String text) {
        Iterator<QuestionnaireProcessor> it = listeners.iterator();
        while (it.hasNext()) {
            it.next().processText(text);
        }
    }
    
    private void notifyAboutCommand(String command, ArrayList<String> args) 
                 throws MalformedCommandException {
        Iterator<QuestionnaireProcessor> it = listeners.iterator();
        while (it.hasNext()) {
            it.next().processCommand(command, args);
        }
    }
    
    private boolean isComment(String line) {
        if (line.isEmpty() || line.trim().isEmpty()) {
            return false;
        }
        int index = 0;
        while (Character.isWhitespace(line.charAt(index))) {
            ++index;
        }
        return line.charAt(index) == COMMENT;
    }
    
    private String readCommand(String line) {
        int start = 1;
        int end = 1;
        while (end < line.length()) {
            if (Character.isWhitespace(line.charAt(end))) {
                break;
            }
            ++end;
        }
        return line.substring(start, end);
    }

    private ArrayList<String> readArguments(String arg) {
        ArrayList<String> args = new ArrayList<>();
        if (arg.isEmpty() || arg.trim().isEmpty()) {
            return args;
        }
        arg = arg.trim();
        int cb = arg.indexOf(LEFT_BRACE);
        if (cb != -1) {
            store(arg.substring(cb + 1));
            args.add(readObjectArgument());
        } else {
            String[] arr = StringUtils.split(arg);
            for (String a : arr) {
                args.add(a);
            }
        }
        return args;
    }

    private void store(String s) {
        storage.delete(0, storage.length());
        storage.ensureCapacity(s.length());
        storage.append(s);
        storageIndex = 0;
    }

    private boolean hasMoreInput() {
        return this.endOfInput == false;
    }
    
    private String readLine() {
        if (endOfInput) {
            return null;
        }
        
        String line = null;
        try {
            line = reader.readLine();
            if (line == null) {
                endOfInput = true;
                reader.close();
            }
        } catch (IOException e) {
            endOfInput = true;
        }
        return line;
    }
    
    private String readObjectArgument() {
        clearBuffer();
        boolean inString = false;
        boolean inComment = false;
        cbstack = 1;
        buffer.append(LEFT_BRACE);
        
        while (cbstack > 0) {
            getc();
            if (endOfStream()) {
                return "";
            }

            if (currentChar == COMMENT) {
                inComment = true;
                continue;
            }

            if (inComment) {
                /*
                 * No action needed. This test is here so that the
                 * comment character does not make the parser mad.
                 */
                
                // A comment ends at a newline character.
                if (currentChar == '\n') {
                    inComment = false;
                }
                continue;
            }
            
            if (currentChar == DOUBLE_QUOTE) {
                if (previousChar != ESCAPE) {
                    inString = inString ? false : true;
                }
            }

            if (inString) {
                /*
                 * No action needed. This test is here so that the
                 * curly braces inside a string do not affect the 
                 * curly brace stack.
                 */
            }
            else if (currentChar == LEFT_BRACE) {
                ++cbstack;
            }
            else if (currentChar == RIGHT_BRACE) {
                --cbstack;
            }
            buffer.append(currentChar);
        }
        return buffer.toString();
    }
    
    private int getc() {
        int c;
        
        if (storageIndex != -1 && storageIndex < storage.length()) {
            c = storage.charAt(storageIndex);
            ++storageIndex;
            previousChar = currentChar;
            currentChar = (char) c;
            return c;
        } else {
            storageIndex = -1;
        }
        
        if (this.endOfInput) {
            return END_OF_STREAM;
        }
        
        try {
            c = reader.read();
            if (c == END_OF_STREAM) {
                this.endOfInput = true;
            } else {
                previousChar = currentChar;
                currentChar = (char) c;
            }
        } catch (IOException e) {
            this.endOfInput = true;
            c = END_OF_STREAM;
        }
        return c;
        
    }
    
    private void clearBuffer() {
        int length = buffer.length();
        if (length > 0) {
            buffer.delete(0, length);
        }
    }
    
    private BufferedReader reader;
    private ArrayList<QuestionnaireProcessor> listeners;
    private StringBuilder buffer;
    private StringBuilder storage;
    private int storageIndex;
    private boolean endOfInput;
    
    private char previousChar;
    private char currentChar;
    
    private int cbstack;        // Curly brace stack
    
    private static final char COMMAND_BEGIN = '/';
    private static final char COMMENT = '#';
    private static final char LEFT_BRACE = '{';
    private static final char RIGHT_BRACE = '}';
    private static final char DOUBLE_QUOTE = '"';
    private static final char ESCAPE = '\\';
    private static final int END_OF_STREAM = -1;

    public int lineNumber;
    
}
 