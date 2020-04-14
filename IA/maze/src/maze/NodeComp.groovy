package maze

class NodeComp implements Comparator<NodeMaze> {

	@Override
	public int compare(NodeMaze o1, NodeMaze o2) {
		if(o1.score() > o2.score()) {
			return 1
		}
		if(o2.score() > o1.score()) {
			return -1
		}
		if(o1.score() == o2.score())
			return -1
		return 0;
	}
}
