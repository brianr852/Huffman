/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

/**
 *
 * @author brian
 */
import java.util.Arrays;
import java.util.List;


public class HuffmanCode implements Comparable
{
    List<Character> symbolList;
    int totalFrequency;

    /**
     * Constructor for objects of class HuffmanCode
     */
    public HuffmanCode(List<Character> symbols, int frequency)
    {
        this.symbolList = symbols;
        this.totalFrequency = frequency;
    }

    /**
     * Get the combined frequency of all the symbols
     *
     * @return the total frequency
     */
    public int getFrequency()
    {
        return this.totalFrequency;
    }


    /**
     * Get the List of all the symbols
     *
     * @return the total frequency
     */
    public List<Character> getSymbols()
    {
        return this.symbolList;
    }


    /**
     * Determine if a symbol is in the list
     *
     * @param symbol the symbol to look for
     * @return true if the symbol is in the list
     */
    public boolean inList(Character symbol)
    {
        return this.symbolList.contains(symbol);
    }

    public int compareTo(Object o)
    {
        HuffmanCode other = (HuffmanCode) o;
        return this.totalFrequency - other.totalFrequency;
    }

    public String toString()
    {
        return Arrays.toString(this.symbolList.toArray()) + " -> " + this.totalFrequency;
    }
}
