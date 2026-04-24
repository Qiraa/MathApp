package com.example.mathapp.data.profile

/**
 * Простая мок-\"авторизация\".
 *
 * На основе введённых данных в AuthScreen мы выставляем currentUserType,
 * а репозиторий профиля возвращает данные либо родителя, либо ребёнка.
 */
object AuthMock {

    enum class UserType {
        PARENT,
        CHILD,
    }

    var currentUserType: UserType = UserType.PARENT

    // Мок-данные доступа
    const val PARENT_LOGIN: String = "+79081826545"
    const val PARENT_PASSWORD: String = "parent123"

    const val CHILD_LOGIN: String = "+79080000000"
    const val CHILD_PASSWORD: String = "child123"

    fun authenticate(login: String, password: String): UserType? {
        val trimmedLogin = login.trim()
        return when {
            trimmedLogin == PARENT_LOGIN && password == PARENT_PASSWORD -> UserType.PARENT
            trimmedLogin == CHILD_LOGIN && password == CHILD_PASSWORD -> UserType.CHILD
            else -> null
        }
    }
}

