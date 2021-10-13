import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Filename: Graph.java Project: p4 Authors: Adeel Iqbal
 * 
 * Directed and unweighted graph implementation
 */

public class Graph implements GraphADT {
  private int numEdges;
  private int numVertices;
  private String[][] matrix;//adjacency matrix of edges
  private int matrixSize;
  private ArrayList<String> vertexList;
  private boolean resize;
  private String[][] resizedMatrix;//matrix of edges resized, if needed

  /*
   * Default no-argument constructor
   */
  public Graph() {
    numEdges = 0;
    numVertices = 0;
    matrixSize = 5;//default
    resize = false;
    matrix = new String[matrixSize][matrixSize];
    vertexList = new ArrayList<String>();   
  }

  /**
   * checks if the vertex is in graph
   * @param vertex - the vertex to check for
   * @return - true if in graph, false otherwise
   */
  private boolean hasVertex(String vertex) {
    return vertexList.contains(vertex);
  }

  /**
   * looks for the index of vertex
   * @param vertex - the vertex whose index we want
   * @return - index
   */
  private int vertexIndex(String vertex) {
    return vertexList.indexOf(vertex);
  }

  /**
   * Add new vertex to the graph.
   *
   * If vertex is null or already exists,
   * method ends without adding a vertex or 
   * throwing an exception.
   * 
   * Valid argument conditions:
   * 1. vertex is non-null
   * 2. vertex is not already in the graph 
   * 
   * @param vertex the vertex to be added
   * @Override
   */
  public void addVertex(String vertex) {
    if (vertex == null) {
      return;
    } // adding the vertex into graph if not already in graph
    if (!hasVertex(vertex)) {
      vertexList.add(vertex);
      numVertices++;//increment number of vertices
    }
  }

  /**
   * Remove a vertex and all associated 
   * edges from the graph.
   * 
   * If vertex is null or does not exist,
   * method ends without removing a vertex, edges, 
   * or throwing an exception.
   * 
   * Valid argument conditions:
   * 1. vertex is non-null
   * 2. vertex is not already in the graph 
   *  
   * @param vertex the vertex to be removed
   * @Override
   */
  public void removeVertex(String vertex) {
    if (vertex == null) {
      return;
    }
    vertexList.remove(vertex);//removes vertex
    numVertices--;//decrement number of vertices
  }

  /**
   * Add the edge from vertex1 to vertex2
   * to this graph.  (edge is directed and unweighted)
   * 
   * If either vertex does not exist,
   * VERTEX IS ADDED and then edge is created.
   * No exception is thrown.
   *
   * If the edge exists in the graph,
   * no edge is added and no exception is thrown.
   * 
   * Valid argument conditions:
   * 1. neither vertex is null
   * 2. both vertices are in the graph 
   * 3. the edge is not in the graph
   *  
   * @param vertex1 the first vertex (src)
   * @param vertex2 the second vertex (dst)
   * @Override
   */
  public void addEdge(String vertex1, String vertex2) {
    if (vertex1==null || vertex2==null) {//checks if either vertex is null
      return;
    }
    if(!hasVertex(vertex1)) {//adds vertex if not in graph
      addVertex(vertex1);
    }
    if (!hasVertex(vertex2)) {//adds vertex if not in graph
      addVertex(vertex2);
    }
    //gets vertex of each index
    int vertex1Index = vertexIndex(vertex1);
    int vertex2Index = vertexIndex(vertex2);
    //checks if resizing is needed
    if(vertex1Index >= matrixSize || vertex2Index >= matrixSize) {
      resizedMatrix = updateMatrixSize();//doubles array size
      resizedMatrix[vertex1Index][vertex2Index] = "Edge";//indicates edge at index
      numEdges++;//increment number of edges
      return;      
    }   
    matrix[vertex1Index][vertex2Index] = "Edge";//indicates edge in adjacency matrix
    numEdges++;//increment number of edges
  }
  
