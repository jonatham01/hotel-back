package disabled;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomOccupancyPK implements Serializable {

    @Column(name="occupancyR_day")
    private LocalDate occupancyDay;

    @Column(name="occupancyR_room")
    private Integer occupancyRoom;
}
