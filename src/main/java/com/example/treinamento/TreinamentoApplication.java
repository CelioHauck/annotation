package com.example.treinamento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.treinamento.exception.Serialize;
import com.example.treinamento.reflection.CriadorClasse;

@SpringBootApplication
public class TreinamentoApplication {

	public static void main(String[] args) throws Serialize {
		try {
			CriadorClasse.criarDTOS();
			SpringApplication.run(TreinamentoApplication.class, args);
		} 
		catch (Exception e) {
			System.out.println("Erro " + e.getMessage());
		}
	}
}
