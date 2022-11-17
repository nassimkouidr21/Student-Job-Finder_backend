package studentjobfinderAPI.studentjobfinder.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("Questions")
public class Question {

    @Id
    private Integer id;
    private String question;
    private String answer;
}
