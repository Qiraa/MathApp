package com.example.mathapp.data.network

import com.example.mathapp.data.network.dto.AuthRequest
import com.example.mathapp.data.network.dto.AuthResponse
import com.example.mathapp.data.network.dto.CourseDto
import com.example.mathapp.data.network.dto.GroupDto
import com.example.mathapp.data.network.dto.LessonDto
import com.example.mathapp.data.network.dto.UserDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST("auth/login")
    suspend fun login(@Body request: AuthRequest): AuthResponse

    @GET("users/user/{username}")
    suspend fun getUserByUsername(@Path("username") username: String): UserDto

    @GET("teacher/{teacherId}/schedule")
    suspend fun getTeacherSchedule(@Path("teacherId") teacherId: String): List<LessonDto>

    @GET("teacher/{teacherId}/groups")
    suspend fun getTeacherGroups(@Path("teacherId") teacherId: String): List<GroupDto>

    @GET("child/{childId}/profile")
    suspend fun getChildProfile(@Path("childId") childId: String): UserDto

    @GET("parent/{parentId}/profile")
    suspend fun getParentProfile(@Path("parentId") parentId: String): UserDto

    @GET("course/all")
    suspend fun getAllCourses(): List<CourseDto>
}
