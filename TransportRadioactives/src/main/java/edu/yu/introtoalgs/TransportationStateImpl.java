package edu.yu.introtoalgs;

/** Implements the TransportationState interface.  
 *
 *
 * Students may ONLY use the specified constructor, and may (perhaps even
 * encouraged to) add as many other methods as they choose.
 *
 * @author Avraham Leff
 */

import static edu.yu.introtoalgs.TransportationState.Location.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TransportationStateImpl implements TransportationState { 
   
   public static class Pair {
        public final int mithium;
        public final int cathium;
        public Pair (int mithium, int cathium) {
            this.mithium=mithium;
            this.cathium=cathium;
        }
        @Override
        public boolean equals (Object o) {
            if (o==null) {
                return false;
            }
            if (!(o instanceof Pair)) {
                return false;
            }
            Pair p = (Pair)o;
            if (this.mithium==p.mithium&&this.cathium==p.cathium) {
                return true;
            }
            return false;
        }
        @Override
        public int hashCode() {
            return Objects.hash(this.mithium, this.cathium);
        }
        @Override
        public String toString() {
            return "Mithium: " + this.mithium + ", Cathium: " + this.cathium;
        }
        public List<Pair> getPossiblePairs() {
            List<Pair> list = new ArrayList<>();
            list.add(new Pair(1,0));
            list.add(new Pair(1,1));
            list.add(new Pair(2,0));
            list.add(new Pair(0,1));
            list.add(new Pair(0,2));
            return list;
        }
   }
   
   int mithiumAtSrc;
   int cathiumAtSrc;
   Location truckLocation;
   final int totalMithium;
   final int totalCathium;
   
  /** Constructor:
   *
   * @param mithiumAtSrc amount of mithium at the src location, must be >= 0
   * @param cathiumAtSrc amount of cathium at the src location, must be >= 0
   * @param truckLocation location of the truck, must not be null
   * @param totalMithium sum of mithium amounts at src + dest, must be > 0
   * @param totalCathium sum of cathium amounts at src + dest, must be > 0
   *
   * @Students: you may NOT USE ANY OTHER CONSTRUCTOR SIG
   */
  public TransportationStateImpl(final int mithiumAtSrc, final int cathiumAtSrc, final Location truckLocation, final int totalMithium, final int totalCathium) {
      if (mithiumAtSrc<0||cathiumAtSrc<0||truckLocation==null||totalMithium<=0||totalCathium<=0) {
          throw new IllegalArgumentException();
      }
      this.mithiumAtSrc=mithiumAtSrc;
      this.cathiumAtSrc=cathiumAtSrc;
      this.truckLocation=truckLocation;
      this.totalMithium=totalMithium;
      this.totalCathium=totalCathium;
  } // constructor


  @Override
  public int getMithiumSrc() {
      return this.mithiumAtSrc;
  }

  @Override
  public int getCathiumSrc() { 
      return this.cathiumAtSrc;
  }
    
  @Override
  public int getMithiumDest() { 
      return this.totalMithium-this.mithiumAtSrc;
    }
    
  @Override
  public int getCathiumDest() { 
      return this.totalCathium-this.cathiumAtSrc;
  }
    
  @Override
  public Location truckLocation() { 
      return this.truckLocation;
   }

  @Override
  public int getTotalMithium() { 
      return this.totalMithium;
   }

  @Override
  public int getTotalCathium() { 
      return this.totalCathium;
   }
   @Override
   public boolean equals (Object o) {
       if (o==null) {
           return false;
       }
       if (!(o instanceof TransportationStateImpl)) {
           return false;
       }
       TransportationStateImpl t = (TransportationStateImpl)o;
       if (this.mithiumAtSrc!=t.mithiumAtSrc||this.cathiumAtSrc!=t.cathiumAtSrc||this.truckLocation!=t.truckLocation||this.totalMithium!=t.totalMithium||this.totalCathium!=t.totalCathium) {
           return false;
       }
       return true;
   }
   @Override
   public int hashCode() {
       return Objects.hash(this.mithiumAtSrc, this.cathiumAtSrc, this.truckLocation, this.totalMithium, this.totalCathium);
   }
   @Override
   public String toString() {
       return this.mithiumAtSrc + " kg of mithium and " + this.cathiumAtSrc + " kg of cathium at src.\n" + 
       this.getMithiumDest() +" kg of mithium and "+ this.getCathiumDest()+ " kg of cathium at dest.\n" +
       "The truck is parked at the " + this.truckLocation + "\n\n";
   }
   public List<TransportationStateImpl> getPossibleMovingPlaces() {
        Pair p;
        if (this.truckLocation==SRC) {
            p=new Pair(this.mithiumAtSrc, this.cathiumAtSrc);
        } else {
            p=new Pair(this.getMithiumDest(), this.getCathiumDest());
        }
        List<Pair> list = p.getPossiblePairs();
        List<TransportationStateImpl> result = new ArrayList<>();
        for (Pair x : list) {
            if (this.truckLocation==SRC) {
                if (this.mithiumAtSrc>=x.mithium&&this.cathiumAtSrc>=x.cathium) {
                    result.add(new TransportationStateImpl(mithiumAtSrc-x.mithium, cathiumAtSrc-x.cathium, DEST, this.totalMithium, this.totalCathium));
                }
            } else {
                if (this.getMithiumDest()>=x.mithium&&this.getCathiumDest()>=x.cathium) {
                    result.add(new TransportationStateImpl(mithiumAtSrc+x.mithium, cathiumAtSrc+x.cathium, SRC, this.totalMithium, this.totalCathium));
                }
            }
        }
        return result;
   }
}   // class