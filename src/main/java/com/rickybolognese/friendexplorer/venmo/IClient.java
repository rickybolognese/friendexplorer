package com.rickybolognese.friendexplorer.venmo;

import java.util.Iterator;

interface IClient {
    IUser getUser(String userId);
    Iterator<IUser> getUserFriends(String userId);
}
