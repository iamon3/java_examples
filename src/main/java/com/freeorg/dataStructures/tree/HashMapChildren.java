package com.freeorg.dataStructures.tree;

import java.util.HashMap;
/**
 *
 */
public class HashMapChildren<D,C> implements Children<D,C> {

    private HashMap<D,Node>  children;

    @Override
    public Node<D,C> getChild(D data) {
        return children.get(data);
    }

    @Override
    public void setChild(D oldData, Node<D,C> node) {
        // First Node with
        //children.put(oldData, node);
    }

	@Override
	public void addChild(Node<D, C> nodeWithNewData) {
		// TODO Auto-generated method stub
		
	}
}
