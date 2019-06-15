package com.util;

import com.models.Comment;
import com.models.Showing;
import com.models.User;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    public Comment myComment() {
        return new Comment();
    }

    public Showing myShowing() {
        return new Showing();
    }

    public User myUser() {
        return new User();
    }


}
