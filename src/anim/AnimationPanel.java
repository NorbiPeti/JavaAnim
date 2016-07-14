package anim;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

public class AnimationPanel extends JPanel {
	private long lastDrawTime = System.nanoTime();

	private static class Ball {
		private double angle = Math.random();

		private void draw(Graphics g, long time) {
			int x = calcPositionFromDistance(time / 2000000 * Math.sin(angle)) + 10;
			int y = calcPositionFromDistance(time / 2000000 * Math.cos(angle)) + 10;

			g.setColor(Color.RED);
			g.fillOval(x, y, 20, 20);
			g.setColor(Color.BLACK);
			g.drawOval(x, y, 20, 20);
		}

		private int calcPositionFromDistance(double distance) {
			distance = distance % (480 * 2);
			if (distance > 480)
				distance = 480 * 2 - distance;
			return (int) distance;
		}
	}

	public AnimationPanel() {
		for (int i = 0; i < 10; i++) {
			balls.add(new Ball());
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		long time = System.nanoTime();

		g.drawString(String.valueOf(1000000000 / (time - lastDrawTime)), 20, 30);

		lastDrawTime = time;

		g.setColor(Color.BLACK);
		g.drawRect(10, 10, 500, 500);

		for (Ball ball : balls)
			ball.draw(g, time);

		repaint();
	}

	private static ArrayList<Ball> balls = new ArrayList<>();

	/*
	 * public static void main(String[] args) { JFrame frame = new
	 * JFrame("Animation"); frame.setMinimumSize(new Dimension(540, 560));
	 * frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); frame.add(new
	 * AnimationPanel()); frame.setVisible(true); }
	 */
}
