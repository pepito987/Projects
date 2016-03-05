package project.generator;

import org.junit.Test;


public class FibonacciGeneratorTest
{

	@Test
	public void getShould() throws Exception
	{
		FibonacciGenerator.fibonacci(10).forEach(System.out::println);
	}
}