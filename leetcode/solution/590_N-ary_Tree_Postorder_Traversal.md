# 第590题 N叉树的后序遍历

## 1 题目

给定一个 N 叉树，返回其节点值的 后序遍历 。

N 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 null 分隔（请参见示例）。

示例 1：

![590-题图1](images/590-题图1.png)

输入：root = [1,null,3,2,4,null,5,6]
输出：[5,6,3,2,4,1]
示例 2：

![590-题图2](images/590-题图2.png)

输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
输出：[2,6,14,11,7,3,12,8,4,13,9,10,5,1]

## 2 解法

### 2.1 递归

```
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
    List<Integer> res = new ArrayList<>();
    
    public List<Integer> postorder(Node root) {
        if (root == null) {
            return res;
        }

        List<Node> childrenNodes = root.children;

        for (Node child : childrenNodes) {
            postorder(child);
        }

        res.add(root.val);
        
        return res;
    }
}
```

复杂度分析：

1. 时间复杂度：每个结点都被访问一遍，故时间复杂度为**O(n)**；
2. 空间复杂度：递归栈空间为二叉树的深度，故空间复杂度为**O(logn)**，最坏情况下二叉树退化为链表，空间复杂度为O(n)。

### 2.2 迭代

```
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
    public List<Integer> postorder(Node root) {
        List<Integer> res = new ArrayList<>();

        if (root == null) {
            return res;
        }

        Deque<Node> stack = new LinkedList<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node node = stack.pop();

            if (node != null) {
                res.add(node.val);
                List<Node> childrenNodes = node.children;
                for (int i = 0; i < childrenNodes.size(); i++) {
                    stack.push(childrenNodes.get(i));
                }
            }
        }

        Collections.reverse(res);

        return res;
    }
}
```

复杂度分析：

1. 时间复杂度：每个结点都被访问一遍，故时间复杂度为**O(n)**；
2. 空间复杂度：队列中最大结点数不超过n个，故空间复杂度为**O(n)**。

