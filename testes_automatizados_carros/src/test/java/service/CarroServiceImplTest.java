package service;

import model.Carro;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CarroServiceImplTest {

    private static CarroService carroService;
    private static Carro carro;

    @Before
    public void setUp() throws Exception {
        carroService = new CarroServiceImpl();
        carro = new Carro("Azul", "Fiat", "Uno", 2015, 150);
    }

    @Test
    public void deveSerCapazDeLigarQuandoEstiverDesligado() {
        assertFalse(carro.isLigado());
        carroService.ligar(carro);
        assertTrue(carro.isLigado());
    }

    @Test
    public void deveSerCapazDeMostrarEstadoAtualQuandoEstiverDesligado() {
        assertFalse(carro.isLigado());
        assertEquals("Desligado", carroService.estadoAtual(carro));
    }

    @Test
    public void deveSerCapazDeDesligarQuandoEstiverLigado() {
        carroService.ligar(carro);
        assertTrue(carro.isLigado());
        carroService.desligar(carro);
        assertFalse(carro.isLigado());
    }

    @Test
    public void naoDeveSerCapazDeDesligarQuandoEstiverEmMovimento() {
        carroService.ligar(carro);
        assertTrue(carro.isLigado());
        carroService.acelerar(carro, 10);
        assertEquals(10, carro.getVelocidadeAtual());
        carroService.desligar(carro);
        assertTrue(carro.isLigado());
    }

    @Test
    public void deveAcelerarCorretamente() {
        carroService.ligar(carro);

        // When
        carroService.acelerar(carro, 10);

        // Then
        // Assertivas
        // O Junit busca por Asserts no método para validar que o teste passou
        assertEquals(10, carro.getVelocidadeAtual());
    }

    @Test
    public void deveLigarCorretamenteEAcelerarCorretamente() {
        // Quando:
        carroService.ligar(carro);
        carroService.acelerar(carro, 10);

        // Então:
        assertTrue(carro.isLigado());
        assertEquals(10, carro.getVelocidadeAtual());
    }

    @Test
    public void naoDeveAcelerarQuandoEstiverDesligado() {
        assertFalse(carro.isLigado());
        carroService.acelerar(carro, 10);
        assertEquals(0, carro.getVelocidadeAtual());
    }

    @Test
    public void velocidadeNaoDeveSerMaiorQueAMaxima() {
        carroService.ligar(carro); // velocidade zero
        carroService.acelerar(carro,200);
        assertTrue(carro.isLigado());
        assertEquals(carro.getVelocidadeMaxima(), carro.getVelocidadeAtual());
    }

    @Test
    public void deveFrearCorretamente() {
        carroService.ligar(carro);
        carroService.acelerar(carro, 50);
        carroService.frear(carro, 30);
        assertEquals(20, carro.getVelocidadeAtual());
    }

    @Test
    public void velocidadeNaoDeveSerMenorQueZero() {
        // O Junit busca por Assets no método para validar que o teste passou

        // Dado:
        carroService.ligar(carro); // velocidade zero

        // Quando:
        carroService.frear(carro, 10);

        // Então:
        assertTrue(carro.isLigado());
        assertEquals(0, carro.getVelocidadeAtual());
    }

    @Test
    public void deveSerCapazDeMostrarEstadoAtualQuandoEstiverLigado() {
        carroService.ligar(carro); //velocidade 0, portanto 'Parado'
        assertTrue(carro.isLigado());
        assertEquals("Parado", carroService.estadoAtual(carro));

        carroService.acelerar(carro, 10); //Carro em movimento
        assertEquals("Em movimento", carroService.estadoAtual(carro));
    }



}
