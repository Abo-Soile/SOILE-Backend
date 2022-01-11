package fi.abo.kogni.soile2.elang;

public abstract class ProgramInstruction {
    
    public ProgramInstruction(Opcode opcode, ProgramInstructionIndex index) {
        super();
        this.opcode = opcode;
        this.index = index;
        this.command = "";
    }

    public final String getCommand() {
        return command;
    }

    public final void setCommand(String command) {
        this.command = command;
    }

    public final Opcode getOpcode() {
        return this.opcode;
    }
    
    public final int getIndexValue() {
        return index.getValue();
    }

    public final ProgramInstructionIndex getIndex() {
        return index;
    }
    
    public static interface Branching {
        int getJmp();
        void setJmp(int jmp);
    }

    public static final class If extends ProgramInstruction
                                 implements Branching {
        public If(ProgramInstructionIndex index) {
            super(Opcode.If, index);
        }
        public String getCond() {
            return cond;
        }
        public void setCond(String cond) {
            this.cond = cond;
        }
        public int getJmp() {
            return jmp;
        }
        public void setJmp(int jmp) {
            this.jmp = jmp;
        }

        private String cond;
        private int jmp;
    }


    public static final class While extends ProgramInstruction {
        public While(ProgramInstructionIndex index) {
            super(Opcode.While, index);
        }
        public String getCond() {
            return cond;
        }
        public void setCond(String cond) {
            this.cond = cond;
        }
        public int getJmp() {
            return jmp;
        }
        public void setJmp(int jmp) {
            this.jmp = jmp;
        }

        private String cond;
        private int jmp;
    }


    public static final class Assign extends ProgramInstruction {
        public Assign(ProgramInstructionIndex index) {
            super(Opcode.Assign, index);
        }
        public String getHost() {
            return host;
        }
        public void setHost(String host) {
            this.host = host;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getValue() {
            return value;
        }
        public void setValue(String value) {
            this.value = value;
        }
        private String host;
        private String name;
        private String value;
    }


    public static final class Fcall extends ProgramInstruction {
        public Fcall(ProgramInstructionIndex index) {
            super(Opcode.Fcall, index);
        }
        public String getHost() {
            return host;
        }
        public void setHost(String host) {
            this.host = host;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getParams() {
            return params;
        }
        public void setParams(String value) {
            this.params = value;
        }
        private String host;
        private String name;
        private String params;
    }

    public static final class Wait extends ProgramInstruction {
        public Wait(ProgramInstructionIndex index) {
            super(Opcode.Wait, index);
        }
        public String getMs() {
            return msExpr;
        }
        public void setMs(String ms) {
            this.msExpr = ms;
        }
        public void setMs(int ms) {
            setMs(Integer.toString(ms, 10));
        }

        private String msExpr;
    }

    public static final class Suspend extends ProgramInstruction {
        public Suspend(ProgramInstructionIndex index) {
            super(Opcode.Suspend, index);
        }
    }

    public static final class Goto extends ProgramInstruction 
                                   implements Branching {
        public Goto(ProgramInstructionIndex index) {
            super(Opcode.Goto, index);
        }
        public int getJmp() {
            return jmp;
        }
        public void setJmp(int jmp) {
            this.jmp = jmp;
        }

        private int jmp;
    }


    public static final class Def extends ProgramInstruction {
        public Def(ProgramInstructionIndex index) {
            super(Opcode.Def, index);
        }
        
        public String getFunc() {
            return func;
        }

        public void setFunc(String func) {
            this.func = func;
        }

        private String func;
    }


    public static final class Undef extends ProgramInstruction {
        public Undef(ProgramInstructionIndex index) {
            super(Opcode.Undef, index);
        }
    }

    public static final class Eoc extends ProgramInstruction {
        public Eoc(ProgramInstructionIndex index) {
            super(Opcode.Eoc, index);
        }
    }

    public static enum Opcode {
        Assign(0, "Assignment", "Assign"),
        Fcall(1, "FunctionCall", "Fcall"),
        If(2, "If"),
        While(3, "While"),
        Goto(4, "Goto"),
        Wait(5, "Wait"),
        Def(6, "Define", "Def"),
        Undef(7, "Un-define", "Undef"),
        Suspend(8, "Suspend"),
        
        // Keep this last!
        Eoc(-1, "End of Code", "Eoc");
        
        Opcode(int code, String name) {
            this(code, name, name);
        }
        
        Opcode(int code, String name, String cmdName) {
            this.code = code;
            this.name = name;
            this.opcodeName = cmdName;
        }
        
        public int code() {
            return this.code;
        }
        
        public int intValue() {
            return this.code();
        }
        
        public String getName() {
            return this.name;
        }
        
        public String getOpcodeName() {
            return this.opcodeName;
        }

        @Override
        public String toString() {
            return this.opcodeName;
        }

        private final int code;
        private String name;
        private String opcodeName;
    }
    
    private Opcode opcode;
    private ProgramInstructionIndex index;
    private String command;
    
}
