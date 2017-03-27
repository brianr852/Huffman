# Huffman
Program of the Huffman code algorithm.</br>
In general terms the algorithm is as follow:</br>
1.	Count frequency of each letter and save this information in an array of Huffman trees</br>
2.	Repeat the following steps until the complete Huffman tree is created (the number of trees at the end will be 1)</br>
a.	Find the smallest tree and swap it to be the last</br>
b.	Find the second smallest tree and swap it to be the second last</br>
c.	Combine the two smallest trees into one and place it where the second smallest three was</br>
d.	Decrement number of trees by 1</br>
3.	Encode the given message</br>
a.	Branching to the left will encode as ‘0’, and branching to the right will encode as ‘1’</br>
</br>
Based on the above algorithm “Jill Dill” will encode to “110 111 0 0 100 101 111 0 0”</br>
</br>
The message lines are:</br>
Jill Dill</br>
</br>
Creating 5 initial trees</br>
---> Count of character   is 1</br>
---> Count of character D is 1</br>
---> Count of character J is 1</br>
---> Count of character i is 2</br>
---> Count of character l is 4</br>
Building Huffman Tree</br>
--->Smallest tree moved to the position 4</br>
--->Second smallest tree moved to the position 3</br>
--->Combined tree created: [ , D] -> [ , D] -> 2</br>
--->Combined tree added at position 3</br>
--->Smallest tree moved to the position 3</br>
--->Second smallest tree moved to the position 2</br>
--->Combined tree created: [J, i] -> [J, i] -> 3</br>
--->Combined tree added at position 2</br>
--->Smallest tree moved to the position 2</br>
--->Second smallest tree moved to the position 1</br>
--->Combined tree created: [ , D, J, i] -> [ , D, J, i] -> 5</br>
--->Combined tree added at position 1</br>
--->Smallest tree moved to the position 1</br>
--->Second smallest tree moved to the position 0</br>
--->Combined tree created: [l,  , D, J, i] -> [l,  , D, J, i] -> 9</br>
--->Combined tree added at position 0</br>
</br>
Huffman Tree:</br>
[l,  , D, J, i] -> 9</br>
[l] -> 4</br>
[ , D, J, i] -> 5</br>
[ , D] -> 2</br>
[ ] -> 1</br>
[D] -> 1</br>
[J, i] -> 3</br>
[J] -> 1</br>
[i] -> 2</br>
</br>
The coded lines are (displaying 80 characters per line):</br>
110 111 0 0 100 101 111 0 0 </br>
