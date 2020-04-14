package maze
import maze.Search.SearchType
class Main {
	static void main(String[] args) {
		def tom = []
		List path = []
		int larg = 10
		int comp = 10
		final int x = args.length >= 1 ? Integer.parseInt(args[0]) : larg;
		final int y = args.length == 2 ? Integer.parseInt(args[1]) : comp;
		final Maze maze = new Maze(x, y,1,1,(larg*4 - 1),(comp*2 - 1));
		
		SearchType type = SearchType.AStar
		Search busca = new Search(type)
		path = busca.solve(maze, type)
		for(NodeMaze no: path) {
			maze.addPoint(no.x, no.y)
			maze.display()
			sleep(200)
		}
	}
}
