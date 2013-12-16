/******************************************************************************
 *
 * The Graph class models a graph data structure using an array of linked lists and
 *  a separate array of objects able to store labels for each vertex
 *
 * @see
 *   <A HREF="https://github.com/AugustBrenner">
 *       Checkout my GitHub</A>
 *
 * @author
 * August Brenner
 * G00682282
 *
 * @version
 *   December 18th, 2013
 ******************************************************************************/

// Import dependencies
import java.util.ArrayList;
import java.util.LinkedList;

public class Graph<E> implements Cloneable {
    // Invariant of Graph class:
    // vertexList must store labels for the edges
    // in this Graph class
    // Edges must store LinkedLists of vertexes for each vertex
    private E[] vertexList;
    private LinkedList<EdgeNode>[] edges;


    /**
     * Initializes a Graph with n vertices,
     * no edges and null labels
     * @param n
     * <dt><b>Precondition:</b></dt>
     *  n >= 0
     * <dt><b>Postcondition:</b></dt>
     *  This graph has n vertices, numbered from 0 to n-1.
     *  It has no edges, and all vertex labels are null.
     * @throws OutOfMemoryError
     *  Indicates insufficient memory to create this graph.
     * @throws NegativeArraySizeException
     *   Indicates that n is negative.
     */
    public Graph ( int n ) {
        if ( n >= 0 ) {
            edges = new LinkedList[n];
            vertexList = (E[]) new Object[n];
        } else {
            throw new NegativeArraySizeException("ArrayList size must be positive");
        }
    }


    /**
     * Adds an edge to the graph.
     * @param source
     * @param target
     * <dt><b>Precondition:</b></dt>
     *  Both source and target are non-negative and less than size().
     * <dt><b>Postcondition:</b></dt>
     *  The specified edge is added to this Graph(unless already present).
     * @throws ArrayIndexOutOfBoundsException
     *  Indicates that the source or target was not a valid vertex number.
     */
    public void addEdge(int source, int target){
        if(source >= 0 && source < size() && target >= 0 && target < size()){
            EdgeNode input = new EdgeNode(target);
            if(edges[source] == null){
                edges[source] = new LinkedList<EdgeNode>();
            }
            if(!edges[source].contains(input)){
                edges[source].add(input);
            }
        } else {
            throw new ArrayIndexOutOfBoundsException("Source or Target out of bounds.");
        }
    }

    /**
     * Removes an edge from the graph.
     * @param source
     * @param target
     * <dt><b>Precondition:</b></dt>
     *  Both source and target are non-negative and less than size().
     * <dt><b>Postcondition:</b></dt>
     *  The specified edge has been removed from this Graph.
     * @throws ArrayIndexOutOfBoundsException
     *  Indicates that the source or target was not a valid vertex number.
     */
    public void removeEdge(int source, int target){
        if(source >= 0 && source < size() && target >= 0 && target < size()){
            if(edges[source] != null && edges[target] != null){
                edges[source].remove(new EdgeNode(target));
            }
        } else {
            throw new ArrayIndexOutOfBoundsException("Source or Target out of bounds.");
        }
    }


    /**
     * Determines whether a specified edge is present.
     * @param source
     * @param target
     * @return
     * <dt><b>Precondition:</b></dt>
     *  Both source and target are non-negative and less than size().
     * <dt><b>Postcondition:</b></dt>
     *  The Graph is unchanged.
     * @throws ArrayIndexOutOfBoundsException
     *  Indicates that the source or target was not a valid vertex number.
     */
    public boolean isEdge(int source, int target){
        if(source >= 0 && source < size() && target >= 0 && target < size()){
            if(edges[source] != null && edges[target] != null){
                return edges[source].contains(new EdgeNode(target));
            }else{
                return false;
            }
        } else {
            throw new ArrayIndexOutOfBoundsException("Source or Target out of bounds.");
        }
    }


    /**
     * Change the label of a vertex of this Graph.
     * @param vertex
     * @param newLabel
     * <dt><b>Precondition:</b></dt>
     *  vertex is non-negative and less than size().
     * <dt><b>Postcondition:</b></dt>
     *  The label of the specified vertex in this Graph has been changed to newLabel.
     * @throws ArrayIndexOutOfBoundsException
     *  Indicates that the vertex was not a valid vertex number.
     */
    public void setLabel(int vertex, E newLabel){
        if(vertex >= 0 && vertex < size()){
            vertexList[vertex] = newLabel;
        } else {
            throw new ArrayIndexOutOfBoundsException("Source or Target out of bounds.");
        }
    }


