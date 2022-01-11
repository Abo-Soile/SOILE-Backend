package fi.abo.kogni.soile2.elang;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class ProgramInstructionArray implements Iterable<ProgramInstruction> {
    
    public ProgramInstructionArray() {
        super();
        this.instructions = new ArrayList<>();
    }
    
    public void add(ProgramInstruction instr) {
        instructions.add(instr);
    }
    
    public ProgramInstruction[] toArray() {
        Collections.sort(instructions, new ProgramInstructionComparator());
        ProgramInstruction array[] = new ProgramInstruction[instructions.size()];
        return instructions.toArray(array);
    }

    @Override
    public Iterator<ProgramInstruction> iterator() {
        return instructions.iterator();
    }
    
    public int size() {
        return instructions.size();
    }

    private ArrayList<ProgramInstruction> instructions;
    
    private class ProgramInstructionComparator 
            implements Comparator<ProgramInstruction> {

        @Override
        public int compare(ProgramInstruction o1, ProgramInstruction o2) {
            int i1 = o1.getIndexValue();
            int i2 = o2.getIndexValue();
            return Integer.compare(i1, i2);
        }
        
    }
}
