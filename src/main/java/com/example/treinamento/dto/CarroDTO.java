package com.example.treinamento.dto;
import com.example.treinamento.annotation.Atributo;
import com.example.treinamento.annotation.Classe;
import java.util.List;

public class CarroDTO { 
private List cores; 
private String modelo; 
private String marca; 

public CarroDTO() {
}
public CarroDTO( List cores, String modelo, String marca) {
this.cores = cores;
this.modelo = modelo;
this.marca = marca;
}

public List getCores() { 
return cores;
}
public String getModelo() { 
return modelo;
}
public String getMarca() { 
return marca;
}

public void setCores(List cores) { 
this.cores = cores;
}
public void setModelo(String modelo) { 
this.modelo = modelo;
}
public void setMarca(String marca) { 
this.marca = marca;
}

 }
