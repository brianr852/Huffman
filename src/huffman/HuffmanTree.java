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
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class HuffmanTree<T> implements java.io.Serializable
{
    private TreeNode<T> root;
    private TreeNode<T> current;

    public HuffmanTree()
    {
        this.root = null;
        reset();
    } // end default constructor

    public HuffmanTree(T rootData)
    {
        this.root = new TreeNode<T>(rootData);
        reset();
    } // end constructor

    public HuffmanTree(T rootData, HuffmanTree<T> leftTree,
                       HuffmanTree<T> rightTree)
    {
        privateSetTree(rootData, leftTree, rightTree);
        reset();
    } // end constructor

    private void privateSetTree(T rootData,
                                HuffmanTree<T> leftTree, HuffmanTree<T> rightTree)
    {
        this.root = new TreeNode<T>(rootData);

        if ((leftTree != null) && !leftTree.isEmpty())
            this.root.setLeft(leftTree.root);

        if ((rightTree != null) && !rightTree.isEmpty())
        {
            if (rightTree != leftTree)
                this.root.setRight(rightTree.root);
            else
                this.root.setRight(rightTree.root.copy());
        } // end if

        if ((leftTree != null) && (this != leftTree))
            leftTree.clear();

        if ((rightTree != null) && (this != rightTree))
            rightTree.clear();
    } // end privateSetTree

    public T getRootData()
    {
        T rootData = null;
        if (this.root != null)
            rootData = root.getData();
        return rootData;
    } // end getRootData

    public boolean isEmpty()
    {
        return this.root == null;
    } // end isEmpty

    public void clear()
    {
        this.root = null;
    } // end clear


    /**
     * Get the symbol/frequency for the current node in the Huffman tree.
     *
     * @return The object of type HuffmanCode being held at the current node.
     */
    public HuffmanCode getCurrentData()
    {
        HuffmanCode result = null;

        if (this.current != null)
        {
            result = (HuffmanCode) this.current.getData();
        }
        return result;
    }

    /**
     * Determine whether current node contains a single code letter.
     *
     * @return true if the current node is a leaf
     */
    public boolean isSingleSymbol()
    {
        return (this.current.getLeft() == null
                && this.current.getRight() == null);
    }

    /**
     * Moves the current node to the left child of
     * the current node.
     */
    public void advanceLeft()
    {
        this.current = this.current.getLeft();
    }

    /**
     * Moves the current node to the right child of
     * the current node.
     */
    public void advanceRight()
    {
        this.current = this.current.getRight();
    }

    /**
     * Check the node to the left of the current node to see if a symbol is there.
     *
     * @param symbol the symbol to look for
     * @return true if the symbol is on the left
     */
    public boolean checkLeft(Character symbol)
    {
        boolean result = false;
        TreeNode<T> toCheck = this.current.getLeft();
        if (toCheck != null)
        {
            HuffmanCode sfp = (HuffmanCode) toCheck.getData();
            result = sfp.inList(symbol);
        }
        return result;
    }

    /**
     * Check the node to the right of the current node to see if a symbol is there.
     *
     * @param symbol the symbol to look for
     * @return true if the symbol is on the right
     */
    public boolean checkRight(Character symbol)
    {
        boolean result = false;
        TreeNode<T> toCheck = this.current.getRight();
        if (toCheck != null)
        {
            HuffmanCode sfp = (HuffmanCode) toCheck.getData();
            result = sfp.inList(symbol);
        }
        return result;
    }

    /**
     * Sets the current node to the root of the tree.
     */
    public void reset()
    {
        this.current = this.root;
    }

    public void preOrderTraverse()
    {
        preOrderTraverse(root);
    }


    private void preOrderTraverse(TreeNode<T> node)
    {
        if (node != null)
        {
            System.out.println(node.getData());
            preOrderTraverse(node.getLeft());
            preOrderTraverse(node.getRight());
        }
    }

    /* create an inorder iterator
    * return the iterator
    */
    public Iterator<HuffmanCode> getInorderIterator()
    {
        return new InorderIterator();
    }

    private class InorderIterator implements Iterator<HuffmanCode>
    {
        private Stack<TreeNode<T>> nodeStack;
        private TreeNode<T> currentNode;

        public InorderIterator()
        {
            nodeStack = new Stack<TreeNode<T>>();
            currentNode = root;
        } // end default constructor

        public boolean hasNext()
        {
            return !nodeStack.isEmpty() || (currentNode != null);
        } // end hasNext

        public HuffmanCode next()
        {
            TreeNode<T> nextNode = null;

            while (currentNode != null)
            {
                nodeStack.push(currentNode);
                currentNode = currentNode.getLeft();
            } // end while

            if (!nodeStack.isEmpty())
            {
                nextNode = nodeStack.pop();
                currentNode = nextNode.getRight();
            } else
                throw new NoSuchElementException();

            return (HuffmanCode) nextNode.getData();
        } // end next


        public void remove()
        {
            throw new UnsupportedOperationException();
        } // end remove

    } // end InorderIterator
}