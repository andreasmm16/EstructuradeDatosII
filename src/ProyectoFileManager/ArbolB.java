package ProyectoFileManager;

import java.io.IOException;
import java.util.ArrayList;

//public class ArbolB {
//
//    private int T;
//
//    public class Node {
//
//        int n;
//        EstructuraIndex key[] = new EstructuraIndex[(2 * T) - 1];
//        Node child[] = new Node[2 * T];
//        boolean leaf = true;
//
//        public int Find(String k) {
//            for (int i = 0; i < this.n; i++) {
//                int cmp = this.key[i].valor.compareTo(k);
//                if (cmp == 0) {
//                    return key[i].puntero_registro;
//                }
//            }
//            return -1;
//        }
//
//        public void Delete(String k) {
//            for (int i = 0; i < this.n; i++) {
//                if (this.key[i] != null) {
//                    int cmp = this.key[i].valor.compareTo(k);
//                    if (cmp == 0) {
//                        this.key[i] = null;
//                    }
//                }
//            }
//        }
//
//        public void Modify(String k, String nuevo) {
//            for (int i = 0; i < this.n; i++) {
//                if (this.key[i] != null) {
//                    int cmp = this.key[i].valor.compareTo(k);
//                    if (cmp == 0) {
//                        this.key[i].valor = nuevo;
//                    }
//                }
//            }
//        }
//    }
//
//    public ArbolB(int t) {
//        T = t;
//        root = new Node();
//        root.n = 0;
//        root.leaf = true;
//    }
//
//    private Node root;
//
//    // Search key
//    private Node Search(Node x, String key) {
//        int i = 0;
//        if (x == null) {
//            return x;
//        }
//        for (i = 0; i < x.n; i++) {
//            String str = "name";
//            int sum = key.chars().reduce(0, Integer::sum);
//            int cmp = x.key[i].valor.chars().reduce(0, Integer::sum);
//
//          
//            if (cmp == sum) {
//                return x;
//            }
//        }
//        if (x.leaf) {
//            return null;
//        } else {
//            return Search(x.child[i], key);
//        }
//    }
//
//    
//    //
////    int i = 0;
////    if (x == null)
////      return x;
////    for (i = 0; i < x.n; i++) {
////      if (key < x.key[i]) {
////        break;
////      }
////      if (key == x.key[i]) {
////        return x;
////      }
////    }
////    if (x.leaf) {
////      return null;
////    } else {
////      return Search(x.child[i], key);
////    }
//    // Splitting the node
//    private void Split(Node x, int pos, Node y) {
//        Node z = new Node();
//        z.leaf = y.leaf;
//        z.n = T - 1;
//        for (int j = 0; j < T - 1; j++) {
//            z.key[j] = y.key[j + T];
//        }
//        if (!y.leaf) {
//            for (int j = 0; j < T; j++) {
//                z.child[j] = y.child[j + T];
//            }
//        }
//        y.n = T - 1;
//        for (int j = x.n; j >= pos + 1; j--) {
//            x.child[j + 1] = x.child[j];
//        }
//        x.child[pos + 1] = z;
//
//        for (int j = x.n - 1; j >= pos; j--) {
//            x.key[j + 1] = x.key[j];
//        }
//        x.key[pos] = y.key[T - 1];
//        x.n = x.n + 1;
//    }
//
//    // Inserting a value
//    public void Insert(final EstructuraIndex key) {
//        Node r = root;
//
//        if (r.n == 2 * T - 1) {
//            Node s = new Node();
//            root = s;
//            s.leaf = false;
//            s.n = 0;
//            s.child[0] = r;
//            Split(s, 0, r);
//            insertValue(s, key);
//        } else {
//            insertValue(r, key);
//        }
//    }
//
//    // Insert the node
//    final private void insertValue(Node x, EstructuraIndex k) {
//
//        if (x.leaf) {
//            int cmp = 0;
//            int i = 0;
//            if (x.key[i] != null) {
//                cmp = k.valor.compareTo(x.key[i].valor);
//
//            }
//
//            for (i = x.n - 1; i >= 0 && cmp < 0; i--) {
//                x.key[i + 1] = x.key[i];
//            }
//            x.key[i + 1] = k;
//            x.n = x.n + 1;
//        } else {
//            int i = 0;
//            int cmp = k.valor.compareTo(x.key[i].valor);
//
//            for (i = x.n - 1; i >= 0 && cmp < 0; i--) {
//            }
//            i++;
//            Node tmp = x.child[i];
//            if (tmp.n == 2 * T - 1) {
//                Split(x, i, tmp);
//                int cmp2 = k.valor.compareTo(x.key[i].valor);
//
//                if (cmp2 > 0) {
//                    i++;
//                }
//            }
//            insertValue(x.child[i], k);
//        }
//    }
//
//    public void Show() {
//        Show(root);
//    }
//
//    public void WriteIndexFile() throws IOException {
//        WriteIndexFile(root);
//    }
//
//    // Display
//    private void Show(Node x) {
//        assert (x == null);
//        for (int i = 0; i < x.n; i++) {
//            System.out.print(x.key[i] + " ");
//
//        }
//        if (!x.leaf) {
//            for (int i = 0; i < x.n + 1; i++) {
//                Show(x.child[i]);
//            }
//        }
//    }
//
//    private void WriteIndexFile(Node x) throws IOException {
//
//        assert (x == null);
//        for (int i = 0; i < x.n; i++) {
//            if (x.key[i] != null) {
//                Main.indexFile.writeUTF(x.key[i].valor + ",");
//                Main.indexFile.writeInt(x.key[i].puntero_registro);
//                Main.indexFile.writeUTF("|");
//            }
//
//        }
//        if (!x.leaf) {
//            for (int i = 0; i < x.n + 1; i++) {
//                WriteIndexFile(x.child[i]);
//            }
//        }
//    }
//
//    public Node Contain(String k) {
//        Node search = this.Search(root, k);
//        if (search != null) {
//            return search;
//        }
//        return null;
//    }
//
////  public static void main(String[] args) {
////    ArbolB b = new ArbolB(3);
////    b.Insert(10);
////    b.Insert(20);
////    b.Insert(30);
////    b.Insert(40);
////    b.Insert(50);
////    b.Insert(60);
////    b.Insert(70);
////        b.Insert(80);
////
////b.Insert(90);
////    b.Show();
////
////    if (b.Contain(12)) {
////      System.out.println("\nfound");
////    } else {
////      System.out.println("\nnot found");
////    }
////    ;
////  }
//}
class BTree {

