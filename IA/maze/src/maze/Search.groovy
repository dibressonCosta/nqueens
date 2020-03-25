package maze
import maze.Node
class Search {
	enum SearchType{
		BF, DF, AStar
	}
	SearchType type
	def Fringe() {}
	List Visited = [[0, 0]]
	def Directions = ["D": [0, 1], "R": [1, 0], "U":[0, -1], "L":[-1, 0]]
	public Boolean isVisited(int x, int y) {
		return Visited.contains([x, y])
	}
	public addVisited(int x, int y) {
		Visited.add([x, y])
	}
	public Node nextCoordinate(NodeMaze No, int x, int y) {
		NodeMaze nextNode = new NodeMaze(No, (No.x + x), (No.y + y))
		if(isVisited(nextNode.x, nextNode.y)) {
			return null
		}

		return nextNode
	}
	public Boolean exploreDF(Maze maze, NodeMaze no, List path) {
		if(isVisited(no.x, no.y) ||path.contains(no) || !maze.isEmpty(no.x, no.y)) {
			return false
		}
		path.add(no)
		addVisited(no.x,no.y)
		if(no.isGoal()) {
			return true
		}
		for (def Dir : Directions) {
			int x = Dir.value[0]
			int y = Dir.value[1]
			NodeMaze node = nextCoordinate(no, x, y);
			if(node != null) {
				//maze.addPoint(no.x, no.y)
				if (exploreDF(maze, node, path)) {
					return true;
				}
			}
		}
		path.remove(path.size() - 1);
		maze.rmvPoint(no.x, no.y)
		return false;
	}
	public List solve(Maze maze, SearchType type) {
		def path = []
		NodeMaze no = new NodeMaze(maze.getOriginX(), maze.getOriginY())
		if(type == "DF") {
			if(exploreDF(maze, no, path)) {
				return path
			}
		}
		return Collections.emptyList()
	}

}
