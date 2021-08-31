package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class FriedmanDTO {

    private int keyLength;
    private List<String> substrings;
    private String language;

}
