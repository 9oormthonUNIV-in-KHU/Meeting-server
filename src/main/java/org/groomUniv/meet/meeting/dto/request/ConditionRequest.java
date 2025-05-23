package org.groomUniv.meet.meeting.dto.request;

import lombok.Data;
import lombok.Getter;

import java.util.List;

// 미팅 탐색 전에 사용자가 정하는 최소한을 위한 기준
// 이를 기반으로 미팅 search
@Getter
@Data
public class ConditionRequest {
    // 미팅을 위한 조건들
    private Long minAge;
    private Long maxAge;
    private String location;

    // 멤버들 가져오기 위한 아이디 미팅 참여할 자신들의 ID 입력
    private List<Long> memberIds;
}
