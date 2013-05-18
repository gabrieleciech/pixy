package at.ac.tuwien.infosys.www.pixy.analysis.dep.tf;

import at.ac.tuwien.infosys.www.pixy.analysis.LatticeElement;
import at.ac.tuwien.infosys.www.pixy.analysis.TransferFunction;
import at.ac.tuwien.infosys.www.pixy.analysis.dep.Dep;
import at.ac.tuwien.infosys.www.pixy.analysis.dep.DepLatticeElement;
import at.ac.tuwien.infosys.www.pixy.analysis.dep.DepSet;
import at.ac.tuwien.infosys.www.pixy.conversion.nodes.CfgNodeCallBuiltin;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Nenad Jovanovic <enji@seclab.tuwien.ac.at>
 */
public class DepTfCallBuiltin
    extends TransferFunction {

    private CfgNodeCallBuiltin cfgNode;

// *********************************************************************************
// CONSTRUCTORS ********************************************************************
// *********************************************************************************

    public DepTfCallBuiltin(CfgNodeCallBuiltin cfgNode) {
        this.cfgNode = cfgNode;
    }

// *********************************************************************************
// OTHER ***************************************************************************
// *********************************************************************************

    public LatticeElement transfer(LatticeElement inX) {

        DepLatticeElement in = (DepLatticeElement) inX;
        DepLatticeElement out = new DepLatticeElement(in);

        // create an appropariate taint value (holding the function's name);
        // the array label is identic to the taint value
        Set<Dep> ets = new HashSet<>();
        ets.add(Dep.create(this.cfgNode));
        DepSet retDepSet = DepSet.create(ets);
        DepSet retArrayLabel = retDepSet;

        // assign this taint/label to the node's temporary
        out.handleReturnValueBuiltin(this.cfgNode.getTempVar(), retDepSet, retArrayLabel);

        return out;
    }
}