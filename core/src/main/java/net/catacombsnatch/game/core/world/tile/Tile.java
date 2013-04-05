package net.catacombsnatch.game.core.world.tile;

import net.catacombsnatch.game.core.entity.Entity;
import net.catacombsnatch.game.core.entity.components.Physics;
import net.catacombsnatch.game.core.screen.Renderable;
import net.catacombsnatch.game.core.screen.Tickable;
import net.catacombsnatch.game.core.world.level.Level;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public abstract class Tile implements Renderable, Tickable {
	public static final int HEIGHT = 32;
	public static final int WIDTH = 32;

	protected Level level;
	protected Sprite sprite;
	protected Color minimapColor;

	protected Rectangle bb;

	
	protected Tile(Color color) {
		minimapColor = color;
	}
	
	public void init(Level level, int x, int y) {
		this.level = level;
		this.bb = new Rectangle( x, y, HEIGHT, WIDTH );
	}

	/** @return The {@link Level} this tile is placed in */
	public Level getLevel() {
		return level;
	}

	/** @return The {@link Rectangle} of the tile boundaries. */
	public Rectangle getBounds() {
		return bb;
	}
	
	/** @return The {@link Color} shown on the minimap. */
	public Color getMinimapColor() {
		return minimapColor;
	}

	/**
	 * Returns true if the entity can pass the tile.
	 * 
	 * @param entity The entity to check
	 * @return True if the entity can pass, otherwise false
	 */
	public boolean canPass( Entity entity ) {
		Physics p = entity.getComponent(Physics.class);
		return p != null ? p.intersects( getBounds() ) : true;
	}
}
