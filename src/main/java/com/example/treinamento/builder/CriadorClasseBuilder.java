package com.example.treinamento.builder;

public class CriadorClasseBuilder implements ICriadorClasse {
	private static final String PUBLIC_CLASS = "public class %s { \r\n";
	
	@Override
	public ICriadorClasse iniciarClasseComFields(String original, String nomeClasse) {
		original  += String.format(PUBLIC_CLASS, nomeClasse);
		return this;
	}

}	
