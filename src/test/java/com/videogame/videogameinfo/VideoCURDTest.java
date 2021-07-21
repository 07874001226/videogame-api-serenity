package com.videogame.videogameinfo;


import com.videogame.testbase.TestBase;
import com.videogame.utils.TestUtils;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.equalTo;


@RunWith(SerenityRunner.class)
public class VideoCURDTest extends TestBase {

    static int id = 1 + TestUtils.getRandomValueInt();
    static String name = "mario";
    static String releaseDate = "2021-07-15T19:08:27.609Z";
    static int reviewScore = 10;
    static String category = "abc";
    static String rating = "9";
    @Steps
    VideoGameSteps videoGameSteps;

    @Title("This will get a videogame list")
    @Test
    public void test001() {

        videoGameSteps.getAllVideoGames().log().all().statusCode(200);

    }

    @Title("This will create a new videogame")
    @Test
    public void test002() {

        videoGameSteps.createVideoGame(id, name, releaseDate, reviewScore, category, rating).statusCode(200).extract().response().body().jsonPath();

    }


    @Title("This will get videogame by id")
    @Test
    public void test003() {
        videoGameSteps.getVideoGameById(id).statusCode(200).log().all();


    }

    @Title("This test will update the video game by existing id")
    @Test
    public void test004(){
        id = id ;
        name = name + "_Updated";
        releaseDate = releaseDate ;
        reviewScore =  reviewScore+1  ;
        category = category + "_Updated";
        rating = rating + "_Updated";


        videoGameSteps.updateVideoGameById(id,name,releaseDate,reviewScore,category,rating).statusCode(200).log().all();
        videoGameSteps.getVideoGameById(id).body("id",equalTo(id));

    }
        @Title("This will delete a new videogame")
    @Test
    public void test005() {
        videoGameSteps.deleteVideoGame(id).statusCode(200).log().all();

    }

}


