# 栈

## 1 概念

栈(stack)是一种限制插入和身处智能在一个位置上进行的表，该位置叫做栈顶(top)。由于每次删除的元素都是最后进栈的元素，故栈也被称为**后进先出**或**先进后出**(Last In First Out, LIFO)表。

每个栈都有一个栈顶指针，它总是指向最后一个入栈的元素。栈有两种基本操作，即压栈(入栈) - (push)和弹栈(出栈) - (pop)，因为在进栈只需要移动一个变量存储空间，所以它的时间复杂度为O(1)。

![栈示意图](images/栈示意图.jpg)

## 2 基本操作(用链表实现)

### 2.1 初始状态

初始状态下，链表只有头结点head，栈内并无元素。

![栈-初始状态](images/栈-初始状态.png)

### 2.2 压栈

1. 创建待压栈结点，新结点的next指向原栈顶元素；
2. 将新结点更新为新的栈顶元素。

![压栈-1](images/压栈-1.png)

![压栈-2](images/压栈-2.png)

![压栈-3](images/压栈-3.png)

### 2.3 弹栈

1. 判断栈内是否为空，即判断栈顶head的next是否为null；
2. 返回当前栈顶元素；
3. 将原来栈的第二个元素成为新的栈顶元素。

![弹栈-1](images/弹栈-1.png)

![弹栈-2](images/弹栈-2.png)

### 2.4 修改

1. 判断传入位置索引是否越界；
2. 遍历找到对应位置的结点，并将该结点的数据域set为新值。

### 2.4 查看元素

#### 2.4.1 栈顶元素

top指针指向栈顶，故直接返回top即可。

#### 2.4.2 遍历栈元素

如果遍历时node指针指向了链表的头结点，则通过node.next判断是否为null：如果是null则表明当前node指向链表的头结点，则不输出，直接跳出循环。

