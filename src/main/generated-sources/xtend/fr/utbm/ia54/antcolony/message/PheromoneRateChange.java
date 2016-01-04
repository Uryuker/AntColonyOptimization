package fr.utbm.ia54.antcolony.message;

import io.sarl.lang.annotation.Generated;
import io.sarl.lang.core.Event;

/**
 * @author quentin barthelemy
 */
@SuppressWarnings("all")
public class PheromoneRateChange extends Event {
  public int pheromoneRate;
  
  public PheromoneRateChange(final int i) {
    this.pheromoneRate = i;
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
    PheromoneRateChange other = (PheromoneRateChange) obj;
    if (other.pheromoneRate != this.pheromoneRate)
      return false;
    return true;
  }
  
  @Override
  @Generated
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + this.pheromoneRate;
    return result;
  }
  
  /**
   * Returns a String representation of the PheromoneRateChange event's attributes only.
   */
  @Generated
  protected String attributesToString() {
    StringBuilder result = new StringBuilder(super.attributesToString());
    result.append("pheromoneRate  = ").append(this.pheromoneRate);
    return result.toString();
  }
  
  @Generated
  private final static long serialVersionUID = 84829318L;
}
