package Exercise;

/**
 * 使用两个线程，交替打印 数字 1-52和 字母 A-Z，
 * 要求：每打印两个数字打印一个字母
 * 打印结果为  12A34B...5152Z
 * */
public class AlternatePrintNumAndChar {
    public static volatile boolean printNum = true;
    public static Object obj = new Object();

    class printNum extends Thread{
        @Override
        public void run(){
            synchronized (obj){
                for (int i = 1; i <= 52;){
                    if (printNum){
                        System.out.print(i + "" + (i+1));
                        printNum = false;
                        i += 2;
                        obj.notify();
                    }else{
                        try {
                            obj.wait();
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    class printChar extends Thread{
        @Override
        public void run(){
            synchronized (obj){
                for (char ch = 'A'; ch <= 'Z'; ){
                    if (printNum == false){
                        System.out.print(ch);
                        printNum = true;
                        ch++;
                        obj.notify();
                    }else {
                        try {
                            obj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public void run(){
        new printNum().start();
        new printChar().start();
    }

    public static void main(String[] args){
        AlternatePrintNumAndChar alt = new AlternatePrintNumAndChar();
        alt.run();
    }

}
