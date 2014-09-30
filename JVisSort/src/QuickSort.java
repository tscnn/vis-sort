public class QuickSort implements SortingAlgorithm {

    private VisualList vl;

	@Override
	public void sort(VisualList vl) {
		this.vl = vl;
        quickSort(0, vl.getList().size() - 1);
	}
    
    private int part(int left, int right) {
        int pivotIndex = (int) (Math.random() * (right - left) + left);
        vl.setItemForegroundSelected2(pivotIndex);
        vl.setRangeBackgroundSelected(left, right);
        double pivot = vl.getList().get(pivotIndex).getValue();
        int i = left;
        while (i <= right) {
            if (i != pivotIndex) {
                vl.highlightElement1(i, 1);
            }
            if (i < pivotIndex && pivot < vl.getList().get(i).getValue()) {
                vl.moveElement(i, right);
                pivotIndex--;           
            } else if (i > pivotIndex && pivot > vl.getList().get(i).getValue()) {
                vl.moveElement(i, pivotIndex);
                pivotIndex++;
                i++;
            } else {
                i++;
            }
        }
        vl.setItemForegroundDefault(pivotIndex);
        vl.setRangeBackgroundDefault(left, right);
        return pivotIndex;
    }
    
    private void quickSort(int left, int right) {
        if (left < right) {
            int pivotIndex = part(left, right);
            vl.setItemColorSorted(pivotIndex);
            quickSort(left, pivotIndex - 1);
            quickSort(pivotIndex + 1, right);
        } else if (left == right) {
        	vl.setItemColorSorted(right);
        }
    }

	@Override
	public String toString() {
		return "QuickSort";
	}

}
