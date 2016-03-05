package project.generator;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import project.data.Node;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;
import static junit.framework.Assert.assertEquals;

public class BreadthFirstSearchGeneratorTest
{
	private static final Logger LOG = LoggerFactory.getLogger(BreadthFirstSearchGeneratorTest.class);


	@Test
	public void bfsShouldReturnTheRootNode() throws Exception
	{
		LOG.info("Executing [bfsShouldReturnTheRootNode]");

		Node root = new Node().setValue("rootNode");

		final List<Node> list = BreadthFirstSearchGenerator.bfs(root)
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

		final List<Node> list = BreadthFirstSearchGenerator.bfs(root)
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

		final List<Node> list = BreadthFirstSearchGenerator.bfs(root)
				.filter(n -> super1.getValue().equals(n.getValue()))
				.collect(toList());

		assertEquals(1, list.size());
		assertEquals(super1.getValue(), list.get(0).getValue());
	}

}