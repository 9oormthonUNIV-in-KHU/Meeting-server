package org.groomUniv.meet.meeting.dto.request;

import lombok.Data;

import java.util.List;
@Data
//미팅 그룹을 만들기 위한 객체
public class MakeGroup {

    private List<Long> memberIds;
}
