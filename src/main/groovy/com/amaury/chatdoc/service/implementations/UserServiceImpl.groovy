package com.amaury.chatdoc.service

import com.amaury.chatdoc.data.ConversationRepository
import com.amaury.chatdoc.data.UserRepository
import com.amaury.chatdoc.data.RoleRepository
import com.amaury.chatdoc.data.entity.Conversation
import com.amaury.chatdoc.data.entity.Role
import com.amaury.chatdoc.data.entity.User
import com.amaury.chatdoc.data.entity.key.ConversationId
import com.amaury.chatdoc.exception.InvalidRequest
import com.amaury.chatdoc.exception.NotFound
import com.amaury.chatdoc.service.security.UserDetailsImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service


@Service
class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository
    @Autowired
    RoleRepository roleRepository
    @Autowired
    ConversationRepository conversationRepository

    @Override
    Optional<User> getUser(String userName) {
        userRepository.findById(userName)
    }

    @Override
        List<User> getFilteredUsers(String filter, Integer startIndex, Integer endIndex) {
        if(Objects.isNull(filter))
            return new ArrayList<>();


        List<User> list = userRepository.findAll().findAll {it.getName().toUpperCase().contains(filter.toUpperCase()) }

        if(list.isEmpty())
            return list

        startIndex = Objects.isNull(startIndex) ? 0 : startIndex
        endIndex = Objects.isNull(endIndex) ? list.size() : endIndex
        endIndex = endIndex >= list.size() ? list.size() : endIndex

        list.subList(startIndex, endIndex)
    }

    @Override
    void createUser(User user) {
        if(userRepository.findById(user.getName()).isPresent())
            throw new InvalidRequest("Username ${user.getName()} already taken!")
        if(userRepository.findByEmail(user.getEmail()).isPresent())
            throw new InvalidRequest("Email ${user.getEmail()} already being used by another user!")


        Role role = roleRepository.findById(Role.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Role with Id ${Role.ROLE_USER} not found"));

        user.setRoles([role].toSet())
        userRepository.save(user)
    }

    @Override
    void updateUser(User user) {
        userRepository.save(user)
    }

    @Override
    void startNewConversation(String mainUserName, Object withUserName) {
        String key = (mainUserName + "-" + withUserName).digest("SHA-1").decodeHex().encodeBase64();

        if(userRepository.findById(withUserName).isEmpty())
            throw new NotFound("User ${withUserName} not found")

        if(conversationRepository.findById(new ConversationId(key: key, mainUser: mainUserName)).isPresent())
            throw new InvalidRequest("There's already a conversation going between those users(${mainUserName} and ${withUserName})!")

        conversationRepository.save(new Conversation(key: key, mainUser: mainUserName, withUser: withUserName))
        conversationRepository.save(new Conversation(key: key, mainUser: withUserName, withUser: mainUserName))
    }

    @Override
    String getCurrentUser() {
        ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername()
    }
}
