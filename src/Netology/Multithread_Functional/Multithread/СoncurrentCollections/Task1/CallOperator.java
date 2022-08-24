package Netology.Multithread_Functional.Multithread.СoncurrentCollections.Task1;

public class CallOperator {
    private final int id;
    private final CallCenter callCenter;

    public CallOperator(CallCenter callCenter, int id) {
        this.callCenter = callCenter;
        this.id = id;
    }

    public void acceptCall() {
        String currentPhoneCall;
        while ((currentPhoneCall = callCenter.calls.poll()) != null) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.printf("Оператор %d принял %s \n", id, currentPhoneCall);
        }
    }
}
