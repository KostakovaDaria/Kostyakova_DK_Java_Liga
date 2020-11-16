package com.network.network.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class CustomKey implements Serializable {

    @Column(name = "user_id")
    @org.hibernate.annotations.Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID UserId;

    @Column(name = "friend_id")
    @org.hibernate.annotations.Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID FriendId;

    public CustomKey() {}

    public CustomKey(UUID UserId, UUID FriendId)
    {
        this.UserId = UserId;
        this.FriendId = FriendId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomKey customKey = (CustomKey) o;
        return Objects.equals(UserId, customKey.UserId) &&
                Objects.equals(FriendId, customKey.FriendId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(UserId, FriendId);
    }
}
