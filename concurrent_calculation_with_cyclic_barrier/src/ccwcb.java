import java.util.concurrent.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

class Computer implements Callable<Void> {
    private int parameter;
    private AtomicInteger result;
    private CyclicBarrier barrier;

    public Computer(int parameter, AtomicInteger result, CyclicBarrier barrier) {
        this.parameter = parameter;
        this.result = result;
        this.barrier = barrier;
    }

    @Override
    public Void call() throws Exception {

        if (parameter % 2 == 0) {
            result.incrementAndGet();
        } else {
            result.decrementAndGet();
        }

        barrier.await();
        return null;
    }
}

public class ccwcb {
    public static void main(String[] args) {

        AtomicInteger result = new AtomicInteger(0);


        CyclicBarrier barrier = new CyclicBarrier(111, () ->
                System.out.println(result.get())
        );


        ExecutorService executorService = Executors.newCachedThreadPool();


        List<Computer> computers = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 111; i++) {
            int randomNumber = random.nextInt(100) + 1;
            computers.add(new Computer(randomNumber, result, barrier));
        }

        try {

            executorService.invokeAll(computers);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);

        } finally {
            executorService.shutdown();
        }
    }
}
