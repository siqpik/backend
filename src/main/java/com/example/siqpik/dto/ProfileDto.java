package com.example.siqpik.dto;
import com.example.siqpik.domain.AdmireRequest;
import com.example.siqpik.domain.User;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class ProfileDto {

    private String name;
    private List<PhotoDto> pics;
    private Integer admirers;
    private Integer admiring;
    private String profilePicUrl;
    private Boolean isAdmiring;
    private User loggedUser;    //User that is log in
    private User user;      //The user of whom we want to see the profile

    public ProfileDto(User loggedUser, User user) {
        this.loggedUser = loggedUser;
        this.user = user;
        isAdmiring = getIsAdmiring();
        name = user.getUserName();
        pics = user.getPhotos().stream().map(PhotoDto::new).collect(toList());
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

    public Boolean isActualUser() {
        return loggedUser.getId().equals(user.getId());
    }

    public Boolean getIsAdmiring() {
        return isActualUser()
                || loggedUser.getAdmirings()
                .stream()
                .anyMatch(admirer -> admirer.getAdmired().equals(user));
    }

    public String getRequestStatus() {
        return isActualUser()
                ? null
                : loggedUser.getRequestsSend()
                .stream()
                .filter(request -> request.getReceiver().equals(user))
                .findFirst()
                .map(AdmireRequest::getStatus)
                .orElse(null);
    }

    public Integer getNotifications() {
        return isActualUser()
                ? (int)loggedUser.getNotifications()
                .stream()
                .filter(notification -> !notification.getViewed())
                .count()
                : null;
    }

}
