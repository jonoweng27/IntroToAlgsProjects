package edu.yu.introtoalgs;

/** Specifies the interface for generating a sequence of transportation states
 * that moves the radioactives from src to dest per the requirements doc.
 *
 * @author Avraham Leff
 */

import java.util.*;

import edu.yu.introtoalgs.TransportationState.Location;

public class TransportRadioactives {

  public static class RecursiveFunction {
    ArrayDeque<TransportationStateImpl> queue;
    Set<TransportationStateImpl> checked;
    TransportationStateImpl start;
    TransportationStateImpl end;
    HashMap<TransportationStateImpl, TransportationStateImpl> parent;
    public RecursiveFunction(TransportationStateImpl start) {
      this.start=start;
      this.queue=new ArrayDeque<>();
      this.checked = new HashSet<>();
      this.parent = new HashMap<>();
    }
    public List<TransportationState> run() {
        repeat(this.start);
        List<TransportationState> list = new ArrayList<>();
        if (end==null) {
          return list;
        }
        TransportationState t = this.parent.get(end);
        if (t==null) {
            return list;
        } else {
            list.add(0, end);
            while (t!=null) {
                list.add(0, t);
                t = this.parent.get(t);
            }
            return list;
        }
    }
    private void repeat(TransportationStateImpl startHere) {
        if (startHere.cathiumAtSrc>startHere.mithiumAtSrc) {
          return;
        }
        //add to list
        queue.add(startHere);
        //marked in the checked set as true
        checked.add(startHere);
        if (startHere.getMithiumDest()==startHere.getTotalMithium()&&startHere.getCathiumDest()==startHere.getTotalCathium()) {
          this.end=startHere;
          return;
        }
        while (!queue.isEmpty()) {
            TransportationStateImpl state = queue.remove();
            if (state.getMithiumDest()==state.getTotalMithium()&&state.getCathiumDest()==state.getTotalCathium()) {
              this.end=state;
              return;
            }
            for (TransportationStateImpl t : state.getPossibleMovingPlaces()) {
                if ((!this.checked.contains(t))&&(t.mithiumAtSrc==0||t.mithiumAtSrc>=t.cathiumAtSrc)&&(t.getMithiumDest()==0||t.getMithiumDest()>=t.getCathiumDest())) {
                  queue.add(t);
                  checked.add(t);
                  this.parent.put(t, state);
                }
            }
        }
    }
  }
  /** Computes a sequence of "transport radioactives" movements between the src
   * and the dest such that all of the initial methium and initial cathium are
   * transported safely from the src to the dest.  Each movement must respect
   * the constraints specified in the requirements doc.
   *
   * @param initialMithium initial amount of mithium (in kg) at the src
   * @param initialCathium initial amount of cathium (in kg) at the src
   * @return List of "transport radioactives" movements between the src and the
   * dest (if such a sequence can be computed), or an empty List if no such
   * sequence can be computed under the specified constraints.
   */

  public static List<TransportationState> transportIt(final int initialMithium, final int initialCathium) {
    if (initialMithium<0||initialCathium<0) {
      throw new IllegalArgumentException();
  }
  RecursiveFunction r = new RecursiveFunction(new TransportationStateImpl(initialMithium, initialCathium, Location.SRC, initialMithium, initialCathium));
  return r.run();
  } // transportIt

} // TransportRadioactives
