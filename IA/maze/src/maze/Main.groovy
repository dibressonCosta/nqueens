package maze
import maze.Search.SearchType
class Main {
	static void main(String[] args) {
		def tom = []
		List path = []
		final int x = args.length >= 1 ? Integer.parseInt(args[0]) : 8;
		final int y = args.length == 2 ? Integer.parseInt(args[1]) : 8;
		final Maze maze = new Maze(x, y,1,1,31,15);
				
		SearchType type = SearchType.values()[0]
		Search busca = new Search(type)
		path = busca.solve(maze, type)
		for(NodeMaze no: path) {
			maze.addPoint(no.x, no.y)
			maze.display()
			sleep(200)
		}
		
		//maze.addOption(1, 1)
	}
}
