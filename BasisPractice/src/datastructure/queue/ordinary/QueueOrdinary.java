package datastructure.queue.ordinary;

/**
 * @Description: 定义队列基本元素和方法：根据大小创建，判断队列空/满，出队/入队，查看队列、队头元素
 */
public class QueueOrdinary {
    // 用数组表示队列
    private int[] arr;
    // 队列长度
    private int maxSize;
    // 头指针，指向队列第一个元素的【前一个】位置
    private int front;
    // 尾指针，指向队列的最后一个元素
    private int rear;

    /* 构造方法，传递数组大小，通过该值创建队列 */
    public QueueOrdinary(int arrSize) {
        maxSize = arrSize;
        arr = new int[maxSize];

        // 初始时，头指针和尾指针都指向-1
        front = -1;
        rear = -1;
    }

    /**
     * @Description: 判断队列是否为空
     */
    public boolean isEmpty() {
        return front == rear;
    }

    /**
     * @Description: 判断队列是否已满
     */
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    /**
     * @Description: 元素入队
     * @param: n 入队元素
     */
    public void enqueue(int n) {
        // 首先判断队列是否已满
        if (isFull()) {
            System.out.println("队列已满，元素无法入队！");
            return;
        }

        rear++;
        arr[rear] = n;
    }

    /**
     * @Description: 元素出队
     */
    public int dequeue() {
        // 判断队列是否空
        if (isEmpty()) {
            throw new RuntimeException("队列空，元素无法出队！");
        }

        front++;

        // 弹出元素
        return arr[front];
    }

    /**
     * @Description: 查看队列元素
     */
    public void showQueue() {
        // 判断队列是否空
        if (isEmpty()) {
            throw new RuntimeException("队列空，无法查看队列！");
        }

        for (int i = front + 1; i <= rear; i++) {
            System.out.printf("arr[%d] = %d\n", i, arr[i]);
        }
    }

    /**
     * @Description: 查看队头元素
     */
    public int peekQueue() {
        // 判断队列是否空
        if (isEmpty()) {
            throw new RuntimeException("队列空，无法查看队头数据！");
        }

        return arr[front + 1];
    }
}