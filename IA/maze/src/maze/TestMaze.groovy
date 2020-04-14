package maze



class TestMaze {
	static List Visited = [[0, 0]]
	static def Directions = ["D": [0, 1], "R": [1, 0], "L":[-1, 0], "U":[0, -1]]
	static Boolean isVisited(int x, int y) {
		return Visited.contains([x, y])
	}
	static addVisited(int x, int y) {
		Visited.add([x, y])
	}
	static Node nextCoordinate(NodeMaze No, int x, int y) {
		NodeMaze nextNode = new NodeMaze(No, (No.x + x), (No.y + y))
		return nextNode
	}
	static Boolean explore(Maze maze, NodeMaze no, List path) {
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
				if (explore(maze, node, path)) {
					return true;
				}
			}
		}
		path.remove(path.size() - 1);
		//maze.rmvPoint(no.x, no.y)
		return false;
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
	static List solve(Maze maze) {
		TreeSet<NodeMaze> fringe = new TreeSet<NodeMaze>(new NodeComp())
		def path = []
		NodeMaze init = new NodeMaze(maze.getOriginX(), maze.getOriginY())
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
			if(current.isGoal(maze.getDestinationX(), maze.getDestinationY())) {
				return findPath(current)
			}
			for (def Dir : Directions) {
				int x = Dir.value[0]
				int y = Dir.value[1]
				NodeMaze node = nextCoordinate(current, x, y);
				fringe.add(node)
				addVisited(current.x, current.y)
				node.setClose(true)
			}
			
		}
		return Collections.emptyList()
	}
	static void main(String[] args) {
		def tom = []
		TreeSet<NodeMaze> fringe = new TreeSet<NodeMaze>(new NodeComp())
		List path = []
		final int x = args.length >= 1 ? Integer.parseInt(args[0]) : 15;
		final int y = args.length == 2 ? Integer.parseInt(args[1]) : 15;
		final Maze maze = new Maze(x, y,1,1,59,29);
		path = solve(maze)
		println path
		for(NodeMaze no: path) {
			maze.addPoint(no.x, no.y)
			maze.display()
			sleep(200)
		}
	}
}
