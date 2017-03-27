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
public class TreeNode<T>
{
    private T data;
    private TreeNode<T> left;
    private TreeNode<T> right;

    public TreeNode(T dataPortion)
    {
        this.data = dataPortion;
        this.left = null;
        this.right = null;
    } // end constructor

    public void setData(T newData)
    {
        this.data = newData;
    } // end setData

    public T getData()
    {
        return this.data;
    } // end getData

    public TreeNode getLeft()
    {
        return this.left;
    } // end getLeft

    public void setLeft(TreeNode newLeft)
    {
        this.left = newLeft;
    } // end setLeft

    public boolean hasLeft()
    {
        return this.left != null;
    } // end hasLeft

    public TreeNode getRight()
    {
        return this.right;
    } // end getRight

    public void setRight(TreeNode newRight)
    {
        this.right = newRight;
    } // end setRight

    public boolean hasRight()
    {
        return this.right != null;
    } // end hasRight

    public boolean isLeaf()
    {
        return (this.left == null) && (this.right == null);
    } // end isLeaf

    public TreeNode<T> copy()
    {
        TreeNode<T> newRoot = new TreeNode<T>(this.data);
        if (this.left != null)
            newRoot.left = this.left.copy();
        if (this.right != null)
            newRoot.right = this.right.copy();
        return newRoot;
    } // end copy

    public int getHeight()
    {
        return getHeight(this); // call private getHeight
    } // end getHeight

    private int getHeight(TreeNode<T> node)
    {
        int height = 0;
        if (node != null)
            height = 1 + Math.max(getHeight(node.left),
                    getHeight(node.right));
        return height;
    } // end getHeight

    public int getNumberOfNodes()
    {
        int leftNumber = 0;
        int rightNumber = 0;

        if (this.left != null)
            leftNumber = this.left.getNumberOfNodes();

        if (this.right != null)
            rightNumber = this.right.getNumberOfNodes();

        return 1 + leftNumber + rightNumber;
    } // end getNumberOfNodes
}
