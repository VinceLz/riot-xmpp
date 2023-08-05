package com.hawolt.xmpp.event.objects.friends;

import com.hawolt.xmpp.event.objects.friends.status.FailedFriendStatus;

/**
 * Created: 16/04/2022 14:16
 * Author: Twitter @hawolt
 **/

public interface IFriendListener {
    void onFriendRemove(GenericFriend friend);

    void onIncomingFriendRequest(GenericFriend friend);

    void onOutgoingFriendRequest(GenericFriend friend);

    void onIncomingFriendRequestRevoked(GenericFriend friend);

    void onIncomingFriendRequestAccepted(GenericFriend friend);

    void onOutgoingFriendRequestCanceled(GenericFriend friend);

    void onOutgoingFriendRequestAccepted(GenericFriend friend);

    void onFailedInteraction(FailedFriendStatus status);
}
