package com.alura.tabelafipe.main;

import com.alura.tabelafipe.models.*;
import com.alura.tabelafipe.service.ConsumoApi;
import com.alura.tabelafipe.service.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    Scanner leitor = new Scanner(System.in);

    ConsumoApi consomeApi = new ConsumoApi();
    ConverteDados conversor = new ConverteDados();

    private final String ENDERECO = "https://parallelum.com.br/fipe/api/v1/";
    private String tipoVeiculo;
    private String marcaVeiculo;
    private String modelo;
    private Integer ano;
    private String link;
    //exemplo de link: https://parallelum.com.br/fipe/api/v1/carros/marcas/59/modelos/5940/anos/



    public void exibiMenu() {
        //exibi menu
        var menu = """
                **** OPÇÕES **** 
                Carros
                Motos
                Caminhões
                """;
        System.out.println(menu);
        tipoVeiculo = leitor.nextLine();

        //exibe marcas e busca na api
        var jsonMarcas = consomeApi.obterDados(ENDERECO + tipoVeiculo + "/" + "marcas/");
        List<DadosMarca> marcas = conversor.obterLista(jsonMarcas, DadosMarca.class);
        marcas.forEach(m -> System.out.println("Cod: " + m.codigo() + ", Marca:" + m.marca()));
        System.out.println("Digite o código da marca do veículo: ");
        marcaVeiculo = leitor.nextLine();


        //exibe modelos e busca na api
        var jsonModelos = consomeApi.obterDados(ENDERECO + tipoVeiculo + "/marcas/" + marcaVeiculo + "/modelos/");
        System.out.println(ENDERECO + tipoVeiculo + "/marcas/" + marcaVeiculo + "/modelos/");
        List<DadosModelo> modelos = conversor.obterDados(jsonModelos, ModelosCarros.class).listaDeModelos();

        //filtra modelos
        System.out.println("\nDigite um trecho do nome do carro a ser buscado");
        var nomeVeiculo = leitor.nextLine();

        List<DadosModelo> modelosFiltrados = modelos.stream()
                .filter(m -> m.descricao().toLowerCase().contains(nomeVeiculo.toLowerCase()))
                .collect(Collectors.toList());

        System.out.println("\nModelos filtrados");
        modelosFiltrados.forEach(m -> System.out.println("Cod: " + m.codigo() + ", Modelo:" + m.descricao()));

        //recebe modelo
        System.out.println("Digite o código do modelo do veículo: ");
        modelo = leitor.nextLine();

        //exibe modelos e busca na api
        System.out.println(ENDERECO + tipoVeiculo + "/marcas/" + marcaVeiculo + "/modelos/" + modelo + "/anos/");
        var jsonAnos = consomeApi.obterDados(ENDERECO + tipoVeiculo + "/marcas/" + marcaVeiculo + "/modelos/" + modelo + "/anos/");
        List<DadosAnos> anos = conversor.obterLista(jsonAnos, DadosAnos.class);
        anos.forEach(m -> System.out.println("Cod: " + m.codigo() + ", ano:" + m.nome()));

        //exibi modelos por ano
        System.out.println("Todos os veículos com os valores por ano: ");
        List<DadosVeiculo> veiculos = new ArrayList<>();

        for (int i = 0; i < anos.size(); i++) {
            String enderecoAno = ENDERECO + tipoVeiculo + "/marcas/" + marcaVeiculo + "/modelos/" + modelo + "/anos/" + anos.get(i).codigo();
            String jsonValores = consomeApi.obterDados(enderecoAno);
            DadosVeiculo veiculo = conversor.obterDados(jsonValores, DadosVeiculo.class);
            veiculos.add(veiculo);
            System.out.println(veiculo);
        }
    }
}
