package maze



class TestMaze {
	static def u = 0
	static List Visited = [[0, 0]]
	static def Directions = ["D": [0, 1], "R": [1, 0], "U":[0, -1], "L":[-1, 0]]
	static Boolean isVisited(int x, int y) {
		return Visited.contains([x, y])
	}
	static addVisited(int x, int y) {
		Visited.add([x, y])
	}
	static Node nextCoordinate(NodeMaze No, int x, int y) {
		NodeMaze nextNode = new NodeMaze(No, (No.x + x), (No.y + y))
		if(isVisited(nextNode.x, nextNode.y)) {
			return null
		}

		return nextNode
	}
	static Boolean explore(Maze maze, NodeMaze no, List path) {
		//println no.close
		//maze.display()
		//sleep(20)

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
		maze.rmvPoint(no.x, no.y)
		return false;
	}
	static List solve(Maze maze) {
		def path = []
		NodeMaze no = new NodeMaze(maze.getOriginX(), maze.getOriginY())
		if(explore(maze, no, path)) {
			return path
		}
		return Collections.emptyList()
	}
	static void main(String[] args) {
		def tom = []
		List path = []
		final int x = args.length >= 1 ? Integer.parseInt(args[0]) : 8;
		final int y = args.length == 2 ? Integer.parseInt(args[1]) : 8;
		final Maze maze = new Maze(x, y,1,1,31,15);

		//NodeMaze goal = new NodeMaze(31,15)
		path = solve(maze)
		for(NodeMaze no: path) {
			maze.addPoint(no.x, no.y)
			maze.display()
			sleep(100)
		}
		
		//maze.addOption(1, 1)
	}
}