    /**
     * Accessor method to get the label of a vertex of this Graph.
     * @param vertex
     * @return
     *  The label fo the specified vertex in this Graph.
     * <dt><b>Precondition:</b></dt>
     *  vertex is non-negative and less than size().
     * <dt><b>Postcondition:</b></dt>
     *  The Graph is unchanged.
     * @throws ArrayIndexOutOfBoundsException
     *  Indicates that the vertex was not a valid vertex number.
     */
    public E getLabel(int vertex){
        if(vertex >= 0 && vertex < size()){
            return vertexList[vertex];
        } else {
            throw new ArrayIndexOutOfBoundsException("Source or Target out of bounds.");
        }
    }


    /**
     * Accessor method to get all labels of this Graph.
     * @return
     *  An array of all labels of this graph.
     * <dt><b>Postcondition:</b></dt>
     *  The Graph is unchanged.
     */
    public Object[] getAllLabels(){
        return vertexList;
    }


    /**
     * Accessor method to obtain a list of neighbors of a specified vertex of this Graph.
     * @param vertex
     * @return
     *  The return value is an array that contains all the vertex numbers of vertices
     *  that are targets of edges with a source at the specified vertex.
     * @throws ArrayIndexOutOfBoundsException
     * Indicates that the vertex was not a valid vertex number.
     */
    public int[] neighbors(int vertex){
        if(vertex >= 0 && vertex < size()){
            LinkedList<EdgeNode> workingVertex = edges[vertex];
            int size = workingVertex.size();
            int answer[] = new int[size];
            for(int i = 0; i < size; i++){
                answer[i] = workingVertex.get(i).getVertexNum();
            }
            return answer;
        } else {
            throw new ArrayIndexOutOfBoundsException("Source or Target out of bounds.");
        }
    }


    /**
     * Generates a copy of this class
     * @return
     *  The Return value is a copy of this Graph. Subsequent changes to the copy
     *  will not affect the original, nor vice versa. The return value must be
     *  typecasted to a Graph before it is used.
     * @throws OutOfMemoryError
     *  Indicates insufficient memory for creating the clone.
     */
    public Graph<E> clone(){
        Graph<E> answer;
        try
        {
            answer = (Graph<E>) super.clone( );
        }
        catch (CloneNotSupportedException e)
        {
            throw new Error();
        }
        return answer;
    }


    /**
     * Accessor method to determine the number of vertices in this Graph
     * @return
     *  The number of vertices in this Graph.
     */
    public int size(){
        return edges.length;
    }


    /**
     * Static method to print the labels of a graph with a depth-first search.
     * @param source
     * @param target
     * @return
     *  true - if the target is found.
     *  false - if the target is not found.
     * <dt><b>Precondition:</b></dt>
     *  Both source and target are non-negative and less than size().
     * <dt><b>Postcondition:</b></dt>
     *  The Graph is unchanged.
     * @throws ArrayIndexOutOfBoundsException
     *  Indicates that the source or target was not a valid vertex number.
     */
    public boolean isPath(int source, int target){
        if(source >= 0 && source < size() && target >= 0 && target < size()){
            ArrayQueue vertexQueue = new ArrayQueue(size());
            vertexQueue.enqueue(source);
            Boolean[] finished = new Boolean[size()];
            boolean answer = false;

            // While loop to process vertices in search
            int workingVertex;
            int workingNeighbors[];
            boolean loop = true;
            while(loop && !vertexQueue.isEmpty()){
                workingVertex = (Integer)vertexQueue.dequeue();
                System.out.println(workingVertex);
                if(workingVertex == target){
                    loop = false;
                    answer = true;
                } else{
                    finished[workingVertex] = true;
                    workingNeighbors = neighbors(workingVertex);
                    for(int neighbor : workingNeighbors){
                        vertexQueue.enqueue(neighbor);
                    }
                }
            }
            return answer;
        } else {
            throw new ArrayIndexOutOfBoundsException("Source or Target out of bounds.");
        }
    }
}
