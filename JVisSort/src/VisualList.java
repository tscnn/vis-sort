import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JPanel;

public class VisualList extends JPanel implements ComponentListener {

	private static final long serialVersionUID = 1L;
	
	private static Color COLOR_BG_DEFAULT = Color.WHITE;
	private static Color COLOR_BG_SORTED = Color.LIGHT_GRAY;
	private static Color COLOR_BG_SELECTED = Color.CYAN;
	private static Color COLOR_FG_DEFAULT = Color.BLACK;
	private static Color COLOR_FG_SORTED = Color.BLACK;
	private static Color COLOR_FG_SELECTED_1 = Color.RED;
	private static Color COLOR_FG_SELECTED_2 = Color.GREEN;

	private int width;
	private int height;
	private Graphics bufferGraphics = null;
    private Image offscreen = null; 
    final private Lock lock;
	private List<VisualItem> items;
	private int timeUnit;
	private int comparisons;
	
	public VisualList() {
		super();
		addComponentListener(this);
		items = new LinkedList<VisualItem>();
		lock = new ReentrantLock();
	}
	
	public void setRandomList(int numElements) {
		items.clear();
		for (int i=1; i<=numElements; i++) {
			VisualItem item = new VisualItem();
			item.setValue((double)i/(double)numElements);
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
		repaint();
	}
	
	public void update(Graphics g) {
         paint(g);
    } 
	
	public void paint(Graphics g) {
		bufferGraphics.setColor(COLOR_BG_DEFAULT);
		bufferGraphics.fillRect(0, 0, width, height);
		lock.lock();
		for (int i=0; i<items.size(); i++) {
			drawElement(i);
		}
		lock.unlock();
		g.drawImage(offscreen, 0, 0, this); 
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
		repaint();
	}
    
    public void moveElement(int index1, int index2) {
        if (index1 == index2) {
            return;
        } else if (index1 < index2) {
        	lock.lock();
            items.add(index2 + 1, items.get(index1));
            items.remove(index1);
            lock.unlock();
        } else {
        	lock.lock();
            items.add(index2, items.get(index1));
            items.remove(index1 + 1);
            lock.unlock();
        }
		repaint();
    }
	
	public void highlightPair1(int index1, int index2, int timeUnits) {
		if (timeUnit > -1) {
			setPairForegroundSelected1(index1, index2);
			wait(timeUnits);
			setPairForegroundDefault(index1, index2);
		}
	}
	
	public void highlightPair2(int index1, int index2, int timeUnits) {
		if (timeUnit > -1) {
			setPairForegroundSelected2(index1, index2);
			wait(timeUnits);
			setPairForegroundDefault(index1, index2);
		}
	}
    
    public void highlightElement1(int index, int timeUnits) {
    	if (timeUnit > -1) {
    		setItemForegroundSelected1(index);
    		wait(timeUnits);
    		setItemForegroundDefault(index);
    	}
    }
    
    public void highlightElement2(int index, int timeUnits) {
    	if (timeUnit > -1) {
    		setItemForegroundSelected2(index);
    		wait(timeUnits);
    		setItemForegroundDefault(index);
    	}
    }
	
	private void setItemBackground(int index, Color color) {
		items.get(index).setBackground(color);
		repaint();
	}
	
	private void setPairBackground(int index1, int index2, Color color1, Color color2) {
		items.get(index1).setBackground(color1);
		items.get(index2).setBackground(color2);
		repaint();
	}
	
	private void setRangeBackground(int leftIndex, int rightIndex, Color color) {
		for (int i=leftIndex; i<=rightIndex; i++) {
			items.get(i).setBackground(color);
		}
		repaint();
	}
	
	private void setItemForeground(int index, Color color) {
		items.get(index).setForeground(color);
		repaint();
	}
	
	private void setPairForeground(int index1, int index2, Color color1, Color color2) {
		items.get(index1).setForeground(color1);
		items.get(index2).setForeground(color2);
		repaint();
	}
	
	private void setRangeForeground(int leftIndex, int rightIndex, Color color) {
		for (int i=leftIndex; i<=rightIndex; i++) {
			items.get(i).setForeground(color);
		}
		repaint();
	}
	
	private void setItemColor(int index, Color fg, Color bg) {
		items.get(index).setForeground(fg);
		items.get(index).setBackground(bg);
		repaint();
	}
	
	private void setPairColor(int index1, int index2, Color fg1, Color bg1, Color fg2, Color bg2) {
		items.get(index1).setForeground(fg1);
		items.get(index1).setBackground(bg1);
		items.get(index2).setForeground(fg2);
		items.get(index1).setBackground(bg2);
		repaint();
	}
	
	private void setRangeColor(int leftIndex, int rightIndex, Color fg, Color bg) {
		for (int i=leftIndex; i<=rightIndex; i++) {
			items.get(i).setForeground(fg);
			items.get(i).setBackground(bg);
		}
		repaint();
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
		Double value = items.get(index).getValue();
		int h = (int) (value * height);
		int w = getWidth() / items.size();
		int offset = width - items.size() * w;
		bufferGraphics.setColor(items.get(index).getForeground());
		bufferGraphics.fillRect(offset + index * w, height - h, w, h);
	}
	
	private void drawElementBackground(int index) {
		Double value = items.get(index).getValue();
		int h = (int) (value * height);
		int w = width / items.size();
		int offset = width - items.size() * w;
		bufferGraphics.setColor(items.get(index).getBackground());
		bufferGraphics.fillRect(offset + index * w, 0, w, height - h);
	}
	
	private void drawElement(int index) {
		drawElementForeground(index);
		drawElementBackground(index);
	}
	
	public void setTimeUnit(int timeUnit) {
		this.timeUnit = timeUnit;
	}
	
	public void wait(int timeUnits) {
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

	@Override
	public void componentHidden(ComponentEvent arg0) {
	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
	}

	@Override
	public void componentShown(ComponentEvent arg0) {
	}

	@Override
	public void componentResized(ComponentEvent e) {
		width = getWidth();
		height = getHeight();
		offscreen = createImage(width, height);
		bufferGraphics = offscreen.getGraphics();
	}

}
