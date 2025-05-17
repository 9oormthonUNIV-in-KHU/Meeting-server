package org.groomUniv.meet.oauth.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum BodyType {
    THIN("마름"),NORMAL("보통"),FAT("통통"),MUSCLE("근육질");

    private final String description;

}
