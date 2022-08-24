package Netology.Multithread_Functional.Multithread.СoncurrentCollections.Task1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws InterruptedException {
       task1(3, 60, 3, 2000);
    }

    public static void task1(int operatorsValue, int callsValue, int cyclesCallsValue, int timeOut) throws InterruptedException {
        System.out.println("\n**********************************\n" +
                "* Задача 1. Задача 1. Колл-центр *\n" +
                "**********************************\n");

        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        CallCenter callCenter = new CallCenter();

        executorService.submit(() -> {
            int cycleCount = 0;
            while (cycleCount < cyclesCallsValue) {
                IntStream.range(0, callsValue).forEach(call -> callCenter.addCall());
                cycleCount++;
                try {
                    Thread.sleep(timeOut);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread.sleep(timeOut);
        IntStream.range(0, operatorsValue).
                mapToObj(x -> new CallOperator(callCenter, x+1)).
                forEach(x -> executorService.submit(x::acceptCall));

        executorService.shutdown();
        while (!executorService.isTerminated()) {
            Thread.sleep(timeOut / 10);
        }
    }

}
