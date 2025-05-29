package org.groomUniv.meet.meeting.controller;

import lombok.RequiredArgsConstructor;
import org.groomUniv.meet.chat.service.KafkaProducer;
import org.groomUniv.meet.meeting.dto.MeetingChatDto;
import org.groomUniv.meet.meeting.dto.request.ConditionRequest;
import org.groomUniv.meet.meeting.dto.request.CreateMeetingGroupRequest;
import org.groomUniv.meet.meeting.dto.response.SearchMeetingResponse;
import org.groomUniv.meet.meeting.entity.MeetingGroup;
import org.groomUniv.meet.meeting.service.MeetingService;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/meeting")
@RequiredArgsConstructor
public class MeetingController {
    private final KafkaProducer<MeetingChatDto> producer;
    private final MeetingService meetingService;

    @PostMapping("/group")
    public ResponseEntity<?> createMeetingGroup(@RequestBody CreateMeetingGroupRequest request) {
        MeetingGroup group = meetingService.createMeetingGroup(request.getMemberIds(), request.getRegion());


        return ResponseEntity.ok("Meeting group created");
    }

    @PostMapping("/search")
    public ResponseEntity<?> searchMeeting(@RequestBody ConditionRequest conditionRequest) {
        try {
            SearchMeetingResponse response = meetingService.searchMeeting(conditionRequest);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @MessageMapping("/chat")
    public void send(MeetingChatDto dto) {
        MeetingChatDto saved = meetingService.save(dto);
        producer.sendMessage("meeting-chat-topic", saved);
    }

    @GetMapping("/chat/{meetingId}")
    public ResponseEntity<List<MeetingChatDto>> history(@PathVariable Long meetingId) {
        return ResponseEntity.ok(meetingService.loadHistory(meetingId));
    }
}
