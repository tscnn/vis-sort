import java.util.List;

public class SimpleSort implements SortingAlgorithm {

	@Override
	public void sort(VisualList vl) {
		List<VisualItem> list = vl.getList();
		for (int i=0; i<list.size(); i++) {
			for (int j=i+1; j<list.size(); j++) {
				vl.highlightPair2(i, j, 1);
				if (vl.greater(i, j)) {
					vl.swapElements(i, j);
				}
			}
			vl.setItemColorSorted(i);
		}
	}

	@Override
	public String toString() {
		return "SimpleSort";
	}

}
