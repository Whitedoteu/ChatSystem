package objects

import java.sql.Timestamp

import org.joda.time.DateTime
import play.api.libs.json.Json._
import play.api.libs.json._

/**
  * Created by theer on 02.05.2017.
  * This Class is used to carry all Data for the User
  */
case class UserRecord(
                       userid: Option[Int] = None,
                       username: String = "",
                       password: String = "",
                       firstname: String = "",
                       lastname: String = "",
                       email: String = "",
                       nickname: Option[String] = Option(""),
                       lastlogin: Option[Timestamp] = Option(new Timestamp(0)),
                       picture: Option[String] = Option("")
                     )

object UserRecord {

  def timestampToDateTime(t: Timestamp): DateTime = new DateTime(t.getTime)

  def dateTimeToTimestamp(dt: DateTime): Timestamp = new Timestamp(dt.getMillis)

  implicit val timestampFormat = new Format[Timestamp] {

    def writes(t: Timestamp): JsValue = toJson(timestampToDateTime(t))

    def reads(json: JsValue): JsResult[Timestamp] = fromJson[DateTime](json).map(dateTimeToTimestamp)

  }

  implicit val format = Json.format[UserRecord]

}
