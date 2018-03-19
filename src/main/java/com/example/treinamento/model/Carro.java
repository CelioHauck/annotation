package com.example.treinamento.model;

import java.util.List;

import com.example.treinamento.annotation.Atributo;
import com.example.treinamento.annotation.Classe;

@Classe
public class Carro {

	@Atributo
	private String marca;

	@Atributo
	private String modelo;

	private String cor;

	private List<String> cores;
}
