package com.just.ytdemo.demoTest.DataStructureTest;

/**
 * @author yutao.yan
 * @date 2018/8/9 13:57
 *
 * 栈
 * 先进后出
 */
public class MyStack<T> {

    /**
     * 栈顶
     */
    public Node stackTop;


    /**
     * 往栈中加数据
     * @param data
     */
    public void push(T data) {

        Node newData = new Node(data);

        newData.next = stackTop;

        stackTop = newData;

    }

    /**
     * 遍历  栈
     */
    public void printAll() {

        Node temp = stackTop;
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
        System.out.println(temp == null?"":temp.data);
    }


    /**
     * 出栈
     */
    public void popStack() {

        if (stackTop != null) {
            Node temp = stackTop;
            stackTop = temp.next;
        } else {
            System.out.println("栈为空 无法出栈");
        }
    }

    /**
     * 清栈
     * 栈顶 为 null
     * 栈底 为 null
     */
    public void clearStack(){
        stackTop = null;
//        stackBottom = null;
    }



    public static void main(String[] args) {
        MyStack<Integer> myStack = new MyStack<>();
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        myStack.printAll();
//        myStack.popStack();
//        myStack.popStack();
//        myStack.popStack();
        myStack.popStack();
        System.out.println("出栈一次");

        System.out.println(4 ^ 4 ^ 1);

//        myStack.clearStack();
        myStack.printAll();
    }
}
