package project.generator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import project.data.Node;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

public class BreadthFirstSearchGenerator implements Iterable<Node>
{
	private static final Logger LOG = LoggerFactory.getLogger(BreadthFirstSearchGenerator.class);

	private Queue<Node> queue = new LinkedBlockingDeque<Node>();
	private Collection<Node> visited = new HashSet<Node>();

	BreadthFirstSearchGenerator(Node n)
	{
		queue.add(n);
	}


	@Override
	public Iterator<Node> iterator()
	{
		return new Iterator<Node>()
		{
			@Override
			public boolean hasNext()
			{
				return !queue.isEmpty();
			}

			@Override
			public Node next()
			{
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
		};
	}
}
