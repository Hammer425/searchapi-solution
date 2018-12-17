package com.eu.search.utils;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

public class URIUtils {
	
	private URIUtils() {}

    public static boolean isSameDomain(String sourceUrl, String scannedUrl) {
        String sourceDomainName = getDomainName(sourceUrl);
        String scannedDomainName = getDomainName(scannedUrl);

        if (sourceDomainName.equals(scannedDomainName)) {
            return true;
        }

        if (scannedDomainName.endsWith(sourceDomainName)) {
            return true;
        }

        return false;
    }

    public static boolean isAbsoluteUrl(String urlValue) {
        URI uri = null;
        try {
            uri = getUri(urlValue);
        } catch (MalformedURLException | URISyntaxException e) {
            return false;
        }
        return uri.isAbsolute();
    }

    public static String getDomainName(String urlValue) {
        URI uri = null;
        try {
            uri = getUri(urlValue);
        } catch (MalformedURLException | URISyntaxException e) {
            return "";
        }

        Objects.requireNonNull(uri);
        String domain = uri.getHost();

        if (Objects.isNull(domain)) {
            return "";
        }
        return domain.startsWith("www.") ? domain.substring(4) : domain;
    }

    public static URI getUri(String uriValue) throws MalformedURLException, URISyntaxException {
        URL url = new URL(uriValue);
        return new URI(url.getProtocol(), url.getHost(), url.getPath(), url.getQuery(), null);
    }

}
