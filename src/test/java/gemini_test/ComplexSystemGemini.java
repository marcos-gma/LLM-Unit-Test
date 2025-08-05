import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Testes unitários para a classe ComplexSystem.
 * Utiliza JUnit 5 e Mockito para garantir isolamento e cobertura completa.
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("ComplexSystem Unit Tests")
class ComplexSystemGeminiTest {

    private ComplexSystem system;

    // Mocks para as dependências de Module
    @Mock
    private ComplexSystem.PaymentModule paymentModuleMock;

    @Mock
    private ComplexSystem.InventoryModule inventoryModuleMock;
    
    @Mock
    private ComplexSystem.Module genericModuleMock;


    @Nested
    @DisplayName("Constructor Tests")
    class ConstructorTests {

        @Test
        @DisplayName("constructor_ValidParameters_CreatesInstanceSuccessfully")
        void constructor_ValidParameters_CreatesInstanceSuccessfully() {
            // Input parameter(s)
            String name = "TestSystem";
            int maxModules = 5;

            // Action
            ComplexSystem newSystem = new ComplexSystem(name, maxModules);

            // Expected result
            assertNotNull(newSystem);
            assertEquals(ComplexSystem.SystemStatus.INITIALIZING, newSystem.getStatus());
        }

        @Test
        @DisplayName("constructor_NullName_ThrowsNullPointerException")
        void constructor_NullName_ThrowsNullPointerException() {
            // Input parameter(s)
            String name = null;
            int maxModules = 5;

            // Expected result
            assertThrows(NullPointerException.class, () -> new ComplexSystem(name, maxModules));
        }

        @Test
        @DisplayName("constructor_ZeroMaxModules_ThrowsIllegalArgumentException")
        void constructor_ZeroMaxModules_ThrowsIllegalArgumentException() {
            // Input parameter(s)
            String name = "TestSystem";
            int maxModules = 0;

            // Expected result
            assertThrows(IllegalArgumentException.class, () -> new ComplexSystem(name, maxModules));
        }
        
        @Test
        @DisplayName("constructor_NegativeMaxModules_ThrowsIllegalArgumentException")
        void constructor_NegativeMaxModules_ThrowsIllegalArgumentException() {
            // Input parameter(s)
            String name = "TestSystem";
            int maxModules = -1;

            // Expected result
            assertThrows(IllegalArgumentException.class, () -> new ComplexSystem(name, maxModules));
        }
    }
    
    @Nested
    @DisplayName("Module Management Tests")
    class ModuleManagementTests {
        
        @BeforeEach
        void setUp() {
            system = new ComplexSystem("ModuleSystem", 1);
        }

        @Test
        @DisplayName("addModule_UnderLimit_AddsModuleSuccessfully")
        void addModule_UnderLimit_AddsModuleSuccessfully() {
            // Input parameter(s)
            // (1) system instance with maxModules = 1
            // (2) genericModuleMock

            // Action
            system.addModule(genericModuleMock);

            // Expected result
            Optional<ComplexSystem.Module> foundModule = system.findModule(genericModuleMock.getClass());
            assertTrue(foundModule.isPresent(), "Módulo deveria ser encontrado após ser adicionado.");
            assertEquals(genericModuleMock, foundModule.get());
        }

        @Test
        @DisplayName("addModule_AtLimit_ThrowsModuleLimitException")
        void addModule_AtLimit_ThrowsModuleLimitException() {
            // Input parameter(s)
            // (1) system instance with maxModules = 1
            // (2) two modules: genericModuleMock and paymentModuleMock
            
            // Action
            system.addModule(genericModuleMock); // First module added successfully

            // Expected result
            // Attempting to add a second module should fail
            assertThrows(ComplexSystem.ModuleLimitException.class, () -> system.addModule(paymentModuleMock));
        }

        @Test
        @DisplayName("findModule_ModuleExists_ReturnsOptionalOfModule")
        void findModule_ModuleExists_ReturnsOptionalOfModule() {
            // Input parameter(s)
            system.addModule(paymentModuleMock);
            
            // Action
            Optional<ComplexSystem.Module> result = system.findModule(ComplexSystem.PaymentModule.class);

            // Expected result
            assertTrue(result.isPresent());
            assertEquals(paymentModuleMock, result.get());
        }

        @Test
        @DisplayName("findModule_ModuleDoesNotExist_ReturnsEmptyOptional")
        void findModule_ModuleDoesNotExist_ReturnsEmptyOptional() {
            // Input parameter(s)
            // system has no modules added

            // Action
            Optional<ComplexSystem.Module> result = system.findModule(ComplexSystem.InventoryModule.class);

            // Expected result
            assertTrue(result.isEmpty());
        }
    }

    @Nested
    @DisplayName("System Operations Tests")
    class SystemOperationsTests {

        @BeforeEach
        void setUp() {
            system = new ComplexSystem("OpsSystem", 3);
            system.addModule(paymentModuleMock);
            system.addModule(inventoryModuleMock);
        }

        @Test
        @DisplayName("runDiagnostics_AllModulesOk_CompletesWithoutError")
        void runDiagnostics_AllModulesOk_CompletesWithoutError() {
            // Mock condition(s)
            when(paymentModuleMock.selfTest()).thenReturn(true);
            when(inventoryModuleMock.selfTest()).thenReturn(true);

            // Action
            system.runDiagnostics();

            // Expected result (verify interactions)
            verify(paymentModuleMock, times(1)).selfTest();
            verify(inventoryModuleMock, times(1)).selfTest();
        }
        
