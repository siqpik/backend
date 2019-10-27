package com.example.siqpik.dto;
import com.example.siqpik.domain.Notification;
import com.example.siqpik.domain.Photo;
import com.example.siqpik.domain.Request;
import com.example.siqpik.domain.User;

import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class ProfileDto {

    private String name;
    private List<PhotoDto> pics;
    private Integer admirers;
    private Integer admiring;
    private String profilePicUrl;
    private User loggedUser;    //User that is log in
    private User user;      //The user of whom we want to see the profile

    public ProfileDto(User loggedUser, User user) {
        this.loggedUser = loggedUser;
        this.user = user;
        name = user.getUserName();
        pics = user.getPhotos()
                .stream()
                .sorted(Comparator.comparing(Photo::getDate)
                        .reversed())
                .map(PhotoDto::new)
                .collect(toList());
        admirers = user.getAdmirers().size();
        admiring = user.getAdmirings().size();
        profilePicUrl = user.getProfilePicUrl();
    }

    public String getName() {
        return name;
    }

    public List<PhotoDto> getPics() {
        return pics;
    }

    public Integer getAdmirers() {
        return admirers;
    }

    public Integer getAdmiring() {
        return admiring;
    }

    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    public Boolean getIsActualUser() {
        return loggedUser.getId().equals(user.getId());
    }

    public Boolean getIsAdmiring() {
        return getIsActualUser()
                ? null
                : loggedUser.getAdmirings()
                .stream()
                .anyMatch(admirer -> admirer.getAdmired().equals(user));
    }

    public String getRequestStatus() {
        return getIsActualUser()
                ? null
                : loggedUser.getRequestsSend()
                .stream()
                .filter(request -> request.getReceiver().equals(user))
                .findFirst()
                .map(Request::getStatus)
                .orElse(null);
    }

    public Integer getNotifications() {
        return 0;
//        return getIsActualUser()
//                ? loggedUser.getNotifications()
//                .stream()
//                .filter(Notification::getViewed)
//                .count()
//                : -1;
    }

}
