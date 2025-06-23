package com.ecoaware.tracker;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class TrackerApplicationTests {

	Calculator cal = new Calculator();

	@Test
	void contextLoads() {
		// given
		int numberOne = 10;
		int numberTwo = 20;

		// when
		int result = cal.addNumber(numberOne, numberTwo);

		// then
		int expected = 30;
		assertThat(result).isEqualTo(expected);
	}

	static class Calculator {

		public int addNumber(int a, int b){
			return a + b;
		}
	}

}
