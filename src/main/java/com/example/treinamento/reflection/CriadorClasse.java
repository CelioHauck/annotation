package com.example.treinamento.reflection;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;

import com.example.treinamento.annotation.AtributoClasseDTO;
import com.example.treinamento.annotation.ClasseModeloDTO;
import com.example.treinamento.builder.EscritorClasseBuilder;
import com.example.treinamento.builder.PacoteBuilder;
import com.example.treinamento.exception.Serialize;
import com.example.treinamento.util.ArquivoUtil;
import com.example.treinamento.util.ReflectionUtils;
import com.google.common.reflect.Reflection;

public class CriadorClasse {
	
	public static void criarDTOS(String pacoteModel) throws Serialize {
		Set<Class<?>> classesAnotadas = new Reflections(pacoteModel).getTypesAnnotatedWith(ClasseModeloDTO.class);
		for (Class<?> classe : classesAnotadas) {
			criarClasseDTO(classe);
		}
	}

	private static void criarClasseDTO(Class<?> classe) throws Serialize {
		try {
			String nomeClasse = classe.getSimpleName();
			Map<String, String> atributosAnotados = obterCampos(classe);
			String classeCriada = criarClasse(atributosAnotados, nomeClasse, classe);
			ArquivoUtil.criarArquivo(classeCriada, nomeClasse);
		} 
		catch (Exception e) {
			throw new Serialize(e.getMessage());
		}
	}

	private static Map<String, String> obterCampos(Class<?> classe) {
		Map<String, String> atributosAnotados = new  HashMap<>();
		for (Field field : classe.getDeclaredFields()) {
			field.setAccessible(true);
			if (field.isAnnotationPresent(AtributoClasseDTO.class)) {
				AtributoClasseDTO anotacao = field.getAnnotation(AtributoClasseDTO.class);
				tratarCampo(atributosAnotados, field, anotacao);
			}
		}
		
		return atributosAnotados;
	}

	private static void tratarCampo(Map<String, String> atributosAnotados, Field field, AtributoClasseDTO anotacao) {
		
		if(isListaDTO(field, anotacao)) {
			atributosAnotados.put(field.getName(), ReflectionUtils.getNomeTipoListaDTO(field));
		}
		else if(isApenasLista(field, anotacao)) {
			atributosAnotados.put(field.getName(), ReflectionUtils.montarNomeTipoList(field));
		}
		else if (anotacao.isTipoComplexo()) {
			atributosAnotados.put(field.getName() + "DTO", field.getType().getSimpleName() + "DTO");
		} 
		else {
			atributosAnotados.put(field.getName(), field.getType().getSimpleName());
		}
	}

	private static boolean isListaDTO(Field field, AtributoClasseDTO anotacao) {
		return anotacao.isTipoComplexo() && field.getType().getSimpleName().equals("List");
	}

	private static boolean isApenasLista(Field field, AtributoClasseDTO anotacao) {
		return !anotacao.isTipoComplexo() && field.getType().getSimpleName().equals("List");
	}
	
	private static String criarClasse(Map<String, String> atributos, String nomeClasse, Class<?> classe) {
		String classeFinalizada = new EscritorClasseBuilder()
								   .adicionarPacote(PacoteBuilder.getPacote(classe))
								   .adicionarImports(nomeClasse)
								   .iniciarClasse(nomeClasse)
								   .criarFields(atributos)
								   .criarGets(atributos)
								   .criarSets(atributos)
								   .build();
		return classeFinalizada;
	}
}
