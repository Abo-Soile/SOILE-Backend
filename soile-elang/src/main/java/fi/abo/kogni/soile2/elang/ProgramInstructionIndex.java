package fi.abo.kogni.soile2.elang;

public class ProgramInstructionIndex
        implements Comparable<ProgramInstructionIndex> {
    
    public ProgramInstructionIndex() {
        this(DEFERRED);
    }

    public ProgramInstructionIndex(int value) {
        super();
        this.indexValue = value;
    }

    public int getValue() {
        return indexValue;
    }

    @Override
    public String toString() {
        return String.format("ProgramInstructionIndex@[%d]", indexValue);
    }

    public void setValue(int value) {
        this.indexValue = value;
    }

    @Override
    public int compareTo(ProgramInstructionIndex o) {
        return Integer.compare(this.getValue(), o.getValue());
    }

    private int indexValue;
    
    private static final int DEFERRED = Integer.MIN_VALUE;

}