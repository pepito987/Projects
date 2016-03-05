package project.generator;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import project.data.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;
import static junit.framework.Assert.*;

public class BreadthFirstSearchGeneratorTest
{
	List<Node> nodes;

	@Test
	public void bfsShouldReturnTheRootNode() throws Exception
	{
		Node root = new Node().setValue("rootNode");

		Stream<Node> stream = StreamSupport.stream(new BreadthFirstSearchGenerator(root).spliterator(), false);

		final List<Node> list = stream.map(Function.identity())
				.collect(toList());
		assertEquals(1,list.size());
		assertEquals(root.getValue(),list.get(0).getValue());
	}

	@Test
	public void bfsShouldTraversInBFS()
	{
		/*
		root -> (edge,super2)
						|-> edge
		*/

		Node root = new Node().setValue("root");
		Node edge1 = new Node().setValue("sibling");
		Node super1= new Node().setValue("sibling");
		Node edge2 = new Node().setValue("sun");

		super1.setSuperNodes(Collections.singleton(edge2));
		root.setSuperNodes(Arrays.asList(edge1,super1));

		Stream<Node> stream = StreamSupport.stream(new BreadthFirstSearchGenerator(root).spliterator(), false);

		final List<Node> list = stream.map(Function.identity()).collect(toList());

		assertEquals("root",list.get(0).getValue());
		assertEquals("sibling",list.get(1).getValue());
		assertEquals("sibling",list.get(2).getValue());
		assertEquals("sun",list.get(3).getValue());

	}

	@Test
	public void bfsShouldFindASpecificNode()
	{
		Node root = new Node().setValue("root");
		Node edge1 = new Node().setValue("hello");
		Node super1= new Node().setValue("world");
		Node edge2 = new Node().setValue("!");

		super1.setSuperNodes(Collections.singleton(edge2));
		root.setSuperNodes(Arrays.asList(edge1,super1));

		Stream<Node> stream = StreamSupport.stream(new BreadthFirstSearchGenerator(root).spliterator(), false);
		final List<Node> list = stream.filter(n -> super1.getValue().equals(n.getValue()))
				.collect(toList());

		assertEquals(1,list.size());
		assertEquals(super1.getValue(),list.get(0).getValue());
	}

	@Test
	public void bfs()
	{
		Node root = new Node().setValue("root");

		final BreadthFirstSearchGenerator nodes = new BreadthFirstSearchGenerator(root);


	}




}