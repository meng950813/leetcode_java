package Exercise;

/**
 * 三个线程交替打印，即3个线程是按顺序执行的。
 * 一个线程执行完之后，唤醒下一个线程，然后阻塞，等待被该线程的上一个线程唤醒。
 * 执行的顺序是一个环装的队列 0->1->2->0 ....->0->1->2
 * */

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 解题思路：
 * 由于三个线程一次只能有一个在打印数字，所以需要用一个锁来进行同步。
 * 但是在打印时要保证顺序就要求 一个线程打印完之后只能唤醒它的下一个线程，而不是唤醒所有的线程。 这就要求给每一个线程都有一个自己的状态来控制阻塞和唤醒。
 *
 *  java 并发包中的锁（实现了Lock接口的ReentrantLock、ReentrantReadWriteLock）有一个newCondition方法。
 *  调用该方法会返回与该锁绑定Condition对象实例。当线程获取锁之后，调用Condition实例的await方法会自动释放线程的锁，
 *
 *  当其他线程调用该Condition对象实例的signal方法后，该线程会自动尝试获取锁。
 *  通过对Condition的分析可知，我们只要对三个线程生成三个Condition对象。
 *  当一个线程打印一个数字之后就调用下一个线程的Condition对象的signal方法唤醒下一个线程，然后调用自己的Condition的await线程进入等待状态。
 *  这样就实现了线程执行顺序的控制。由于线程的执行是一个环形的队列，我们用一个数组存放每个线程的Condition对象，
 *  通过对下标加一然后取模来实现环形队列。
 */
public class ThreeThreadAlternatePrint extends Thread {

    private static int sequence = 0; // 多个线程共享该数据
    private static final int SEQUENCE_END = 100; // 打印边界

    private int id; // 线程id, 可以对应到 conditions数组的下标， 用于切换线程
    private ReentrantLock lock; // 用于加锁
    // 监视器对象
    private Condition[] conditions;

    public ThreeThreadAlternatePrint(int id){
        this.id = id;
        this.setName("Thread" + id); // 设置线程名
        this.lock = new ReentrantLock();
//        for ()
//        this.conditions = conditions;
    }

    @Override
    public void run(){
        while (sequence >= 0 && sequence < SEQUENCE_END){
            lock.lock();
            try {
                //对序号取模,如果不等于当前线程的id ==> 未到当前线程打印的时候
                // 则先唤醒其他线程,然后当前线程进入等待状态
                while (sequence % conditions.length != id) {
                    // 唤醒下一个线程
                    conditions[(id + 1) % conditions.length].signal();
                    // 当前线程阻塞
                    conditions[id].await();
                }
                System.out.println(Thread.currentThread().getName() + " " + sequence);
                //序号加1
                ++sequence;
                //唤醒当前线程的下一个线程
                conditions[(id + 1) % conditions.length].signal();
                //当前线程进入等待状态
                conditions[id].await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                // 释放锁
                lock.unlock();
            }
        }
        //数字打印完毕,线程结束前唤醒其余的线程,让其他线程也可以结束
        end();
    }

    public void end(){
        lock.lock();
        for (int i  = 0; i< conditions.length; i++){
            conditions[i].signal();
        }
        lock.unlock();
    }

    public static void main(String[] args) {
        int threadNum = 3;
        ReentrantLock lock = new ReentrantLock();
        Condition[] conditions = new Condition[threadNum];
        ThreeThreadAlternatePrint[] threeThreadAlternatePrints = new ThreeThreadAlternatePrint[threadNum];
        for (int i = 0; i < threadNum; i++){
            conditions[i] = lock.newCondition();
        }
        for (int  i = 0; i< threadNum; i++){
            threeThreadAlternatePrints[i] = new ThreeThreadAlternatePrint(i);
        }

        for (ThreeThreadAlternatePrint printNumber : threeThreadAlternatePrints) {
            printNumber.start();
        }

    }
}