    private static final int T = 3;
    private Node mRootNode;
    private static final int LEFT_CHILD_NODE = 0;
    private static final int RIGHT_CHILD_NODE = 1;

    class Node {

        public int mNumKeys = 0;
        public int[] mKeys = new int[(2 * T) - 1];
        public EstructuraIndex[] mEstructuraIndexs = new EstructuraIndex[(2 * T) - 1];
        public Node[] mChildNodes = new Node[2 * T];
        public boolean mIsLeafNode;

        int binarySearch(int key) {
            int leftIndex = 0;
            int rightIndex = mNumKeys - 1;

            while (leftIndex <= rightIndex) {
                final int middleIndex = leftIndex + ((rightIndex - leftIndex) / 2);
                if (mKeys[middleIndex] < key) {
                    leftIndex = middleIndex + 1;
                } else if (mKeys[middleIndex] > key) {
                    rightIndex = middleIndex - 1;
                } else {
                    return middleIndex;
                }
            }

            return -1;
        }

        boolean contains(int key) {
            return binarySearch(key) != -1;
        }

        // Remove an element from a node and also the left (0) or right (+1) child.
        void remove(int index, int leftOrRightChild) {
            if (index >= 0) {
                int i;
                for (i = index; i < mNumKeys - 1; i++) {
                    mKeys[i] = mKeys[i + 1];
                    mEstructuraIndexs[i] = mEstructuraIndexs[i + 1];
                    if (!mIsLeafNode) {
                        if (i >= index + leftOrRightChild) {
                            mChildNodes[i] = mChildNodes[i + 1];
                        }
                    }
                }
                mKeys[i] = 0;
                mEstructuraIndexs[i] = null;
                if (!mIsLeafNode) {
                    if (i >= index + leftOrRightChild) {
                        mChildNodes[i] = mChildNodes[i + 1];
                    }
                    mChildNodes[i + 1] = null;
                }
                mNumKeys--;
            }
        }

