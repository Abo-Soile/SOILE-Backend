package fi.abo.kogni.soile2.elang;

import java.util.ArrayList;
import java.util.HashMap;

import fi.abo.kogni.soile2.elang.Transition.Phase;
import fi.abo.kogni.soile2.elang.Transition.Rule;

public class TransitionTable {
    
    public TransitionTable() {
        this.rules = new HashMap<>();
    }
    
    public void addRule(String name, Rule rule) {
        ruleArray(name).add(rule);
    }
    
    public ArrayList<Rule> getRules(String name) {
        if (! rules.containsKey(name)) {
            return null;
        }
        return rules.get(name);
    }

    public void addPhase(String name) {
        if (! rules.containsKey(name)) {
            rules.put(name, new ArrayList<Rule>());
        }
    }
    
    public boolean noTransitionsFrom(String phaseName) {
        if (! rules.containsKey(phaseName)) {
            return true;
        }
        return rules.get(phaseName).isEmpty();
    }

    public Phase getStartPhase() {
        return startPhase;
    }
    
    public void setStartPhase(String name) {
        setStartPhase(new Phase(name));
    }
    
    public void setStartPhase(Phase phase) {
        this.startPhase = phase;
        addPhase(phase.getName());
    }
    
    public boolean isStartPhase(String name) {
        return startPhase.getName().compareTo(name) == 0;
    }
    
    public Phase getFinalPhase() {
        return finalPhase;
    }
    
    public void setFinalPhase(String name) {
        setFinalPhase(new Phase(name));
    }
    
    public void setFinalPhase(Phase phase) {
        this.finalPhase = phase;
        addPhase(phase.getName());
    }
    
    public boolean isFinalPhase(String name) {
        return finalPhase.getName().compareTo(name) == 0;
    }
    
    private ArrayList<Rule> ruleArray(String name) {
        if (! rules.containsKey(name)) {
            rules.put(name, new ArrayList<Rule>());
        }
        return rules.get(name);
    }
    
    private HashMap<String, ArrayList<Rule>> rules;
    private Phase startPhase;
    private Phase finalPhase;
}
