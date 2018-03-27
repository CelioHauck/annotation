package com.example.treinamento.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;

import com.thoughtworks.qdox.JavaDocBuilder;
import com.thoughtworks.qdox.model.JavaSource;

public final class ReflectionUtils {

	private static final String CAMINHO_PROJETO = System.getProperty("user.dir");
	
	private String pacoteBase;
	
	public ReflectionUtils(String pacoteBase) {
		this.pacoteBase = pacoteBase;
	}
	
	public List<String> pegarImports(String nomeClasse) throws FileNotFoundException {
		JavaDocBuilder builder = new JavaDocBuilder();
		builder.addSource(new FileReader(getPath(nomeClasse)));
		JavaSource src = builder.getSources()[0];
		return Arrays.asList(src.getImports());
	}

	private String getPath(String nomeClasse) {
		String path = String.format("%s%s", CAMINHO_PROJETO, pacoteBase);
		return String.format(path, nomeClasse);
	}
	
	public static String getNomeTipoListaDTO(Field listField) {
		String nomeClasseList = getSimpleNameTipoLista(listField);
        return montarNomeTipoListDTO(nomeClasseList);
	}
	
	private static String montarNomeTipoListDTO(String nomeClasseList) {
		return String.format("List<%sDTO>", nomeClasseList);
	}
	
	public static String montarNomeTipoList(Field fieldLista) {
		String nomeTipoClasse = getSimpleNameTipoLista(fieldLista);
		return String.format("List<%s>", nomeTipoClasse);
	}
	
	private static String getSimpleNameTipoLista(Field listField) {
		ParameterizedType stringListType = (ParameterizedType) listField.getGenericType();
        Class<?> nomeClasseList = (Class<?>) stringListType.getActualTypeArguments()[0];
		return nomeClasseList.getSimpleName();
	}
}
