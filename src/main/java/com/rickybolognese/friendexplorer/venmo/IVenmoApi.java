package com.rickybolognese.friendexplorer.venmo;

import java.util.Iterator;

interface IVenmoApi {
    VenmoUser getUser(String userId);
    Iterator<VenmoUser> getUserFriends(String userId);
}
