package com.sena.hospital2;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Hospital2ApplicationTests {

	@Test
	void contextLoads() {
	}

	
	@Test
	public void PruebaEjemploTest(){
		int num1 = 8;
		int num2 = 5;
		int resultadoEjemplo = 13;
		assertEquals(resultadoEjemplo, num1+num2);
	}
}
