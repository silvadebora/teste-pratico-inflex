package com.projedata.iniflex;

import com.projedata.iniflex.controller.FuncionarioController;
import org.springframework.boot.SpringApplication;

import java.util.Arrays;

public class IniflexApplication {

	public static void main(String[] args) {
		SpringApplication.run(IniflexApplication.class, args);

		FuncionarioController controller = new FuncionarioController();
		//3.1
		controller.inserirFuncionarios();
		//3.2
		controller.removerJoao();
		//3.3
		controller.imprimirFuncionarios();
		//3.4
		controller.aumentarSalario();
		//3.5 e 3.6
		controller.agruparPorFuncao();
		//3.8
		controller.imprimirPorAniversario(Arrays.asList(10,12));
		//3.9
		controller.imprimirPorMaiorIdade();
		//3.10
		controller.imprimirPorOrdemAlfabetica();
		//3.11
		controller.imprimirSalarioTotal();
		//3.12
		controller.imprimirQuantidadeDeSalario();
	}

}
