![Image alt](https://github.com/MsLFoxGit/ConcurrentCollectionsTask1/blob/8d323ae46e269538ab456ab0be5d891de01aceaf/src/Netology/Multithread_Functional/Multithread/%D0%A1oncurrentCollections/Task1/screenshots/scr.png)
### 1. Исправил
### 2. Моя идея реализации такова:
- ***Потоки-специалисты берут доступные звонки в работу.Методом Thread.sleep() нужно реализовать эмуляцию работы специалиста над вопросом (3-4 секунды, например)***
взял 200мс чтоб быстрее работало
```
        IntStream.range(0, operatorsValue).
                mapToObj(x -> new CallOperator(callCenter, x+1)).
                forEach(x -> executorService.submit(x::acceptCall));
```                
добавил в пул потоков , один поток -> один оператор  -> , по количеству операторов 
```
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
```
каждый отдельный `CallOperator`(их общее число = `operatorsValue`) в своем потоке отвечает на звонки (в режиме закончил один -> взял следующий ) пока не закончится очередь.
***Разве не так?***
