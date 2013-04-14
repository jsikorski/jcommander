package view.utils;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;

public class ExtensibleFlowLayout extends FlowLayout {
	public ExtensibleFlowLayout(int align) {
		super(align);
	}
	
	@Override
	public Dimension preferredLayoutSize(Container target) {
		synchronized (target.getTreeLock()) {
	        Dimension dim = new Dimension(0, 0);
	        int nmembers = target.getComponentCount();
	        boolean firstVisibleComponent = true;
	        boolean useBaseline = getAlignOnBaseline();
	        int maxAscent = 0;
	        int maxDescent = 0;

	        for (int i = 0 ; i < nmembers ; i++) {
	            Component m = target.getComponent(i);
	            if (m.isVisible()) {
	                Dimension d = m.getPreferredSize();
	                dim.height = Math.max(dim.height, d.height);
	                if (firstVisibleComponent) {
	                    firstVisibleComponent = false;
	                } else {
	                    dim.width += getHgap();
	                }
	                dim.width += d.width;
	                if (useBaseline) {
	                    int baseline = m.getBaseline(d.width, d.height);
	                    if (baseline >= 0) {
	                        maxAscent = Math.max(maxAscent, baseline);
	                        maxDescent = Math.max(maxDescent, d.height - baseline);
	                    }
	                }
	            }
	        }
	        if (useBaseline) {
	            dim.height = Math.max(maxAscent + maxDescent, dim.height);
	        }
	        Insets insets = target.getInsets();          
	        dim.width += insets.left + insets.right + getHgap() *2;
	        
	        int numOfRows = (int) Math.ceil(dim.width / target.getWidth());
	        dim.height += dim.height * numOfRows + numOfRows * getVgap();
	         	        
	        dim.height += insets.top + insets.bottom + getVgap() *2;
	        return dim;
	      }
	}
}
