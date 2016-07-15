package anim;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

public class AnimationPanel2 extends JPanel {
	private static long lastDrawTime = System.nanoTime();

	private static class Ball {
		private double angle = Math.random();
		private double x = 10;
		private double y = 10;
		private int speed = 200;

		public Ball() {
			Random rand = new Random();
			x = rand.nextInt(480) + 10;
			y = rand.nextInt(480) + 10;
			boolean cont = true;
			while (cont) {
				cont = false;
				for (Ball ball : balls) {
					if (ball.x - 10 < this.x + 10 && ball.x + 10 > this.x - 10) {
						if (ball.y - 10 < this.y + 10 && ball.y + 10 > this.y - 10) {
							x = rand.nextInt(480) + 10;
							y = rand.nextInt(480) + 10;
							cont = true;
						}
					}
				}
			}
		}

		private void draw(Graphics g, long time) {
			g.setColor(Color.RED);
			g.fillOval((int) x, (int) y, 20, 20);
			g.setColor(Color.BLACK);
			g.drawOval((int) x, (int) y, 20, 20);
		}

		public void move() {
			x += Math.sin(angle) * 10;
			y += Math.cos(angle) * 10;
			if (x > 490)
				angle += Math.PI / 2;
			if (x > 500)
				x = 480;
			if (y > 490)
				angle += Math.PI / 2;
			if (y > 500)
				y = 480;
			if (x < 10)
				angle += Math.PI / 2;
			if (x < 0)
				x = 10;
			if (y < 10)
				angle += Math.PI / 2;
			if (y < 0)
				y = 10;
			for (Ball ball : balls) {
				if (this == ball)
					continue;
				if (ball.x - 10 < this.x + 10 && ball.x + 10 > this.x - 10) {
					if (ball.y - 10 < this.y + 10 && ball.y + 10 > this.y - 10)
						angle += Math.PI;
				}
			}
		}
	}

	public AnimationPanel2() {
		for (int i = 0; i < 10; i++) {
			balls.add(new Ball());
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		long time = System.nanoTime();

		g.setColor(Color.BLACK);
		g.drawRect(10, 10, 500, 500);

		for (Ball ball : balls)
			ball.draw(g, time);

		g.setColor(Color.BLACK);
		g.drawString(String.valueOf(1000000000 / (time - lastDrawTime)), 20, 30);

		lastDrawTime = time;

		// repaint();
	}

	private static ArrayList<Ball> balls = new ArrayList<>();

	public static void main(String[] args) {
		JFrame frame = new JFrame("Animation");
		frame.setMinimumSize(new Dimension(540, 560));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		AnimationPanel2 ap = new AnimationPanel2();
		frame.add(ap);
		frame.setVisible(true);
		Timer timer = new Timer(1000 / 30, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (Ball ball : balls)
					ball.move();
				ap.repaint();
			}
		});
		timer.start();
	}
}