        void shiftRightByOne() {
            if (!mIsLeafNode) {
                mChildNodes[mNumKeys + 1] = mChildNodes[mNumKeys];
            }
            for (int i = mNumKeys - 1; i >= 0; i--) {
                mKeys[i + 1] = mKeys[i];
                mEstructuraIndexs[i + 1] = mEstructuraIndexs[i];
                if (!mIsLeafNode) {
                    mChildNodes[i + 1] = mChildNodes[i];
                }
            }
        }

        int subtreeRootNodeIndex(int key) {
            for (int i = 0; i < mNumKeys; i++) {
                if (key < mKeys[i]) {
                    return i;
                }
            }
            return mNumKeys;
        }
    }

    public BTree() {
        mRootNode = new Node();
        mRootNode.mIsLeafNode = true;
    }

    public void add(int key, EstructuraIndex object) {
        Node rootNode = mRootNode;
        if (!update(mRootNode, key, object)) {
            if (rootNode.mNumKeys == (2 * T - 1)) {
                Node newRootNode = new Node();
                mRootNode = newRootNode;
                newRootNode.mIsLeafNode = false;
                mRootNode.mChildNodes[0] = rootNode;
                splitChildNode(newRootNode, 0, rootNode); // Split rootNode and move its median (middle) key up into newRootNode.
                insertIntoNonFullNode(newRootNode, key, object); // Insert the key into the B-Tree with root newRootNode.
            } else {
                insertIntoNonFullNode(rootNode, key, object); // Insert the key into the B-Tree with root rootNode.
            }
        }
    }

    public void Modify(int oldValue, int newValue, EstructuraIndex object) {
        EstructuraIndex s = search(oldValue);
    }

    // Split the node, node, of a B-Tree into two nodes that both contain T-1 elements and move node's median key up to the parentNode.
    // This method will only be called if node is full; node is the i-th child of parentNode.
    void splitChildNode(Node parentNode, int i, Node node) {
        Node newNode = new Node();
        newNode.mIsLeafNode = node.mIsLeafNode;
        newNode.mNumKeys = T - 1;
        for (int j = 0; j < T - 1; j++) { // Copy the last T-1 elements of node into newNode.
            newNode.mKeys[j] = node.mKeys[j + T];
            newNode.mEstructuraIndexs[j] = node.mEstructuraIndexs[j + T];
        }
        if (!newNode.mIsLeafNode) {
            for (int j = 0; j < T; j++) { // Copy the last T pointers of node into newNode.
                newNode.mChildNodes[j] = node.mChildNodes[j + T];
            }
            for (int j = T; j <= node.mNumKeys; j++) {
                node.mChildNodes[j] = null;
            }
        }
        for (int j = T; j < node.mNumKeys; j++) {
            node.mKeys[j] = 0;
            node.mEstructuraIndexs[j] = null;
        }
        node.mNumKeys = T - 1;

        // Insert a (child) pointer to node newNode into the parentNode, moving other keys and pointers as necessary.
        for (int j = parentNode.mNumKeys; j >= i + 1; j--) {
            parentNode.mChildNodes[j + 1] = parentNode.mChildNodes[j];
        }
        parentNode.mChildNodes[i + 1] = newNode;
        for (int j = parentNode.mNumKeys - 1; j >= i; j--) {
            parentNode.mKeys[j + 1] = parentNode.mKeys[j];
            parentNode.mEstructuraIndexs[j + 1] = parentNode.mEstructuraIndexs[j];
        }
        parentNode.mKeys[i] = node.mKeys[T - 1];
        parentNode.mEstructuraIndexs[i] = node.mEstructuraIndexs[T - 1];
        node.mKeys[T - 1] = 0;
        node.mEstructuraIndexs[T - 1] = null;
        parentNode.mNumKeys++;
    }

