import edu.princeton.cs.algs4.StdOut;

/**
 * Represents a sports division and determines which teams are mathematically eliminated
 */
public class BaseballElimination {

    /**
     * Create a baseball division from given filename
     *
     * @param filename in specified format
     */
    public BaseballElimination(String filename) {

    }

    /**
     * Get number of teams
     *
     * @return number of teams
     */
    public int numberOfTeams() {
        return 0;
    }

    /**
     * Get all teams
     *
     * @return all teams
     */
    public Iterable<String> teams() {
        return null;
    }

    /**
     * Number of wins for given team
     *
     * @param team team's name
     * @return number of wins
     */
    public int wins(String team) {
        return 0;
    }

    /**
     * Number of losses for given team
     *
     * @param team team's name
     * @return number of losses
     */
    public int losses(String team) {
        return 0;
    }

    /**
     * Number of remaining games for given team
     *
     * @param team team's name
     * @return number of remaining games
     */
    public int remaining(String team) {
        return 0;
    }

    /**
     * Number of remaining games between team1 and team2
     *
     * @param team1 team's name
     * @param team2 team's name
     * @return number of remaining games
     */
    public int against(String team1, String team2) {
        return 0;
    }

    /**
     * Is given team eliminated?
     *
     * @param team team's name
     * @return true if given team is eliminated
     */
    public boolean isEliminated(String team) {
        return false;
    }

    /**
     * Subset R of teams that eliminates given team; null if not eliminated
     *
     * @param team team's name
     * @return subset of teams that eliminates given team
     */
    public Iterable<String> certificateOfElimination(String team) {
        return null;
    }

    public static void main(String[] args) {
        BaseballElimination division = new BaseballElimination(args[0]);
        for (String team : division.teams()) {
            if (division.isEliminated(team)) {
                StdOut.print(team + " is eliminated by the subset R = { ");
                for (String t : division.certificateOfElimination(team)) {
                    StdOut.print(t + " ");
                }
                StdOut.println("}");
            } else {
                StdOut.println(team + " is not eliminated");
            }
        }
    }
}
