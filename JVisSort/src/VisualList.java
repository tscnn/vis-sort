import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class VisualList extends Canvas {

	private static final long serialVersionUID = 1L;
	
	private static Color COLOR_BG_DEFAULT = Color.WHITE;
	private static Color COLOR_BG_SORTED = Color.LIGHT_GRAY;
	private static Color COLOR_BG_SELECTED = Color.CYAN;
	private static Color COLOR_FG_DEFAULT = Color.BLACK;
	private static Color COLOR_FG_SORTED = Color.BLACK;
	private static Color COLOR_FG_SELECTED_1 = Color.RED;
	private static Color COLOR_FG_SELECTED_2 = Color.GREEN;

	private List<VisualItem> items;
	private int timeUnit;
	private int comparisons;
	
	public VisualList() {
		super();
		init();
	}

	public VisualList(GraphicsConfiguration config) {
		super(config);
		init();
	}
	
	private void init() {
		items = new LinkedList<VisualItem>();
		//setIgnoreRepaint(true);
	}
	
	public void setRandomList(int numElements) {
		items.clear();
		for (int i=0; i<numElements; i++) {
			VisualItem item = new VisualItem();
			item.setValue(Math.random());
			items.add(item);
		}
		reset();
	}
	
	public void reset() {
		for (VisualItem item: items) {
			item.setForeground(COLOR_FG_DEFAULT);
			item.setBackground(COLOR_BG_DEFAULT);
		}
		comparisons = 0;
		Collections.shuffle(items, new Random(System.nanoTime()));
		paint(getGraphics());
	}
	
	public void paint(Graphics g) {
		g.setColor(getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());
		for (int i=0; i<items.size(); i++) {
			drawElement(i);
		}
	}
	
	public int getComparisons() {
		return comparisons;
	}

	public List<VisualItem> getList() {
		return items;
	}
	
	public boolean greater(int index1, int index2) {
		comparisons++;
		return items.get(index1).getValue() > items.get(index2).getValue();
	}

	public void swapElements(int index1, int index2) {
		VisualItem tmp = items.get(index1);
		items.set(index1, items.get(index2));
		items.set(index2, tmp);
		drawElement(index1);
		drawElement(index2);
		getToolkit().sync();
	}
    
    public void moveElement(int index1, int index2) {
        if (index1 == index2) {
            return;
        } else if (index1 < index2) {
            items.add(index2 + 1, items.get(index1));
            items.remove(index1);
            for (int i=index1; i<=index2; i++) {
                drawElement(i);
            }
        } else {
            items.add(index2, items.get(index1));
            items.remove(index1 + 1);
            for (int i=index2; i<=index1; i++) {
                drawElement(i);
            }
        }
        getToolkit().sync();
        wait(1);
    }
	
	public void highlightPair1(int index1, int index2, int timeUnits) {
		setPairForegroundSelected1(index1, index2);
		wait(timeUnits);
		setPairForegroundDefault(index1, index2);
	}
	
	public void highlightPair2(int index1, int index2, int timeUnits) {
		setPairForegroundSelected2(index1, index2);
		wait(timeUnits);
		setPairForegroundDefault(index1, index2);
	}
    
    public void highlightElement1(int index, int timeUnits) {
        setItemForegroundSelected1(index);
        wait(timeUnits);
        setItemForegroundDefault(index);
    }
    
    public void highlightElement2(int index, int timeUnits) {
        setItemForegroundSelected2(index);
        wait(timeUnits);
        setItemForegroundDefault(index);
    }
	
	private void setItemBackground(int index, Color color) {
		items.get(index).setBackground(color);
		drawElement(index);
		getToolkit().sync();
	}
	
	private void setPairBackground(int index1, int index2, Color color1, Color color2) {
		items.get(index1).setBackground(color1);
		items.get(index2).setBackground(color2);
		drawElementBackground(index1);
		drawElementBackground(index2);
		getToolkit().sync();
	}
	
	private void setRangeBackground(int leftIndex, int rightIndex, Color color) {
		for (int i=leftIndex; i<=rightIndex; i++) {
			items.get(i).setBackground(color);
			drawElementBackground(i);
		}
		getToolkit().sync();
	}
	
	private void setItemForeground(int index, Color color) {
		items.get(index).setForeground(color);
		drawElement(index);
		getToolkit().sync();
	}
	
	private void setPairForeground(int index1, int index2, Color color1, Color color2) {
		items.get(index1).setForeground(color1);
		items.get(index2).setForeground(color2);
		drawElementForeground(index1);
		drawElementForeground(index2);
		getToolkit().sync();
	}
	
	private void setRangeForeground(int leftIndex, int rightIndex, Color color) {
		for (int i=leftIndex; i<=rightIndex; i++) {
			items.get(i).setForeground(color);
			drawElementForeground(i);
		}
		getToolkit().sync();
	}
	
	private void setItemColor(int index, Color fg, Color bg) {
		items.get(index).setForeground(fg);
		items.get(index).setBackground(bg);
		drawElement(index);
		getToolkit().sync();
	}
	
	private void setPairColor(int index1, int index2, Color fg1, Color bg1, Color fg2, Color bg2) {
		items.get(index1).setForeground(fg1);
		items.get(index1).setBackground(bg1);
		items.get(index2).setForeground(fg2);
		items.get(index1).setBackground(bg2);
		drawElement(index1);
		drawElement(index2);
		getToolkit().sync();
	}
	
	private void setRangeColor(int leftIndex, int rightIndex, Color fg, Color bg) {
		for (int i=leftIndex; i<=rightIndex; i++) {
			items.get(i).setForeground(fg);
			items.get(i).setBackground(bg);
			drawElement(i);
		}
		getToolkit().sync();
	}
	
	public void setItemBackgroundDefault(int index) {
		setItemBackground(index, COLOR_BG_DEFAULT);
	}
	
	public void setItemBackgroundSelected(int index) {
		setItemBackground(index, COLOR_BG_SELECTED);		
	}
	
	public void setPairBackgroundDefault(int index1, int index2) {
		setPairBackground(index1, index2, COLOR_BG_DEFAULT, COLOR_BG_DEFAULT);
	}
	
	public void setPairBackgroundSelected(int index1, int index2) {
		setPairBackground(index1, index2, COLOR_BG_SELECTED, COLOR_BG_SELECTED);
	}
	
	public void setRangeBackgroundDefault(int leftIndex, int rightIndex) {
		setRangeBackground(leftIndex, rightIndex, COLOR_BG_DEFAULT);
	}
	
	public void setRangeBackgroundSelected(int leftIndex, int rightIndex) {
		setRangeBackground(leftIndex, rightIndex, COLOR_BG_SELECTED);
	}
	
	public void setItemForegroundDefault(int index) {
		setItemForeground(index, COLOR_FG_DEFAULT);
	}
	
	public void setItemForegroundSelected1(int index) {
		setItemForeground(index, COLOR_FG_SELECTED_1);		
	}
	
	public void setItemForegroundSelected2(int index) {
		setItemForeground(index, COLOR_FG_SELECTED_2);		
	}
	
	public void setPairForegroundDefault(int index1, int index2) {
		setPairForeground(index1, index2, COLOR_FG_DEFAULT, COLOR_FG_DEFAULT);
	}
	
	public void setPairForegroundSelected1(int index1, int index2) {
		setPairForeground(index1, index2, COLOR_FG_SELECTED_1, COLOR_FG_SELECTED_1);
	}
	
	public void setPairForegroundSelected2(int index1, int index2) {
		setPairForeground(index1, index2, COLOR_FG_SELECTED_2, COLOR_FG_SELECTED_2);
	}
	
	public void setRangeForegroundDefault(int leftIndex, int rightIndex) {
		setRangeForeground(leftIndex, rightIndex, COLOR_FG_DEFAULT);
	}
	
	public void setRangeForegroundSelected1(int leftIndex, int rightIndex) {
		setRangeForeground(leftIndex, rightIndex, COLOR_FG_SELECTED_1);
	}
	
	public void setRangeForegroundSelected2(int leftIndex, int rightIndex) {
		setRangeForeground(leftIndex, rightIndex, COLOR_FG_SELECTED_2);
	}
	
	public void setItemColorSorted(int index) {
		setItemColor(index, COLOR_FG_SORTED, COLOR_BG_SORTED);
	}
	
	public void setPairColorSorted(int index1, int index2) {
		setPairColor(index1, index2, COLOR_FG_SORTED, COLOR_BG_SORTED, COLOR_FG_SORTED, COLOR_BG_SORTED);
	}
	
	public void setRangeColorSorted(int leftIndex, int rightIndex) {
		setRangeColor(leftIndex, rightIndex, COLOR_FG_SORTED, COLOR_BG_SORTED);
	}
	
	
	

	private void drawElementForeground(int index) {
		Graphics graphics = getGraphics();
		Double value = items.get(index).getValue();
		int height = (int) (value * getHeight());
		int width = getWidth() / items.size();
		int offset = getWidth() - items.size() * width;
		graphics.setColor(items.get(index).getForeground());
		graphics.fillRect(offset + index * width, getHeight() - height, width, height);
	}
	
	private void drawElementBackground(int index) {
		Graphics graphics = getGraphics();
		Double value = items.get(index).getValue();
		int height = (int) (value * getHeight());
		int width = getWidth() / items.size();
		int offset = getWidth() - items.size() * width;
		graphics.setColor(items.get(index).getBackground());
		graphics.fillRect(offset + index * width, 0, width, getHeight() - height);
	}
	
	private void drawElement(int index) {
		drawElementForeground(index);
		drawElementBackground(index);
	}
	
	public void setTimeUnit(int timeUnit) {
		this.timeUnit = timeUnit;
	}
	
	private void wait(int timeUnits) {
		Double d = Math.pow(2, timeUnit * timeUnits) - 1;
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
