import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.util.ArrayList;
import java.util.List;

public class ValueListCanvas extends Canvas {

	private static final long serialVersionUID = 1L;

	private List<Double> values;
	private List<Color> colors;
	private int timeUnit;
	private int comparisons;
	private boolean showComparisons = true;
	
	public ValueListCanvas() {
		super();
		init();
	}

	public ValueListCanvas(GraphicsConfiguration config) {
		super(config);
		init();
	}
	
	private void init() {
		values = new ArrayList<Double>();
		colors = new ArrayList<Color>();
		setIgnoreRepaint(true);
	}
	
	public void setRandomList(int numElements) {
		values.clear();
		colors.clear();
		for (int i=0; i<numElements; i++) {
			values.add(Math.random());
			colors.add(getForeground());
		}
		Graphics g = getGraphics();
		g.setColor(getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());
		for (int i=0; i<numElements; i++) {
			drawElement(i);
		}
		comparisons = 0;
	}
	
	public int getComparisons() {
		return comparisons;
	}

	public List<Double> getList() {
		return values;
	}
	
	public boolean greater(int index1, int index2) {
		comparisons++;
		return values.get(index1) > values.get(index2);
	}

	public void swap(int index1, int index2) {
		Double tmp1 = values.get(index1);
		values.set(index1, values.get(index2));
		values.set(index2, tmp1);
		Color tmp2 = colors.get(index1);
		colors.set(index1, colors.get(index2));
		colors.set(index2, tmp2);
	}
	
	public void touch(int index1, int index2) {
		if (this.showComparisons) {
			markElement(index1, Color.GREEN);
			markElement(index2, Color.GREEN);
			getToolkit().sync();
			wait(timeUnit);
			markElement(index1, getForeground());
			markElement(index2, getForeground());
			getToolkit().sync();
		} else {
			wait(timeUnit);
			drawElement(index1);
			drawElement(index2);
		}
	}	
	
	public void markElement(int index, Color color) {
		colors.set(index, color);
		drawElement(index);
	}

	public void drawElement(int index) {
		Graphics g = getGraphics();
		Double value = values.get(index);
		int height = (int) (value * getHeight());
		int width = getWidth() / values.size();
		int offset = getWidth() - values.size() * width;
		g.setColor(getBackground());
		g.fillRect(offset + index * width, 0, width, getHeight() - height);
		g.setColor(colors.get(index));
		g.fillRect(offset + index * width, getHeight() - height, width, height);
	}
	
	public void setTimeUnit(int timeUnit) {
		this.timeUnit = timeUnit;
	}
	
	public void setShowComp(boolean comp) {
		this.showComparisons = comp;
	}
	
	private void wait(int exp) {
		Double d = Math.pow(2, exp) - 1;
		try {
			if (d < 1) {
				Thread.sleep(0, (int) (d * 1000));
			} else {
				Thread.sleep((long) Math.round(d));
			}
		} catch (InterruptedException e) {
		}	
	}

}
