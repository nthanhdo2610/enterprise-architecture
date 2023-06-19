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
public class Comment {
    @Id
    private String id;

    private String msg;

    private String gameId;

    private String memberName;

    public Comment(String message, String memberName) {
        this.msg = message;
        this.gameId = "";
        this.memberName = memberName;
    }

    @Override
    public String toString() {
        return "{" +
            " message='" + getMsg() + "'" +
            ", gameID='" + getGameId() + "'" +
            ", memberName='" + getMemberName() + "'" +
            "}";
    }

    
}
