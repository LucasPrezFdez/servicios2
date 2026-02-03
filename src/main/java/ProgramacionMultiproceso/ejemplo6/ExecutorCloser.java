package ProgramacionMultiproceso.ejemplo6;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Wrapper para usar ExecutorService en try-with-resources.
 */
public class ExecutorCloser implements AutoCloseable {
    private final ExecutorService executor;

    public ExecutorCloser(ExecutorService executor) {
        this.executor = executor;
    }

    public ExecutorService getExecutor() {
        return executor;
    }

    @Override
    public void close() {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(3, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}

