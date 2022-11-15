package com.amaury.chatdoc.service

import com.amaury.chatdoc.data.entity.User

interface UserService {
    Optional<User> getUser(String userName)
    List<User> getFilteredUsers(String filter, Integer startIndex, Integer endIndex)
    void createUser(User user)
    void updateUser(User user)
    void startNewConversation(String mainUserName, withUserName)
    String getCurrentUser()
}