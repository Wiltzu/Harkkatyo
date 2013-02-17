package org.wiltzu.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import org.wiltzu.listener.BasicKeyListener;
import org.wiltzu.ui.component.ColoredWorm;
import org.wiltzu.ui.component.Drawable;
import org.wiltzu.ui.component.Worm;
import org.wiltzu.ui.component.exception.CollisionException;
import org.wiltzu.ui.component.food.Food;
import org.wiltzu.ui.component.food.FoodFactory;
import org.wiltzu.util.Direction;

/**
 * <p>This class is main class of game. Game class implements the user interface and runs game loop.</p>
 * 
 * @author Ville Ahti
 */
public class Game extends JFrame implements Runnable {

	private JLabel lblInfo;
	private JPanel pnlGame;
	private final int frameHeight = 477;
	private final int frameWidth = 477;
	private Graphics backBuffer;
	private Image backImage = null;
	private final int FPS = 10;
	private volatile boolean isPaused;
	private volatile boolean running;
	private Worm worm;
	private int x = 0, y = 0;
	private Food food;
	private BasicKeyListener keylistener;
	private final String gameTitle = "WORM GAME";
	private int gameScore = 0;

	/**
	 * <p>Constructor without parameters</p>
	 */
	public Game() {
		initComponents();
	}

	/**
	 * Initializes the main user interface. 
	 */
	private void initComponents() {

		updateGameTitle();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationByPlatform(true);

		getContentPane().setLayout(new BorderLayout());

		addMenu();
		pnlGame = new JPanel();
		lblInfo = new JLabel("You Lose", JLabel.CENTER);
		lblInfo.setVisible(false);
		pnlGame.add(lblInfo);

		setSize(frameWidth, frameHeight);
		setResizable(false);

		worm = new ColoredWorm(Direction.DOWN, x, y, Color.BLUE);

		add(pnlGame, BorderLayout.CENTER);
		keylistener = new BasicKeyListener();
		addKeyListener(keylistener);

	}

	/**
	 * <p>Initializes menubar on the top.</p>
	 */
	private void addMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Game");

		menuBar.add(menu);
		final JMenuItem newGame = new JMenuItem("New Game");
		newGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == newGame) {
					stopGame();
					try {
						Thread.sleep(100);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					startGame();
				}
			}
		});
		addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					newGame.doClick();
				}
				if (e.getKeyCode() == KeyEvent.VK_P) {
					if (isPaused)
						resumeGame();
					else
						pauseGame();
				}
			}
		});
		menu.add(newGame);
		setJMenuBar(menuBar);

	}

	public static void main(String[] args) {

		Game gameWindow = new Game();
		gameWindow.setVisible(true);
	}

	/** 
	 * <p>Runs game loop.</p>
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {

		long beforeTime, timeDiff, sleeptime;
		beforeTime = System.currentTimeMillis();

		while (running) {

			if (!isPaused) {
				updateState();

				if (running) { //updateState() could stop the game so this check is necessary
					renderGame();
					paintScreen();

					timeDiff = System.currentTimeMillis() - beforeTime;
					sleeptime = (1000 / FPS) - (timeDiff / 1000L);

					if (sleeptime <= 0) {
						sleeptime = 10;
					}

					try {
						Thread.sleep(sleeptime);
					} catch (InterruptedException ex) {
						Logger.getLogger(Game.class.getName()).log(
								Level.SEVERE, null, ex);
					}

					beforeTime = System.currentTimeMillis();
				}
			}
		}

	}

	/**
	 * <p>Starts the game.</p>
	 */
	public synchronized void startGame() {
		lblInfo.setVisible(false);
		gameScore = 0;
		running = true;
		isPaused = false;
		worm.reset(Direction.DOWN, 200, 200);
		food = FoodFactory.getFood();
		new Thread(this).start();
	}

	/**
	 * Stops the game.
	 */
	public synchronized void stopGame() {
		running = false;
	}

	/**
	 * <p>Pauses the game.</p>
	 */
	public synchronized void pauseGame() {
		isPaused = true;
		lblInfo.setText("Game Paused!");
		lblInfo.setVisible(true);
	}

	/**
	 * <p>Resumes the game</p>
	 */
	public synchronized void resumeGame() {
		lblInfo.setVisible(false);
		isPaused = false;
	}

	/**
	 * <p>Renders game image to back buffer.</p>
	 */
	private void renderGame() {
		if (backImage == null) {
			backImage = createImage(getWidth(), getHeight());
			backBuffer = backImage.getGraphics();
		}

		backBuffer.clearRect(0, 0, getWidth(), getHeight());

		worm.draw(backBuffer);
		((Drawable) food).draw(backBuffer);
	}

	/**
	 * <p>Paints image to screen.</p>
	 */
	private void paintScreen() {
		Graphics g = pnlGame.getGraphics();

		if (g != null && backImage != null) {
			g.drawImage(backImage, 0, 0, null);
		}

		g.dispose();
	}

	/**
	 * <p>Updates the game state.</p>
	 */
	private void updateState() {
		Rectangle foodpos = ((Drawable) food).getBounds();
		Rectangle wormpos = worm.getBounds();
		if (foodpos.intersects(wormpos)) {
			worm.feed(food);
			food = FoodFactory.getFood();
			gameScore += 10; // kasvattaa pelin tulosta
		}
		worm.setDirection(keylistener.getDirection());
		try {
			worm.update(pnlGame);
		} catch (CollisionException ex) {
			stopGame();
			lblInfo.setText("You lose!");
			lblInfo.setVisible(true);
		}
		updateGameTitle();
	}

	/**
	 * <p>Updates the title of game window.</p>
	 */
	private void updateGameTitle() {
		setTitle(gameTitle + " - Score: " + gameScore);
	}
}
