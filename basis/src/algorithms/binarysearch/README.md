# 二分查找

## 1 概念

给定一个整数x和**已排好序**的序列：A<sub>0</sub>, A<sub>1</sub>, ..., A<sub>n-1</sub>，后者已经预先排序并在内存中，求下标i使得A<sub>i</sub> = x。如果x不在数据中，则返回i = -1。

普通解法是从左到右扫描数据，其运行花费线性O(n)时间。而要充分利用序列已经排序这个事实，则可以采用**二分查找(折半查找，binary search)**。即：验证x是否为居中元素，如果是，则返回x所在下标；如果x小于居中元素，则应用同样的方法于居中元素左边的子序列；如果x大于居中元素，则应用同样的方法于居中元素右边的子序列。

## 2 步骤

1. 初始化left和right指针指向数组的第一个元素和最后一个元素；
2. 居中元素下标center = (left + right) / 2；
3. 如果x ＜ arr[center]，则更新right为center  - 1，继续比较；
4. 如果x ＞ arr[center]，则更新left为center  + 1，继续比较；
5. 如果x == arr[center]，说明已找到，则返回center；
6. 如果更新过程中left ＞ right，说明找不到需要查找的元素，则返回-1。

## 3 复杂度

### 3.1 时间复杂度

循环从right - left = n - 1开始，并保持right - left ≥ - 1。每次循环后right - left至少将该次循环前的值折半。于是，循环的次数最多为log(n - 1) + 2。如：若right - left == 128，则在各次迭代后right - left的最大值(即待查找序列的元素个数)为64，32，16，8，4，2，1，0，-1。因此，运行时间为**O(logn)**。

### 3.2 空间复杂度

#### 3.2.1 迭代方式

由于辅助空间为常数级别，因此迭代方式下折半查找的空间复杂度为**O(1)**。

#### 3.2.2 递归方式

递归的次数和深度都是logn，且每次所需要的辅助空间都是常数级别的，所以递归方式下折半查找的空间复杂度为**O(logn)**。