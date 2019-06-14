package com.chen.mybatis.mapper

import com.chen.mybatis.entity.OauthClientDetails
import org.apache.ibatis.annotations.*

@Mapper
interface OauthClientDetailsMapper {
    @Insert("insert into oauth_client_details(`client_id`,`resource_ids`,`client_secret`,`scope`," +
            "`authorized_grant_types`,`web_server_redirect_uri`,`authorities`,`access_token_validity`," +
            "`refresh_token_validity`,`additional_information``autoapprove`) values(#{clientId},#{resourceIds},#{clientSecret}," +
            "#{scope},#{authorizedGrantTypes},#{webServerRedirectUri},#{authorities},#{accessTokenValidity},#{refreshTokenValidity},#{additionalInformation}," +
            "#{autoapprove}) ")
    fun addOauthClientDetails(oauthClientDetails: OauthClientDetails): Int

    @Select("select * from  oauth_client_details")
//    @Results(value = [
//        Result(property = "client_id", column = "clientId")
//    ])
    @Results(value = [
        Result(property = "client_id", column = "clientId"),
        Result(property = "resource_ids", column = "resourceIds"),
        Result(property = "client_secret", column = "clientSecret"),
        Result(property = "scope", column = "scope"),
        Result(property = "authorized_grant_types", column = "authorizedGrantTypes"),
        Result(property = "web_server_redirect_uri", column = "webServerRedirectUri"),
        Result(property = "authorities", column = "authorities"),
        Result(property = "access_token_validity", column = "accessTokenValidity"),
        Result(property = "refresh_token_validity", column = "refreshTokenValidity"),
        Result(property = "additional_information", column = "additionalInformation"),
        Result(property = "autoapprove", column = "autoapprove")
    ])
    fun getOauthClientDetails(): List<OauthClientDetails>

    @Delete("delete from oauth_client_details where id = #{id}")
    fun deleteOauthClientDetails(id: Int): Int

    @Update("update oauth_client_details set client_id =#{clientId}, resource_ids = #{resourceIds},scope=#{scope}  where id =#{id}")
    fun updateUser(oauthClientDetails: OauthClientDetails): Int

}