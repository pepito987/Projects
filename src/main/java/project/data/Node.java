package project.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;

public class Node
{
	private static final Logger LOG = LoggerFactory.getLogger(Node.class);

	private String value;
	private int intValue;

	Collection<Node> superNodes;
	Collection<Node> subNodes;

	public Node()
	{
		superNodes = new ArrayList<>();
		subNodes = new ArrayList<>();
	}

	public String getValue()
	{
		return value;
	}

	public Node setValue(final String value)
	{
		this.value = value;
		return this;
	}

	public int getIntValue()
	{
		return intValue;
	}

	public Node setIntValue(final int intValue)
	{
		this.intValue = intValue;
		return this;
	}

	public Collection<Node> getSuperNodes()
	{
		return superNodes;
	}

	public Node setSuperNodes(final Collection<Node> superNodes)
	{
		this.superNodes = superNodes;
		return this;
	}

	public Collection<Node> getSubNodes()
	{
		return subNodes;
	}

	public Node setSubNodes(final Collection<Node> subNodes)
	{
		this.subNodes = subNodes;
		return this;
	}
}
