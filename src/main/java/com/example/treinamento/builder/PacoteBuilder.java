package com.example.treinamento.builder;

public final class PacoteBuilder {
	
	private static final String DTO = "dto";

	private PacoteBuilder() {}
	
	public static String getPacote(Class<?> classe) {
		String[] partesPacote = classe.getPackage().getName().split("\\.");
		partesPacote[partesPacote.length - 1] = DTO;
		return montarPacote(partesPacote);
	}
	
	private static String montarPacote(String[] partesPacote) {
		StringBuilder stringBuilder = new StringBuilder();
		for(int i = 0; i < partesPacote.length; i++) {
			stringBuilder.append(partesPacote[i]);
			
			if(i < partesPacote.length - 1) {
				stringBuilder.append(".");
			}
		}
		return stringBuilder.toString();
	}
}
