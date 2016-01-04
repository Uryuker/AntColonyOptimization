package fr.utbm.ia54.antcolony.gui;

import fr.utbm.ia54.antcolony.gui.Capacities;
import fr.utbm.ia54.antcolony.gui.XMLToGraph;
import io.sarl.lang.annotation.Generated;
import io.sarl.lang.core.Agent;
import io.sarl.lang.core.Skill;
import org.graphstream.graph.Graph;

@SuppressWarnings("all")
public class DefaultCapacitySkill extends Skill implements Capacities {
  public Graph colorGraph(final Graph graph) {
    XMLToGraph xml = null;
    return xml.colorizeEdge(graph);
  }
  
  /**
   * Construct a skill.
   * @param owner - agent that is owning this skill.
   */
  @Generated
  public DefaultCapacitySkill(final Agent owner) {
    super(owner);
  }
  
  /**
   * Construct a skill. The owning agent is unknown.
   */
  @Generated
  public DefaultCapacitySkill() {
    super();
  }
}
