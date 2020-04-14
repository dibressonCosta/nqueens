package maze
import maze.Node
class Search {
	enum SearchType{BF, DF, AStar}
	SearchType type
	public Search(SearchType type) {
		this.type = type
	}
	List Visited = [[0, 0]]
	def Directions = ["D": [0, 1], "R": [1, 0], "U":[0, -1], "L":[-1, 0]]
	public Boolean isVisited(int x, int y) {
		return Visited.contains([x, y])
	}
	public addVisited(int x, int y) {
		Visited.add([x, y])
	}
	public Node nextCoordinate(NodeMaze No, int x, int y, Maze maze) {
		NodeMaze nextNode = new NodeMaze(No, (No.x + x), (No.y + y), maze)
		return nextNode
	}
	static List findPath(NodeMaze goal) {
		List path = []
		NodeMaze node = goal
		while(	node!=null	) {
			path.add(node)
			node = node.parent
		}
		return path
	}
	public Boolean searchDF(Maze maze, NodeMaze init, List path) {
		if(isVisited(init.x, init.y) || path.contains(init) || !maze.isEmpty(init.x, init.y)) {
			return false
		}
		path.add(init)
		addVisited(init.x,init.y)
		if(init.isGoal()) {
			return true
		}
		for (def Dir : Directions) {
			int x = Dir.value[0]
			int y = Dir.value[1]
			NodeMaze node = nextCoordinate(init, x, y, maze);
			if (searchDF(maze, node, path)) {
				return true;
			}
		}
		path.remove(path.size() - 1);
		return false;
	}

	public List solve(Maze maze, SearchType type) {
		def path = []
		NodeMaze init = new NodeMaze(maze.getOriginX(), maze.getOriginY(), maze)
		if(type == SearchType.DF) {
			if(searchDF(maze, init, path)) {
				return path
			}
		}else if(type == SearchType.BF) {
			LinkedList fringe = []
			fringe.add(init)
			while(!fringe.isEmpty() ) {
				NodeMaze current = fringe.remove()
				if( !maze.isValid(current.x, current.y) || isVisited(current.x, current.y)) {
					continue
				}
				if(!maze.isEmpty(current.x, current.y)) {
					addVisited(current.x, current.y)
					continue
				}
				if(current.isGoal()) {
					return findPath(current)
				}
				for (def Dir : Directions) {
					int x = Dir.value[0]
					int y = Dir.value[1]
					NodeMaze node = nextCoordinate(current, x, y, maze);
					fringe.add(node)
					addVisited(current.x, current.y)
					node.setClose(true)
				}
			}
		}else {
			TreeSet<NodeMaze> fringe = new TreeSet<NodeMaze>(new NodeComp())
			fringe.add(init)
			while(!fringe.isEmpty()) {
				NodeMaze current = fringe.pollFirst()
				if( !maze.isValid(current.x, current.y) || isVisited(current.x, current.y)) {
					continue
				}
				if(!maze.isEmpty(current.x, current.y)) {
					addVisited(current.x, current.y)
					continue
				}
				if(current.isGoal()) {
					return findPath(current)
				}
				for (def Dir : Directions) {
					int x = Dir.value[0]
					int y = Dir.value[1]
					NodeMaze node = nextCoordinate(current, x, y, maze);
					fringe.add(node)
					addVisited(current.x, current.y)
					node.setClose(true)
				}
			}
		}
		return Collections.emptyList()
	}
}
