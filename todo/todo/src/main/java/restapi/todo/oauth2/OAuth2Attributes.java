package restapi.todo.oauth2;

import lombok.Getter;
import lombok.Setter;
import restapi.todo.domain.entity.User;

import java.util.Map;

@Setter
@Getter
public class OAuth2Attributes {

    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;

    public OAuth2Attributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
    }

    public OAuth2Attributes() {
    }

    public static OAuth2Attributes of(String registrationId,String userNameAttributeName,Map<String,Object> attributes){
        if (registrationId.equals("kakao")) {
            return ofKakao(userNameAttributeName, attributes);
        }

        return ofGoogle(userNameAttributeName, attributes);
    }

    public static OAuth2Attributes ofKakao(String userNameAttributeName,Map<String,Object> attributes){
        Map<String, Object> kakao_account = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) kakao_account.get("profile");

        return new OAuth2Attributes(attributes,
                    userNameAttributeName,
                (String) profile.get("nickname"),
                (String) kakao_account.get("email"));
    }

    public static OAuth2Attributes ofGoogle(String userNameAttributeName,Map<String,Object> attributes) {

        return new OAuth2Attributes(attributes,
                userNameAttributeName,
                (String) attributes.get("name"),
                (String) attributes.get("email"));
    }

    public User toEntity() {
        return User.createUser(this.email,this.name);
    }
}
