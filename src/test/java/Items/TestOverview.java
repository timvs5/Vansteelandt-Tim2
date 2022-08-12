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

import static org.junit.jupiter.api.Assertions.*;

public class TestOverview {

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

    //Het juist tonen van de items word al in "TestFormulier" getest

    @Test
    public void test_overview_remove_cancel() {

        driver.findElement(By.id("OverviewLink")).click();
        assertEquals("Overview", driver.getTitle());

        clearOverview();

        insertGame("Disco Elysium", "Detective, but Disco","96");

        ArrayList<WebElement> tds = (ArrayList<WebElement>)  driver.findElements(By.tagName("td"));
        assertTrue(containsWebElementWithText(tds, "Disco Elysium"));
        driver.findElement(By.cssSelector("input[class='removeButton']")).click();
        assertEquals("Confirm_delete", driver.getTitle());
        driver.findElement(By.id("cancel")).click();
        tds = (ArrayList<WebElement>)  driver.findElements(By.tagName("td"));
        assertEquals("Overview", driver.getTitle());
        assertTrue(containsWebElementWithText(tds, "Disco Elysium"));
    }

    @Test
    public void test_overview_remove_confirm() {
        driver.findElement(By.id("OverviewLink")).click();
        String name = "Garfield Kart";
        insertGame(name, "Lasagna racing", "77");
        clearOverview();
        ArrayList<WebElement> tds = (ArrayList<WebElement>)  driver.findElements(By.tagName("td"));
        assertFalse(containsWebElementWithText(tds, name));
    }

    @Test
    public void test_overview_search() {
        driver.findElement(By.id("OverviewLink")).click();
        insertGameList(8);
        String name = "Worms WMD";
        String score = "84";
        insertGame(name, "Kablooit", score);

        WebElement searchbar = driver.findElement(By.name("getGameScore"));
        searchbar.clear();
        searchbar.sendKeys(name);
        driver.findElement(By.id("search")).click();

        assertEquals("result", driver.getTitle());

        ArrayList<WebElement> p = (ArrayList<WebElement>)  driver.findElements(By.tagName("p"));
        assertTrue(containsWebElementWithText(p, score));

    }

    @Test
    public void test_overview_update() {
        driver.findElement(By.id("OverviewLink")).click();
        assertEquals("Overview", driver.getTitle());

        clearOverview();

        insertGame("Mordow", "Medieval shooter","14");
        driver.findElement(By.cssSelector("input[class='changeButton']")).click();

        assertEquals("Confirm_change", driver.getTitle());

        String name = "Mordhau";
        String genre = "Medieval warfare";
        String score = "84";

        WebElement naamInput = driver.findElement(By.id("gameName"));
        naamInput.clear();
        naamInput.sendKeys(name);

        WebElement genreInput = driver.findElement(By.id("gameGenre"));
        genreInput.clear();
        genreInput.sendKeys(genre);

        WebElement ScoreInput = driver.findElement(By.id("gameScore"));
        ScoreInput.clear();
        ScoreInput.sendKeys(score);

        driver.findElement(By.id("confirmChange")).click();
       assertEquals("Overview", driver.getTitle());

        ArrayList<WebElement> tds = (ArrayList<WebElement>)  driver.findElements(By.tagName("td"));
        assertTrue(containsWebElementWithText(tds, name));
        assertTrue(containsWebElementWithText(tds, genre));
        assertTrue(containsWebElementWithText(tds, score));
    }

    private void clearOverview() {
        driver.findElement(By.id("OverviewLink")).click();

        ArrayList<WebElement> trs = (ArrayList<WebElement>)  driver.findElements(By.tagName("tr"));
        int l = trs.size();
        for (int i = 0; i < l-1; i++) {
            driver.findElement(By.cssSelector("input[class='removeButton']")).click();
            driver.findElement(By.id("confirm")).click();
        }

    }

    private void insertGameList(int amount) {
        for (int i = 0; i < amount; i++)
            insertGame("Game " + Integer.toString(i), "RPG", Integer.toString(8+i));
    }

    private void insertGame(String name, String genre, String score) {
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
