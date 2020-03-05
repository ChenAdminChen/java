package com.chen.demo.geologydemo

import com.esri.core.geometry.*
import org.junit.jupiter.api.Test


//@SpringBootTest
class GeologyDemoApplicationTests {

    @Test
    fun contextLoads() {
    }

    /**
     * geology point
     */
    @Test
    fun createPoint() {
//		val pt = Point(-106.4453583, 39.11775)
        val jsonString = "{\"x\":-106.4453583,\"y\":39.11775,\"spatialReference\":{\"wkid\":4326}}"

        val mapGeom = OperatorImportFromJson.local().execute(Geometry.Type.Point, jsonString)

        println(mapGeom)
    }

    /**
     * 2D
     * wkid: 4326
     */
    @Test
    fun createPolygonFromJson() {
        val jsonString = ("{\"paths\":[[[6.8,8.8],[7,9],[7.2,8.9],[7.4,9]],"
                + "[[7.4,8.9],[7.25,8.6],[7.15,8.8]],[[6.9, 9.1],[7, 8.8]]],"
                + "\"spatialReference\":{\"wkid\":4326}}")
        val mapGeom = OperatorImportFromJson.local().execute(Geometry.Type.Polyline, jsonString)

        println(mapGeom)
    }

    /**
     * 3D polyline
     * wkid: 54004
     */
    @Test
    fun createPolygonFromJson3D() {
        val jsonString = ("{\n" +
                "  \"hasZ\" : true,\n" +
                "  \"hasM\" : true,\n" +
                "  \"paths\": [\n" +
                "             [[32462,-57839,20,1],[43892,-49160,25,2],[35637,-65035,null,3],[46009,-60379,22,4]],\n" +
                "             [[52570,-77736,30],[39870,-83027,32],[46644,-99114,35]]\n" +
                "           ],\n" +
                "  \"spatialReference\" : {\"wkid\" : 54004}\n" +
                "}")
        val mapGeom = OperatorImportFromJson.local().execute(Geometry.Type.Polyline, jsonString)

        println(mapGeom)

    }

    /**
     * 2D
     * wkid: 4326
     */
    @Test
    fun overlapPolygon() {
        val jsonString1 = ("{\"rings\":[[[0,0],[5,0],[5,5],[0,5]]],"
                + "\"spatialReference\":{\"wkid\":54004}}")
        val mapGeom1 = OperatorImportFromJson.local().execute(Geometry.Type.Polygon, jsonString1)

        val geometryA = mapGeom1!!.geometry

        val jsonString2 = ("{\"rings\":[[[5,1],[7,2.5],[5,4.5],[3,2.5]]],"
                + "\"spatialReference\":{\"wkid\":54004}}")
        val mapGeom2 = OperatorImportFromJson.local().execute(Geometry.Type.Polygon, jsonString2)

        val geometryB = mapGeom2!!.geometry

        val sr = SpatialReference.create(54004)
//
//        val overlaps = OperatorOverlaps.local().execute(geometryA, geometryB, sr, null)
//        println(overlaps)

        val difference = OperatorDifference.local().execute(geometryA, geometryB, sr, null)
        println(difference)

//        val overlaps1 = OperatorOverlaps.local().execute(difference, geometryB, sr, null)


        val differenceOffset = OperatorOffset.local().execute(geometryA, sr, 10.0, OperatorOffset.JoinType.Round, 0.0, 0.0, null)

        println(differenceOffset)

        val buffer = OperatorBuffer.local().execute(geometryA,sr,3.0,null)
        println(buffer)

    }
}


// [0,0],[5,0],[5,5],[0,5]