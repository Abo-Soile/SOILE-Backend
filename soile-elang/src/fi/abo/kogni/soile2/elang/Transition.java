package fi.abo.kogni.soile2.elang;

import fi.abo.kogni.soile2.elang.ElangParser.TransitionCondContext;

public abstract class Transition {
    
    private Transition(){}
    
    public static final class Rule {
        
        public Rule(Phase from, Phase to) {
            this(from, to, ALWAYS_TRUE);
        }
        
        public Rule(Phase from, Phase to, Cond cond) {
            super();
            this.from = from;
            this.to = to;
            this.cond = cond;
        }
        
        public Phase getFrom() {
            return from;
        }

        public Phase getTo() {
            return to;
        }

        public Cond getCond() {
            return cond;
        }

        public void setCond(Cond cond) {
            this.cond = cond;
        }

        public boolean alwaysTrue() {
            return cond.alwaysTrue();
        }
        
        private Phase from;
        private Phase to;
        private Cond cond;
        
    }
    
    public static final class Phase implements Comparable<Phase> {
        
        public Phase(String name) {
            super();
            this.name = name;
        }

        public String getName() {
            return name;
        }

        private String name;

        @Override
        public int compareTo(Phase o) {
            String myName = this.getName();
            String otherName = o.getName();
            return myName.compareTo(otherName);
        }
    }
    
    public static class Cond {
        
        public Cond() {
            this.alwaysTrue = true;
        }
        
        public void context(TransitionCondContext condCtx) {
            this.ctx = condCtx;
            this.alwaysTrue = false;
        }
        
        public TransitionCondContext context() {
            return ctx;
        }
        
        public boolean alwaysTrue() {
            return this.alwaysTrue;
        }

        private boolean alwaysTrue;
        private TransitionCondContext ctx;
    }
    
    private static final class AlwaysTrueCond extends Cond {
        public AlwaysTrueCond() {
            super();
        }

        @Override
        public void context(TransitionCondContext _) {
            /* no effect */
        }

        @Override
        public TransitionCondContext context() {
            return null;
        }

        @Override
        public boolean alwaysTrue() {
            return true;
        }
        
    }
    
    public static final Cond ALWAYS_TRUE = new AlwaysTrueCond();
    
}
