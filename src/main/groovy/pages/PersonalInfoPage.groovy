package pages

import helpers.ConfigReader
import helpers.WaitUtils
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.By

class PersonalInfoPage extends Page {
    @FindBy(id= "customer_firstname")
    private WebElement first_name

    @FindBy(id= "customer_lastname")
    private WebElement last_name

    @FindBy(id ="email")
    private WebElement email

    @FindBy(id= "passwd")
    private WebElement password

    @FindBy(id = "days")
    private WebElement combo_day

    @FindBy(id = "months")
    private WebElement combo_months

    @FindBy(id = "years")
    private WebElement combo_years

    @FindBy(id= "firstname")
    private WebElement address_first_name

    @FindBy(id= "lastname")
    private WebElement address_last_name

    @FindBy(id= "address1")
    private WebElement address_address1

    @FindBy(id= "city")
    private WebElement city

    @FindBy(id= "id_state")
    private WebElement state

    @FindBy(id= "postcode")
    private WebElement postal_code

    @FindBy(id= "id_country")
    private WebElement country

    @FindBy(id= "phone_mobile")
    private WebElement mobile_phone

    @FindBy(id= "alias")
    private WebElement alias

    @FindBy(id ="submitAccount")
    private WebElement submit_button

    @FindBy(css = "a.home[title='Return to Home']")
    private WebElement return_home

    void enterName(String gender, String fname, String lname) {
        enterText(first_name, fname)
        select_radio_button_by_label(gender)
        enterText(last_name, lname)
        String placeholder = runScript("return \$('#search_query_top').attr('placeholder')")
        printf("Placeholder of top search box: %s%n", placeholder)
    }

    void setCredentials(String user_email, String user_password) {
        enterText(email, user_email)
        enterText(password, user_password)
    }

    void set_date_of_birth(String day, String month, String year) {
        selectDropdownItem(combo_day, day)
        selectDropdownItem(combo_months, month)
        selectDropdownItem(combo_years, year)
    }

    void fill_address_name(String fname, String lname, String address) {
        enterText(address_first_name, fname)
        enterText(address_last_name, lname)
        enterText(address_address1, address)
    }

    void fill_address_location(String user_city, String user_state, String user_country, String user_postal_code) {
        enterText(city, user_city)
        selectDropdownItem(state, user_state)
        enterText(postal_code, user_postal_code)
        selectDropdownItem(country, user_country)
    }

    void fill_contact(String phone, String reference) {
        enterText(mobile_phone, phone)
        enterText(alias, reference)
    }

    void submit() {
        clickElement(submit_button)
    }

    void wait_for_dashboard() {
        def conf = ConfigReader.loadConfig()
        WaitUtils.waitVisibilityOf(return_home, conf.wait.default_wait)
    }

    private void select_radio_button_by_label(String label) {
        driver.findElement(By.xpath("//label[contains(.,'${label}')]")).click()
    }
}
