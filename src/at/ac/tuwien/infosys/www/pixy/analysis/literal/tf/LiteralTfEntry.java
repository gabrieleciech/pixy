package at.ac.tuwien.infosys.www.pixy.analysis.literal.tf;

import at.ac.tuwien.infosys.www.pixy.analysis.LatticeElement;
import at.ac.tuwien.infosys.www.pixy.analysis.TransferFunction;
import at.ac.tuwien.infosys.www.pixy.analysis.literal.LiteralLatticeElement;
import at.ac.tuwien.infosys.www.pixy.conversion.TacFunction;
import at.ac.tuwien.infosys.www.pixy.conversion.Variable;

import java.util.Map;

/**
 * Transfer function for function entries.
 *
 * @author Nenad Jovanovic <enji@seclab.tuwien.ac.at>
 */
public class LiteralTfEntry
    extends TransferFunction {

    private TacFunction function;

// *********************************************************************************
// CONSTRUCTORS ********************************************************************
// *********************************************************************************

    public LiteralTfEntry(TacFunction function) {
        this.function = function;
    }

// *********************************************************************************
// OTHER ***************************************************************************
// *********************************************************************************

    public LatticeElement transfer(LatticeElement inX) {

        LiteralLatticeElement in = (LiteralLatticeElement) inX;
        LiteralLatticeElement out = new LiteralLatticeElement(in);

        // initialize g-shadows

        Map<Variable, Variable> globals2GShadows = this.function.getSymbolTable().getGlobals2GShadows();

        for (Map.Entry<Variable, Variable> entry : globals2GShadows.entrySet()) {
            Variable global = entry.getKey();
            Variable gShadow = entry.getValue();

            // note: the TacConverter already took care that arrays and array elements
            // don't get shadow variables, so you don't have to check this here again

            // initialize shadow to the literal of its original
            out.setShadow(gShadow, global);
        }

        // initialize f-shadows

        Map<Variable, Variable> formals2FShadows = this.function.getSymbolTable().getFormals2FShadows();

        for (Map.Entry<Variable, Variable> entry : formals2FShadows.entrySet()) {
            Variable formal = entry.getKey();
            Variable fShadow = entry.getValue();

            // initialize
            out.setShadow(fShadow, formal);
        }

        return out;
    }
}