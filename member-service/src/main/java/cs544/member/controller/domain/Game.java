package cs544.member.controller.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@Document
public class Game {
    @Id
    private String gameId;
	private String leagueName;
    private String teamNameHome;
    private String teamNameVisitor;
	private String status;
    private int goalHome;
    private int goalVisit;
    private String wonTeam;
    private Integer durationMinutes;
}