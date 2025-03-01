package br.com.yandeara.voting.web.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum VoteEnum {
    YES("Sim"), NO("Não");

    private String value;

    VoteEnum(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static VoteEnum fromString(String value) {
        for (VoteEnum voteEnum : VoteEnum.values()) {
            if (voteEnum.getValue().equalsIgnoreCase(value)) {
                return voteEnum;
            }
        }
        throw new IllegalArgumentException("Invalid value for Vote: " + value);
    }
}