  /**
   * doubles the size of the matrix array
   * @return - new matrix with doubled size
   */
  private String[][] updateMatrixSize() {
    resize = true;//lets me know that the array was resized
    matrixSize = matrixSize * 2;//doubles array size
    String [][] newMatrix = new String [matrixSize][matrixSize];
    for (int i = 0; i < matrixSize/2; i++) {
      for (int j = 0; j < matrixSize/2; j++) {
        newMatrix[i][j] = matrix[i][j];//copying elements from old array to new
      }
    }
    return newMatrix;   
  }

  /**
   * Remove the edge from vertex1 to vertex2
   * from this graph.  (edge is directed and unweighted)
   * If either vertex does not exist,
   * or if an edge from vertex1 to vertex2 does not exist,
   * no edge is removed and no exception is thrown.
   * 
   * Valid argument conditions:
   * 1. neither vertex is null
   * 2. both vertices are in the graph 
   * 3. the edge from vertex1 to vertex2 is in the graph
   *  
   * @param vertex1 the first vertex
   * @param vertex2 the second vertex
   * @Override
   */
  public void removeEdge(String vertex1, String vertex2) {
     if (vertex1==null || vertex2==null || //check if vertex parameters are valid
     !(hasVertex(vertex1) && hasVertex(vertex2))) {
     return;
     }
     //get index of each vertex
     int vertex1Index = vertexIndex(vertex1);
     int vertex2Index = vertexIndex(vertex2);
     
     if(resize) {//if matrix was resized earlier
       resizedMatrix[vertex1Index][vertex2Index] = null;//set that element to null
       numEdges--;//decrement number of edges
     }
     else {//if matrix was not resized
       matrix[vertex1Index][vertex2Index] = null;//set that element to null
       numEdges--;//decrement number of edges
     }
  }

  /**
   * Returns a Set that contains all the vertices
   * 
   * @return a Set<String> which contains all the vertices in the graph
   * @Override
   */
  public Set<String> getAllVertices() {
    // TODO Auto-generated method stub
    Set<String> vertices = new HashSet<String>();
    try {
      for (int i = 0; i < vertexList.size(); i++) {//iterate through vertex list
        vertices.add(vertexList.get(i));//copy the vertex from vertex list to set
      }
    } catch (Exception exception) {
      System.out.println("");
    }
    return vertices;
  }

  /**
   * Get all the neighbor (adjacent-dependencies) of a vertex
   * 
   * For the example graph, A->[B, C], D->[A, B] 
   *     getAdjacentVerticesOf(A) should return [B, C]. 
   * 
   * In terms of packages, this list contains the immediate 
   * dependencies of A and depending on your graph structure, 
   * this could be either the predecessors or successors of A.
   * 
   * @param vertex the specified vertex
   * @return an List<String> of all the adjacent vertices for specified vertex
   * @Override
   */  
  public List<String> getAdjacentVerticesOf(String vertex) {
    List<String> adjacentVertices = new ArrayList<String>();
    int vertexIndex = vertexIndex(vertex);
    
    if(resize) {//if the matrix was resized
    //iterates through the column of the vertex to find which ones it has edge with
      for (int i = 0; i < matrixSize; i++) {
        if (resizedMatrix[vertexIndex][i]!=null) {
          adjacentVertices.add(vertexList.get(i));//add vertex to list
        }
      }
      return adjacentVertices;
    }//end resize condition
    else {
      for (int i = 0; i < matrixSize;i++) {//iterates through column of vertex
        if(matrix[vertexIndex][i]!=null) {//adds to list if the element is not null
          adjacentVertices.add(vertexList.get(i));
        }
      }
      return adjacentVertices;
    }
  }

  /**
   * Returns the number of edges in this graph.
   * @return number of edges in the graph.
   * @Override
   */
  public int size() {
    return numEdges;
  }

  /**
   * Returns the number of vertices in this graph.
   * @return number of vertices in graph.
   * @Override
   */
  public int order() {
    return numVertices;
  }
}
