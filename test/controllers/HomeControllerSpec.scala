package controllers
import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.Play.materializer
import play.api.test._
import play.api.test.Helpers._

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 *
 * For more information, see https://www.playframework.com/documentation/latest/ScalaTestingWithScalaTest
 */
class HomeControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {

  "HomeController GET" should {

    "render the index page from a new instance of controller" in {
      val controller = new HomeController(stubControllerComponents())
      val home = controller.index().apply(FakeRequest(GET, "/"))

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include ("Welcome to Play")
    }

    "render the index page from the application" in {
      val controller = inject[HomeController]
      val home = controller.index().apply(FakeRequest(GET, "/"))

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include ("Welcome to Play")
    }

    "should return 400 Bad Request if the Content-Type header is not set" in {
      val todoListItemJson = "{\"description\":\"some test description\"}"

      val controller = new HomeController(stubControllerComponents())
      val addNewItem = controller.addNewItem().apply(
        FakeRequest(POST, "/hello").withTextBody(todoListItemJson)
      )
      status(addNewItem) mustBe 415
    }
    }
}