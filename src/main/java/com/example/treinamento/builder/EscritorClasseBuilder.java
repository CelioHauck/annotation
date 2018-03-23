package com.example.treinamento.builder;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import com.example.treinamento.util.ReflectionUtils;

public class EscritorClasseBuilder {
	
	private static final String PACOTE_BASE =  "\\src\\main\\java\\com\\example\\treinamento\\model\\%s.java";
	
	private static final String IMPORT = "import %s; \n";
	private static final String CAMPO_PRIVATE = "    private %s %s; \n";
	private static final String PACOTE = "package %s; \n";
	private static final String PUBLIC_CLASS = "public class %sDTO { \n";
	private static final String GET = "    public %s get%s() { \n        return this.%s; \n    } \n\n";
	private static final String SET = "    public void set%s(%s %s) { \n        this.%s = %s; \n    } \n\n";
	private String textoClasse = "";

	public EscritorClasseBuilder adicionarPacote(String pacote) {
		textoClasse  += String.format(PACOTE, pacote);
		pularLinha();
		return this;
	}
	
	public EscritorClasseBuilder adicionarImports(String nomeClasse) {
		
		try {
			List<String> importsModel = new ReflectionUtils(PACOTE_BASE).pegarImports(nomeClasse);
			importsModel.stream()
						.forEach(importModel -> textoClasse  += String.format(IMPORT, importModel));
			pularLinha();
		} 
		catch (FileNotFoundException e) {
			throw new RuntimeException(String.format("Pacote da classe %s não encontrado", nomeClasse));
		}
		
		return this;
	}

	
	public EscritorClasseBuilder iniciarClasse(String nomeClasse) {
		textoClasse  += String.format(PUBLIC_CLASS, nomeClasse);
		pularLinha();
		return this;
	}

	private void pularLinha() {
		textoClasse +="\n";
	}
	
	public EscritorClasseBuilder criarFields(Map<String, String> atributos) {		
			atributos.entrySet()
					 .stream()
				     .forEach(att -> textoClasse  += String.format(CAMPO_PRIVATE, att.getValue(), att.getKey()));
		pularLinha();
		return this;
	}
	
	
	public EscritorClasseBuilder criarGets(Map<String, String> atributos) {
		atributos.entrySet()
				 .stream()
				 .forEach(entry -> {
					 String nomeCampo = entry.getKey();
					 textoClasse += String.format(GET, entry.getValue(), primeiraLetraMaiuscula(nomeCampo), nomeCampo);
				 });
		
		return this;
	}
	
	public EscritorClasseBuilder criarSets(Map<String, String> atributos) {
		atributos.entrySet()
				 .stream()
				 .forEach(entry -> {
					 String nomeCampo = entry.getKey();
					 textoClasse += String.format(SET, primeiraLetraMaiuscula(nomeCampo),
							 					  entry.getValue(),
							 					  nomeCampo, nomeCampo, nomeCampo);
				 });
		return this;
	}
	

	private static String primeiraLetraMaiuscula(String palavra) {
		return palavra.substring(0, 1).toUpperCase() + palavra.substring(1);

	}
	
	public String build() {
		return textoClasse +="}";
	}
	
	
//	public ICriadorClasse adicionarPacote(String pacote) {
//		textoClasse  += String.format(PACOTE, pacote);
//		return new CriadorClasseBuilder();
//	}
//	
	
}
