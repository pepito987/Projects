package project.generator.graph;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import project.data.Node;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class GraphVisitGenerator
{
	private static final Logger LOG = LoggerFactory.getLogger(GraphVisitGenerator.class);

	public static Stream<Node> BFS(Node root)
	{
		Queue<Node> queue = new LinkedBlockingDeque<Node>();
		Collection<Node> visited = new HashSet<Node>();
		queue.add(root);
		LOG.info("Creating and initialization");

		return StreamSupport.stream(((Iterable<Node>) () -> new Iterator<Node>()

		{
			@Override
			public boolean hasNext()
			{
				return !queue.isEmpty();
			}

			@Override
			public Node next()
			{
				LOG.info("Calling next");
				Node node = queue.poll();
				while (!queue.isEmpty() && visited.contains(node))
				{
					node = queue.poll();
				}
				if (node != null)
				{
					visited.add(node);
					queue.addAll(node.getSuperNodes());
				}
				return node;
			}
		}).spliterator(), false);
	}

	public static Stream<Node> DFS(Node root)
	{
		Deque<Node> stack = new ArrayDeque<>();
		Collection<Node> visited = new HashSet<>();
		stack.push(root);

		return StreamSupport.stream(((Iterable<Node>) () -> new Iterator<Node>()
		{
			@Override
			public boolean hasNext()
			{
				return !stack.isEmpty();
			}

			@Override
			public Node next()
			{
				LOG.info("Evaluating next");
				Node node = stack.pop();
				while ( !stack.isEmpty() && visited.contains(node))
				{
					node = stack.pop();
				}
				if(node != null)
				{
					visited.add(node);
					node.getSuperNodes().stream()
							.forEach(stack::push);
				}
				return node;
			}
		}).spliterator(),false);
	}

}
