import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;
/**
 * Filename: GraphTest.java Project: p4 Authors: Adeel Iqbal
 * 
 * test of Directed and unweighted graph implementation
 */
public class GraphTest {

  Graph graph = new Graph();
  @Test
  void test00_add_null_vertex() {
    try {
      graph.addVertex(null);
    }catch(NullPointerException exc){
      fail();
    }
  }
  
  @Test
  void test01_add_one_vertex() {
    graph.addVertex("A");
    if(graph.order()!=1) {
      fail();
    }
  }
  
  @Test
  void test02_add_mult_vertex() {
    graph.addVertex("A");
    graph.addVertex("B");
    graph.addVertex("C");
    graph.addVertex("D");
    graph.addVertex("E");

    if(graph.order()!=5) {
      fail();
    }
  }
  
  @Test
  void test03_add_one_edge() {
    graph.addVertex("A");
    graph.addVertex("B");
    graph.addEdge("A", "B");
    if(graph.size()!=1 && graph.order()!=2) {
      fail();
    }
  }
  
  @Test
  void test04_add_mult_edges() {
    graph.addVertex("A");
    graph.addVertex("B");
    graph.addVertex("C");
    graph.addVertex("D");
    
    graph.addEdge("A", "B");
    graph.addEdge("C", "D");

    if(graph.size()!=2 && graph.order()!=4) {
      fail();
    }
  }
  
  @Test
  void test05_add_edge_no_vertex() {
    
    graph.addEdge("A", "B");

    if(graph.size()!=1 && graph.order()!=2) {
      fail();
    }
  }
  
  @Test
  void test06_add_edge_remove_edge() {
    
    graph.addEdge("A", "B");
    graph.addEdge("C", "D");
    graph.removeEdge("C", "D");

    if(graph.size()!=1 && graph.order()!=4) {
      fail();
    }
  }  
  
  @Test
  void test07_remove_vertex_with_an_edge() {
    
    graph.addEdge("A", "B");
    graph.addEdge("C", "D");
    graph.removeVertex("C");;

    if(graph.size()!=1 && graph.order()!=3) {
      fail();
    }
  }
  

}
