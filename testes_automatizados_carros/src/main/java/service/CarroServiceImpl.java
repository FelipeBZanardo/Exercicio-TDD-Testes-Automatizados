package service;

import model.Carro;

public class CarroServiceImpl implements CarroService {

    @Override
    public void acelerar(Carro carro, int velocidadeAMais) {
        if(carro.isLigado())
            carro.setVelocidadeAtual(
                    Math.min((carro.getVelocidadeAtual() + velocidadeAMais),
                            carro.getVelocidadeMaxima()));
    }

    @Override
    public void frear(Carro carro, int velocidadeAMenos) {
        if(carro.isLigado())
            carro.setVelocidadeAtual(
                    Math.max(carro.getVelocidadeAtual() - velocidadeAMenos, 0));

    }

    @Override
    public void ligar(Carro carro) {
        if(!carro.isLigado())
            carro.setLigado(true);
    }

    @Override
    public void desligar(Carro carro) {
        if(carro.isLigado() && carro.getVelocidadeAtual() == 0)
            carro.setLigado(false);
    }

    @Override
    public String estadoAtual(Carro carro) {
        return !carro.isLigado() ? "Desligado" :
                (carro.getVelocidadeAtual() == 0 ? "Parado" : "Em movimento");
    }
}