    // Insert an element into a B-Tree. (The element will ultimately be inserted into a leaf node). 
    void insertIntoNonFullNode(Node node, int key, EstructuraIndex object) {
        int i = node.mNumKeys - 1;
        if (node.mIsLeafNode) {
            // Since node is not a full node insert the new element into its proper place within node.
            while (i >= 0 && key < node.mKeys[i]) {
                node.mKeys[i + 1] = node.mKeys[i];
                node.mEstructuraIndexs[i + 1] = node.mEstructuraIndexs[i];
                i--;
            }
            i++;
            node.mKeys[i] = key;
            node.mEstructuraIndexs[i] = object;
            node.mNumKeys++;
        } else {
            // Move back from the last key of node until we find the child pointer to the node
            // that is the root node of the subtree where the new element should be placed.
            while (i >= 0 && key < node.mKeys[i]) {
                i--;
            }
            i++;
            if (node.mChildNodes[i].mNumKeys == (2 * T - 1)) {
                splitChildNode(node, i, node.mChildNodes[i]);
                if (key > node.mKeys[i]) {
                    i++;
                }
            }
            insertIntoNonFullNode(node.mChildNodes[i], key, object);
        }
    }

    public void delete(int key) {
        delete(mRootNode, key);
    }

    public String ReWriteIndexFile() throws IOException {
        return ReWriteIndexFile(mRootNode);
    }

    public String ReWriteIndexFile(Node node) throws IOException {
        String string = "";
        if (node != null) {
            if (node.mIsLeafNode) {
                for (int i = 0; i < node.mNumKeys; i++) {
                    string += node.mEstructuraIndexs[i].valor + ", ";
                    Main.indexFile.writeUTF(node.mEstructuraIndexs[i].valor + ",");
                    Main.indexFile.writeInt(node.mEstructuraIndexs[i].puntero_registro);
                    Main.indexFile.writeUTF("|");
                }
            } else {
                int i;
                for (i = 0; i < node.mNumKeys; i++) {
                    string += ReWriteIndexFile(node.mChildNodes[i]);
                    string += node.mEstructuraIndexs[i].valor + ", ";
                    Main.indexFile.writeUTF(node.mEstructuraIndexs[i].valor + ",");
                    Main.indexFile.writeInt(node.mEstructuraIndexs[i].puntero_registro);
                    Main.indexFile.writeUTF("|");
                }
                string += ReWriteIndexFile(node.mChildNodes[i]);
            }
        }
        return string;
    }

