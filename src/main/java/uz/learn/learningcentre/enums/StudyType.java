package uz.learn.learningcentre.enums;

import lombok.Getter;

@Getter
public enum StudyType {
    GRANT, CONTRACT, NONE;

   /* public static StudyType getByName(String type) {
        if (type.isEmpty()) {
            return NONE;
        }

        for (StudyType value : values()) {
            if (value.name().equalsIgnoreCase(type)) {
                return value;
            }
        }

        return NONE;
    }*/
}
