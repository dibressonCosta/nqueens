package IA;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Maze {

	/** Numero de quadrados do labirinto */
	private final int x;
	/** Numero de quadrados do labirinto */
	private final int y;
	/** Largura em pixels */
	private int ncols;
	/** Altura em pixels */
	private int nrows;
	/** Mapa de quadrados do labirinto */
	private final int[][] map;
	/** Representacao em ASCII do labirinto */
	private ArrayList<String> rows;
	/** Pontos adicionados pelo usuário representados por * */
	private final ArrayList<Point> points;
	/** Pontos adicionados pelo usuário representados por ? */
	private final ArrayList<Point> options;
	
	/** Destino */
	private int destinationX;
	/** Destino */
	private int destinationY;
	/** Origem */
	private int originX;
	/** Origem */
	private int originY;
	
	public Maze(final int x, final int y, final int originX, final int originY, final int destinationX, final int destinationY) {
		this.x = x;
		this.y = y;
		points = new ArrayList<>();
		options = new ArrayList<>();
		map = new int[this.x][this.y];
		generateMaze(0, 0);
		populate();
		this.originX = originX;
		this.originY = originY;
		this.destinationX = destinationX;
		this.destinationY = destinationY;
	}
 
	public Maze(final Maze m) {
		this.x = m.x;
		this.y = m.y;
		this.ncols = m.ncols;
		this.nrows = m.nrows;
		this.map = m.map;
		this.rows = (ArrayList<String>) m.rows.clone();
		this.points = new ArrayList<>();
		this.options = new ArrayList<>();
		this.destinationX = m.destinationX;
		this.destinationY = m.destinationY;
		this.originX = m.originX;
		this.originY = m.originY;
	}
	
	public int getNRows() {
		return nrows;
	}
	
	public int getNCols() {
		return ncols;
	}
	
	public boolean isEmpty(final int x, final int y) {
		if (x>getNCols())
			return false;
		if (y>getNRows())
			return false;
		if (x<0)
			return false;
		if (y<0)
			return false;
		final char c = rows.get(y).charAt(x);
		return c ==' ';
	}
	
	public void addPoint(final int x, final int y) {
		points.add(new Point(x,y));
		final String newRow = rows.get(y).substring(0, x) + "*" + rows.get(y).substring(x+1,getNCols());
		rows.add(y, newRow);
		rows.remove(y+1);
	}
	public void addOption(final int x, final int y) {
		options.add(new Point(x,y));
		final String newRow = rows.get(y).substring(0, x) + "?" + rows.get(y).substring(x+1,getNCols());
		rows.add(y, newRow);
		rows.remove(y+1);
	}
	
	public void display() {
		for (final String s : rows)
			System.out.println(s);
	}
	
	private void populate() {
		rows = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < y; i++) {
			// draw the north edge
			for (int j = 0; j < x; j++) {
				sb.append((map[j][i] & 1) == 0 ? "+---" : "+   ");
			}
			sb.append("+");
			rows.add(sb.toString());
			sb = new StringBuilder();
			// draw the west edge
			for (int j = 0; j < x; j++) {
				sb.append((map[j][i] & 8) == 0 ? "|   " : "    ");
			}
			sb.append("|");
			rows.add(sb.toString());
			sb = new StringBuilder();
		}
		// draw the bottom line
		for (int j = 0; j < x; j++) {
			sb.append("+---");
		}
		sb.append("+");
		rows.add(sb.toString());
		
		nrows = rows.size();
		ncols = rows.get(0).length();
	}
 
	int r = 0;
	private void generateMaze(final int cx, final int cy) {
		final DIR[] dirs = DIR.values();
		Collections.shuffle(Arrays.asList(dirs));
		r=r+1;
		for (final DIR dir : dirs) {
			final int nx = cx + dir.dx;
			final int ny = cy + dir.dy;
			if (between(nx, x) && between(ny, y)
					&& (map[nx][ny] == 0)) {
				map[cx][cy] |= dir.bit;
				map[nx][ny] |= dir.opposite.bit;
				generateMaze(nx, ny);
			}
		}
	}
 
	private static boolean between(final int v, final int upper) {
		return (v >= 0) && (v < upper);
	}
 
	private enum DIR {
		N(1, 0, -1), S(2, 0, 1), E(4, 1, 0), W(8, -1, 0);
		private final int bit;
		private final int dx;
		private final int dy;
		private DIR opposite;
 
		// use the static initializer to resolve forward references
		static {
			N.opposite = S;
			S.opposite = N;
			E.opposite = W;
			W.opposite = E;
		}
 
		private DIR(final int bit, final int dx, final int dy) {
			this.bit = bit;
			this.dx = dx;
			this.dy = dy;
		}
	};
 
	public int getDestinationX() {
		return destinationX;
	}

	public void setDestinationX(final int destinationX) {
		this.destinationX = destinationX;
	}

	public int getDestinationY() {
		return destinationY;
	}

	public void setDestinationY(final int destinationY) {
		this.destinationY = destinationY;
	}

	public int getOriginX() {
		return originX;
	}

	public void setOriginX(final int originX) {
		this.originX = originX;
	}

	public int getOriginY() {
		return originY;
	}

	public void setOriginY(final int originY) {
		this.originY = originY;
	}
	
	public static void main(final String[] args) {
		final int x = args.length >= 1 ? Integer.parseInt(args[0]) : 8;
		final int y = args.length == 2 ? Integer.parseInt(args[1]) : 8;
		final Maze maze = new Maze(x, y,1,1,31,15);
		//maze.display();
		
		maze.addPoint(31,15);
		maze.addPoint(1,1);
		maze.display();
		
	}

}
