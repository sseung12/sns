package code.sns.domain;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String email;

    private String password;

    private String name;

    private String profile;

    private String profile_img;

    private LocalDate birth;

    private String userLink;

    @Builder
    public User(String email, String password, String name, String profile, String profile_img, LocalDate birth, String userLink, Gender gender) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.profile = profile;
        this.profile_img = profile_img;
        this.birth = birth;
        this.userLink = userLink;
        this.gender = gender;
    }

    @Enumerated(EnumType.STRING)
    private Gender gender;


    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();


    @OneToMany(mappedBy = "user")
    private List<Follow> follows = new ArrayList<>();
}
