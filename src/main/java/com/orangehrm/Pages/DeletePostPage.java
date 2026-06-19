
package com.orangehrm.Pages;

import com.orangehrm.Utils.Elements;
import com.orangehrm.Utils.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;

public class DeletePostPage {

        //delete post scenario
        //.after creating post grep title , datetime that post creation
        //.locate post by title and date
        //.then locate three dots button and click on it
        //.after that click on li Delete Post
        //.a popup message shall appear to confirm deletion
        //finally click on yes, delete button

        private final WebDriver driver ;
        private final String PostTitle ;
        private final By ThreeDotsButton = By.xpath(".//button[contains(@class,'oxd-icon-button')]");
        private final By DeleteIcon =     By.xpath(".//li[contains(@class,'orangehrm-buzz-post-header-config-item')][.//p[text()='Delete Post']]");
        private final By DeleteButton = By.cssSelector("#app > div.oxd-overlay.oxd-overlay--flex.oxd-overlay--flex-centered > div > div > div > div.orangehrm-modal-footer > button.oxd-button.oxd-button--medium.oxd-button--label-danger.orangehrm-button-margin");



        public DeletePostPage(WebDriver driver,String PostTitle){
            this.driver = driver;
            this.PostTitle = PostTitle;
        }

        private By getTargetPostContainer() {
            System.out.println("Post title: " + PostTitle);
            System.out.println("Posts found: " + driver.findElements(getTargetPostContainer()).size());
            return By.xpath(
                    "//div[contains(@class,'orangehrm-buzz-post')]" +
                            "[.//p[contains(@class,'orangehrm-buzz-post-body-text') and text()='" + PostTitle + "']]"

            );
        }
        public DeletePostPage clickOnThreeDotsButton(){
            //

            WebElement container = driver.findElement(getTargetPostContainer());
            Waits.waitUntilVisible(driver, container);
            WebElement ThreeDotsButtonElement = container.findElement(ThreeDotsButton);

            ThreeDotsButtonElement.click();
            return  this ;
        }

        public DeletePostPage clickOnDeleteIcon(){

            WebElement container = driver.findElement(getTargetPostContainer());
            WebElement deleteIcon = container.findElement(DeleteIcon);

            deleteIcon.click();

            return this;
        }

        public DeletePostPage clickOnDeleteButton(){
            Elements.clickButton(driver,DeleteButton);
            return this ;
        }

        public void assertPostDeletionSuccessfully(){

        }

    }

