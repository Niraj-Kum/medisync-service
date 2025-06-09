package com.medisync.medisync_service.config;

import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.*;

@AllArgsConstructor
public class KeycloakJwtAuthenticationConverter implements Converter<Jwt, Collection<GrantedAuthority>>
{
    @Override
    public Collection<GrantedAuthority> convert(Jwt source)
    {
        List<GrantedAuthority> authorities = new ArrayList<>();
        String scope = source.getClaimAsString("scope");
        if(StringUtils.isNotBlank(scope)) {
            authorities.addAll(Arrays.stream(scope.split(" ")).map(s -> new SimpleGrantedAuthority("SCOPE_" + s)).toList());
        }
        extractRealmRoles(source ,authorities);
        return authorities;
    }

    private void extractRealmRoles(Jwt jwt, List<GrantedAuthority> authorities)
    {
        Map<String, Object> realmAccess = new HashMap<>(jwt.getClaim("realm_access"));
        if (realmAccess.containsKey("roles")) {
            Collection<String> roles = (Collection<String>) realmAccess.get("roles");
            authorities.addAll(roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role)).toList());
        }
    }
}
