package pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

import com.google.common.base.Function

class TopMenu extends Page {
    @FindBy(partialLinkText = "Sign in")
    private WebElement login

    LoginPage click_login() {
        clickElement(login)
        new LoginPage()
    }

    void print_featured_products() {
        injectScriptFile('jquery.js')
        def products = runScript("return document.querySelectorAll('ul#homefeatured li h5')")
        println('Featured products: ')
        products.each { println it.getText() }
    }
}
