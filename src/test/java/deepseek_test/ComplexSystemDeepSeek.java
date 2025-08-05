import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.Optional;

class ComplexSystemDeepSeekTest {

    private ComplexSystem system;
    private ComplexSystem.PaymentModule paymentModule;
    private ComplexSystem.InventoryModule inventoryModule;

    @BeforeEach
    void setUp() {
        system = new ComplexSystem("TestSystem", 3);
        paymentModule = mock(ComplexSystem.PaymentModule.class);
        inventoryModule = mock(ComplexSystem.InventoryModule.class);
    }

    // Testes para o construtor ComplexSystem(String name, int maxModules)
    @Test
    void constructor_ValidParameters_InitializesSystem() {
        assertEquals("TestSystem", system.getSystemName());
        assertEquals(3, system.getMaxModules());
        assertEquals(ComplexSystem.SystemStatus.INITIALIZING, system.getStatus());
        assertEquals("1.0.0", system.getSettings().get("version"));
    }

    @Test
    void constructor_ZeroMaxModules_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new ComplexSystem("Test", 0));
    }

    @Test
    void constructor_NullName_ThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ComplexSystem(null, 1));
    }

    // Testes para addModule(Module module)
    @Test
    void addModule_WithinLimit_AddsModuleSuccessfully() {
        system.addModule(paymentModule);
        assertEquals(1, system.getModules().size());
    }

    @Test
    void addModule_ExceedsLimit_ThrowsModuleLimitException() {
        system.addModule(paymentModule);
        system.addModule(inventoryModule);
        system.addModule(mock(ComplexSystem.Module.class));
        
        assertThrows(ComplexSystem.ModuleLimitException.class, 
            () -> system.addModule(mock(ComplexSystem.Module.class)));
    }

    // Testes para runDiagnostics()
    @Test
    void runDiagnostics_WithModules_RunsTestsOnAllModules() {
        when(paymentModule.getName()).thenReturn("PaymentModule");
        when(paymentModule.selfTest()).thenReturn(true);
        
        when(inventoryModule.getName()).thenReturn("InventoryModule");
        when(inventoryModule.selfTest()).thenReturn(false);
        
        system.addModule(paymentModule);
        system.addModule(inventoryModule);
        
        system.runDiagnostics();
        
        verify(paymentModule).selfTest();
        verify(inventoryModule).selfTest();
    }

    @Test
    void runDiagnostics_WithFailingModule_ContinuesExecution() {
        ComplexSystem.Module failingModule = mock(ComplexSystem.Module.class);
        when(failingModule.getName()).thenReturn("FailingModule");
        when(failingModule.selfTest()).thenThrow(new RuntimeException("Test failed"));
        
        system.addModule(failingModule);
        
        assertDoesNotThrow(() -> system.runDiagnostics());
    }

    // Testes para processEvent(String event)
    @Test
    void processEvent_START_ChangesStatusToRunning() {
        system.processEvent("START");
        assertEquals(ComplexSystem.SystemStatus.RUNNING, system.getStatus());
    }

    @Test
    void processEvent_SHUTDOWN_ChangesStatusToStopped() {
        system.processEvent("SHUTDOWN");
        assertEquals(ComplexSystem.SystemStatus.STOPPED, system.getStatus());
    }

    @Test
    void processEvent_UnknownEvent_PrintsMessage() {
        system.processEvent("UNKNOWN");
        // Verificação indireta - não há exceção lançada
        assertEquals(ComplexSystem.SystemStatus.INITIALIZING, system.getStatus());
    }

    // Testes para processEvent(String event, Object... params)
    @Test
    void processEventWithParams_PAYMENT_ValidAmount_ProcessesPayment() {
        system.addModule(paymentModule);
        system.processEvent("PAYMENT", 100.50);
        verify(paymentModule).processPayment(100.50);
    }

    @Test
    void processEventWithParams_PAYMENT_InvalidParams_NoAction() {
        system.addModule(paymentModule);
        system.processEvent("PAYMENT", "invalid");
        verify(paymentModule, never()).processPayment(anyDouble());
    }

    @Test
    void processEventWithParams_INVENTORY_ValidParams_UpdatesStock() {
        system.addModule(inventoryModule);
        system.processEvent("INVENTORY", "Item001", 3);
        verify(inventoryModule).updateStock("Item001", 3);
    }

    @Test
    void processEventWithParams_INVENTORY_InvalidParams_NoAction() {
        system.addModule(inventoryModule);
        system.processEvent("INVENTORY", "Item001", "invalid");
        verify(inventoryModule, never()).updateStock(anyString(), anyInt());
    }

    // Testes para shutdown()
    @Test
    void shutdown_ChangesStatusAndStopsModules() {
        system.addModule(paymentModule);
        system.addModule(inventoryModule);
        
        system.shutdown();
        
        assertEquals(ComplexSystem.SystemStatus.STOPPED, system.getStatus());
        verify(paymentModule).stop();
        verify(inventoryModule).stop();
    }

    // Testes para findModule(Class<? extends Module> type)
    @Test
    void findModule_ExistingType_ReturnsModule() {
        system.addModule(paymentModule);
        Optional<ComplexSystem.Module> result = system.findModule(ComplexSystem.PaymentModule.class);
        assertTrue(result.isPresent());
        assertEquals(paymentModule, result.get());
    }

    @Test
    void findModule_NonExistingType_ReturnsEmpty() {
        system.addModule(paymentModule);
        Optional<ComplexSystem.Module> result = system.findModule(ComplexSystem.InventoryModule.class);
        assertTrue(result.isEmpty());
    }

    // Testes para getStatus()
    @Test
    void getStatus_ReturnsCurrentStatus() {
        assertEquals(ComplexSystem.SystemStatus.INITIALIZING, system.getStatus());
        system.processEvent("START");
        assertEquals(ComplexSystem.SystemStatus.RUNNING, system.getStatus());
    }

    // Testes para PaymentModule
    @Test
    void PaymentModule_processPayment_ValidAmount_UpdatesTotal() {
        ComplexSystem.PaymentModule realPaymentModule = new ComplexSystem.PaymentModule();
        realPaymentModule.processPayment(100.50);
        assertEquals(100.50, realPaymentModule.getTotalProcessed(), 0.001);
    }

    @Test
    void PaymentModule_processPayment_InvalidAmount_ThrowsException() {
        ComplexSystem.PaymentModule realPaymentModule = new ComplexSystem.PaymentModule();
        assertThrows(IllegalArgumentException.class, () -> realPaymentModule.processPayment(-10));
    }

    @Test
    void PaymentModule_selfTest_ReturnsCorrectStatus() {
        ComplexSystem.PaymentModule realPaymentModule = new ComplexSystem.PaymentModule();
        assertTrue(realPaymentModule.selfTest());
    }

    // Testes para InventoryModule
    @Test
    void InventoryModule_updateStock_ValidItem_UpdatesStock() {
        ComplexSystem.InventoryModule realInventoryModule = new ComplexSystem.InventoryModule();
        realInventoryModule.updateStock("Item001", 5);
        assertEquals(15, realInventoryModule.getStock().get("Item001"));
    }

    @Test
    void InventoryModule_updateStock_NewItem_AddsToStock() {
        ComplexSystem.InventoryModule realInventoryModule = new ComplexSystem.InventoryModule();
        realInventoryModule.updateStock("Item003", 2);
        assertEquals(2, realInventoryModule.getStock().get("Item003"));
    }

    @Test
    void InventoryModule_selfTest_ReturnsCorrectStatus() {
        ComplexSystem.InventoryModule realInventoryModule = new ComplexSystem.InventoryModule();
        assertTrue(realInventoryModule.selfTest());
    }
}