package com.example.dexdogs.api.dto

import com.example.dexdogs.model.User

class UserDTOMapper {

    fun fromUserDTOToUserDomain(userDTO: UserDTO): User {
        return User(
            userDTO.id, userDTO.email, userDTO.authenticationToken
        )
    }


}