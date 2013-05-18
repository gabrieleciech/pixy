package at.ac.tuwien.infosys.www.pixy.analysis.dep;

import at.ac.tuwien.infosys.www.pixy.conversion.TacPlace;
import at.ac.tuwien.infosys.www.pixy.conversion.nodes.CfgNode;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Nenad Jovanovic <enji@seclab.tuwien.ac.at>
 */
public class SinkProblem {
    // a place for which we want to know something
    TacPlace place;

    // nodes from which the function containing the
    // sensitive sink is called
    List<CfgNode> callNodes;

    public SinkProblem(TacPlace place) {
        this.place = place;
        this.callNodes = new LinkedList<>();
    }

    public void setCallList(List<CfgNode> callNodes) {
        this.callNodes = callNodes;
    }

    public TacPlace getPlace() {
        return this.place;
    }

    public List<CfgNode> getCallNodes() {
        return this.callNodes;
    }
}