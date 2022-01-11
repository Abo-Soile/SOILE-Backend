package fi.abo.kogni.soile2.elang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class PhaseData implements Iterable<PhaseDef> {
    
    public PhaseData() {
        super();
        this.phases = new ArrayList<>();
        this.name2phase = new HashMap<>();
    }
    
    public PhaseDef getDef(String name) {
        return name2phase.get(name);
    }
    
    public void addDef(String name, PhaseDef def) {
        phases.add(def);
        name2phase.put(name, def);
    }
    
    public Iterator<PhaseDef> iterator() {
        return phases.iterator();
    }
    
    private ArrayList<PhaseDef> phases;
    private HashMap<String, PhaseDef> name2phase;
}
