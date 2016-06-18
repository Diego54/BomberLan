package com.uqbar.vainilla;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.security.InvalidParameterException;

import javax.swing.JFrame;


public class DesktopGameLauncher extends JFrame {

	private GamePlayer player;

	// ****************************************************************
	// ** CONSTRUCTORS
	// ****************************************************************
	
	static {
		System.setProperty("sun.java2d.opengl", "true");
	}

	public DesktopGameLauncher(Game game) {
		GamePlayer player = new GamePlayer(game);

		this.setPlayer(player);
		this.add(player);

		this.setResizable(false);
		this.pack();
		this.setTitle(game.getTitle());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.addWindowListener(new WindowListener() {
			@Override
			public void windowActivated(WindowEvent e) {
				DesktopGameLauncher.this.getPlayer().resume();
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				DesktopGameLauncher.this.getPlayer().pause();
			}

			@Override
			public void windowOpened(WindowEvent e) {
			}

			@Override
			public void windowIconified(WindowEvent e) {
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
			}

			@Override
			public void windowClosing(WindowEvent e) {
			}

			@Override
			public void windowClosed(WindowEvent e) {
			}
		});
	}

	// ****************************************************************
	// ** OPERATIONS
	// ****************************************************************

	public void launch() {
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	// ****************************************************************
	// ** ACCESSORS
	// ****************************************************************

	protected GamePlayer getPlayer() {
		return this.player;
	}

	protected void setPlayer(GamePlayer player) {
		this.player = player;
	}
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		if(args.length < 1) {
			throw new InvalidParameterException("Se espera el nombre de la clase por parámetro");
		}
		Game game = (Game)Class.forName(args[0]).newInstance();
		new DesktopGameLauncher(game).launch();
	}
}