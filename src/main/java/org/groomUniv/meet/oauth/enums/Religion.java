package org.groomUniv.meet.oauth.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@AllArgsConstructor
public enum Religion {

    NONE("무교"),Christianity("기독교"),Catholicism("천주교"),Buddhism("불교"), OTHERS("기타");
private final String description;


}