    public void delete(Node node, int key) {
        if (node.mIsLeafNode) { // 1. If the key is in node and node is a leaf node, then delete the key from node.
            int i;
            if ((i = node.binarySearch(key)) != -1) { // key is i-th key of node if node contains key.
                node.remove(i, LEFT_CHILD_NODE);
            }
        } else {
            int i;
            if ((i = node.binarySearch(key)) != -1) { // 2. If node is an internal node and it contains the key... (key is i-th key of node if node contains key)                   
                Node leftChildNode = node.mChildNodes[i];
                Node rightChildNode = node.mChildNodes[i + 1];
                if (leftChildNode.mNumKeys >= T) { // 2a. If the predecessor child node has at least T keys...
                    Node predecessorNode = leftChildNode;
                    Node erasureNode = predecessorNode; // Make sure not to delete a key from a node with only T - 1 elements.
                    while (!predecessorNode.mIsLeafNode) { // Therefore only descend to the previous node (erasureNode) of the predecessor node and delete the key using 3.
                        erasureNode = predecessorNode;
                        predecessorNode = predecessorNode.mChildNodes[node.mNumKeys - 1];
                    }
                    node.mKeys[i] = predecessorNode.mKeys[predecessorNode.mNumKeys - 1];
                    node.mEstructuraIndexs[i] = predecessorNode.mEstructuraIndexs[predecessorNode.mNumKeys - 1];
                    delete(erasureNode, node.mKeys[i]);
                } else if (rightChildNode.mNumKeys >= T) { // 2b. If the successor child node has at least T keys...
                    Node successorNode = rightChildNode;
                    Node erasureNode = successorNode; // Make sure not to delete a key from a node with only T - 1 elements.
                    while (!successorNode.mIsLeafNode) { // Therefore only descend to the previous node (erasureNode) of the predecessor node and delete the key using 3.
                        erasureNode = successorNode;
                        successorNode = successorNode.mChildNodes[0];
                    }
                    node.mKeys[i] = successorNode.mKeys[0];
                    node.mEstructuraIndexs[i] = successorNode.mEstructuraIndexs[0];
                    delete(erasureNode, node.mKeys[i]);
                } else { // 2c. If both the predecessor and the successor child node have only T - 1 keys...
                    // If both of the two child nodes to the left and right of the deleted element have the minimum number of elements,
                    // namely T - 1, they can then be joined into a single node with 2 * T - 2 elements.
                    int medianKeyIndex = mergeNodes(leftChildNode, rightChildNode);
                    moveKey(node, i, RIGHT_CHILD_NODE, leftChildNode, medianKeyIndex); // Delete i's right child pointer from node.
                    delete(leftChildNode, key);
                }
            } else { // 3. If the key is not resent in node, descent to the root of the appropriate subtree that must contain key...
                // The method is structured to guarantee that whenever delete is called recursively on node "node", the number of keys
                // in node is at least the minimum degree T. Note that this condition requires one more key than the minimum required
                // by usual B-tree conditions. This strengthened condition allows us to delete a key from the tree in one downward pass
                // without having to "back up".
                i = node.subtreeRootNodeIndex(key);
                Node childNode = node.mChildNodes[i]; // childNode is i-th child of node.                               
                if (childNode.mNumKeys == T - 1) {
                    Node leftChildSibling = (i - 1 >= 0) ? node.mChildNodes[i - 1] : null;
                    Node rightChildSibling = (i + 1 <= node.mNumKeys) ? node.mChildNodes[i + 1] : null;
                    if (leftChildSibling != null && leftChildSibling.mNumKeys >= T) { // 3a. The left sibling has >= T keys...                                              
                        // Move a key from the subtree's root node down into childNode along with the appropriate child pointer.
                        // Therefore, first shift all elements and children of childNode right by 1.
                        childNode.shiftRightByOne();
                        childNode.mKeys[0] = node.mKeys[i - 1]; // i - 1 is the key index in node that is smaller than childNode's smallest key.
                        childNode.mEstructuraIndexs[0] = node.mEstructuraIndexs[i - 1];
                        if (!childNode.mIsLeafNode) {
                            childNode.mChildNodes[0] = leftChildSibling.mChildNodes[leftChildSibling.mNumKeys];
                        }
                        childNode.mNumKeys++;

                        // Move a key from the left sibling into the subtree's root node. 
                        node.mKeys[i - 1] = leftChildSibling.mKeys[leftChildSibling.mNumKeys - 1];
                        node.mEstructuraIndexs[i - 1] = leftChildSibling.mEstructuraIndexs[leftChildSibling.mNumKeys - 1];

                        // Remove the key from the left sibling along with its right child node.
                        leftChildSibling.remove(leftChildSibling.mNumKeys - 1, RIGHT_CHILD_NODE);
                    } else if (rightChildSibling != null && rightChildSibling.mNumKeys >= T) { // 3a. The right sibling has >= T keys...                                    
                        // Move a key from the subtree's root node down into childNode along with the appropriate child pointer.
                        childNode.mKeys[childNode.mNumKeys] = node.mKeys[i]; // i is the key index in node that is bigger than childNode's biggest key.
                        childNode.mEstructuraIndexs[childNode.mNumKeys] = node.mEstructuraIndexs[i];
                        if (!childNode.mIsLeafNode) {
                            childNode.mChildNodes[childNode.mNumKeys + 1] = rightChildSibling.mChildNodes[0];
                        }
                        childNode.mNumKeys++;

                        // Move a key from the right sibling into the subtree's root node. 
                        node.mKeys[i] = rightChildSibling.mKeys[0];
                        node.mEstructuraIndexs[i] = rightChildSibling.mEstructuraIndexs[0];

                        // Remove the key from the right sibling along with its left child node.                                                
                        rightChildSibling.remove(0, LEFT_CHILD_NODE);
                    } else { // 3b. Both of childNode's siblings have only T - 1 keys each...
                        if (leftChildSibling != null) {
                            int medianKeyIndex = mergeNodes(childNode, leftChildSibling);
                            moveKey(node, i - 1, LEFT_CHILD_NODE, childNode, medianKeyIndex); // i - 1 is the median key index in node when merging with the left sibling.                          
                        } else if (rightChildSibling != null) {
                            int medianKeyIndex = mergeNodes(childNode, rightChildSibling);
                            moveKey(node, i, RIGHT_CHILD_NODE, childNode, medianKeyIndex); // i is the median key index in node when merging with the right sibling.
                        }
                    }
                }
                delete(childNode, key);
            }
        }
    }

