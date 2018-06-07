package Input;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.Dimension;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Point;

import org.graphstream.graph.implementations.MultiGraph;

import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class SFGgui {

	private JFrame frame;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private AdjacencyLists Graph;
	private int Size, x, y, edge;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private String TransferFunction = "";
	private String forward = "";
	private String forwardGain = "";
	private String Loops = "";
	private String LoopsGain = "";
	private String DeltaGain = "";
	private String Nontouched = "";
	private JLabel lblForwardPaths;
	private JLabel label;
	private JLabel lblGain;
	private JLabel label_1;
	private JLabel lblLoops;
	private JLabel label_2;
	private JLabel lblTransferFunction;
	private logic l;
	private JLabel label_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SFGgui window = new SFGgui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public boolean isNumeric(String s) {
		return s.matches("[-+]?\\d*\\.?\\d+");
	}

	/**
	 * Create the application.
	 */
	public SFGgui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel();
		panel.setPreferredSize(new Dimension(100, 100));
		panel.setForeground(Color.BLACK);
		panel.setBackground(Color.WHITE);
		frame.getContentPane().add(panel, BorderLayout.CENTER);

		panel_1 = new JPanel();
		panel_1.setBackground(new Color(30, 144, 255));
		panel_1.setPreferredSize(new Dimension(800, 100));
		panel_1.setBounds(new Rectangle(100, 100, 100, 200));
		panel.add(panel_1);

		JLabel lblWelcomeToSignal = new JLabel("Welcome to signal flow graph application");
		lblWelcomeToSignal.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblWelcomeToSignal.setFont(new Font("Calibri", Font.BOLD, 24));
		lblWelcomeToSignal.setForeground(Color.WHITE);
		lblWelcomeToSignal.setPreferredSize(new Dimension(420, 50));
		panel_1.add(lblWelcomeToSignal);

		panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setPreferredSize(new Dimension(800, 150));
		panel_3.setBounds(new Rectangle(100, 100, 100, 100));
		panel.add(panel_3);
		panel_3.setLayout(null);

		JLabel lblPleaseEnterThe = new JLabel("Please enter the number of nodes");
		lblPleaseEnterThe.setBounds(33, 11, 189, 17);
		lblPleaseEnterThe.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblPleaseEnterThe.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_3.add(lblPleaseEnterThe);

		textField = new JTextField();
		textField.setBounds(33, 28, 56, 20);
		panel_3.add(textField);
		textField.setColumns(10);

		JButton btnOk = new JButton("Ok");
		btnOk.setBounds(171, 27, 51, 23);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (isNumeric(textField.getText()) && Integer.valueOf(textField.getText()) > 0) {
					Size = Integer.valueOf(textField.getText());
					Graph = new AdjacencyLists(Size);
				} else
					JOptionPane.showMessageDialog(null, "You must write positive integer greater than 0");
			}
		});
		btnOk.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_3.add(btnOk);

		JLabel lblEnterTheNumber = new JLabel("Enter the number of starting node");
		lblEnterTheNumber.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblEnterTheNumber.setPreferredSize(new Dimension(200, 20));
		lblEnterTheNumber.setBounds(33, 59, 200, 16);
		panel_3.add(lblEnterTheNumber);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(33, 78, 56, 20);
		panel_3.add(textField_1);

		JLabel lblEnterTheNumber_1 = new JLabel("Enter the number of ending node");
		lblEnterTheNumber_1.setPreferredSize(new Dimension(200, 20));
		lblEnterTheNumber_1.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblEnterTheNumber_1.setBounds(317, 59, 189, 16);
		panel_3.add(lblEnterTheNumber_1);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(317, 78, 56, 20);
		panel_3.add(textField_2);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(581, 78, 56, 20);
		panel_3.add(textField_3);

		JLabel lblEnterTheWeight = new JLabel("Enter the weight of the edge\r\n");
		lblEnterTheWeight.setPreferredSize(new Dimension(200, 20));
		lblEnterTheWeight.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblEnterTheWeight.setBounds(581, 59, 169, 16);
		panel_3.add(lblEnterTheWeight);

		JButton button_2 = new JButton("Ok");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isNumeric(textField_1.getText()) && isNumeric(textField_2.getText())
						&& isNumeric(textField_3.getText()) && Integer.valueOf(textField_1.getText()) > 0
						&& Integer.valueOf(textField_1.getText()) <= Size && Integer.valueOf(textField_2.getText()) > 0
						&& Integer.valueOf(textField_2.getText()) <= Size) {
					x = Integer.valueOf(textField_1.getText());
					y = Integer.valueOf(textField_2.getText());
					edge = Integer.valueOf(textField_3.getText());
					Graph.addEdge(x - 1, y - 1, edge);
				} else
					JOptionPane.showMessageDialog(null, "You must write correct node number");
			}
		});
		button_2.setAlignmentX(0.5f);
		button_2.setBounds(728, 77, 51, 23);
		panel_3.add(button_2);

		JButton btnNewButton = new JButton("Done");
		btnNewButton.addActionListener(new ActionListener() {

			private String Delta;

			public void actionPerformed(ActionEvent e) {
				MultiGraph graph = new MultiGraph("SFG");
				Random rand = new Random();
				for (int i = 10000; i < Size + 10000; i++)
					graph.addNode(Integer.toString(i - 10000)).addAttribute("ui.label",
							Integer.toString(i - 10000 + 1));
				for (int i = 10000; i < Graph.getSize() + 10000; i++) {
					for (int j = 0; j < Graph.getNode(i - 10000).size(); j++) {
						graph.addEdge(
								Integer.toString(
										(int) ((Point) Graph.getNode(i - 10000).get(j)).getY() + rand.nextInt(10000)),
								Integer.toString(i - 10000),
								Integer.toString((int) ((Point) Graph.getNode(i - 10000).get(j)).getX()), true)
								.addAttribute("ui.label",
										Integer.toString((int) ((Point) Graph.getNode(i - 10000).get(j)).getY()));
					}
				}
				graph.display();

				l = new logic(Graph);

				TransferFunction = "<html>Transfer Function<br>";
				TransferFunction += l.findPathsAndLoops(0, Size - 1) + "</html>";

				forward = "<html>Forward paths";
				Delta = "<html>Delta";
				for (int i = 0; i < l.p; i++) {
					String temp = "";
					for (int j = 0; j < l.paths[i].size(); j++)
						temp += Integer.toString((int) l.paths[i].get(j) + 1) + " ";
					forward += "<br>" + temp;
					Delta += "<br>" + temp;
				}
				forward += "</html>";
				Delta += "</html>";

				forwardGain = "<html>Gain";
				for (int i = 0; i < l.p; i++) {
					forwardGain += "<br>" + l.pathsVal.get(i);
				}
				forwardGain += "</html>";

				Loops = "<html>Loops";
				for (int i = 0; i < l.l; i++) {
					String temp = "";
					for (int j = 0; j < l.loops[i].size(); j++)
						temp += Integer.toString((int) l.loops[i].get(j)) + " ";
					Loops += "<br>" + temp;
				}
				Loops += "</html>";

				LoopsGain = "<html>Gain";
				for (int i = 0; i < l.l; i++) {
					LoopsGain += "<br>" + l.loopsVal.get(i);
				}
				LoopsGain += "</html>";
				
				Nontouched = "<html>Nontouched loops<br>";
				for(int i = 1 ; i < l.c.length && l.c[i].size() > 0 ; i++){
					Nontouched += Integer.toString(i + 1) + " Nontouched loops";
					for(int j = 0 ; j < l.c[i].size() ; j++){
						String temp = "";
						for(int k = 0 ; k < l.c[i].get(j).size() ; k++)
							temp += Integer.toString((int)l.c[i].get(j).get(k) + 1) + " ";
						Nontouched += "<br>" + temp;
					}
					Nontouched += "<br>";
				}
				Nontouched += "</html>";

				DeltaGain = "<html>Gain";
				for (int i = 0; i < l.deltaVal.size(); i++) {
					DeltaGain += "<br>" + l.deltaVal.get(i);
				}
				DeltaGain += "</html>";

				lblForwardPaths.setText(forward);
				label.setText(Delta);
				lblGain.setText(forwardGain);
				label_1.setText(DeltaGain);
				lblLoops.setText(Loops);
				label_2.setText(LoopsGain);
				lblTransferFunction.setText(Nontouched);
				label_4.setText(TransferFunction);

			}
		});
		btnNewButton.setBounds(690, 116, 89, 23);
		panel_3.add(btnNewButton);

		panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(800, 300));
		panel_2.setBackground(Color.WHITE);
		panel.add(panel_2);
		panel_2.setLayout(null);

		lblForwardPaths = new JLabel(forward);
		lblForwardPaths.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblForwardPaths.setVerticalAlignment(SwingConstants.TOP);
		lblForwardPaths.setBounds(10, 148, 279, 141);
		panel_2.add(lblForwardPaths);

		label = new JLabel(forward);
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setFont(new Font("Calibri", Font.PLAIN, 16));
		label.setBounds(10, 0, 279, 149);
		panel_2.add(label);

		lblGain = new JLabel(forwardGain);
		lblGain.setFont(new Font("Calibri", Font.CENTER_BASELINE, 16));
		lblGain.setVerticalAlignment(SwingConstants.TOP);
		lblGain.setBounds(299, 0, 55, 146);
		panel_2.add(lblGain);

		label_1 = new JLabel(DeltaGain);
		label_1.setVerticalAlignment(SwingConstants.TOP);
		label_1.setFont(new Font("Calibri", Font.CENTER_BASELINE, 16));
		label_1.setBounds(299, 151, 55, 138);
		panel_2.add(label_1);

		lblLoops = new JLabel(Loops);
		lblLoops.setVerticalAlignment(SwingConstants.TOP);
		lblLoops.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblLoops.setBounds(364, 0, 263, 149);
		panel_2.add(lblLoops);

		label_2 = new JLabel(LoopsGain);
		label_2.setVerticalAlignment(SwingConstants.TOP);
		label_2.setFont(new Font("Calibri", Font.CENTER_BASELINE, 16));
		label_2.setBounds(637, 0, 55, 149);
		panel_2.add(label_2);

		lblTransferFunction = new JLabel(TransferFunction);
		lblTransferFunction.setVerticalAlignment(SwingConstants.TOP);
		lblTransferFunction.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblTransferFunction.setBounds(364, 148, 201, 141);
		panel_2.add(lblTransferFunction);
		
		label_4 = new JLabel("");
		label_4.setVerticalAlignment(SwingConstants.TOP);
		label_4.setFont(new Font("Calibri", Font.BOLD, 16));
		label_4.setBounds(575, 148, 204, 141);
		panel_2.add(label_4);

	}
}
