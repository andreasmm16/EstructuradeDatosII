package ProyectoFileManager;

import java.io.IOException;

public class ArbolB {

    private int T;

    public class Node {

        int n;
        EstructuraIndex key[] = new EstructuraIndex[(2 * T) - 1];
        Node child[] = new Node[2 * T];
        boolean leaf = true;

        public int Find(String k) {
            for (int i = 0; i < this.n; i++) {
                int cmp = this.key[i].valor.compareTo(k);
                if (cmp == 0) {
                    return key[i].puntero_registro;
                }
            }
            return -1;
        }

        public void Delete(String k) {
            for (int i = 0; i < this.n; i++) {
                if (this.key[i] != null) {
                    int cmp = this.key[i].valor.compareTo(k);
                    if (cmp == 0) {
                        this.key[i] = null;
                    }
                }
            }
        }

        public void Modify(String k, String nuevo) {
            for (int i = 0; i < this.n; i++) {
                if (this.key[i] != null) {
                    int cmp = this.key[i].valor.compareTo(k);
                    if (cmp == 0) {
                        this.key[i].valor = nuevo;
                    }
                }
            }
        }
    }

    public ArbolB(int t) {
        T = t;
        root = new Node();
        root.n = 0;
        root.leaf = true;
    }

    private Node root;

    // Search key
    private Node Search(Node x, String key) {
        int i = 0;
        if (x == null) {
            return x;
        }
        for (i = 0; i < x.n; i++) {
            String str = "name";
            int sum = key.chars().reduce(0, Integer::sum);
            int cmp = x.key[i].valor.chars().reduce(0, Integer::sum);

          
            if (cmp == sum) {
                return x;
            }
        }
        if (x.leaf) {
            return null;
        } else {
            return Search(x.child[i], key);
        }
    }

    
    //
//    int i = 0;
//    if (x == null)
//      return x;
//    for (i = 0; i < x.n; i++) {
//      if (key < x.key[i]) {
//        break;
//      }
//      if (key == x.key[i]) {
//        return x;
//      }
//    }
//    if (x.leaf) {
//      return null;
//    } else {
//      return Search(x.child[i], key);
//    }
    // Splitting the node
    private void Split(Node x, int pos, Node y) {
        Node z = new Node();
        z.leaf = y.leaf;
        z.n = T - 1;
        for (int j = 0; j < T - 1; j++) {
            z.key[j] = y.key[j + T];
        }
        if (!y.leaf) {
            for (int j = 0; j < T; j++) {
                z.child[j] = y.child[j + T];
            }
        }
        y.n = T - 1;
        for (int j = x.n; j >= pos + 1; j--) {
            x.child[j + 1] = x.child[j];
        }
        x.child[pos + 1] = z;

        for (int j = x.n - 1; j >= pos; j--) {
            x.key[j + 1] = x.key[j];
        }
        x.key[pos] = y.key[T - 1];
        x.n = x.n + 1;
    }

    // Inserting a value
    public void Insert(final EstructuraIndex key) {
        Node r = root;

        if (r.n == 2 * T - 1) {
            Node s = new Node();
            root = s;
            s.leaf = false;
            s.n = 0;
            s.child[0] = r;
            Split(s, 0, r);
            insertValue(s, key);
        } else {
            insertValue(r, key);
        }
    }

    // Insert the node
    final private void insertValue(Node x, EstructuraIndex k) {

        if (x.leaf) {
            int cmp = 0;
            int i = 0;
            if (x.key[i] != null) {
                cmp = k.valor.compareTo(x.key[i].valor);

            }

            for (i = x.n - 1; i >= 0 && cmp < 0; i--) {
                x.key[i + 1] = x.key[i];
            }
            x.key[i + 1] = k;
            x.n = x.n + 1;
        } else {
            int i = 0;
            int cmp = k.valor.compareTo(x.key[i].valor);

            for (i = x.n - 1; i >= 0 && cmp < 0; i--) {
            }
            i++;
            Node tmp = x.child[i];
            if (tmp.n == 2 * T - 1) {
                Split(x, i, tmp);
                int cmp2 = k.valor.compareTo(x.key[i].valor);

                if (cmp2 > 0) {
                    i++;
                }
            }
            insertValue(x.child[i], k);
        }
    }

    public void Show() {
        Show(root);
    }

    public void WriteIndexFile() throws IOException {
        WriteIndexFile(root);
    }

    // Display
    private void Show(Node x) {
        assert (x == null);
        for (int i = 0; i < x.n; i++) {
            System.out.print(x.key[i] + " ");

        }
        if (!x.leaf) {
            for (int i = 0; i < x.n + 1; i++) {
                Show(x.child[i]);
            }
        }
    }

    private void WriteIndexFile(Node x) throws IOException {

        assert (x == null);
        for (int i = 0; i < x.n; i++) {
            if (x.key[i] != null) {
                Main.indexFile.writeUTF(x.key[i].valor + ",");
                Main.indexFile.writeInt(x.key[i].puntero_registro);
                Main.indexFile.writeUTF("|");
            }

        }
        if (!x.leaf) {
            for (int i = 0; i < x.n + 1; i++) {
                WriteIndexFile(x.child[i]);
            }
        }
    }

    public Node Contain(String k) {
        Node search = this.Search(root, k);
        if (search != null) {
            return search;
        }
        return null;
    }

//  public static void main(String[] args) {
//    ArbolB b = new ArbolB(3);
//    b.Insert(10);
//    b.Insert(20);
//    b.Insert(30);
//    b.Insert(40);
//    b.Insert(50);
//    b.Insert(60);
//    b.Insert(70);
//        b.Insert(80);
//
//b.Insert(90);
//    b.Show();
//
//    if (b.Contain(12)) {
//      System.out.println("\nfound");
//    } else {
//      System.out.println("\nnot found");
//    }
//    ;
//  }
}
