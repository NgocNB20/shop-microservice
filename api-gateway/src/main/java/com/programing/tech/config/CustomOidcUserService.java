//package com.programing.tech.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.InternalAuthenticationServiceException;
//import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
//import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
//import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
//import org.springframework.security.oauth2.core.oidc.user.OidcUser;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//
//@Service
//public class CustomOidcUserService extends OidcUserService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
//        OidcUser oidcUser = super.loadUser(userRequest);
//
//        try {
//            return processOidcUser(userRequest, oidcUser);
//        } catch (Exception ex) {
//            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
//        }
//    }
//
//    private OidcUser processOidcUser(OidcUserRequest userRequest, OidcUser oidcUser) {
//        OAuth2User
//        GoogleUserInfo googleUserInfo = new GoogleUserInfo(oidcUser.getAttributes());
//
//        // see what other data from userRequest or oidcUser you need
//
//        Optional<User> userOptional = userRepository.findByEmail(googleUserInfo.getEmail());
//        if (!userOptional.isPresent()) {
//            User user = new User();
//            user.setEmail(googleUserInfo.getEmail());
//            user.setName(googleUserInfo.getName());
//
//            // set other needed data
//
//            userRepository.save(user);
//        }
//
//        return oidcUser;
//    }
//}
