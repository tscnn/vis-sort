import java.util.List;

public class SimpleSort implements SortingAlgorithm {

	@Override
	public void sort(ValueListCanvas vlc) {
		List<Double> list = vlc.getList();
		for (int i=0; i<list.size(); i++) {
			for (int j=i+1; j<list.size(); j++) {
				vlc.touch(i, j);
				if (vlc.greater(i, j)) {
					vlc.swap(i, j);
				}
			}
		}
	}

	@Override
	public String toString() {
		return "SimpleSort";
	}

}
