package anim;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

public class AnimationPanel2 extends JPanel {
	private long lastDrawTime = System.nanoTime();

	private static class Ball {
		private double angle = Math.random();
		private int x = 10;
		private int y = 10;

		private void draw(Graphics g, long time) {
			g.setColor(Color.RED);
			g.fillOval(x, y, 20, 20);
			g.setColor(Color.BLACK);
			g.drawOval(x, y, 20, 20);
			x += Math.sin(angle) * 2;
			y += Math.cos(angle) * 1.5;
			System.out.println(x + " - " + y);
			if (x > 480)
				angle -= 90;
			if (y > 480)
				angle -= 90;
			if (x < 0)
				angle -= 90;
			if (y < 0)
				angle -= 90;
		}
	}

	public AnimationPanel2() {
		for (int i = 0; i < 1000; i++) {
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

	public static void main(String[] args) {
		JFrame frame = new JFrame("Animation");
		frame.setMinimumSize(new Dimension(540, 560));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new AnimationPanel2());
		frame.setVisible(true);
	}
}
