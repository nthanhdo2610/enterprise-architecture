package cs544.domain;

import lombok.Getter;
import lombok.Setter;
// import org.springframework.data.annotation.Id;
// import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
// @Document
public class Game {
	// @Id
	private String gameId;
	private String leagueName;
    private String teamNameHome;
    private String teamNameVisitor;
	private String status;
    private int goalHome;
    private int goalVisit;
    private String wonTeam;

    public Game(String leagueName, String teamNameHome, String teamNameVisitor, String status) {
        this.leagueName = leagueName;
        this.teamNameHome = teamNameHome;
        this.teamNameVisitor = teamNameVisitor;
        this.status = status;
    }
    @Override
    public String toString() {
        return "{" +
            ", leagueName='" + getLeagueName() + "'" +
            ", teamNameHome='" + getTeamNameHome() + "'" +
            ", teamNameVisitor='" + getTeamNameVisitor() + "'" +
            ", SCORE  (" + getGoalHome() + ":" + getGoalVisit() + ")" +
            " gameId='" + getGameId() + "'" +
            "}";
    }
}