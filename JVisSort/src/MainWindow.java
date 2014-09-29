import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import java.awt.Insets;
import javax.swing.JSlider;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class MainWindow {

	private JFrame frmHowSortingWorks;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frmHowSortingWorks.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmHowSortingWorks = new JFrame();
		frmHowSortingWorks.setTitle("How sorting works");
		frmHowSortingWorks.setBounds(100, 100, 700, 500);
		frmHowSortingWorks.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHowSortingWorks.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panelNorth = new JPanel();
		panelNorth.setBackground(Color.LIGHT_GRAY);
		panelNorth.setBorder(new EmptyBorder(3, 3, 3, 3));
		frmHowSortingWorks.getContentPane().add(panelNorth, BorderLayout.NORTH);
		GridBagLayout gbl_panelNorth = new GridBagLayout();
		gbl_panelNorth.columnWidths = new int[]{0, 0, 0, 100, 0, 100, 0, 0};
		gbl_panelNorth.rowHeights = new int[]{0, 0};
		gbl_panelNorth.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelNorth.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelNorth.setLayout(gbl_panelNorth);
		
		JLabel labelAlgorithm = new JLabel("Algorithm");
		GridBagConstraints gbc_labelAlgorithm = new GridBagConstraints();
		gbc_labelAlgorithm.insets = new Insets(0, 5, 0, 5);
		gbc_labelAlgorithm.anchor = GridBagConstraints.EAST;
		gbc_labelAlgorithm.gridx = 0;
		gbc_labelAlgorithm.gridy = 0;
		panelNorth.add(labelAlgorithm, gbc_labelAlgorithm);
		
		final JComboBox<SortingAlgorithm> comboBoxAlgorithm = new JComboBox<SortingAlgorithm>();
		comboBoxAlgorithm.addItem(new SimpleSort());
		
		GridBagConstraints gbc_comboBoxAlgorithm = new GridBagConstraints();
		gbc_comboBoxAlgorithm.insets = new Insets(1, 0, 0, 5);
		gbc_comboBoxAlgorithm.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxAlgorithm.gridx = 1;
		gbc_comboBoxAlgorithm.gridy = 0;
		panelNorth.add(comboBoxAlgorithm, gbc_comboBoxAlgorithm);
		
		JLabel labelDelay = new JLabel("Delay");
		GridBagConstraints gbc_labelDelay = new GridBagConstraints();
		gbc_labelDelay.insets = new Insets(0, 15, 0, 0);
		gbc_labelDelay.gridx = 2;
		gbc_labelDelay.gridy = 0;
		panelNorth.add(labelDelay, gbc_labelDelay);
		
		JLabel labelElements = new JLabel("Elements");
		GridBagConstraints gbc_labelElements = new GridBagConstraints();
		gbc_labelElements.insets = new Insets(0, 10, 0, 0);
		gbc_labelElements.gridx = 4;
		gbc_labelElements.gridy = 0;
		panelNorth.add(labelElements, gbc_labelElements);
		
		JPanel panelSouth = new JPanel();
		panelSouth.setBackground(Color.LIGHT_GRAY);
		panelSouth.setBorder(new EmptyBorder(3, 3, 3, 3));
		frmHowSortingWorks.getContentPane().add(panelSouth, BorderLayout.SOUTH);
		GridBagLayout gbl_panelSouth = new GridBagLayout();
		gbl_panelSouth.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panelSouth.rowHeights = new int[]{0, 0};
		gbl_panelSouth.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panelSouth.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelSouth.setLayout(gbl_panelSouth);
		
		JLabel labelCompCaption = new JLabel("Comparisons");
		GridBagConstraints gbc_labelCompCaption = new GridBagConstraints();
		gbc_labelCompCaption.insets = new Insets(0, 0, 0, 5);
		gbc_labelCompCaption.gridx = 0;
		gbc_labelCompCaption.gridy = 0;
		panelSouth.add(labelCompCaption, gbc_labelCompCaption);
		
		JLabel labelComp = new JLabel("0");
		GridBagConstraints gbc_labelComp = new GridBagConstraints();
		gbc_labelComp.insets = new Insets(0, 0, 0, 5);
		gbc_labelComp.gridx = 1;
		gbc_labelComp.gridy = 0;
		panelSouth.add(labelComp, gbc_labelComp);
		
		JLabel labelBy = new JLabel("<html>by <a href=\"http://tobiasschomann.de\">Tobias Schomann</a></html>");
		labelBy.setFont(new Font("Dialog", Font.PLAIN, 11));
		GridBagConstraints gbc_labelBy = new GridBagConstraints();
		gbc_labelBy.anchor = GridBagConstraints.EAST;
		gbc_labelBy.gridx = 2;
		gbc_labelBy.gridy = 0;
		panelSouth.add(labelBy, gbc_labelBy);
		
		final ValueListCanvas vlc = new ValueListCanvas();
		vlc.setForeground(Color.BLACK);
		vlc.setBackground(Color.WHITE);
		frmHowSortingWorks.getContentPane().add(vlc, BorderLayout.CENTER);
		
		final JSlider sliderDelay = new JSlider();
		sliderDelay.setValue(4);
		sliderDelay.setMaximum(9);
		sliderDelay.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				vlc.setTimeUnit(sliderDelay.getValue());
			}
		});
		sliderDelay.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_sliderDelay = new GridBagConstraints();
		gbc_sliderDelay.fill = GridBagConstraints.HORIZONTAL;
		gbc_sliderDelay.insets = new Insets(1, 0, 0, 5);
		gbc_sliderDelay.gridx = 3;
		gbc_sliderDelay.gridy = 0;
		panelNorth.add(sliderDelay, gbc_sliderDelay);
		
		final JSlider sliderElements = new JSlider();
		sliderElements.setValue(260);
		sliderElements.setMaximum(510);
		sliderElements.setMinimum(10);
		sliderElements.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				vlc.setRandomList(sliderElements.getValue());
			}
		});
		sliderElements.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_sliderElements = new GridBagConstraints();
		gbc_sliderElements.fill = GridBagConstraints.HORIZONTAL;
		gbc_sliderElements.insets = new Insets(1, 0, 0, 5);
		gbc_sliderElements.gridx = 5;
		gbc_sliderElements.gridy = 0;
		panelNorth.add(sliderElements, gbc_sliderElements);
		
		
		JButton buttonRun = new JButton("run");
		buttonRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Thread thread = new Thread() {
					@Override
					public void run() {
						((SortingAlgorithm)comboBoxAlgorithm.getSelectedItem()).sort(vlc);
					}
				};
				thread.start();
			}
		});
		GridBagConstraints gbc_buttonRun = new GridBagConstraints();
		gbc_buttonRun.insets = new Insets(0, 10, 0, 0);
		gbc_buttonRun.gridx = 6;
		gbc_buttonRun.gridy = 0;
		panelNorth.add(buttonRun, gbc_buttonRun);
		
		frmHowSortingWorks.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				vlc.setTimeUnit(sliderDelay.getValue());
				vlc.setRandomList(sliderElements.getValue());
			}
		});
		
	}

}
