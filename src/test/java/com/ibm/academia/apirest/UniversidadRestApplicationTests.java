package com.ibm.academia.apirest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


class UniversidadRestApplicationTests 
{
	Calculadora calculadora = new Calculadora();
	
	@Test
	@DisplayName("Test: Suma de valores")
	void sumarValores() 
	{
		//Given
		Integer valorA = 2;
		Integer valorB = 3;
		
		//When ---> Ejecutar la seccion, es decir, que quieres probar.
		Integer expected = calculadora.sumar(valorA, valorB);
		
		//Then ---> validar que lo que se esta probando funciona correctamente.
		Integer resultadoEsperado = 5;
		assertThat(expected).isEqualTo(resultadoEsperado);
	}
	
	class Calculadora
	{
		Integer sumar(Integer a, Integer b) 
		{
			return a + b;
		}
	}

}
