package com.example.treinamento.model;

import java.util.List;

import com.example.treinamento.annotation.AtributoClasseDTO;
import com.example.treinamento.annotation.ClasseModeloDTO;

@ClasseModeloDTO
public class Carro {

	@AtributoClasseDTO
	private String marca;

	private String modelo;

	private String cor;

	@AtributoClasseDTO
	private List<String> cores;
}
