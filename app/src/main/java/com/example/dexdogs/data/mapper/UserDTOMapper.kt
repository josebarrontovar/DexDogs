package com.example.dexdogs.data.mapper

import com.example.dexdogs.data.model.UserDTO
import com.example.dexdogs.domain.model.User

class UserDTOMapper {

    fun fromUserDTOToUserDomain(userDTO: UserDTO): User {
        return User(
            userDTO.id, userDTO.email, userDTO.authenticationToken
        )
    }


}