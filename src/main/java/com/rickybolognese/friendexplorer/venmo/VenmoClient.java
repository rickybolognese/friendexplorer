package com.rickybolognese.friendexplorer.venmo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;

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
    
    private HttpUriRequest buildGetRequest(String path) {
        return new HttpGet(buildUri(path));
    }

    private URI buildUri(String path) {
        try {
            return new URI(apiUrl + path + "?access_token=" + accessToken);
        } catch (URISyntaxException e) {
            throw new ClientException(e);
        }
    }

    public VenmoUser getUser(String userId) throws ClientException, UserNotFoundException {
        final HttpUriRequest request = buildGetRequest("/users/" + userId);

        try {
            final HttpResponse response = httpClient.execute(request);
            final HttpEntity entity = response.getEntity();
            final InputStream content = entity.getContent();
            final StatusLine statusLine = response.getStatusLine();

            if (HttpStatus.SC_BAD_REQUEST == statusLine.getStatusCode()) {
                throw new ClientException(statusLine.getReasonPhrase());
            }

            try (final ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {
                final byte[] bytes = new byte[16384];
                int nRead = 0;

                while (-1 != (nRead = content.read(bytes, 0, bytes.length))) {
                    buffer.write(bytes, 0, nRead);
                }

                buffer.flush();
            }

            return new VenmoUser();
        } catch (final ClientProtocolException e) {
            throw new ClientException(e);
        } catch (final IOException e) {
            throw new ClientException(e);
        }
    }

    public Iterator<VenmoUser> getUserFriends(String userId) throws UserNotFoundException {
        throw new UnsupportedOperationException();
    }
}
