package fr.utbm.ia54.antcolony.message;

import io.sarl.lang.annotation.Generated;
import io.sarl.lang.core.Event;
import org.graphstream.graph.Graph;

/**
 * @author quentin barthelemy
 */
@SuppressWarnings("all")
public class GraphChange extends Event {
  public Graph graph;
  
  public GraphChange(final Graph newGraph) {
    this.graph = newGraph;
  }
  
  @Override
  @Generated
  public boolean equals(final Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    if (!super.equals(obj))
      return false;
    GraphChange other = (GraphChange) obj;
    if (this.graph == null) {
      if (other.graph != null)
        return false;
    } else if (!this.graph.equals(other.graph))
      return false;
    return true;
  }
  
  @Override
  @Generated
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((this.graph== null) ? 0 : this.graph.hashCode());
    return result;
  }
  
  /**
   * Returns a String representation of the GraphChange event's attributes only.
   */
  @Generated
  protected String attributesToString() {
    StringBuilder result = new StringBuilder(super.attributesToString());
    result.append("graph  = ").append(this.graph);
    return result.toString();
  }
  
  @Generated
  private final static long serialVersionUID = -1458321572L;
}
