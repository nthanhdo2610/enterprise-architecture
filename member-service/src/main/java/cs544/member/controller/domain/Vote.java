package cs544.member.controller.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Document
@NoArgsConstructor
public class Vote {
    @Id
    private String id;

    private Boolean isLike;

    private String gameId;

    private String memberName;

    public Vote(Boolean like, String memberName) {
        this.isLike = like;
        this.gameId = "";
        this.memberName = memberName;
    }

    @Override
    public String toString() {
        return "{" +
            " like='" + getIsLike() + "'" +
            ", gameID='" + getGameId() + "'" +
            ", memberName='" + getMemberName() + "'" +
            "}";
    }
}
