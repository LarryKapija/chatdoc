package com.amaury.chatdoc.data.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "ROLES")
class Role {
    final static long ROLE_USER = 1L;

    @Id
    Long id
    @Column
    String name
}
