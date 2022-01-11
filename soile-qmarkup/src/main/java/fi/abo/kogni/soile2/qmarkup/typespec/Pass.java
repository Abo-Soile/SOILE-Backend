package fi.abo.kogni.soile2.qmarkup.typespec;

import java.util.IdentityHashMap;

import org.antlr.v4.runtime.ParserRuleContext;


public class Pass extends TypeSpecBaseListener {
    private IdentityHashMap<ParserRuleContext, NodeData> nodeData;

    Pass(IdentityHashMap<ParserRuleContext, NodeData> nodeData) {
        super();
        this.nodeData = nodeData;
    }
    
    protected void newNodeData(ParserRuleContext key) {
        if (nodeData.containsKey(key) == false) {
            nodeData.put(key, new NodeData());
        }
    }
    
    protected NodeData getNodeData(ParserRuleContext key) {
        newNodeData(key);
        return nodeData.get(key);
    }

}