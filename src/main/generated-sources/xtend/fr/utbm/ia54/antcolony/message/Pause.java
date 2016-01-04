package fr.utbm.ia54.antcolony.message;

import io.sarl.lang.annotation.Generated;
import io.sarl.lang.core.Event;

/**
 * @author quentin barthelemy
 */
@SuppressWarnings("all")
public class Pause extends Event {
  public boolean pause;
  
  public Pause(final boolean p) {
    this.pause = p;
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
    Pause other = (Pause) obj;
    if (other.pause != this.pause)
      return false;
    return true;
  }
  
  @Override
  @Generated
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + (this.pause ? 1231 : 1237);
    return result;
  }
  
  /**
   * Returns a String representation of the Pause event's attributes only.
   */
  @Generated
  protected String attributesToString() {
    StringBuilder result = new StringBuilder(super.attributesToString());
    result.append("pause  = ").append(this.pause);
    return result.toString();
  }
  
  @Generated
  private final static long serialVersionUID = 63482716L;
}
