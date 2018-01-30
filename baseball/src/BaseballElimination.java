import edu.princeton.cs.algs4.FlowEdge;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.FordFulkerson;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a sports division and determines which teams are mathematically eliminated
 */
public class BaseballElimination {

    private final Map<String, Team> teams = new HashMap<>();
    private final Map<Integer, Team> ids = new HashMap<>();
    private final int[][] divisionRemains;

    /**
     * Create a baseball division from given filename
     *
     * @param filename in specified format
     */
    public BaseballElimination(String filename) {
        In in = new In(filename);
        int teamNumber = in.readInt();
        int teamIndex = 0;
        divisionRemains = new int[teamNumber][teamNumber];

        while (!in.isEmpty()) {
            String teamName = in.readString();
            int win = in.readInt();
            int lose = in.readInt();
            int remain = in.readInt();
            teams.put(teamName, new Team(teamIndex, teamName, win, lose, remain));
            ids.put(teamIndex, new Team(teamIndex, teamName, win, lose, remain));

            for (int i = 0; i < teamNumber; i++) {
                divisionRemains[teamIndex][i] = in.readInt();
            }
            teamIndex++;
        }
    }

    /**
     * Get number of teams
     *
     * @return number of teams
     */
    public int numberOfTeams() {
        return teams.size();
    }

    /**
     * Get all teams
     *
     * @return all teams
     */
    public Iterable<String> teams() {
        return teams.keySet();
    }

    /**
     * Number of wins for given team
     *
     * @param team team's name
     * @return number of wins
     */
    public int wins(String team) {
        validateTeam(team);
        return teams.get(team).win;
    }

    /**
     * Number of losses for given team
     *
     * @param team team's name
     * @return number of losses
     */
    public int losses(String team) {
        validateTeam(team);
        return teams.get(team).lose;
    }

    /**
     * Number of remaining games for given team
     *
     * @param team team's name
     * @return number of remaining games
     */
    public int remaining(String team) {
        validateTeam(team);
        return teams.get(team).remain;
    }

    /**
     * Number of remaining games between team1 and team2
     *
     * @param team1 team's name
     * @param team2 team's name
     * @return number of remaining games
     */
    public int against(String team1, String team2) {
        validateTeam(team1);
        validateTeam(team2);
        return divisionRemains[teams.get(team1).id][teams.get(team2).id];
    }

    /**
     * Is given team eliminated?
     *
     * @param team team's name
     * @return true if given team is eliminated
     */
    public boolean isEliminated(String team) {
        validateTeam(team);
        return isTrivialEliminated(team) || isNonTrivialEliminated(team);
    }

    private boolean isNonTrivialEliminated(String team) {
        return certificateOfElimination(team) != null;
    }

    private FordFulkerson getFordFulkerson(String team) {
        int teamId = teams.get(team).id;
        int vertexGameNumber = getVertexGameNumber();
        int vertexNumber = getVertexNumber();
        FlowNetwork network = new FlowNetwork(vertexNumber);

        int vertexGameIndex = 1;
        for (int i = 0; i < numberOfTeams(); i++) {
            if (i != teamId) {
                boolean hasAnyRemain = false;
                for (int j = i + 1; j < numberOfTeams(); j++) {
                    if (j != teamId && divisionRemains[i][j] != 0) {

                        FlowEdge edgeFromSToGame = new FlowEdge(0, vertexGameIndex, divisionRemains[i][j]);
                        FlowEdge edgeFromGameToTeam1 = new FlowEdge(vertexGameIndex, 1 + vertexGameNumber + i,
                                Double.POSITIVE_INFINITY);

                        FlowEdge edgeFromGameToTeam2 = new FlowEdge(vertexGameIndex, 1 + vertexGameNumber + j,
                                Double.POSITIVE_INFINITY);

                        network.addEdge(edgeFromSToGame);
                        network.addEdge(edgeFromGameToTeam1);
                        network.addEdge(edgeFromGameToTeam2);
                        vertexGameIndex++;
                        hasAnyRemain = true;
                    }
                }
                if (hasAnyRemain && wins(team) + remaining(team) > ids.get(i).win) {
                    FlowEdge edgeFromTeam1ToT = new FlowEdge(vertexGameNumber + i + 1, vertexNumber - 1,
                            wins(team) + remaining(team) - ids.get(i).win);
                    network.addEdge(edgeFromTeam1ToT);
                }
            }
        }

       // System.out.println(network.toString());

        return new FordFulkerson(network, 0, vertexNumber - 1);
    }

    /**
     * Contains of vertex S + number of games + number of teams include team x + vertex T
     * @return number of all vertexes in graph
     */
    private int getVertexNumber() {
        return 1 + getVertexGameNumber() + numberOfTeams() + 1;
    }

    /**
     * Contains of triangle without diagonal and without team x games
     * @return number of used game vertexes
     */
    private int getVertexGameNumber() {
        return numberOfTeams() * (numberOfTeams() - 1) / 2 - (numberOfTeams() - 1);
    }

    private boolean isTrivialEliminated(String team) {
        int teamMaximumPoints = teams.get(team).win + teams.get(team).remain;
        for (Map.Entry<String, Team> entry : teams.entrySet()) {
            if (entry.getValue().win > teamMaximumPoints) {
                return true;
            }
        }
        return false;
    }

    /**
     * Subset R of teams that eliminates given team; null if not eliminated
     *
     * @param team team's name
     * @return subset of teams that eliminates given team
     */
    public Iterable<String> certificateOfElimination(String team) {
        validateTeam(team);
        return isTrivialEliminated(team) ? getTrivialElimination(team) : getNonTrivialElimination(team);

    }

    private Iterable<String> getTrivialElimination(String team) {
        int teamMaximumPoints = teams.get(team).win + teams.get(team).remain;
        Queue<String> r = new Queue<>();
        for (Map.Entry<String, Team> entry : teams.entrySet()) {
            if (entry.getValue().win > teamMaximumPoints) {
                r.enqueue(entry.getKey());
            }
        }
        return r.isEmpty() ? null : r;
    }

    private Iterable<String> getNonTrivialElimination(String team) {
        FordFulkerson algorithm = getFordFulkerson(team);
        Queue<String> r = new Queue<>();
        for (int i = 0; i < numberOfTeams(); i++) {
            if (!team.equals(ids.get(i).name) && algorithm.inCut(1 + getVertexGameNumber() + i)) {
                r.enqueue(ids.get(i).name);
            }
        }

        return r.isEmpty() ? null : r;
    }

    private void validateTeam(String team) {
        if (!teams.containsKey(team)) {
            throw new IllegalArgumentException("team " + team + " is invalid");
        }
    }

    public static void main(String[] args) {
        BaseballElimination division = new BaseballElimination("./baseball/data/teams4.txt");
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

    private static class Team {
        int id;
        String name;
        int win;
        int lose;
        int remain;

        public Team(int id, String name, int win, int lose, int remain) {
            this.id = id;
            this.name = name;
            this.win = win;
            this.lose = lose;
            this.remain = remain;
        }
    }
}