    // Merge two nodes and keep the median key (element) empty.
    int mergeNodes(Node dstNode, Node srcNode) {
        int medianKeyIndex;
        if (srcNode.mKeys[0] < dstNode.mKeys[dstNode.mNumKeys - 1]) {
            int i;
            // Shift all elements of dstNode right by srcNode.mNumKeys + 1 to make place for the srcNode and the median key.
            if (!dstNode.mIsLeafNode) {
                dstNode.mChildNodes[srcNode.mNumKeys + dstNode.mNumKeys + 1] = dstNode.mChildNodes[dstNode.mNumKeys];
            }
            for (i = dstNode.mNumKeys; i > 0; i--) {
                dstNode.mKeys[srcNode.mNumKeys + i] = dstNode.mKeys[i - 1];
                dstNode.mEstructuraIndexs[srcNode.mNumKeys + i] = dstNode.mEstructuraIndexs[i - 1];
                if (!dstNode.mIsLeafNode) {
                    dstNode.mChildNodes[srcNode.mNumKeys + i] = dstNode.mChildNodes[i - 1];
                }
            }

            // Clear the median key (element).
            medianKeyIndex = srcNode.mNumKeys;
            dstNode.mKeys[medianKeyIndex] = 0;
            dstNode.mEstructuraIndexs[medianKeyIndex] = null;

            // Copy the srcNode's elements into dstNode.
            for (i = 0; i < srcNode.mNumKeys; i++) {
                dstNode.mKeys[i] = srcNode.mKeys[i];
                dstNode.mEstructuraIndexs[i] = srcNode.mEstructuraIndexs[i];
                if (!srcNode.mIsLeafNode) {
                    dstNode.mChildNodes[i] = srcNode.mChildNodes[i];
                }
            }
            if (!srcNode.mIsLeafNode) {
                dstNode.mChildNodes[i] = srcNode.mChildNodes[i];
            }
        } else {
            // Clear the median key (element).
            medianKeyIndex = dstNode.mNumKeys;
            dstNode.mKeys[medianKeyIndex] = 0;
            dstNode.mEstructuraIndexs[medianKeyIndex] = null;

            // Copy the srcNode's elements into dstNode.
            int offset = medianKeyIndex + 1;
            int i;
            for (i = 0; i < srcNode.mNumKeys; i++) {
                dstNode.mKeys[offset + i] = srcNode.mKeys[i];
                dstNode.mEstructuraIndexs[offset + i] = srcNode.mEstructuraIndexs[i];
                if (!srcNode.mIsLeafNode) {
                    dstNode.mChildNodes[offset + i] = srcNode.mChildNodes[i];
                }
            }
            if (!srcNode.mIsLeafNode) {
                dstNode.mChildNodes[offset + i] = srcNode.mChildNodes[i];
            }
        }
        dstNode.mNumKeys += srcNode.mNumKeys;
        return medianKeyIndex;
    }

    // Move the key from srcNode at index into dstNode at medianKeyIndex. Note that the element at index is already empty.
    void moveKey(Node srcNode, int srcKeyIndex, int childIndex, Node dstNode, int medianKeyIndex) {
        dstNode.mKeys[medianKeyIndex] = srcNode.mKeys[srcKeyIndex];
        dstNode.mEstructuraIndexs[medianKeyIndex] = srcNode.mEstructuraIndexs[srcKeyIndex];
        dstNode.mNumKeys++;

        srcNode.remove(srcKeyIndex, childIndex);

        if (srcNode == mRootNode && srcNode.mNumKeys == 0) {
            mRootNode = dstNode;
        }
    }

