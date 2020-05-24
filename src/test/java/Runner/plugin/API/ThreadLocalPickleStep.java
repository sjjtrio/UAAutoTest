package Runner.plugin.API;

import cucumber.api.PickleStepTestStep;

public class ThreadLocalPickleStep {
    private static final ThreadLocal<PickleStepTestStep> threadStepDefMatch = new InheritableThreadLocal<>();

    private ThreadLocalPickleStep() {
    }

    public static PickleStepTestStep get() {
        return threadStepDefMatch.get();
    }

    public static void set(PickleStepTestStep match) {
        threadStepDefMatch.set(match);
    }

    public static void remove() {
        threadStepDefMatch.remove();
    }
}
