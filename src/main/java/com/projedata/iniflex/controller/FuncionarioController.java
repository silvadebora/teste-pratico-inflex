package com.projedata.iniflex.controller;

import com.projedata.iniflex.entity.Funcionario;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class FuncionarioController {

    private List<Funcionario> funcionarios = new ArrayList();
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    //3.1
    public void inserirFuncionarios() {
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), BigDecimal.valueOf(2009.44), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), BigDecimal.valueOf(2284.38), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), BigDecimal.valueOf(9836.14), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), BigDecimal.valueOf(19119.88), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), BigDecimal.valueOf(2234.68), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), BigDecimal.valueOf(1582.72), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), BigDecimal.valueOf(4071.84), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), BigDecimal.valueOf(3017.45), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), BigDecimal.valueOf(1606.85), "Eletricista"));
        funcionarios.add(new Funcionario("Helene", LocalDate.of(1996, 9, 2), BigDecimal.valueOf(2799.93), "Gerente"));
    }

    //3.2
    public void removerJoao() {
        funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));
    }

    //3.3
    public void imprimirFuncionarios() {
        System.out.println("Exercício 3.3");
        funcionarios.forEach(funcionario -> {
            System.out.println("Nome: " + funcionario.getNome());
            System.out.println("Data de nascimento: " + funcionario.getDataNascimento().format(dateFormatter));
            System.out.println("Salário: " + String.format("%,.2f", funcionario.getSalario()));
            System.out.println("Função: " + funcionario.getFuncao());
            System.out.println("-------------------------------------------------------");
        });
        System.out.println("-------------------------------------------------------");
    }

    //3.4
    public void aumentarSalario() {
        System.out.println("Exercício 3.4");
        double percentual = 10;
        funcionarios.forEach(funcionario -> {
            System.out.println("Salário anterior do funcionário " + funcionario.getNome() +
                    " era de " + String.format("%,.2f", funcionario.getSalario()));
            funcionario.setSalario(funcionario.getSalario()
                    .multiply(BigDecimal.valueOf(1 + percentual / 100)));
            System.out.println("Salário atual do funcionário " + funcionario.getNome() +
                    " é de " + String.format("%,.2f", funcionario.getSalario()));
        });
        System.out.println("---------------------------------------------------------");
    }

    //3.5 e 3.6
    public void agruparPorFuncao() {
        System.out.println("Exercício 3.5 e 3.6");
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream().collect(
                Collectors.groupingBy(Funcionario::getFuncao)
        );
        funcionariosPorFuncao.forEach((funcao, listaFuncionarios) -> {
            System.out.println("Função: " + funcao);
            listaFuncionarios.forEach(funcionario -> System.out.println(funcionario.getNome()));
        });
        System.out.println("---------------------------------------------------------");
    }

    //3.8
    public void imprimirPorAniversario(List<Integer> mes) {
        System.out.println("Exercício 3.8");
        //por ser mais de um mes, se adiciona eles em um set e fica mais facil comparar o metodo equals
        Set<Integer> meses = new HashSet<>(mes);
         new ArrayList<>();
        List<Funcionario> funcionarioPorMes = funcionarios.stream().filter(
                funcionario -> meses.contains(funcionario.getDataNascimento().getMonthValue())).toList();
        funcionarioPorMes.stream().forEach(aniversariante -> System.out.println(aniversariante));
        System.out.println("---------------------------------------------------------");
    }

    //3.9
    public void imprimirPorMaiorIdade(){
        System.out.println("Exercício 3.9");
        Funcionario maisVelho = funcionarios.stream()
                // o min pega o menor, logo o mais antigo
                .min(Comparator.comparing(Funcionario::getDataNascimento))
                .orElse(null); // usar orElse para não retornar um optional
        int idadeDoMaisVelho = LocalDate.now().getYear() - maisVelho.getDataNascimento().getYear();
        System.out.println("Atributos do funcionário mais velho");
        System.out.println("Nome: " + maisVelho.getNome());
        System.out.println("Idade: " + idadeDoMaisVelho);
        System.out.println("---------------------------------------------------------");

    }

    //3.10
    public void imprimirPorOrdemAlfabetica(){
        System.out.println("Exercício 3.10");
        //ordenação em list
        List<Funcionario> ordemAlfabetica = funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .collect(Collectors.toList());
        ordemAlfabetica.stream().forEach(funcionarioPorOrdemAlfabetica ->
                System.out.println(funcionarioPorOrdemAlfabetica));
        System.out.println("---------------------------------------------------------");
    }

    //3.11
    public void imprimirSalarioTotal(){
        System.out.println("Exercício 3.11");
        BigDecimal total = funcionarios.stream().map(Funcionario::getSalario).reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println(total);
        System.out.println("---------------------------------------------------------");
    }

    //3.12
    public void imprimirQuantidadeDeSalario(){
        System.out.println("Exercício 3.12");
        BigDecimal salarioMinimo = BigDecimal.valueOf(1212.00);
        funcionarios.stream().forEach(funcionario -> {
            BigDecimal quantidadeSalarioMinimo = funcionario.getSalario().divide(salarioMinimo, BigDecimal.ROUND_HALF_EVEN);
            System.out.println("Funcionário: " + funcionario.getNome() +
                    " recebe equivalente a " + quantidadeSalarioMinimo + " de salários mínimos.");
        });
    }


}
