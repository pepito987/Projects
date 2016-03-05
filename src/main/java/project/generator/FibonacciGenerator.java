package project.generator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class FibonacciGenerator
{
	private static final Logger LOG = LoggerFactory.getLogger(FibonacciGenerator.class);

	private Integer a = 1;
	private Integer b = 2;

	public static Iterable<Integer> fibonacci(int limit)
	{
		return () -> {
			return Stream.generate(new Supplier<Integer>()
			{
				int a = 1, b = 2;

				@Override
				public Integer get()
				{
					int temp = a;
					a = b;
					b = a + temp;
					return temp;
				}
			}).limit(limit).iterator();
		};
	}
}
