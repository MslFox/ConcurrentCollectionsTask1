package Netology.Multithread_Functional.Multithread.СoncurrentCollections.Task1;

import java.util.concurrent.ConcurrentLinkedQueue;

public class CallCenter {
    protected ConcurrentLinkedQueue<String> calls = new ConcurrentLinkedQueue<>();
    private int id;

    public void addCall() {
        calls.add("Звонок "+ ++id);
    }
}
