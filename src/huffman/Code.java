/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;
import java.util.ArrayList;
import java.util.Iterator;
/**
 *
 * @author brian
 */
public class Code
{
    public static final int CHARACTERS_PER_LINE = 80;
    private ArrayList<String> lines;
    private int currentLine;
    private Character currentCharacter;

    /**
     * Constructor for objects of class Code
     */
    public Code()
    {
        this.lines = new ArrayList<String>();
        this.currentLine = 0;
        this.lines.add("");
        this.currentCharacter = null;
    }


    /**
     * Add a new character to the code
     *
     * @param c a string
     */
    public void addCharacter(Character c)
    {
        String line = this.lines.get(this.currentLine);
        if (line.length() >= CHARACTERS_PER_LINE)
        {
            this.currentLine++;
            line = "";
            this.lines.add(line);
        }

        if (this.currentCharacter != null)
        {
            line = line + this.currentCharacter;
            this.lines.set(this.currentLine, line);
        }

        this.currentCharacter = c;
    }

    public String toString()
    {
        String result = "The coded lines are (displaying " + CHARACTERS_PER_LINE + " characters per line):\n";
        Iterator<String> iter = this.lines.iterator();
        while (iter.hasNext())
        {
            String line = iter.next();
            result = result + line;

            if (!iter.hasNext())
            {
                // on the last line, don't forget the last character
                if (this.currentCharacter != null)
                {
                    if (line.length() < CHARACTERS_PER_LINE)
                        result = result + this.currentCharacter;
                    else
                        result = result + "\n" + this.currentCharacter;
                }
            }
            // end the line
            result = result + "\n";
        }
        return result;
    }
}
