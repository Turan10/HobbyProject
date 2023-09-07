package dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import model.Hobby;
@Getter
@Setter
@NoArgsConstructor
@ToString

public class HobbyWithCount {

    private Hobby hobby;
    private long count;

    public HobbyWithCount(Hobby hobby, long count) {
        this.hobby = hobby;
        this.count = count;
    }
}
