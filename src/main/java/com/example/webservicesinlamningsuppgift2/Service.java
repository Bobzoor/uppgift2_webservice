package com.example.webservicesinlamningsuppgift2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;

@org.springframework.stereotype.Service
public class Service {

    private final OAuth2AuthorizedClientService oauthService;
    @Autowired
    public Service(OAuth2AuthorizedClientService oauthService) {
        this.oauthService = oauthService;
    }

    public Map<String, Object> user(OAuth2User principal, Authentication auth) {
        var oauthToken = (OAuth2AuthenticationToken) auth;
        var client = oauthService.loadAuthorizedClient(
                oauthToken.getAuthorizedClientRegistrationId(),
                oauthToken.getName()
        );
        System.out.println(client.getAccessToken().getTokenValue());
        return principal.getAttributes();
    }
}
