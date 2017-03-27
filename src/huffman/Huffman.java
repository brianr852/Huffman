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
import sun.awt.Symbol;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * A class that implements text encoding using Huffman compression algorithm
 *
 * @author Brian Radomski
 * @version 11/11/14
 */
public class Huffman
{
    //  contains all the lines included in the given text file
    private Message myMessage;
    //  contains the myMessage encoded with Huffman code
    private Code myCode;
    //  contains Huffman trees for each unique character in myMessage
    private HuffmanTree<HuffmanCode>[] myTrees;
    //  initialized to the number of unique characters in the message
    private int numberOfTrees;
    //   allowing ASCII character set
    private final int MAX_NUMBER_OF_CHARS = 128;

    /**
     * Constructor for objects of class EncodeApplication
     */
    public Huffman()
    {
        this.myMessage = new Message();
        this.myCode = new Code();
        this.myTrees = null;
        this.numberOfTrees = 0;
    }


    /**
     * count the number of times each character appears in the message.
     *
     * @return an array with the count for the number of times each character occurs.
     */
    public int[] getCounts()
    {
        int[] result = new int[MAX_NUMBER_OF_CHARS + 1];
        int counter;

        while (this.myMessage.hasNext())
        {
            counter = this.myMessage.next();
            if (result[counter] == 0)
            {
                this.numberOfTrees++;
            }
            result[counter]++;
        }
        return result;
    }

    /**
     * create the initial forrest of trees.
     *
     * @param count the frequency of each character
     *              to be encoded - essentially the array returned by getCounts method.
     * @return the forest of trees for each single letter
     */
    public HuffmanTree<HuffmanCode>[] createInitialTrees(int[] count)
    {
        System.out.println("Creating " + this.numberOfTrees + " initial trees");
        HuffmanTree<HuffmanCode>[] result = new HuffmanTree[this.numberOfTrees];

        for (int i = 0, index = 0;i < count.length; i++)
        {
            if (count[i] != 0)
            {
                List<Character> list = new ArrayList<Character>();
                list.add((char) i);
                HuffmanCode code = new HuffmanCode(list, count[i]);
                HuffmanTree<HuffmanCode> tree = new HuffmanTree<HuffmanCode>(code);
                result[index] = tree;
                System.out.println("---> Count of character " + (char) i +
                                   " is " + count[i]);
                index++;
            }
        }
        return result;
    }


    /**
     * Creates final Huffman Tree by combing two "smallest trees" at a time.
     * The smallest tree is the tree with the smallest frequency but you should
     * call compareTo method
     * Operates on the forest of trees contained in the variable myTrees.
     */
    public void createHuffmanTree()
    {

        while (this.numberOfTrees > 1)
        {
            // swap smallest to the end
            swap(findSmallest(this.numberOfTrees - 1), this.numberOfTrees - 1);
            System.out.println("--->Smallest tree moved to the position " + (this.numberOfTrees - 1));


            // swap second smallest to the end
            swap(findSmallest(this.numberOfTrees - 1), this.numberOfTrees - 2);
            System.out.println("--->Second smallest tree moved to the position " + (this.numberOfTrees - 2));

            // Construct a new combined tree and put in place of the second last tree
            List<Character> list = new ArrayList<Character>();
            int first = this.myTrees[this.numberOfTrees - 1].getRootData().getSymbols().size();
            int second = this.myTrees[this.numberOfTrees - 2].getRootData().getSymbols().size();
            for (int i = 0; i < first;i ++)
            {
                list.add(this.myTrees[this.numberOfTrees - 1].getRootData().getSymbols().get(i));
            }

            for (int i = 0;i < second;i++)
            {
                list.add(this.myTrees[this.numberOfTrees - 2].getRootData().getSymbols().get(i));
            }

            HuffmanCode code = new HuffmanCode(list,this.myTrees[this.numberOfTrees -1].getRootData().getFrequency() +
                                                    this.myTrees[this.numberOfTrees - 2].getRootData().getFrequency());

            HuffmanTree<HuffmanCode> combinedTree = new HuffmanTree<HuffmanCode>(code, this.myTrees[this.numberOfTrees - 1],
                                                                                       this.myTrees[this.numberOfTrees - 2]);

            this.myTrees[this.numberOfTrees - 1] = null;
            this.myTrees[this.numberOfTrees - 2] = combinedTree;
            System.out.println("--->Combined tree created: " + list + " -> " + code);

            System.out.println("--->Combined tree added at position " + (this.numberOfTrees - 2));
            this.numberOfTrees--;
        }
    }

    /**
     * swaps two elements in myTrees
     *
     * @param index1
     * @param index2
     */
    private void swap(int index1, int index2)
    {
        HuffmanTree<HuffmanCode> temp = this.myTrees[index1];
        this.myTrees[index1] = this.myTrees[index2];
        this.myTrees[index2] = temp;
    }

    /**
     * finds the Huffman tree with the smallest frequency
     *
     * @param last - the index of the last tree to check
     * @return - the index of the "smallest" tree in the given range
     */
    private int findSmallest(int last)
    {
        int smallestAt = 0;
        for (int i = 1; i < last; i++)
        {
            if ((this.myTrees[smallestAt].getRootData()).compareTo(this.myTrees[i].getRootData()) > 0)
            {
                smallestAt = i;
            }
        }
        return smallestAt;
    }

    /**
     * encode a single symbol using a Huffman tree and append
     * the Huffman code to myCode  object
     *
     * @param c the symbol to be encoded.
     */
    public void encodeCharacter(Character c)
    {
        // make sure that we point to the root
        // at this point we only have one tree
        this.myTrees[0].reset();

        while(!this.myTrees[0].isSingleSymbol()) //<-- only look at the trees where the symbol list contains one element
        {
            if (this.myTrees[0].checkLeft(c))
            {
                this.myTrees[0].advanceLeft();
                this.myCode.addCharacter('0');
            }
            else
            {
                this.myTrees[0].advanceRight();
                this.myCode.addCharacter('1');
            }
        }
        this.myCode.addCharacter(' ');
    }

    /**
     * encodes myMessage using the generated Huffman Code by calling
     * the encodeCharacter method for each character in myMessage.
     */
    private void encodeMessage()
    {
        this.myMessage.reset();
        while (this.myMessage.hasNext())
        {
            //fill myCode with the code message
            encodeCharacter(this.myMessage.next());
        }
    }

    /**
     * load the words into the Message
     *
     * @param theFileName the name of the file holding the message
     */
    public boolean loadMessage(String theFileName)
    {
        Scanner input;
        boolean loaded = false;
        try
        {
            input = new Scanner(new File(theFileName));

            while (input.hasNextLine())  // read until  end of file
            {
                this.myMessage.addLine(input.nextLine());
            }
            System.out.println(this.myMessage);
            loaded = true;
        } catch (Exception e)
        {
            System.out.println("There was an error in reading or opening the file: " + theFileName);
            System.out.println(e.getMessage());
        }
        return loaded;
    }

    public void run()
    {
        if (loadMessage("message.txt"))
        {

            this.myTrees = createInitialTrees(getCounts());
            this.numberOfTrees = this.myTrees.length;

            System.out.println("Building Huffman Tree");
            createHuffmanTree();

            //the tree is created so let's display it using preOrder
            System.out.println("\nHuffman Tree:");
            this.myTrees[0].preOrderTraverse();
            System.out.println();
            //we can encode now
            encodeMessage();
            System.out.println(this.myCode);
        }
    }


    public static void main(String[] args)
    {
        Huffman encodeApp = new Huffman();
        encodeApp.run();
    }
}
