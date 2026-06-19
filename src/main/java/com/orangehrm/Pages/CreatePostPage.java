package com.orangehrm.Pages;

import com.orangehrm.Utils.Elements;
import com.orangehrm.Utils.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CreatePostPage {
    // scenario for create post
    // .user logged-in successfully
    // .navigate to create post page
    // .locate Text area field and add text to it
    // .click on Post button

    private final WebDriver driver ;
    private final By PostTextArea = By.cssSelector("textarea");
    private final By PostButton = By.xpath("//button[normalize-space()='Post']");
    private final By successToast = By.xpath("//div[contains(@class,'oxd-toast-content--success')]");
    private final By PostsLocator = By.cssSelector(".orangehrm-buzz-post");
    int postsBefore ;
    int postsAfter ;

    public CreatePostPage(WebDriver driver){
        this.driver = driver ;
    }

    public int getPostsCount(){

        return Elements.getElementsSize(driver,PostsLocator) ;
    }
    public CreatePostPage navigateToCreatePost(String Url){
        driver.get(Url);
        return this ;
    }
    public CreatePostPage enterPostData(String PostText){
        Elements.sendData(driver,this.PostTextArea,PostText);
        return this ;
    }

    public CreatePostPage submitPost(){
        Elements.submitData(driver,this.PostButton);
        postsBefore = getPostsCount();
        return this ;
    }


    //assert post creation successfully
    public void assertPostCreationSuccessfully(){
        // wait until pop-up be visible
        Waits.waitUntilVisible(driver,successToast);
        // check for assertion
        Assert.assertTrue(Elements.getData(driver,successToast).contains("Successfully Saved"));
    }

    public void assertCreatePostWithEmptyContent(){
        postsAfter = getPostsCount();
        System.out.println("Posts After :"+ postsAfter + ", Posts Before :" + postsBefore);
        Assert.assertEquals(postsBefore,postsAfter);
    }

}
