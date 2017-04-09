import play.api.libs.json.{JsValue, Json}

import scala.io.Source

object HubStar {

  type Node = Int
  type Cost = Int
  type Graph = Map[Node, List[(Node, Cost)]]
  type Edges = List[(Node, Node, Cost)]

  def findStar(nodeCount:Int, graph:Graph):Option[(Cost, Edges, Node)] = {
    def min(a:(Cost, Edges), b:(Cost, Edges)) = if (a._1 < b._1) a else b

    def recurse(current:Node, root:Node, cost:Cost, edges:Edges, visited:Set[Node]): Option[(Cost, Edges)] = {
      if (visited contains current) None
      else {
        def rec(node: Node):List[(Cost, Edges)] = {
          val nextVisited = visited + current
          if (nextVisited.size == nodeCount) List((cost, edges))
          else
            for {
              (nextN, nextC) <- graph(node)
              r <- recurse(nextN, root, cost + nextC, (node, nextN, nextC) :: edges, nextVisited)
            } yield r
        }

        (rec(current) ++ rec(root)).reduceOption((a, b) => if (a._1 < b._1) a else b)
      }
    }

    val rootResults = for {
      root <- graph.keySet
      (cost, edges) <- recurse(root, root, 0, Nil, Set())
    } yield (cost, edges, root)

    rootResults.reduceOption((a, b) => if (a._1 < b._1) a else b)
  }

  def buildGraph(edges:Edges):Graph = {
    def removeFirstItem(t:(Int, Int, Int)) = (t._2, t._3)

    edges
      .groupBy(_._1)
      .mapValues(list => list.map(removeFirstItem))
      .withDefaultValue(Nil)
  }

  // input / output

  def toJson(cost:Cost, edges:Edges, root:Node): String = {
    Json.obj(
      "feasible" -> "true",
      "totalCost" -> cost,
      "depotId" -> root,
      "recommendedOffers" -> edges.map {
        case (from, to, cost) => Json.obj(
          "from" -> from,
          "to" -> to,
          "cost" -> cost
        )}
    ).toString
  }

  def notFeasible: String = Json.obj("feasible" -> "false").toString

  def fromJson(str:String): (Int, Edges) = {
    val parsed = Json.parse(str)
    val nodeCount = (parsed \ "citiesCount").get.as[Int]
    val offers = for (offer <- (parsed \ "costOffers").as[List[JsValue]])
      yield (
        (offer \ "from").as[Int],
        (offer \ "to").as[Int],
        (offer \ "price").as[Int]
      )
    (nodeCount, offers)
  }

  def main(args: Array[String]): Unit = {
    val (nodeCount, offers) = fromJson(Source.stdin.mkString)

    val graph = buildGraph(offers)

    val result = findStar(nodeCount, graph)
        .map { case (cost, edges, root) => toJson(cost, edges, root) }
        .getOrElse(notFeasible)

    println(result)
  }

}
