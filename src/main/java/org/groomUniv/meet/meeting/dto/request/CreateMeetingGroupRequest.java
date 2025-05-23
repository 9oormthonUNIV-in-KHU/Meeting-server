package org.groomUniv.meet.meeting.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class CreateMeetingGroupRequest {

private List<Long> memberIds;
private String region;

}

