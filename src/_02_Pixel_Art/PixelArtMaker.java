package _02_Pixel_Art;

import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class PixelArtMaker implements MouseListener{
	private JFrame window;
	private GridInputPanel gip;
	private GridPanel gridPanel;
	ColorSelectionPanel colorSelectionPanel;
	
	public void start() {
		gip = new GridInputPanel(this);	
		window = new JFrame("Pixel Art");
		window.setLayout(new FlowLayout());
		window.setResizable(false);
		
		window.add(gip);
		window.pack();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}

	public void submitGridData(int w, int h, int r, int c) {
		gridPanel = new GridPanel(w, h, r, c);
		colorSelectionPanel = new ColorSelectionPanel();
		window.remove(gip);
		window.add(gridPanel);
		window.add(colorSelectionPanel);
		gridPanel.repaint();
		gridPanel.addMouseListener(this);
		window.pack();
	}
	
	public static void main(String[] args) {
		new PixelArtMaker().start();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		gridPanel.setColor(colorSelectionPanel.getSelectedColor());
		//System.out.println(csp.getSelectedColor());
		gridPanel.clickPixel(e.getX(), e.getY());
		gridPanel.repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}
