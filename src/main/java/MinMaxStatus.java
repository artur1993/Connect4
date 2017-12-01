import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Artur on 2017-12-01.
 */
@AllArgsConstructor
@Getter
public class MinMaxStatus {
    Status status;
    Integer sum;
    Integer sumOne;
    Integer sumTwo;
}
