package com.example.treinamento.model;

import com.example.treinamento.annotation.Atributo;
import com.example.treinamento.annotation.Classe;

@Classe
public class Cliente {

	@Atributo
	private String nome;

	@Atributo
	private String telefone;

	@Atributo
	private Carro carro;
}
