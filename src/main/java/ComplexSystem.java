import java.util.*;
import java.util.stream.Collectors;

/**
 * Sistema complexo para testes de cobertura.
 * Contém múltiplas estruturas e padrões para facilitar análise de testes.
 */
public class ComplexSystem {

    public static void main(String[] args) {
        ComplexSystem system = new ComplexSystem("DemoSystem", 5);
        system.addModule(new PaymentModule());
        system.addModule(new InventoryModule());

        system.runDiagnostics();
        system.processEvent("START");
        system.processEvent("PAYMENT", 100.50);
        system.processEvent("INVENTORY", "Item001", 3);
        system.shutdown();
    }

    private String systemName;
    private int maxModules;
    private final List<Module> modules = new ArrayList<>();
    private final Map<String, String> settings = new HashMap<>();

    // Getters para testes
    public String getSystemName() { return systemName; }
    public int getMaxModules() { return maxModules; }
    public List<Module> getModules() { return modules; }
    public Map<String, String> getSettings() { return settings; }
    private SystemStatus status = SystemStatus.INITIALIZING;

    public ComplexSystem(String name, int maxModules) {
        if (maxModules <= 0) throw new IllegalArgumentException("maxModules must be > 0");
        this.systemName = Objects.requireNonNull(name);
        this.maxModules = maxModules;
        settings.put("version", "1.0.0");
    }

    public void addModule(Module module) {
        if (modules.size() >= maxModules) {
            throw new ModuleLimitException("Max modules reached: " + maxModules);
        }
        modules.add(module);
    }

    public void runDiagnostics() {
        System.out.println("Running diagnostics for " + systemName);
        for (Module m : modules) {
            try {
                boolean ok = m.selfTest();
                System.out.println("Module " + m.getName() + " test: " + (ok ? "OK" : "FAIL"));
            } catch (Exception e) {
                System.out.println("Error testing module " + m.getName() + ": " + e.getMessage());
            }
        }
    }

    public void processEvent(String event) {
        processEvent(event, new Object[]{});
    }

    public void processEvent(String event, Object... params) {
        switch (event.toUpperCase()) {
            case "START":
                status = SystemStatus.RUNNING;
                break;
            case "SHUTDOWN":
                shutdown();
                break;
            case "PAYMENT":
                if (params.length > 0 && params[0] instanceof Double) {
                    double amount = (double) params[0];
                    findModule(PaymentModule.class).ifPresent(m -> ((PaymentModule) m).processPayment(amount));
                }
                break;
            case "INVENTORY":
                if (params.length == 2 && params[0] instanceof String && params[1] instanceof Integer) {
                    findModule(InventoryModule.class).ifPresent(m -> 
                        ((InventoryModule) m).updateStock((String) params[0], (Integer) params[1])
                    );
                }
                break;
            default:
                System.out.println("Unknown event: " + event);
        }
    }

    public void shutdown() {
        status = SystemStatus.SHUTTING_DOWN;
        modules.forEach(Module::stop);
        status = SystemStatus.STOPPED;
    }

    public Optional<Module> findModule(Class<? extends Module> type) {
        return modules.stream().filter(type::isInstance).findFirst();
    }

    public SystemStatus getStatus() {
        return status;
    }

    public enum SystemStatus {
        INITIALIZING, RUNNING, SHUTTING_DOWN, STOPPED
    }

    public interface Module {
        String getName();
        boolean selfTest();
        void stop();
    }

    public static abstract class AbstractModule implements Module {
        protected String moduleName;
        protected boolean active = true;

        public AbstractModule(String moduleName) {
            this.moduleName = moduleName;
        }

        @Override
        public String getName() {
            return moduleName;
        }

        @Override
        public void stop() {
            active = false;
        }
    }

    public static class PaymentModule extends AbstractModule {
        private double totalProcessed = 0.0;

        public double getTotalProcessed() { return totalProcessed; }

        public PaymentModule() {
            super("PaymentModule");
        }

        public void processPayment(double amount) {
            if (amount <= 0) throw new IllegalArgumentException("Amount must be > 0");
            totalProcessed += amount;
            System.out.println("Processed payment: " + amount);
        }

        @Override
        public boolean selfTest() {
            return totalProcessed >= 0;
        }
    }

    public static class InventoryModule extends AbstractModule {
        private final Map<String, Integer> stock = new HashMap<>();

        public Map<String, Integer> getStock() { return stock; }

        public InventoryModule() {
            super("InventoryModule");
            stock.put("Item001", 10);
            stock.put("Item002", 5);
        }

        public void updateStock(String item, int change) {
            stock.put(item, stock.getOrDefault(item, 0) + change);
            System.out.println("Stock updated for " + item + ": " + stock.get(item));
        }

        @Override
        public boolean selfTest() {
            return stock.values().stream().allMatch(q -> q >= 0);
        }
    }

    public static class ModuleLimitException extends RuntimeException {
        private static final long serialVersionUID = 1L;
        public ModuleLimitException(String message) {
            super(message);
        }
    }
}
