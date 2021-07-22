import helpers.Faker
import helpers.Screenshot
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import pages.LoginPage
import pages.PersonalInfoPage
import pages.TopMenu

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestOne extends TestCase{
    static TopMenu topMenu
    Faker faker

    @BeforeAll
    void before(){
        topMenu = new TopMenu()
        faker = new Faker()
    }
    @Test
    void "search Google for keyword"() {
        println('Search Google!')
        println driver == null
    }

    @Test
    void "create user and view dashboard"() {
        String email = faker.getEmail()
        topMenu.print_featured_products()
        LoginPage loginPage = topMenu.click_login()
        PersonalInfoPage infoPage = loginPage.signup(email)

        infoPage.enterName("Mr.", "first name", "last name")
        infoPage.setCredentials(email, "somepassword")
        infoPage.fill_address_name("first name", "last name", "some address")
        infoPage.fill_address_location("some city", "Alaska", "United States", "12345")
        infoPage.fill_contact("123456789", "some reference")
        infoPage.submit()

        infoPage.wait_for_dashboard()
        Screenshot.takeScreenshot('submit')
    }
    @Test
    void 'generate some fake data'() {
        printf("driver is null? %s%n", driver == null)
        Faker faker = new Faker()
        String day = faker.getRandomDate().day()
        String month = faker.getRandomDate().month()
        String year = faker.getRandomDate().year()

        printf("Random day: %s%n",day)
        printf("Random month: %s%n",month)
        printf("Random year: %s%n",year)

        printf("Random text: %s%n",faker.getRandomMessage())
    }
}
