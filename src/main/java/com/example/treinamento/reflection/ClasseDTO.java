package com.example.treinamento.reflection;

import static java.util.stream.Collectors.joining;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.map.HashedMap;
import org.reflections.Reflections;

import com.example.treinamento.annotation.Atributo;
import com.example.treinamento.annotation.Classe;
import com.example.treinamento.exception.Serialize;
import com.thoughtworks.qdox.JavaDocBuilder;
import com.thoughtworks.qdox.model.JavaSource;

public class ClasseDTO {
	private static final String PACKAGE_PATH = "\\src\\main\\java\\com\\example\\treinamento\\model\\%s.java";
	private static final String CAMINHO_PROJETO = System.getProperty("user.dir");
	
	private static String criarClasse(Map<String, String> atributos, String nomeClasse, String caminho) throws FileNotFoundException {
		String classe = "package " + caminho + ";" + "\n" + atribuirImportsDto(nomeClasse) + "\n" + "public class " + nomeClasse + "DTO" + " { \n"
				+ criarAtributos(atributos) + "\n" + criarConstrutorVazio(nomeClasse) + "\n"
				+ criarConstrutor(atributos, nomeClasse) + "\n" + criarGet(atributos) + "\n" + criarSet(atributos)
				+ "\n" + " }";
		return classe;
	}

	public static void atributos(Class<?> clazz) throws Serialize {
		try {
			Map<String, String> atributosAnotados = new HashedMap();
			String nomeClasse = clazz.getName().substring(30);
			String caminho = clazz.getPackage().getName().substring(0, 23) + ".dto";
			for (Field field : clazz.getDeclaredFields()) {
				field.setAccessible(true);
				if (field.isAnnotationPresent(Atributo.class)) {
					atributosAnotados.put(field.getName(),  field.getType().getSimpleName());
				}
			}
			String classe = criarClasse(atributosAnotados, nomeClasse, caminho);
			Arquivo.criarArquivo(classe, nomeClasse);
		} catch (Exception e) {
			throw new Serialize(e.getMessage());
		}

	}

	private static String criarGet(Map<String, String> atributos) {
		String get = atributos.entrySet().stream()
				.map(entry -> "public " + entry.getValue() + " get" + primeiraLetraMaiuscula(entry.getKey()) + "() { "
						+ "\n" + "return " + entry.getKey() + ";" + "\n" + "}" + "\n")
				.collect(joining());
		return get;
	}

	private static String criarSet(Map<String, String> atributos) {
		String set = atributos.entrySet().stream()
				.map(entry -> "public void" + " set" + primeiraLetraMaiuscula(entry.getKey()) + "(" + entry.getValue()
						+ " " + entry.getKey() + ") { " + "\n" + "this." + entry.getKey() + " = " + entry.getKey() + ";"
						+ "\n" + "}" + "\n")
				.collect(joining());
		return set;
	}

	private static String primeiraLetraMaiuscula(String palavra) {
		return palavra.substring(0, 1).toUpperCase() + palavra.substring(1);

	}

	private static String criarConstrutorVazio(String nomeClasse) {
		return "public " + nomeClasse + "DTO" + "() " + "{" + "\n" + "}";
	}

	private static String criarParametroConstrutor(Map<String, String> parametros) {
		return parametros.entrySet().stream().map(mapper -> " " + mapper.getValue() + " " + mapper.getKey())
				.collect(joining(","));
	}

	private static String atribuirVariaveis(Map<String, String> parametros) {
		return parametros.entrySet().stream()
				.map(entry -> "this." + entry.getKey() + " = " + entry.getKey() + ";" + "\n").collect(joining());
	}

	private static String criarConstrutor(Map<String, String> atributos, String nomeClasse) {
		String construtor = "public " + nomeClasse + "DTO" + "(" + criarParametroConstrutor(atributos) + ") {" + "\n"
				+ atribuirVariaveis(atributos) + "}" + "\n";
		return construtor;
	}

	private static String criarAtributos(Map<String, String> atributos) {
		return atributos.entrySet().stream().map(entry -> "private " + entry.getValue() + " " + entry.getKey() + "; \n")
				.collect(joining());
	}

	public static void percorrerClasses() throws Serialize {
		Reflections reflections = new Reflections("com.example");
		Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Classe.class);

		for (Class<?> controller : annotated) {
			atributos(controller);
		}
	}
	
	private static String getPath(String nomeClasse) {
		String path = String.format("%s%s", CAMINHO_PROJETO, PACKAGE_PATH);
		return String.format(path, nomeClasse);
	}
	
	public static Map<String, String> pegarImports(String nomeClasse) throws FileNotFoundException {
	    JavaDocBuilder builder = new JavaDocBuilder();
	    builder.addSource(new FileReader(getPath(nomeClasse)));
	    
		Map<String, String> importsModel = new HashedMap();
	    
	    JavaSource src = builder.getSources()[0];
	    String[] imports = src.getImports();

	    for ( String imp : imports )
	    {
	       importsModel.put(imp, "import");
	    }
	    return importsModel;
	}
	
	public static String atribuirImportsDto(String nomeClasse) throws FileNotFoundException {
		
		Map<String,String> importsModel = pegarImports(nomeClasse);
		
		String ImportsDTO = importsModel
							.entrySet()
							.stream()
							.map(var-> 
								var.getValue() + " " +  var.getKey() + ";"	+ "\n" 
								)
							.collect(joining());
		
		return ImportsDTO;
		
	}

}
