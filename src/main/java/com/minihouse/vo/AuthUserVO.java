package com.minihouse.vo;

import java.util.Objects;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor
@Getter
public class AuthUserVO {

    private final Long id;
    private final String name;
    private final String email;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AuthUserVO that = (AuthUserVO) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name)
            && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email);
    }
}
