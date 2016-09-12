class Node<T1>
{
	Integer keyM;
	T1 itemM;
	Node<T1> nextM;

	public Node()
	{
		keyM = null;
		itemM =  null;
		nextM  = null;
	}
	public Node(T1 itemA, Integer keyA, Node<T1>  nextA)

	{
		itemM= itemA ;
		keyM = keyA;
		nextM = nextA;
	}

}