        @Test
        @DisplayName("runDiagnostics_ModuleFailsTest_CompletesWithoutError")
        void runDiagnostics_ModuleFailsTest_CompletesWithoutError() {
            // Mock condition(s)
            when(paymentModuleMock.selfTest()).thenReturn(true);
            when(inventoryModuleMock.selfTest()).thenReturn(false); // This module fails

            // Action
            system.runDiagnostics();

            // Expected result (verify interactions)
            verify(paymentModuleMock, times(1)).selfTest();
            verify(inventoryModuleMock, times(1)).selfTest();
        }

        @Test
        @DisplayName("runDiagnostics_ModuleThrowsException_HandlesExceptionGracefully")
        void runDiagnostics_ModuleThrowsException_HandlesExceptionGracefully() {
            // Mock condition(s)
            when(paymentModuleMock.selfTest()).thenReturn(true);
            when(inventoryModuleMock.selfTest()).thenThrow(new RuntimeException("Critical Failure"));

            // Action
            system.runDiagnostics();

            // Expected result
            verify(paymentModuleMock, times(1)).selfTest();
            verify(inventoryModuleMock, times(1)).selfTest();
            // Test ensures no exception is propagated out of runDiagnostics
        }

        @Test
        @DisplayName("shutdown_WithModules_StopsAllModulesAndSetsStatus")
        void shutdown_WithModules_StopsAllModulesAndSetsStatus() {
            // Action
            system.shutdown();

            // Expected result
            verify(paymentModuleMock, times(1)).stop();
            verify(inventoryModuleMock, times(1)).stop();
            assertEquals(ComplexSystem.SystemStatus.STOPPED, system.getStatus());
        }
        
        @Test
        @DisplayName("getStatus_AfterInitialization_ReturnsInitializing")
        void getStatus_AfterInitialization_ReturnsInitializing() {
            // Input parameter(s)
            ComplexSystem freshSystem = new ComplexSystem("Fresh", 1);
            
            // Expected result
            assertEquals(ComplexSystem.SystemStatus.INITIALIZING, freshSystem.getStatus());
        }
    }

    @Nested
    @DisplayName("Event Processing Tests")
    class EventProcessingTests {
        
        @BeforeEach
        void setUp() {
            system = new ComplexSystem("EventSystem", 2);
            system.addModule(paymentModuleMock);
            system.addModule(inventoryModuleMock);
        }

        @Test
        @DisplayName("processEvent_StartEvent_StatusBecomesRunning")
        void processEvent_StartEvent_StatusBecomesRunning() {
            // Action
            system.processEvent("START");

            // Expected result
            assertEquals(ComplexSystem.SystemStatus.RUNNING, system.getStatus());
        }
        
        @Test
        @DisplayName("processEvent_ShutdownEvent_CallsShutdown")
        void processEvent_ShutdownEvent_CallsShutdown() {
            // Input parameter(s)
            // Spy allows verifying method calls on the object under test
            ComplexSystem systemSpy = spy(new ComplexSystem("SpySystem", 1));

            // Action
            systemSpy.processEvent("SHUTDOWN");

            // Expected result
            verify(systemSpy, times(1)).shutdown();
        }

        @Test
        @DisplayName("processEvent_PaymentEventWithValidParams_CallsProcessPayment")
        void processEvent_PaymentEventWithValidParams_CallsProcessPayment() {
            // Input parameter(s)
            double amount = 123.45;

            // Action
            system.processEvent("PAYMENT", amount);

            // Expected result
            verify(paymentModuleMock, times(1)).processPayment(amount);
            verify(inventoryModuleMock, never()).updateStock(anyString(), anyInt());
        }

        @Test
        @DisplayName("processEvent_PaymentEventWithInvalidParamType_DoesNotCallProcessPayment")
        void processEvent_PaymentEventWithInvalidParamType_DoesNotCallProcessPayment() {
            // Action
            system.processEvent("PAYMENT", "not-a-double");

            // Expected result
            verify(paymentModuleMock, never()).processPayment(anyDouble());
        }
        
        @Test
        @DisplayName("processEvent_InventoryEventWithValidParams_CallsUpdateStock")
        void processEvent_InventoryEventWithValidParams_CallsUpdateStock() {
            // Input parameter(s)
            String item = "ItemXYZ";
            int quantity = 10;
            
            // Action
            system.processEvent("INVENTORY", item, quantity);

            // Expected result
            verify(inventoryModuleMock, times(1)).updateStock(item, quantity);
            verify(paymentModuleMock, never()).processPayment(anyDouble());
        }

        @Test
        @DisplayName("processEvent_InventoryEventWithWrongParamCount_DoesNotCallUpdateStock")
        void processEvent_InventoryEventWithWrongParamCount_DoesNotCallUpdateStock() {
            // Action
            system.processEvent("INVENTORY", "ItemXYZ"); // Missing quantity

            // Expected result
            verify(inventoryModuleMock, never()).updateStock(anyString(), anyInt());
        }
        
        @Test
        @DisplayName("processEvent_UnknownEvent_DoesNothing")
        void processEvent_UnknownEvent_DoesNothing() {
            // Action
            system.processEvent("NON_EXISTENT_EVENT", 1, 2, 3);
            
            // Expected result
            assertEquals(ComplexSystem.SystemStatus.INITIALIZING, system.getStatus());
            verifyNoInteractions(paymentModuleMock, inventoryModuleMock);
        }
        
        @Test
        @DisplayName("processEvent_NoParamOverload_ForwardsCallCorrectly")
        void processEvent_NoParamOverload_ForwardsCallCorrectly() {
            // Action
            system.processEvent("START"); // Using the overload with no params

            // Expected result
            assertEquals(ComplexSystem.SystemStatus.RUNNING, system.getStatus());
        }
    }
}