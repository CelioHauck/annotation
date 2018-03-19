package com.example.treinamento.reflection;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import com.thoughtworks.qdox.JavaDocBuilder;
import com.thoughtworks.qdox.model.JavaSource;


public class Arquivo {
	
	private static final String PACKAGE_PATH = "\\src\\main\\java\\com\\example\\treinamento\\dto\\%s.java";
	private static final String CAMINHO_PROJETO = System.getProperty("user.dir");

	static void criarArquivo(String dados, String nomeClasse) throws FileNotFoundException, UnsupportedEncodingException {
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
