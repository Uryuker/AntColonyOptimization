package fr.utbm.ia54.antcolony.message;

import io.sarl.lang.annotation.Generated;
import io.sarl.lang.core.Event;

/**
 * @author quentin barthelemy
 */
@SuppressWarnings("all")
public class Play extends Event {
  public boolean play;
  
  public Play(final boolean pl) {
    this.play = pl;
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
    Play other = (Play) obj;
    if (other.play != this.play)
      return false;
    return true;
  }
  
  @Override
  @Generated
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + (this.play ? 1231 : 1237);
    return result;
  }
  
  /**
   * Returns a String representation of the Play event's attributes only.
   */
  @Generated
  protected String attributesToString() {
    StringBuilder result = new StringBuilder(super.attributesToString());
    result.append("play  = ").append(this.play);
    return result.toString();
  }
  
  @Generated
  private final static long serialVersionUID = -113911272L;
}
