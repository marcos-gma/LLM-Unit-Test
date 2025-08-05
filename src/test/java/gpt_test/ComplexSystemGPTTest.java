import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ComplexSystemGPTTest {

    private ComplexSystem system;

    @BeforeEach
    void setUp() {
        system = new ComplexSystem("TestSystem", 2);
    }

    // ----------------------------
    // Testes para o Construtor
    // ----------------------------

    @Test
    void constructor_MaxModulesNegativo_DeveLancarExcecao() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new ComplexSystem("Invalid", -1);
        });
        assertEquals("maxModules must be > 0", exception.getMessage());
    }

    @Test
    void constructor_NomeNulo_DeveLancarNullPointer() {
        assertThrows(NullPointerException.class, () -> {
            new ComplexSystem(null, 2);
        });
    }

    // ----------------------------
    // Testes para addModule
    // ----------------------------

    @Test
    void addModule_AbaixoDoLimite_DeveAdicionarComSucesso() {
        ComplexSystem.Module mockModule = mock(ComplexSystem.Module.class);
        system.addModule(mockModule);
        assertEquals(ComplexSystem.SystemStatus.INITIALIZING, system.getStatus());
    }

    @Test
    void addModule_AtingeLimite_DeveLancarExcecao() {
        system.addModule(mock(ComplexSystem.Module.class));
        system.addModule(mock(ComplexSystem.Module.class));

        ComplexSystem.Module extraModule = mock(ComplexSystem.Module.class);

        assertThrows(ComplexSystem.ModuleLimitException.class, () -> system.addModule(extraModule));
    }

    // ----------------------------
    // Testes para runDiagnostics
    // ----------------------------

    @Test
    void runDiagnostics_TodosModulosOk_DeveImprimirOk() {
        ComplexSystem.Module mockModule = mock(ComplexSystem.Module.class);
        when(mockModule.selfTest()).thenReturn(true);
        when(mockModule.getName()).thenReturn("Mock");

        system.addModule(mockModule);

        system.runDiagnostics(); // Saída no console apenas
        verify(mockModule, times(1)).selfTest();
    }

    @Test
    void runDiagnostics_ModuloLancaExcecao_DeveTratarErro() {
        ComplexSystem.Module faultyModule = mock(ComplexSystem.Module.class);
        when(faultyModule.selfTest()).thenThrow(new RuntimeException("Erro de teste"));
        when(faultyModule.getName()).thenReturn("Faulty");

        system.addModule(faultyModule);

        assertDoesNotThrow(() -> system.runDiagnostics());
    }

    // ----------------------------
    // Testes para processEvent(String)
    // ----------------------------

    @Test
    void processEvent_EventoStart_DeveMudarStatusParaRunning() {
        system.processEvent("START");
        assertEquals(ComplexSystem.SystemStatus.RUNNING, system.getStatus());
    }

    @Test
    void processEvent_EventoDesconhecido_DeveImprimirMensagem() {
        assertDoesNotThrow(() -> system.processEvent("UNKNOWN_EVENT"));
        // Checar saída no console exigiria PrintStream mockado
    }

    // ----------------------------
    // Testes para processEvent(String, Object...)
    // ----------------------------

    @Test
    void processEvent_EventoPayment_ValorValido_DeveProcessar() {
        ComplexSystem.PaymentModule payment = spy(new ComplexSystem.PaymentModule());
        system.addModule(payment);

        system.processEvent("PAYMENT", 100.0);
        verify(payment, times(1)).processPayment(100.0);
    }

    @Test
    void processEvent_EventoPayment_ParamInvalido_NaoProcessa() {
        ComplexSystem.PaymentModule payment = spy(new ComplexSystem.PaymentModule());
        system.addModule(payment);

        system.processEvent("PAYMENT", "nao eh double");
        verify(payment, never()).processPayment(anyDouble());
    }

    @Test
    void processEvent_EventoInventory_ParametrosValidos_DeveAtualizarEstoque() {
        ComplexSystem.InventoryModule inventory = spy(new ComplexSystem.InventoryModule());
        system.addModule(inventory);

        system.processEvent("INVENTORY", "Item001", 5);
        verify(inventory, times(1)).updateStock("Item001", 5);
    }

    @Test
    void processEvent_EventoInventory_ParametrosInvalidos_NaoAtualiza() {
        ComplexSystem.InventoryModule inventory = spy(new ComplexSystem.InventoryModule());
        system.addModule(inventory);

        system.processEvent("INVENTORY", "Item001");
        verify(inventory, never()).updateStock(anyString(), anyInt());
    }

    // ----------------------------
    // Testes para shutdown
    // ----------------------------

    @Test
    void shutdown_DevePararModulosEMudarStatus() {
        ComplexSystem.Module module = mock(ComplexSystem.Module.class);
        system.addModule(module);

        system.shutdown();

        verify(module).stop();
        assertEquals(ComplexSystem.SystemStatus.STOPPED, system.getStatus());
    }

    // ----------------------------
    // Testes para findModule
    // ----------------------------

    @Test
    void findModule_ModuloExistente_DeveRetornarOptional() {
        ComplexSystem.Module payment = new ComplexSystem.PaymentModule();
        system.addModule(payment);

        Optional<ComplexSystem.Module> found = system.findModule(ComplexSystem.PaymentModule.class);
        assertTrue(found.isPresent());
    }

    @Test
    void findModule_ModuloInexistente_DeveRetornarEmpty() {
        Optional<ComplexSystem.Module> found = system.findModule(ComplexSystem.InventoryModule.class);
        assertTrue(found.isEmpty());
    }

    // ----------------------------
    // Testes para getStatus
    // ----------------------------

    @Test
    void getStatus_AposConstrucao_DeveSerInicializing() {
        assertEquals(ComplexSystem.SystemStatus.INITIALIZING, system.getStatus());
    }
}
