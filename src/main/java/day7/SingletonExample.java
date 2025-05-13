package day7;

import day7.스레드풀매니저.ThreadPoolManager;
import day7.의존성주입싱글톤.ApplicationContext;

public class SingletonExample {
    public static void main(String[] args) {
//        ThreadPoolManager threadPoolManager = ThreadPoolManager.getInstance();
//
//        threadPoolManager.execute(() -> {
//            // 동기
//            System.out.println("CPU 집약적 작업 실행 - " + Thread.currentThread().getName());
//        }, true);
//
//        threadPoolManager.execute(() -> {
//            System.out.println("I/O 집약적 작업 실행 - " + Thread.currentThread().getName());
//        }, false);
//
//        threadPoolManager.scheduleAtFixedRate(() -> {
//            System.out.println("예약된 작업 실행 - " + Thread.currentThread().getName());
//        }, 100, 1000);
//
//        System.out.println("동일 객체 여부: " + (threadPoolManager == ThreadPoolManager.getInstance()));
//
//        try {
//            Thread.sleep(200);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }



    }
}
