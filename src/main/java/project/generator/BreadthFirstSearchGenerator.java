package project.generator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import project.data.Node;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class BreadthFirstSearchGenerator
{
	private static final Logger LOG = LoggerFactory.getLogger(BreadthFirstSearchGenerator.class);
	
	public static Stream<Node> bfs(Node root)
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

}
