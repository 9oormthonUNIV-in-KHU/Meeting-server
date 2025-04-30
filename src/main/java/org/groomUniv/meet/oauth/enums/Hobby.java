package org.groomUniv.meet.oauth.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Hobby {

EXCERCISE("운동"), LISTENMUSIC("음악 듣기"),READBOOK("독서"), GAME("게임"), COOK("요리")
        ,TRIP("여행"), DRAWING("그림"), TAKEPICTURE("사진찍기");
private final String description;
}
