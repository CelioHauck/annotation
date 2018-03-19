package com.example.treinamento.dto;
import com.example.treinamento.annotation.Atributo;
import com.example.treinamento.annotation.Classe;
import java.util.List;

public class CarroDTO { 
private String modelo; 
private String marca; 

public CarroDTO() {
}
public CarroDTO( String modelo, String marca) {
this.modelo = modelo;
this.marca = marca;
}

public String getModelo() { 
return modelo;
}
public String getMarca() { 
return marca;
}

public void setModelo(String modelo) { 
this.modelo = modelo;
}
public void setMarca(String marca) { 
this.marca = marca;
}

 }
