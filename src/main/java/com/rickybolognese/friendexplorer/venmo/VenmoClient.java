package com.rickybolognese.friendexplorer.venmo;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

class VenmoClient implements IVenmoApi {
    private final String accessToken;
    private final String apiUrl;
    private final HttpClient httpClient;

    public VenmoClient(final String accessToken,
            final String apiUrl,
            final HttpClient httpClient) {
        this.accessToken = accessToken;
        this.apiUrl = apiUrl;
        this.httpClient = httpClient;
    }
    
    private HttpRequest buildGetRequest(String path) {
        return new HttpGet(buildUri(path));
    }

    private URI buildUri(String path) {
        try {
            return new URI(apiUrl + path + "?access_token=" + accessToken);
        } catch (URISyntaxException e) {
            throw new VenmoClientException(e);
        }
    }

    public VenmoUser getUser(String userId) throws VenmoUserNotFoundException {
        HttpRequest request = buildGetRequest("/users/" + userId);
        return new VenmoUser();
    }

    public Iterator<VenmoUser> getUserFriends(String userId) {
        throw new UnsupportedOperationException();
    }
}
