package Tree;

// 支持泛型，同时二分搜索树中的元素要是可比较的
public class BinarySearchTree<E extends Comparable<E>> {
    // 内部结点类
    private class Node {
        public E e;
        public Node left, right;

        public Node(E e){
            this.e = e;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;
    private int size;

    public BinarySearchTree(){
        this.root = null;
        this.size = 0;
    }

    public int getSize(){

        return size;
    }

    public boolean isEmpty(){

        return size == 0;
    }

    public void add(E e){

        root = add(root, e);
    }


    private Node add(Node node, E e){

        if (node == null){
            size++;
            return new Node(e);
        }

        if (e.compareTo(node.e) < 0)
            node.left = add(node.left, e);
        else if (e.compareTo(node.e) > 0)
            node.right = add(node.right, e);

        return node;

    }

    public boolean contains(E e){

        return contains(root, e);
    }

    private boolean contains(Node node, E e){

        if (node == null)
            return false;

        if (e.compareTo(node.e) == 0)
            return true;
        else if (e.compareTo(node.e) < 0)
            return contains(node.left, e);
        else
            return contains(node.right, e);
    }

    // 二分搜索树中最小元素
    public E minimum(){
        return minimum(root).e;
    }

    private Node minimum(Node node){

        if (node.left == null)
            return node;

        return minimum(node.left);
    }

    // 二分搜索树最大元素
    public E maximum(){
        return maximum(root).e;
    }

    private Node maximum(Node node){

        if (node.right == null)
            return node;

        return maximum(node.right);
    }

    public E removeMin(){
        E ret = minimum();
        root = removeMin(root);
        return ret;
    }

    private Node removeMin(Node node){

        if (node.left == null){
            // 保存右结点
            Node rightNode = node.right;
            // 将原先的右结点与树脱离开
            node.right = null;
            size--;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    public void remove(E e){
        root = remove(root, e);
    }

    private Node remove(Node node, E e){

        if (node == null){
            return null;
        }

        if (e.compareTo(node.e) < 0){
            node.left = remove(node.left, e);
            return node;
        }
        else if (e.compareTo(node.e) > 0){
            node.right = remove(node.right, e);
            return node;
        }
        else {
            if (node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }
            else if (node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }
            else {
                // 待删除结点的后继结点
                Node successor = minimum(node.right);
                successor.right = removeMin(node.right);
                successor.left = node.left;
                // 将原先的结点与树脱离关系
                node.left = node.right = null;
                return successor;
            }
        }



    }

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();

        bst.add((Integer) 21);
        bst.add((Integer) 18);
        bst.add((Integer) 19);
        bst.add((Integer) 25);
        bst.add((Integer) 22);
        bst.add((Integer) 23);
        bst.add((Integer) 29);
        bst.add((Integer) 26);
        bst.add((Integer) 30);

        boolean res = bst.contains((Integer) 30);

        System.out.println("值为30的结点：" + res);

//        Random random = new Random();
//        for (int i = 0; i < 1000; i++) {
//            bst.add(random.nextInt(10000));
//        }
//
//        ArrayList<Integer> arr = new ArrayList<>();
//        while (!bst.isEmpty())
//            arr.add((Integer) bst.removeMin());
//
//        System.out.println(arr);
//        for (int i = 1; i < arr.size(); i++) {
//            if (arr.get(i-1) > arr.get(i))
//                throw new IllegalArgumentException("error");
//        }
//
//        System.out.println("finished");
    }

}
