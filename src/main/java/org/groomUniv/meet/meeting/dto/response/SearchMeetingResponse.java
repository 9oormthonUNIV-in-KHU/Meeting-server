package org.groomUniv.meet.meeting.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class SearchMeetingResponse {
    private Long groupId;
    private String region;
    private int averageAge;
    private Long averageHeight;
    private String dominantMajor;
}
