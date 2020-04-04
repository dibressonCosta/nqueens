package queens
import groovy.time.TimeCategory
import groovy.time.TimeDuration

class Main {
	public static void main(String[] args) throws Exception{
		NQueen sa;
		int n = 8
		int tollerence = 0;
		println "Solution to "+n+" queens using Simulated Annealing: "
		sa = new SimulatedAnnealing(8,tollerence,1000);
		Date start = new Date()
		sa.solve();
		Date end = new Date()
		TimeDuration td = TimeCategory.minus( end, start )
		println "time: "+td
		sa.show();
		HillClimbing hb = new HillClimbing(8);
		hb.solveHill()
	}
}
