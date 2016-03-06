package project.generator.graph;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import project.data.Node;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;
import static junit.framework.Assert.assertEquals;

public class GraphStreamUtilsTest
{
	private static final Logger LOG = LoggerFactory.getLogger(GraphStreamUtilsTest.class);

	private GraphStreamUtils myGraph = new MyGraph();

	@Test
	public void bfsShouldReturnTheRootNode() throws Exception
	{
		LOG.info("Executing [bfsShouldReturnTheRootNode]");

		Node root = new Node().setValue("rootNode");

		final List<Node> list = myGraph.BFS(root)
				.map(Function.identity())
				.collect(toList());

		assertEquals(1, list.size());
		assertEquals(root.getValue(), list.get(0).getValue());
	}

	@Test
	public void bfsShouldTraversInBFS()
	{
		LOG.info("Executing [bfsShouldTraversInBFS]");
		/*
		root -> (edge,super2)
						|-> edge
		*/

		Node root = new Node().setValue("root");
		Node edge1 = new Node().setValue("sibling");
		Node super1 = new Node().setValue("sibling");
		Node edge2 = new Node().setValue("sun");

		super1.setSuperNodes(Collections.singleton(edge2));
		root.setSuperNodes(Arrays.asList(edge1, super1));

		final List<Node> list = myGraph.BFS(root)
				.map(Function.identity())
				.collect(toList());

		assertEquals("root", list.get(0).getValue());
		assertEquals("sibling", list.get(1).getValue());
		assertEquals("sibling", list.get(2).getValue());
		assertEquals("sun", list.get(3).getValue());

	}

	@Test
	public void bfsShouldFindASpecificNode()
	{
		LOG.info("Executing [bfsShouldFindASpecificNode]");
		Node root = new Node().setValue("root");
		Node edge1 = new Node().setValue("hello");
		Node super1 = new Node().setValue("world");
		Node edge2 = new Node().setValue("!");

		super1.setSuperNodes(Collections.singleton(edge2));
		root.setSuperNodes(Arrays.asList(edge1, super1));

		final List<Node> list = myGraph.BFS(root)
				.filter(n -> super1.getValue().equals(n.getValue()))
				.collect(toList());

		assertEquals(1, list.size());
		assertEquals(super1.getValue(), list.get(0).getValue());
	}

	@Test
	public void dfsShouldReturnTheRootElement()
	{
		LOG.info("Executing [dfsShouldReturnTheRootElement]");
		Node root = new Node().setValue("root");

		final List<Node> list = myGraph.DFS(root)
				.map(Function.identity())
				.collect(toList());

		assertEquals(1, list.size());
		assertEquals("root", list.get(0).getValue());
	}

	@Test
	public void dfsShouldTraverseInDFS()
	{
		LOG.info("Executing [dfsShouldTraverseInDFS]");


		Node root = new Node().setIntValue(1);
		Node edge1 = new Node().setIntValue(2);
		Node edge3 = new Node().setIntValue(3);
		Node super1 = new Node().setIntValue(4);
		Node edge2 = new Node().setIntValue(5);

		super1.setSuperNodes(Collections.singleton(edge2));
		List<Node> sorted = Arrays.asList(edge1, super1, edge3);
		sorted.sort((n1, n2) -> Integer.compare(n1.getIntValue(), n2.getIntValue()));
		root.setSuperNodes(sorted);

		Queue<Integer> expectedOrder = new ArrayBlockingQueue<Integer>(6);
		expectedOrder.add(1);
		expectedOrder.add(4);
		expectedOrder.add(5);
		expectedOrder.add(3);
		expectedOrder.add(2);

		myGraph.DFS(root)
				.forEach(n -> {
					assertEquals(expectedOrder.poll(), Integer.valueOf(n.getIntValue()));
				});

	}


}