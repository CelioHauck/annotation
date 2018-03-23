package com.example.treinamento.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public final class ArquivoUtil {
	
	private static final String PACKAGE_PATH = "\\src\\main\\java\\com\\example\\treinamento\\dto\\%s.java";
	private static final String CAMINHO_PROJETO = System.getProperty("user.dir");
	
	private ArquivoUtil() {}

	public static void criarArquivo(String dados, String nomeClasse) throws FileNotFoundException, UnsupportedEncodingException {
		File sourceFile   = new File(getPath(nomeClasse));
		PrintWriter writer = new PrintWriter(sourceFile);
		writer.println(dados);
		writer.close();	
	}

	private static String getPath(String nomeClasse) {
		String path = String.format("%s%s", CAMINHO_PROJETO, PACKAGE_PATH);
		return String.format(path, nomeClasse +"DTO");
	}
}
