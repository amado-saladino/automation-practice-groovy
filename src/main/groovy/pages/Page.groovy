package pages

import helpers.Browser
import helpers.ConfigReader
import helpers.FileLoader
import helpers.WaitUtils
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.Select

abstract class Page {
    protected WebDriver driver = Browser.selectBrowser()
    protected JavascriptExecutor jsRunner
    WaitUtils wait
    def conf = ConfigReader.loadConfig()
    private int TIME_OUT = conf.wait.default_wait

    Page() {
        PageFactory.initElements(driver, this)
        jsRunner = (JavascriptExecutor) driver
        wait = new WaitUtils()
    }

    def runScript(String script, Object... args) {
        jsRunner.executeScript(script, args)
    }

    protected void clickElement(WebElement element) {
        WaitUtils.waitVisibilityOf(element, TIME_OUT)
        element.click()
    }

    protected void enterText(WebElement element, String text) {
        WaitUtils.waitVisibilityOf(element, TIME_OUT)
        element.clear()
        element.sendKeys(text)
    }
    void selectDropdownItem(WebElement comboElement, String option) {
        WaitUtils.waitVisibilityOf(comboElement, TIME_OUT)
        new Select(comboElement).selectByVisibleText(option)
    }
    void selectDropdownItem(WebElement comboElement, int index) {
        WaitUtils.waitVisibilityOf(comboElement, TIME_OUT)
        new Select(comboElement).selectByIndex(index)
    }
    WebElement getSelectedDropdownItem(WebElement comboBox) {
        WaitUtils.waitVisibilityOf(comboBox, TIME_OUT)
        new Select(comboBox).getFirstSelectedOption()
    }
    void scrollToBottom() {
        runScript("scrollTo(0, document.body.scrollHeight)")
    }
    void injectScriptFile(String file) {
        String script = new FileLoader("scripts/" + file).toString()
        runScript(script)
    }
}