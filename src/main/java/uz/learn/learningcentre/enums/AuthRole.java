package uz.learn.learningcentre.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum AuthRole {

    SUPER_ADMIN(10),
    ADMIN(9),
    MANAGER(8),
    TEACHER(5),
    ACCOUNTANT(5),
    ASSISTANT(5),
    RECEPTIONIST(5);

    private final int degree;

    public static boolean isAuthRole(AuthRole role) {
        return Arrays.stream(values()).anyMatch(authRole -> authRole.name().equals(String.valueOf(role)));
    }


    // This method returns the list of names, if the degree of that names smaller than input role
    public List<String> getByDegreeBelow(AuthRole role) {

        List<String> belowRoles = new ArrayList<>(Collections.emptyList());

        for (AuthRole authRole : stream()) {
            if (authRole.getDegree() < role.getDegree()) belowRoles.add(authRole.toString());
        }

        return belowRoles;
    }

    public static List<AuthRole> stream() {
        return Stream.of(AuthRole.values()).collect(Collectors.toList());
    }

}


