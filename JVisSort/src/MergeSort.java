public class MergeSort implements SortingAlgorithm {

	private VisualList vl;
	
	@Override
	public void sort(VisualList vl) {
		this.vl = vl;
		mergeSort(0, vl.getList().size() - 1);
	}

	private void mergeSort(int left, int right) {
		int size = right - left + 1;
		if (size > 1) {
			mergeSort(left, left + size/2 - 1);
			mergeSort(left + size/2, right);
			merge(left, left + size/2 - 1, left + size/2, right);
		}
	}

	private void merge(int left1, int right1, int left2, int right2) {
		int originalLeft = left1;
		vl.setRangeBackgroundDefault(left1, right1);
		vl.setRangeBackgroundSelected(left2, right2);
		while (right1 - left1 + 1 > 0 && right2 - left2 + 1 > 0) {
			if (vl.getList().get(left1).getValue() > vl.getList().get(left2).getValue()) {
				vl.moveElement(left2, left1);
				left2++;
				right1++;
			}
			vl.highlightElement1(left1, 1);
			left1++;
		}
		vl.setRangeBackgroundDefault(originalLeft, right2);
	}

	public String toString() {
		return "MergeSort";
	}

}
