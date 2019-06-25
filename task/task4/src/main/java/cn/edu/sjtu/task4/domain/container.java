package cn.edu.sjtu.task4.domain;

import java.util.Queue;
import java.util.Stack;

public class container {
    private int num;
    private Stack<String> stack;
    private Queue<String> queue;
    private boolean isrun;

    public container() {
    }

    public container(int max){
        this.num = max;
        this.stack.clear();
        this.queue.clear();
        this.isrun = false;
    }

    /**
     * @param isrun the isrun to set
     */
    public void setIsrun(boolean isrun) {
        this.isrun = isrun;
    }

    public boolean getIsrun() {
        return isrun;
    }

    /**
     * @param num the num to set
     */
    public void setNum(int num) {
        this.num = num;
    }

    /**
     * @return the num
     */
    public int getNum() {
        return num;
    }

    /**
     * @return the stack
     */
    public Stack<String> getStack() {
        return stack;
    }

    /**
     * @param stack the stack to set
     */
    public void setStack(Stack<String> stack) {
        this.stack = stack;
    }

    /**
     * @return the queue
     */
    public Queue<String> getQueue() {
        return queue;
    }

    /**
     * @param queue the queue to set
     */
    public void setQueue(Queue<String> queue) {
        this.queue = queue;
    }

    public void get(String s) throws InterruptedException {
        if (queue.size() < num) {
            queue.add(s);
        }
        else {
            stack.add(s);
        }
        if(!isrun) run();
    }

    public void run() throws InterruptedException {
        isrun = true;
        while(true) {
            Thread.sleep(5000);
            if (!stack.isEmpty()) {
                System.out.println(stack.pop());
            }
            else if (!queue.isEmpty()) {
                System.out.println(queue.poll());
            }
            else {
                isrun = false;
                break;
            }
        }
    }
}