    public EstructuraIndex Modify(int key, String newKey) {
        return Modify(mRootNode, key, newKey);
    }

    public EstructuraIndex Modify(Node node, int key, String newKey) {
        int i = 0;
        while (i < node.mNumKeys && key > node.mKeys[i]) {
            i++;
        }
        if (i < node.mNumKeys && key == node.mKeys[i]) {
            int value = newKey.chars().reduce(0, Integer::sum);

            node.mKeys[i] = value;
            node.mEstructuraIndexs[i].valor = newKey;
            return node.mEstructuraIndexs[i];
        }
        if (node.mIsLeafNode) {
            return null;
        } else {
            return search(node.mChildNodes[i], key);
        }
    }

    public EstructuraIndex search(int key) {
        return search(mRootNode, key);
    }

    // Recursive search method.
    public EstructuraIndex search(Node node, int key) {
        int i = 0;
        while (i < node.mNumKeys && key > node.mKeys[i]) {
            i++;
        }
        if (i < node.mNumKeys && key == node.mKeys[i]) {
            return node.mEstructuraIndexs[i];
        }
        if (node.mIsLeafNode) {
            return null;
        } else {
            return search(node.mChildNodes[i], key);
        }
    }

    public EstructuraIndex search2(int key) {
        return search2(mRootNode, key);
    }

    // Iterative search method.
    public EstructuraIndex search2(Node node, int key) {
        while (node != null) {
            int i = 0;
            while (i < node.mNumKeys && key > node.mKeys[i]) {
                i++;
            }
            if (i < node.mNumKeys && key == node.mKeys[i]) {
                return node.mEstructuraIndexs[i];
            }
            if (node.mIsLeafNode) {
                return null;
            } else {
                node = node.mChildNodes[i];
            }
        }
        return null;
    }

    private boolean update(Node node, int key, EstructuraIndex object) {
        while (node != null) {
            int i = 0;
            while (i < node.mNumKeys && key > node.mKeys[i]) {
                i++;
            }
            if (i < node.mNumKeys && key == node.mKeys[i]) {
                node.mEstructuraIndexs[i] = object;
                return true;
            }
            if (node.mIsLeafNode) {
                return false;
            } else {
                node = node.mChildNodes[i];
            }
        }
        return false;
    }

    // Inorder walk over the tree.
    String printBTree(Node node) {
        String string = "";
        if (node != null) {
            if (node.mIsLeafNode) {
                for (int i = 0; i < node.mNumKeys; i++) {
                    string += node.mEstructuraIndexs[i] + ", ";
                }
            } else {
                int i;
                for (i = 0; i < node.mNumKeys; i++) {
                    string += printBTree(node.mChildNodes[i]);
                    string += node.mEstructuraIndexs[i] + ", ";
                }
                string += printBTree(node.mChildNodes[i]);
            }
        }
        return string;
    }

    public String toString() {
        return printBTree(mRootNode);
    }

    void validate() throws Exception {
        ArrayList<Integer> array = getKeys(mRootNode);
        for (int i = 0; i < array.size() - 1; i++) {
            if (array.get(i) >= array.get(i + 1)) {
                throw new Exception("B-Tree invalid: " + array.get(i) + " greater than " + array.get(i + 1));
            }
        }
    }

    // Inorder walk over the tree.
    ArrayList<Integer> getKeys(Node node) {
        ArrayList<Integer> array = new ArrayList<Integer>();
        if (node != null) {
            if (node.mIsLeafNode) {
                for (int i = 0; i < node.mNumKeys; i++) {
                    array.add(node.mKeys[i]);
                }
            } else {
                int i;
                for (i = 0; i < node.mNumKeys; i++) {
                    array.addAll(getKeys(node.mChildNodes[i]));
                    array.add(node.mKeys[i]);
                }
                array.addAll(getKeys(node.mChildNodes[i]));
            }
        }
        return array;
    }
}
