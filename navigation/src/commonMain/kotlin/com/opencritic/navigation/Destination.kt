package com.opencritic.navigation

abstract class Destination(val path: String)

abstract class Routed(destination: Destination, argName: String, value: Any) : Route(destination.path.replace("{${argName}}", value.toString()))