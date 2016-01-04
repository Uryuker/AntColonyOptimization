package fr.utbm.ia54.antcolony.message;

import io.sarl.lang.annotation.Generated;
import io.sarl.lang.core.Event;

/**
 * @author quentin barthelemy
 */
@SuppressWarnings("all")
public class Stop extends Event {
  public boolean stop;
  
  public Stop(final boolean st) {
    this.stop = st;
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
    Stop other = (Stop) obj;
    if (other.stop != this.stop)
      return false;
    return true;
  }
  
  @Override
  @Generated
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + (this.stop ? 1231 : 1237);
    return result;
  }
  
  /**
   * Returns a String representation of the Stop event's attributes only.
   */
  @Generated
  protected String attributesToString() {
    StringBuilder result = new StringBuilder(super.attributesToString());
    result.append("stop  = ").append(this.stop);
    return result.toString();
  }
  
  @Generated
  private final static long serialVersionUID = -113716300L;
}
