package com.amaury.chatdoc.data.entity

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "USERS")
class User{
    @Id
    @Column(name = "USERNAME")
    String name
    @Column(name = "EMAIL")
    String email
    @Column(name = "PASSWORD")
    String password
    @Column(name = "PHOTO_URL")
    String photoUrl

    @OneToMany(mappedBy = "mainUser", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    Set<Conversation> conversations


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "USER_ROLES",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    Set<Role> roles = new HashSet<>();
}
