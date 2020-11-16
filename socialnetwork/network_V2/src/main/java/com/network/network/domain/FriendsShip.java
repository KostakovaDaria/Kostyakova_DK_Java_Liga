package com.network.network.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity(name = "FriendsShip")
@Table(name = "friends")
public class FriendsShip implements Serializable {

    @EmbeddedId
    public CustomKey Id;

    @ManyToOne
    @MapsId("UserId")
    private Profile user;

    @ManyToOne
    @MapsId("FriendId")
    private Profile friend;

    private Boolean status;

    public FriendsShip() {}

    public FriendsShip(CustomKey Id, Profile user, Profile friend)
    {
        this.Id = Id;
        this.user = user;
        this.friend = friend;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public CustomKey getId() {
        return Id;
    }

    public void setId(CustomKey id) {
        Id = id;
    }

    public Profile getUser() {
        return user;
    }

    public void setUser(Profile user) {
        this.user = user;
    }

    public Profile getFriend() {
        return friend;
    }

    public void setFriend(Profile friend) {
        this.friend = friend;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FriendsShip that = (FriendsShip) o;
        return user.equals(that.user) &&
                friend.equals(that.friend) &&
                status.equals(that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, friend, status);
    }
}
