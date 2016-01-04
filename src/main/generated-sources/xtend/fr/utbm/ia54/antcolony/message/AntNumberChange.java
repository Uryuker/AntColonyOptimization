package fr.utbm.ia54.antcolony.message;

import io.sarl.lang.annotation.Generated;
import io.sarl.lang.core.Event;

/**
 * @author quentin barthelemy
 */
@SuppressWarnings("all")
public class AntNumberChange extends Event {
  public int antNumber;
  
  public AntNumberChange(final int i) {
    this.antNumber = i;
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
    AntNumberChange other = (AntNumberChange) obj;
    if (other.antNumber != this.antNumber)
      return false;
    return true;
  }
  
  @Override
  @Generated
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + this.antNumber;
    return result;
  }
  
  /**
   * Returns a String representation of the AntNumberChange event's attributes only.
   */
  @Generated
  protected String attributesToString() {
    StringBuilder result = new StringBuilder(super.attributesToString());
    result.append("antNumber  = ").append(this.antNumber);
    return result.toString();
  }
  
  @Generated
  private final static long serialVersionUID = 1806936928L;
}
