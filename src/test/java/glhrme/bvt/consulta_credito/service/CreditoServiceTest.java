package glhrme.bvt.consulta_credito.service;

import glhrme.bvt.consulta_credito.model.Credito;
import glhrme.bvt.consulta_credito.repository.CreditoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testes unitários para CreditoService")
class CreditoServiceTest {

    @Mock
    private CreditoRepository creditoRepository;

    @InjectMocks
    private CreditoService creditoService;

    @Test
    @DisplayName("Deve retornar lista de créditos quando existe NFSe válida")
    void deveRetornarListaQuandoNumeroNfseValida() {
        String numeroNfse = "1122334";

        List<Credito> creditosEsperados = new ArrayList<>();
        creditosEsperados.add(new Credito());

        when(creditoRepository.findByNumeroNfse(numeroNfse)).thenReturn(creditosEsperados);

        List<Credito> resultado = creditoService.findByNumeroNfse(numeroNfse);

        assertNotNull(resultado);
        assertEquals(creditosEsperados, resultado);
        verify(creditoRepository, times(1)).findByNumeroNfse(numeroNfse);
    }

    @Test
    @DisplayName("Deve retornar lista vazia quando NFSe válida não possui créditos")
    void deveRetornarListaVaziaQuandoNenhumCreditoEncontrado() {
        String numeroNfse = "231321321321312";

        when(creditoRepository.findByNumeroNfse(numeroNfse)).thenReturn(new ArrayList<>());

        List<Credito> resultado = creditoService.findByNumeroNfse(numeroNfse);

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
        verify(creditoRepository, times(1))
                .findByNumeroNfse(numeroNfse);
    }

    @Test
    @DisplayName("Deve lançar exceção quando número da NFSe for nulo, vazio ou apenas espaços")
    void deveLancarExcecaoQuandoNumeroNfseInvalido() {
        List<String> numerosNfseInvalidos = Arrays.asList(null, "", "   ");

        for (String numeroNfse : numerosNfseInvalidos) {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                                                              () -> creditoService.findByNumeroNfse(numeroNfse));
            assertEquals("Um número de nota fiscal eletrônica deve ser informado para consulta.", exception.getMessage());
        }

        verify(creditoRepository, never()).findByNumeroNfse(any());
    }
}