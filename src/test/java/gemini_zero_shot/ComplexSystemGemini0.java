import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Testes unitários para a classe ComplexSystem.
 * Esta classe utiliza JUnit 5 e Mockito para testar o comportamento,
 * interações e casos de exceção da classe ComplexSystem.
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Testes para ComplexSystem")
class ComplexSystemGemini0Test {

    private ComplexSystem system;

    // Mocks para os módulos dependentes. Isso nos permite isolar o teste na classe ComplexSystem.
    @Mock
    private ComplexSystem.PaymentModule paymentModuleMock;

    @Mock
    private ComplexSystem.InventoryModule inventoryModuleMock;

    // Streams para capturar a saída do console
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        // Redireciona a saída padrão do sistema para nosso stream antes de cada teste
        System.setOut(new PrintStream(outContent));
        // Cria uma instância padrão para a maioria dos testes
        system = new ComplexSystem("TestSystem", 3);
    }

    @AfterEach
    void tearDown() {
        // Restaura a saída padrão do sistema após cada teste
        System.setOut(originalOut);
    }

    //region Testes do Construtor
    @Test
    @DisplayName("Deve criar o sistema com sucesso com parâmetros válidos")
    void constructor_whenValidParams_thenSucceeds() {
        assertEquals(ComplexSystem.SystemStatus.INITIALIZING, system.getStatus());
        // O nome e maxModules são privados, então testamos seu comportamento indiretamente
        // em outros testes (runDiagnostics e addModule).
    }

    @Test
    @DisplayName("Deve lançar NullPointerException se o nome for nulo")
    void constructor_whenNameIsNull_thenThrowsNPE() {
        assertThrows(NullPointerException.class, () -> new ComplexSystem(null, 5));
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException se maxModules for zero")
    void constructor_whenMaxModulesIsZero_thenThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new ComplexSystem("Test", 0), "maxModules must be > 0");
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException se maxModules for negativo")
    void constructor_whenMaxModulesIsNegative_thenThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new ComplexSystem("Test", -1), "maxModules must be > 0");
    }
    //endregion

    //region Testes do método addModule
    @Test
    @DisplayName("Deve adicionar um módulo quando dentro do limite")
    void addModule_whenWithinLimit_thenSucceeds() {
        system.addModule(paymentModuleMock);
        Optional<ComplexSystem.Module> found = system.findModule(ComplexSystem.PaymentModule.class);
        assertTrue(found.isPresent(), "O módulo deveria ter sido encontrado.");
        assertEquals(paymentModuleMock, found.get(), "O módulo encontrado deve ser a instância mockada.");
    }

    @Test
    @DisplayName("Deve lançar ModuleLimitException ao exceder o limite de módulos")
    void addModule_whenLimitIsReached_thenThrowsModuleLimitException() {
        ComplexSystem smallSystem = new ComplexSystem("SmallSystem", 1);
        smallSystem.addModule(paymentModuleMock); // Limite atingido

        // Tenta adicionar mais um
        Exception exception = assertThrows(ComplexSystem.ModuleLimitException.class, () -> {
            smallSystem.addModule(inventoryModuleMock);
        });

        assertEquals("Max modules reached: 1", exception.getMessage());
    }
    //endregion

    //region Testes do método findModule
    @Test
    @DisplayName("Deve encontrar um módulo se ele existir")
    void findModule_whenModuleExists_thenReturnsIt() {
        system.addModule(inventoryModuleMock);
        Optional<ComplexSystem.Module> found = system.findModule(ComplexSystem.InventoryModule.class);
        assertTrue(found.isPresent());
        assertEquals(inventoryModuleMock, found.get());
    }

    @Test
    @DisplayName("Deve retornar Optional.empty se o módulo não existir")
    void findModule_whenModuleDoesNotExist_thenReturnsEmpty() {
        // Nenhum InventoryModule foi adicionado
        Optional<ComplexSystem.Module> found = system.findModule(ComplexSystem.InventoryModule.class);
        assertFalse(found.isPresent());
    }
    //endregion

    //region Testes do método shutdown
    @Test
    @DisplayName("Deve alterar o status para STOPPED e parar todos os módulos")
    void shutdown_whenCalled_thenChangesStatusAndStopsModules() {
        system.addModule(paymentModuleMock);
        system.addModule(inventoryModuleMock);

        system.shutdown();

        assertEquals(ComplexSystem.SystemStatus.STOPPED, system.getStatus());
        // Verifica se o método stop() foi chamado exatamente uma vez em cada módulo
        verify(paymentModuleMock, times(1)).stop();
        verify(inventoryModuleMock, times(1)).stop();
    }
    //endregion

    //region Testes do método processEvent
    @Test
    @DisplayName("Deve mudar o status para RUNNING no evento START")
    void processEvent_whenStartEvent_thenStatusIsRunning() {
        system.processEvent("START");
        assertEquals(ComplexSystem.SystemStatus.RUNNING, system.getStatus());
    }
    
    @Test
    @DisplayName("Deve tratar evento START com case insensitivity")
    void processEvent_whenStartEventIsLowercase_thenStatusIsRunning() {
        system.processEvent("start");
        assertEquals(ComplexSystem.SystemStatus.RUNNING, system.getStatus());
    }

    @Test
    @DisplayName("Deve chamar shutdown() no evento SHUTDOWN")
    void processEvent_whenShutdownEvent_thenSystemShutsDown() {
        system.addModule(paymentModuleMock);
        system.processEvent("SHUTDOWN");
        assertEquals(ComplexSystem.SystemStatus.STOPPED, system.getStatus());
        verify(paymentModuleMock, times(1)).stop();
    }

    @Test
    @DisplayName("Deve processar evento PAYMENT com parâmetros válidos")
    void processEvent_whenPaymentEventWithValidParams_thenModuleIsCalled() {
        system.addModule(paymentModuleMock);
        system.processEvent("PAYMENT", 150.75);
        // Verifica se o método do módulo foi chamado com o valor correto
        verify(paymentModuleMock, times(1)).processPayment(150.75);
    }
    
    @Test
    @DisplayName("Não deve fazer nada para PAYMENT se o módulo não existe")
    void processEvent_whenPaymentEventWithoutModule_thenNothingHappens() {
        assertDoesNotThrow(() -> system.processEvent("PAYMENT", 100.0));
        verify(paymentModuleMock, never()).processPayment(anyDouble());
    }

    @Test
    @DisplayName("Não deve chamar módulo PAYMENT com parâmetros inválidos")
    void processEvent_whenPaymentEventWithInvalidParams_thenModuleIsNotCalled() {
        system.addModule(paymentModuleMock);
        system.processEvent("PAYMENT", "not-a-double");
        verify(paymentModuleMock, never()).processPayment(anyDouble());
    }

    @Test
    @DisplayName("Deve processar evento INVENTORY com parâmetros válidos")
    void processEvent_whenInventoryEventWithValidParams_thenModuleIsCalled() {
        system.addModule(inventoryModuleMock);
        system.processEvent("INVENTORY", "ItemABC", 5);
        verify(inventoryModuleMock, times(1)).updateStock("ItemABC", 5);
    }
    
    @Test
    @DisplayName("Não deve chamar módulo INVENTORY com número incorreto de parâmetros")
    void processEvent_whenInventoryEventWithInvalidParamCount_thenModuleIsNotCalled() {
        system.addModule(inventoryModuleMock);
        system.processEvent("INVENTORY", "ItemABC");
        verify(inventoryModuleMock, never()).updateStock(anyString(), anyInt());
    }

    @Test
    @DisplayName("Deve imprimir 'Unknown event' para eventos desconhecidos")
    void processEvent_whenUnknownEvent_thenPrintsToConsole() {
        system.processEvent("SOME_RANDOM_EVENT");
        assertTrue(outContent.toString().contains("Unknown event: SOME_RANDOM_EVENT"));
    }
    //endregion
    
    //region Testes do método runDiagnostics
    @Test
    @DisplayName("Deve imprimir OK para módulos que passam no self-test")
    void runDiagnostics_whenAllModulesPass_thenPrintsOK() {
        // Configuração dos mocks
        when(paymentModuleMock.getName()).thenReturn("Payment");
        when(paymentModuleMock.selfTest()).thenReturn(true);
        when(inventoryModuleMock.getName()).thenReturn("Inventory");
        when(inventoryModuleMock.selfTest()).thenReturn(true);
        
        system.addModule(paymentModuleMock);
        system.addModule(inventoryModuleMock);

        system.runDiagnostics();

        String output = outContent.toString();
        assertTrue(output.contains("Running diagnostics for TestSystem")); // Testa o nome do sistema
        assertTrue(output.contains("Module Payment test: OK"));
        assertTrue(output.contains("Module Inventory test: OK"));
    }

    @Test
    @DisplayName("Deve imprimir FAIL para módulos que falham no self-test")
    void runDiagnostics_whenOneModuleFails_thenPrintsFAIL() {
        when(paymentModuleMock.getName()).thenReturn("Payment");
        when(paymentModuleMock.selfTest()).thenReturn(true);
        when(inventoryModuleMock.getName()).thenReturn("Inventory");
        when(inventoryModuleMock.selfTest()).thenReturn(false); // Módulo que falha
        
        system.addModule(paymentModuleMock);
        system.addModule(inventoryModuleMock);

        system.runDiagnostics();

        String output = outContent.toString();
        assertTrue(output.contains("Module Payment test: OK"));
        assertTrue(output.contains("Module Inventory test: FAIL"));
    }

    @Test
    @DisplayName("Deve imprimir Erro para módulos que lançam exceção no self-test")
    void runDiagnostics_whenOneModuleThrowsException_thenPrintsError() {
        when(paymentModuleMock.getName()).thenReturn("Payment");
        when(paymentModuleMock.selfTest()).thenReturn(true);
        when(inventoryModuleMock.getName()).thenReturn("Inventory");
        // Módulo que lança exceção
        when(inventoryModuleMock.selfTest()).thenThrow(new RuntimeException("Database connection failed"));
        
        system.addModule(paymentModuleMock);
        system.addModule(inventoryModuleMock);

        system.runDiagnostics();
        
        String output = outContent.toString();
        assertTrue(output.contains("Module Payment test: OK"));
        assertTrue(output.contains("Error testing module Inventory: Database connection failed"));
    }
    //endregion
}