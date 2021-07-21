package com.videogame.videogameinfo;

import com.videogame.constants.EndPoints;
import com.videogame.model.VideoGamePojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import static io.restassured.RestAssured.given;


public class VideoGameSteps {
    @Step("Creating videoGame with id: {0}, name: {1}, releaseDate: {2}, reviewScore: {3},category: {4},and rating: {5}"
    )
    public ValidatableResponse createVideoGame(int id, String name, String releaseDate, int reviewScore,
                                               String category, String rating) {


        VideoGamePojo videoGamePojo = new VideoGamePojo();

        videoGamePojo.setId(id);
        videoGamePojo.setName(name);
        videoGamePojo.setReleaseDate(releaseDate);
        videoGamePojo.setReviewScore(reviewScore);
        videoGamePojo.setCategory(category);
        videoGamePojo.setRating(rating);

        return SerenityRest.rest()
                .given().log().all()
                .header("Content-Type", "application/json")
                .body(videoGamePojo).accept("application/json")
                .when()
                .post(EndPoints.CREATE_NEW_VIDEO_GAME_RECORD)
                .then();

    }

    @Step("Get the all videoGame ")
    public ValidatableResponse getAllVideoGames() {
        return SerenityRest.rest()
                .given().log().all()
                .accept("application/json")
                .header("Content-Type", "application/json")
                .when()
                .get(EndPoints.GET_ALL_VIDEO_GAME_RECORD)
                .then();
    }

    @Step("Delete video game")
    public ValidatableResponse deleteVideoGame(int videogameId) {

        return SerenityRest.rest()
                .given()
                .pathParam("id",videogameId ).accept("application/json")
                .log().all()
                .when()
                .delete(EndPoints.DELETE_VIDEO_GAME_BY_ID)
                .then();

    }

    @Step("Get video game by id")

    public ValidatableResponse getVideoGameById(int videogameid) {
        return SerenityRest.rest()
                .given()
                .accept("application/json")
                .pathParam("id", videogameid).accept("application/json")
                .when()
                .get(EndPoints.GET_VIDEO_GAME_BY_ID)
                .then();


    }

    @Step("Updating Video Games with Id: {0}, name:{1}, releaseDate:{2}, reviewScore:{3}, category:{4},rating:{5}")
    public ValidatableResponse updateVideoGameById( int id ,String name, String releaseDate, int reviewScore, String category,
                                                    String rating) {

        VideoGamePojo videoGamePojo = new VideoGamePojo();

        videoGamePojo.setId(id);
        videoGamePojo.setName(name);
        videoGamePojo.setReleaseDate(releaseDate);
        videoGamePojo.setReviewScore(reviewScore);
        videoGamePojo.setCategory(category);
        videoGamePojo.setRating(rating);

        return SerenityRest.rest()
                .given()
                .pathParam("id", id).accept("application/json")
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .body(videoGamePojo).accept("application/json")
                .put(EndPoints.GET_UPDATE_VIDEO_GAME_BY_ID)
                .then();
    }

    }
