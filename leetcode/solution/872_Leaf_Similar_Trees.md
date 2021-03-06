# 第872题 叶子相似的树

## 1 题目

请考虑一棵二叉树上所有的叶子，这些叶子的值按从左到右的顺序排列形成一个 叶值序列 。

举个例子，如上图所示，给定一棵叶值序列为 (6, 7, 4, 9, 8) 的树。

如果有两棵二叉树的叶值序列是相同，那么我们就认为它们是 叶相似 的。

如果给定的两个头结点分别为 root1 和 root2 的树是叶相似的，则返回 true；否则返回 false 。

示例 1：

![872-题图1](images/872-题图1.jpg)

输入：root1 = [3,5,1,6,2,9,8,null,null,7,4], root2 = [3,5,1,6,7,4,2,null,null,null,null,null,null,9,8]
输出：true
示例 2：

输入：root1 = [1], root2 = [1]
输出：true
示例 3：

输入：root1 = [1], root2 = [2]
输出：false
示例 4：

输入：root1 = [1,2], root2 = [2,2]
输出：true
示例 5：

![872-题图2](images/872-题图2.jpg)

输入：root1 = [1,2,3], root2 = [1,3,2]
输出：false


提示：

给定的两棵树可能会有 1 到 200 个结点。
给定的两棵树上的值介于 0 到 200 之间。

## 2 解法

### 2.1 递归

```c++
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */
class Solution {
public:
    bool leafSimilar(TreeNode* root1, TreeNode* root2) {
        vector<int> list1;
        vector<int> list2;

        addLeaves(root1, list1);
        addLeaves(root2, list2);

        return list1 == list2;
    }
private:
    void addLeaves(TreeNode* root, vector<int>& list) {
        if (root == nullptr) {
            return;
        }

        if (root->left == nullptr && root->right == nullptr) {
            list.push_back(root->val);
        }

        addLeaves(root->left, list);
        addLeaves(root->right, list);

        return;
    }    
};
```

复杂度分析：

设树1共有m个结点，树2共有n个结点。

1. 时间复杂度：树1和树2的每一个结点都被访问到，花费O(m + n)时间。判断存放叶子结点的list1和list2是否相等时，最坏情况下需要遍历这两个列表中的每一个元素，花费时间小于O(m + n)。因此，总时间复杂度为**O(m + n)**；
2. 空间复杂度：递归树1花费O(logm)的空间，最坏情况下退化为链表，花费O(m)空间；递归树2花费O(logn)的空间，最坏情况下退化为链表，花费O(n)空间；存放叶子结点的list1和list2花费空间小于O(m + n)。因此，总空间复杂度为**O(m + n)**。

### 2.2 迭代

思路：中序遍历的迭代版本，因为中序遍历可以率先拿到最左边的叶子结点，然后再找到右边的叶子结点。

```
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        addLeaves(root1, list1);
        addLeaves(root2, list2);

        return list1.equals(list2);
    }

    private void addLeaves(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }

        Deque<TreeNode> stack = new LinkedList<>();

        TreeNode node = root;

        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }

            node = stack.pop();
            if (node.left == null && node.right == null) {
                list.add(node.val);
            }
            node = node.right;
        }

        return;
    }
}
```

复杂度分析：

设树1共有m个结点，树2共有n个结点。

1. 时间复杂度：树1和树2的每一个结点都被访问到，花费O(m + n)时间。判断存放叶子结点的list1和list2是否相等时，最坏情况下需要遍历这两个列表中的每一个元素，花费时间小于O(m + n)。因此，总时间复杂度为**O(m + n)**；
2. 空间复杂度：栈中最多结点数为二叉树的深度，故空间复杂度为**O(logn)**，最坏情况下二叉树退化为链表，空间复杂度为O(n)。搜索树1花费O(logm)的空间，最坏情况下退化为链表，花费O(m)空间；搜索树2花费O(logn)的空间，最坏情况下退化为链表，花费O(n)空间；存放叶子结点的list1和list2花费空间小于O(m + n)。因此，总空间复杂度为**O(m + n)**。