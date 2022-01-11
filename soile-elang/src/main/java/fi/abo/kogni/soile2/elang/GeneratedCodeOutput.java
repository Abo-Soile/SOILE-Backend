package fi.abo.kogni.soile2.elang;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

public class GeneratedCodeOutput {
    
    public GeneratedCodeOutput(STGroup stGroup) {
        this.codeTmpl = stGroup.getInstanceOf("gencode");
    }
    
    public GeneratedCodeOutput(ST codeTmpl) {
        this.codeTmpl = codeTmpl;
    }
    
    public void setStartIndex(int idx) {
        codeTmpl.add("startIndex", idx);
    }
    
    public void addToplevelDef(ST st) {
        codeTmpl.addAggr("toplevel.{def}", st.render());
    }
    
    public void addProgramInstruction(String piCmd) {
        codeTmpl.addAggr("pis.{def}", piCmd);
    }
    
    public String toCode() {
        return codeTmpl.render();
    }
    
    private ST codeTmpl;
    
}