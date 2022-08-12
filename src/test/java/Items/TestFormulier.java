package Items;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestFormulier {

    WebDriver driver;
    String url = "http://localhost:8080/web2_redo_war_exploded/";

    @Before
    public void setup() throws Exception {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(url+"index.jsp");
    }

    @After
    public void clean() {
        driver.quit();
    }

    @Test
    public void test_formulier_add_when_filled_in_wrong() {

        String checkString;

        driver.findElement(By.id("FormulierLink")).click();
        assertEquals("Add_Game", driver.getTitle());

        for (int i = 1; i < 3; i++){

            checkString = "U vulde het formulier al " + Integer.toString(i) + " keer fout in.";

            WebElement naamInput = driver.findElement(By.id("gameName"));
            naamInput.clear();
            naamInput.sendKeys("");

            WebElement genreInput = driver.findElement(By.id("gameGenre"));
            genreInput.clear();
            genreInput.sendKeys("");

            WebElement ScoreInput = driver.findElement(By.id("gameScore"));
            ScoreInput.clear();
            ScoreInput.sendKeys("");

            driver.findElement(By.id("gameSubmit")).click();

            assertEquals("Overview", driver.getTitle());

            driver.findElement(By.id("FormulierLink")).click();

            assertEquals("Add_Game", driver.getTitle());
            ArrayList<WebElement> p = (ArrayList<WebElement>) driver.findElements(By.tagName("P"));
            assertTrue(containsWebElementWithText(p, checkString));

        }
    }

    @Test
    public void test_formulier_add_when_filled_in_corretly() {

        String name = "aJaJa";
        String genre = "testywesty";
        String score = "49";

        driver.findElement(By.id("FormulierLink")).click();

        WebElement naamInput = driver.findElement(By.id("gameName"));
        naamInput.clear();
        naamInput.sendKeys(name);

        WebElement genreInput = driver.findElement(By.id("gameGenre"));
        genreInput.clear();
        genreInput.sendKeys(genre);

        WebElement ScoreInput = driver.findElement(By.id("gameScore"));
        ScoreInput.clear();
        ScoreInput.sendKeys(score);

        driver.findElement(By.id("gameSubmit")).click();

        assertEquals("Overview", driver.getTitle());
        ArrayList<WebElement> tds = (ArrayList<WebElement>)  driver.findElements(By.tagName("td"));

        assertTrue(containsWebElementWithText(tds, name));
        assertTrue(containsWebElementWithText(tds, genre));
        assertTrue(containsWebElementWithText(tds, score));




    }

    @Test
    public void test_formulier_reset() {

        driver.findElement(By.id("FormulierLink")).click();

        WebElement naamInput = driver.findElement(By.id("gameName"));
        naamInput.clear();
        naamInput.sendKeys("");

        WebElement genreInput = driver.findElement(By.id("gameGenre"));
        genreInput.clear();
        genreInput.sendKeys("");

        WebElement ScoreInput = driver.findElement(By.id("gameScore"));
        ScoreInput.clear();
        ScoreInput.sendKeys("");

        driver.findElement(By.id("gameSubmit")).click();
        driver.findElement(By.id("FormulierLink")).click();
        ArrayList<WebElement> p = (ArrayList<WebElement>) driver.findElements(By.tagName("P"));
        assertTrue(containsWebElementWithText(p, "U vulde het formulier al 1 keer fout in."));

        driver.findElement(By.id("cookieReset")).click();

        assertEquals("Confirm_cookie_reset", driver.getTitle());

        driver.findElement(By.id("returnToFormulier")).click();

        assertEquals("Add_Game", driver.getTitle());

        p = (ArrayList<WebElement>) driver.findElements(By.tagName("P"));
        assertTrue(containsWebElementWithText(p, "U vulde het formulier al 0 keer fout in."));


    }

    private boolean containsWebElementWithText(ArrayList<WebElement> elements, String text) {
        for (WebElement element : elements) {
            if (element.getText().equals(text)) {
                return true;
            }
        }
        return false;
    }

}
