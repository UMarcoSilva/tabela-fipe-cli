package com.alura.tabelafipe.models;

public class Veiculo {
    private String tipo;
    private String valor;
    private String marca;
    private String modelo;
    private String ano;
    private String combustivel;

    public Veiculo(String tipo, DadosVeiculo dadosVeiculo) {
        this.tipo = tipo;
        this.valor = dadosVeiculo.valor();
        this.marca = dadosVeiculo.marca();
        this.modelo = dadosVeiculo.modelo();
        this.ano = dadosVeiculo.ano();
        this.combustivel = dadosVeiculo.combustivel();
    }

    @Override
    public String toString() {
        return  "tipo='" + tipo +
                ", valor=" + valor +
                ", marca='" + marca +
                ", modelo='" + modelo +
                ", ano=" + ano +
                ", combustivel='" + combustivel;
    }
}