## 3 括号匹配问题
给定一个字符串，里面可能包含 ( ) 小括号和其他字符，请编写程序检查该字符串中的小括号是否成对出现。
e.g. 
- (上海)(长安)：正确
- 上海((长安))：正确
- 上海(长安(北京)(深圳)南京)：正确
- 上海(长安))：错误
- ((上海)长安：错误

步骤：

1. 将字符串分割为字符数组，遍历数组，仅判断左右括号；
2. 创建栈，存放左括号，即栈中元素只有 ( 或 null 两种情况；
3. 每遇到一个左括号，就将左括号压栈；每遇到一个右括号，就弹栈；
4. 如果弹出来的元素为null，说明栈中没有左括号，即字符串中右括号数量大于左括号，直接返回false；
5. 在字符数组遍历完成后，如果栈中还有元素，即有残留的左括号，说明字符串中左括号数量大于右括号，也返回false。
## 4 中缀表达式 -> 后缀表达式

#### 4.1 中缀表达式

在日常应用中，算术表达式中运算符总是出现在两个操作数之间，例如5 * (7 - 2 * 3)+ 8 / 2，这种形式称为中缀表达式。计算一个中缀表达式需要知道运算符的优先级和结合性。乘除是高优先级，加减是低优先级，优先级相同时他们都是左结合的，也就是从左计算到右。有括号就要计算括号内的表达式。

#### 4.2 后缀表达式

后缀表达式：将运算符写在操作数之后，也叫逆波兰表达式(Reverse Polish Notation, RPN)。

#### 4.3 两种表达式举例

|       中缀表达式        |      后缀表达式       |
| :---------------------: | :-------------------: |
| 2 * (9 + 6 / 3 - 5) + 4 | 2 9 6 3 / + 5 - * 4 + |
|  1 + ((2 + 3) * 4) - 5  |   1 2 3 + 4 * + 5 -   |

#### 4.4 分析
1. 用一个栈临时存放操作符，用一个列表存放输出表达式的结果；
2. 遍历字符数组，如果是数字，则直接添加到输出表达式列表；
3. 如果遍历到操作符，则根据优先级判断：
   1. *和 / 的优先级最高，+ 和 - 的优先级次之，( ) 的优先级最低，但后续处理有特殊情况；
   4. 如果遇到 ( ，则**直接**入栈；
   5. 如果遇到 ) ，则将栈顶元素弹出并添加到输出表达式列表中，**直到**栈顶元素为 ( 时停下，再将 ( 弹出但不添加到列表；
   4. 如果栈外操作符优先级**大于**栈内操作符，则直接入栈；
   5. 如果栈外操作符优先级**小于等于**栈内操作符，则将栈内元素弹出并添加到输出表达式列表中，直到栈外优先级**大于**栈内优先级时，将栈外操作符压栈；
4. 最后再将栈内元素全部弹出并添加到输出表达式列表中。

以1 + ((2 + 3) * 4) - 5为例推导：

1. 遍历到操作数，则直接将数字添加到输出表达式列表：

   ![中转后-1](images/中转后-1.png)

2. 遍历到操作符，栈外操作符大于栈内操作符优先级，则栈外操作符直接入栈：

   ![中转后-2](images/中转后-2.png)

3. 遍历到左括号，直接入栈：

   ![中转后-3](images/中转后-3.png)

4. 遍历到左括号，直接入栈：

   ![中转后-4](images/中转后-4.png)

5. 遍历到操作数，则直接将数字添加到输出表达式列表：

   ![中转后-5](images/中转后-5.png)

6. 遍历到操作符，栈外操作符大于栈内操作符优先级，则栈外操作符直接入栈：

   ![中转后-6](images/中转后-6.png)

7. 遍历到操作数，则直接将数字添加到输出表达式列表：

   ![中转后-7](images/中转后-7.png)

8. 遍历到右括号，将栈顶元素弹出并添加到输出表达式列表中，直到栈顶元素为 ( 时停下，再将 ( 弹出但不添加到列表：

   ![中转后-8](images/中转后-8.png)

9. 遍历到操作符，栈外操作符大于栈内操作符优先级，则栈外操作符直接入栈：

   ![中转后-6](images/中转后-9.png)

10. 遍历到操作数，则直接将数字添加到输出表达式列表：

    ![中转后-10](images/中转后-10.png)

11. 遍历到右括号，将栈顶元素弹出并添加到输出表达式列表中，直到栈顶元素为 ( 时停下，再将 ( 弹出但不添加到列表：

    ![中转后-11](images/中转后-11.png)

12. 遍历到操作符，栈外操作符小于等于栈内操作符优先级，则将栈内元素弹出并添加到输出表达式列表中，直到栈外优先级大于栈内优先级时，将栈外操作符压栈；

    ![中转后-12](images/中转后-12.png)

13. 遍历到操作数，则直接将数字添加到输出表达式列表：

    ![中转后-13](images/中转后-13.png)

14. 最后再将栈内元素全部弹出并添加到输出表达式列表中：

    ![中转后-14](images/中转后-14.png)

## 5 后缀表达式 -> 计算结果

给定一个后缀表达式，根据该表达式计算结果。

|      后缀表达式       | 结果 |     对应中缀表达式      |
| :-------------------: | :--: | :---------------------: |
| 2 9 6 3 / + 5 - * 4 + |  16  | 2 * (9 + 6 / 3 - 5) + 4 |
|   1 2 3 + 4 * + 5 -   |  16  |  1 + ((2 + 3) * 4) - 5  |

#### 5.1 分析

1. 创建存放操作数的栈；
2. 遍历字符数组，如果遇到操作数，则入栈；如果遇到操作符，则弹出栈顶两个元素，进行运算，再将结果压栈。PS.弹栈顺序和运算顺序不一致，后弹出的元素的原计算位置为前面；
3. 最后把栈顶元素弹出，即得到运算结果。

以1 2 3 + 4 * + 5 -为例推导：

1. 遍历到操作数，直接入栈：

   ![后转结果-1](images/后转结果-1.png)

2. 遍历到操作数，直接入栈：

   ![后转结果-2](images/后转结果-2.png)

3. 遍历到操作数，直接入栈：

   ![后转结果-3](images/后转结果-3.png)

4. 遍历到操作符，弹出栈顶两个元素，但注意计算顺序为 (后弹出 运算 先弹出)，再将计算结果压栈：

   ![后转结果-4](images/后转结果-4.png)

5. 遍历到操作数，直接入栈：

   ![后转结果-5](images/后转结果-5.png)

6. 遍历到操作符，弹出栈顶两个元素，但注意计算顺序为 (后弹出 运算 先弹出)，再将计算结果压栈：

   ![后转结果-6](images/后转结果-6.png)

7. 遍历到操作符，弹出栈顶两个元素，但注意计算顺序为 (后弹出 运算 先弹出)，再将计算结果压栈：

   ![后转结果-7](images/后转结果-7.png)

8. 遍历到操作数，直接入栈：

   ![后转结果-8](images/后转结果-8.png)

9. 遍历到操作符，弹出栈顶两个元素，但注意计算顺序为 (后弹出 运算 先弹出)，再将计算结果压栈：

   ![后转结果-7](images/后转结果-9.png)

10. 最后把栈顶元素弹出，即得到运算结果。