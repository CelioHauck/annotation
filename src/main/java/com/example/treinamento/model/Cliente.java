package com.example.treinamento.model;


import java.util.List;

import com.example.treinamento.annotation.AtributoClasseDTO;
import com.example.treinamento.annotation.ClasseModeloDTO;

//@ClasseModeloDTO
public class Cliente {

	@AtributoClasseDTO
	private String nome;

	@AtributoClasseDTO
	private String telefone;

	@AtributoClasseDTO(isTipoComplexo = true)
	private List<Carro> carros;
}
