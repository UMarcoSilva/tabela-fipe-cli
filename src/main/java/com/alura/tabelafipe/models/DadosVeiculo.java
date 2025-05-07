package com.alura.tabelafipe.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosVeiculo(@JsonAlias("Valor") String valor,
                           @JsonAlias("Marca") String marca,
                           @JsonAlias("Modelo") String modelo,
                           @JsonAlias("AnoModelo") String ano,
                           @JsonAlias("Combustivel") String combustivel) {
    @Override
    public String toString() {
        return "DadosVeiculo: " +
                "valor='" + valor +
                ", marca='" + marca +
                ", modelo='" + modelo +
                ", ano='" + ano +
                ", combustivel='" + combustivel;
    }
}
// Object
//TipoVeiculo: 1
//Valor: "R$ 94.920,00"
//Marca: "VW - VolksWagen"
//Modelo: "AMAROK High.CD 2.0 16V TDI 4x4 Dies. Aut"
//AnoModelo: 2014
//Combustivel: "Diesel"
//CodigoFipe: "005340-6"
//MesReferencia: "maio de 2025"
//SiglaCombustivel: "D"