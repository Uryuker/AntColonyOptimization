package fr.utbm.ia54.antcolony.gui;

import io.sarl.lang.core.Capacity;
import org.graphstream.graph.Graph;

/**
 * @author quentin barthelemy
 */
@SuppressWarnings("all")
public interface Capacities extends Capacity {
  public abstract Graph colorGraph(final Graph graph);
}
