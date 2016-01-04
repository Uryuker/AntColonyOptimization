package fr.utbm.ia54.antcolony.graphe;
import java.util.ArrayList;

/**
 * This class models a Node in a graph. For ease of 
 * the reader, a label for this Node is required. 
 * Note that the Graph object only accepts one Node per label,
 * so uniqueness of labels is important. This Node's neighborhood
 * is described by the Edges incident to it. 
 * 
 * @author Michael Levet
 * @date June 09, 2015
 */
public class Node {

    private ArrayList<Edge> neighborhood;
    private String label;
    
    /**
     * 
     * @param label The unique label associated with this Node
     */
    public Node(String label){
        this.label = label;
        this.neighborhood = new ArrayList<Edge>();
    }
    
    
    /**
     * This method adds an Edge to the incidence neighborhood of this graph iff
     * the edge is not already present. 
     * 
     * @param edge The edge to add
     */
    public void addNeighbor(Edge edge){
        if(this.neighborhood.contains(edge)){
            return;
        }
        
        this.neighborhood.add(edge);
    }
    
    
    /**
     * 
     * @param other The edge for which to search
     * @return true iff other is contained in this.neighborhood
     */
    public boolean containsNeighbor(Edge other){
        return this.neighborhood.contains(other);
    }
    
    /**
     * 
     * @param index The index of the Edge to retrieve
     * @return Edge The Edge at the specified index in this.neighborhood
     */
    public Edge getNeighbor(int index){
        return this.neighborhood.get(index);
    }
    
    
    /**
     * 
     * @param index The index of the edge to remove from this.neighborhood
     * @return Edge The removed Edge
     */
    Edge removeNeighbor(int index){
        return this.neighborhood.remove(index);
    }
    
    /**
     * 
     * @param e The Edge to remove from this.neighborhood
     */
    public void removeNeighbor(Edge e){
        this.neighborhood.remove(e);
    }
    
    
    /**
     * 
     * @return int The number of neighbors of this Node
     */
    public int getNeighborCount(){
        return this.neighborhood.size();
    }
    
    
    /**
     * 
     * @return String The label of this Node
     */
    public String getLabel(){
        return this.label;
    }
    
    
    /**
     * 
     * @return String A String representation of this Node
     */
    public String toString(){
        return "Node " + label;
    }
    
    /**
     * 
     * @return The hash code of this Node's label
     */
    public int hashCode(){
        return this.label.hashCode();
    }
    
    /**
     * 
     * @param other The object to compare
     * @return true iff other instanceof Node and the two Node objects have the same label
     */
    public boolean equals(Object other){
        if(!(other instanceof Node)){
            return false;
        }
        
        Node v = (Node)other;
        return this.label.equals(v.label);
    }
    
    /**
     * 
     * @return ArrayList<Edge> A copy of this.neighborhood. Modifying the returned
     * ArrayList will not affect the neighborhood of this Node
     */
    public ArrayList<Edge> getNeighbors(){
        return new ArrayList<Edge>(this.neighborhood);
    }


	Node() {
	  throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}


    
}


