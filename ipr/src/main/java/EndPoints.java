



public class EndPoints {

    //-- Редактирование профиля--
    public static final String getProfileInfo = "/account.getProfileInfo";
    public static final String saveProfileInfo = "/account.saveProfileInfo";
    public static final String saveProfilePhoto = "/photos.getProfileUploadServer";

   //-- Работа с лентой
    public static final String newsFeedReccom = "/newsfeed.getRecommended";
    public static final String addLike = "/likes.add";
    public static final String deleteLike = "/likes.delete";
    public static final String isLiked = "/likes.isLiked";
    public static final String accountBan = "/account.ban";
    public static final String accountUnban = "/account.unban";
    public static final String accountGetBanned = "/account.getBanned";
    public static final String faveAddPost = "/fave.addPost";
    public static final String faveGet = "/fave.get";
    public static final String faveRemovePost = "/fave.removePost";
}
